/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.sales.service;

import cl.duoc.sales.dto.request.SaleCreationRequest;
import cl.duoc.sales.dto.response.SaleResponse;
import cl.duoc.sales.exception.SaleNotFoundException;
import cl.duoc.sales.mapper.SaleMapper;
import cl.duoc.sales.model.Sale;
import cl.duoc.sales.model.SaleDetail;
import cl.duoc.sales.repository.SaleDetailRepository;
import cl.duoc.sales.repository.SaleRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepo;
    private final SaleDetailRepository detailRepo;

    private final SaleMapper mapper;

    public SaleResponse findById(Long id) {
        return saleRepo.findById(id)
                .map(sale -> {
                    List<SaleDetail> details = detailRepo.findBySaleId(sale.getId());
                    return mapper.toResponse(sale, details);
                })
                .orElseThrow(() -> new SaleNotFoundException(id));
    }

    public List<SaleResponse> findAll() {
        return saleRepo.findAllActive().stream()
                .map(sale -> {
                    List<SaleDetail> details = detailRepo.findBySaleId(sale.getId());
                    return mapper.toResponse(sale, details);
                })
                .toList();
    }

    public SaleResponse saveSale(SaleCreationRequest req) {
        Sale sale = saleRepo.save(mapper.saleFromRequest(req));
        List<SaleDetail> details = mapper.extractDetails(req, sale);
        detailRepo.saveAll(details);

        return mapper.toResponse(sale, details);
    }

    @Transactional
    public SaleResponse replaceSale(Long id, SaleCreationRequest req) {
        Sale updatedSale = saleRepo.findById(id).orElseThrow(() -> new SaleNotFoundException(id));
        updatedSale.setCustomerId(req.getCustomerId());
        updatedSale.setAmount(req.getAmount());
        updatedSale.setUpdatedAt(LocalDateTime.now());

        detailRepo.deleteAll(detailRepo.findBySaleId(id));
        List<SaleDetail> newDetails = mapper.extractDetails(req, updatedSale);
        detailRepo.saveAll(newDetails);

        return mapper.toResponse(updatedSale, newDetails);
    }

    @Transactional
    public void deleteSale(Long id) {
        saleRepo.findById(id).orElseThrow(() -> new SaleNotFoundException(id)).setDeletedAt(LocalDateTime.now());
    }
}
