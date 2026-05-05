/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.sales.service;

import cl.duoc.sales.dto.response.SaleResponse;
import cl.duoc.sales.mapper.SaleMapper;
import cl.duoc.sales.model.Sale;
import cl.duoc.sales.model.SaleDetail;
import cl.duoc.sales.repository.SaleDetailRepository;
import cl.duoc.sales.repository.SaleRepository;
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
        Sale sale = saleRepo.findById(id).orElse(null);
        if (sale == null) {
            return null;
        }
        List<SaleDetail> details = detailRepo.findBySaleId(sale.getId());

        return mapper.toResponse(sale, details);
    }
}
