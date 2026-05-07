/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.sales.service;

import cl.duoc.sales.dto.request.SaleCreationRequest;
import cl.duoc.sales.dto.response.SaleResponse;
import cl.duoc.sales.exception.ResourceNotFoundException;
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
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found with id: " + id));
    }

    public List<SaleResponse> findAll() {
        return saleRepo.findAll().stream()
                .map(sale -> {
                    List<SaleDetail> details = detailRepo.findBySaleId(sale.getId());
                    return mapper.toResponse(sale, details);
                })
                .toList();
    }

    public SaleResponse saveSale(SaleCreationRequest req) {
        Sale sale = saleRepo.save(mapper.saleFromRequest(req));

        List<SaleDetail> details = req.getDetails().stream()
                .map(detailReq -> mapper.detailFromRequest(detailReq, sale))
                .toList();
        detailRepo.saveAll(details);

        return mapper.toResponse(sale, details);
    }

    @Transactional
    public void deleteSale(Long id) {
        saleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found with id: " + id))
                .setDeletedAt(LocalDateTime.now());
    }
}
