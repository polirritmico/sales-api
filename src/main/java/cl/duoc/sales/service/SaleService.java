/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.sales.service;

import cl.duoc.sales.dto.request.SaleCreationRequest;
import cl.duoc.sales.dto.request.SaleUpdateRequest;
import cl.duoc.sales.dto.response.SaleResponse;
import cl.duoc.sales.dto.response.SaleStatusResponse;
import cl.duoc.sales.exception.ResourceNotFoundException;
import cl.duoc.sales.exception.SaleNotFoundException;
import cl.duoc.sales.exception.SaleStatusNotFoundException;
import cl.duoc.sales.mapper.DtoModelMapper;
import cl.duoc.sales.model.Sale;
import cl.duoc.sales.model.SaleDetail;
import cl.duoc.sales.model.SaleStatus;
import cl.duoc.sales.repository.SaleDetailRepository;
import cl.duoc.sales.repository.SaleRepository;
import cl.duoc.sales.repository.SaleStatusRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaleService {
    private final SaleRepository saleRepo;
    private final SaleStatusRepository statusRepo;
    private final SaleDetailRepository detailRepo;

    private final DtoModelMapper mapper;

    @Cacheable("pending-status")
    private SaleStatus getPendingStatus() {
        return statusRepo
                .findByName("PENDING")
                .orElseThrow(() -> new ResourceNotFoundException("Missing PENDING default status."));
    }

    public SaleResponse findById(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("Starting findById with id: " + id + " by user: " + auth.getName());
        return saleRepo.findById(id)
                .map(sale -> {
                    List<SaleDetail> details = detailRepo.findBySaleId(sale.getId());
                    return mapper.toSaleResponse(sale, details);
                })
                .orElseThrow(() -> new SaleNotFoundException(id));
    }

    public List<SaleResponse> findAll() {
        return saleRepo.findAllActive().stream()
                .map(sale -> {
                    List<SaleDetail> details = detailRepo.findBySaleId(sale.getId());
                    return mapper.toSaleResponse(sale, details);
                })
                .toList();
    }

    public SaleResponse saveSale(SaleCreationRequest req) {
        Sale newSale = saleRepo.save(mapper.saleFromCreationRequest(req, getPendingStatus()));
        List<SaleDetail> details = mapper.detailsFromCreationRequest(req, newSale);
        detailRepo.saveAll(details);

        return mapper.toSaleResponse(newSale, details);
    }

    @Transactional
    public SaleResponse replaceSale(Long id, SaleUpdateRequest req) {
        SaleStatus updatedStatus = statusRepo
                .findByName(req.getStatus())
                .orElseThrow(() -> new SaleStatusNotFoundException(req.getStatus()));
        Sale updatedSale = saleRepo.findById(id).orElseThrow(() -> new SaleNotFoundException(id));
        updatedSale.setCustomerId(req.getCustomerId());
        updatedSale.setAmount(req.getAmount());
        updatedSale.setStatus(updatedStatus);
        updatedSale.setUpdatedAt(LocalDateTime.now());

        detailRepo.deleteBySaleId(id);
        List<SaleDetail> newDetails = mapper.extractDetails(req, updatedSale);
        detailRepo.saveAll(newDetails);

        return mapper.toSaleResponse(updatedSale, newDetails);
    }

    @Transactional
    public void deleteSale(Long id) {
        saleRepo.findById(id).orElseThrow(() -> new SaleNotFoundException(id)).setDeletedAt(LocalDateTime.now());
    }

    public List<SaleStatusResponse> findAllSaleStatus() {
        return statusRepo.findAll().stream().map(mapper::toSaleStatusResponse).toList();
    }
}
