/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.sales.service;

import cl.duoc.sales.dto.response.SaleResponse;
import cl.duoc.sales.mapper.SaleMapper;
import cl.duoc.sales.model.SaleDetail;
import cl.duoc.sales.repository.SaleDetailRepository;
import cl.duoc.sales.repository.SaleRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepo;
    private final SaleDetailRepository detailRepo;

    private final SaleMapper mapper;

    public Optional<SaleResponse> findById(Long id) {
        return saleRepo.findById(id).map(sale -> {
            List<SaleDetail> details = detailRepo.findBySaleId(sale.getId());
            return mapper.toResponse(sale, details);
        });
    }

    public List<SaleResponse> findAll() {
        return saleRepo.findAll().stream()
                .map(sale -> {
                    List<SaleDetail> details = detailRepo.findBySaleId(sale.getId());
                    return mapper.toResponse(sale, details);
                })
                .toList();
    }
}
