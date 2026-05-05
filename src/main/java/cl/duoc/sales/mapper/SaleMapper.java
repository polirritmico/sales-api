/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.sales.mapper;

import cl.duoc.sales.dto.response.SaleDetailResponse;
import cl.duoc.sales.dto.response.SaleResponse;
import cl.duoc.sales.model.Sale;
import cl.duoc.sales.model.SaleDetail;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaleMapper {
    public SaleDetailResponse toDetailResponse(SaleDetail detail) {
        return SaleDetailResponse.builder()
                .id(detail.getId())
                .description(detail.getDescription())
                .sku(detail.getSku())
                .quantity(detail.getQuantity())
                .unitPrice(detail.getUnitPrice())
                .build();
    }

    public SaleResponse toResponse(Sale sale, List<SaleDetail> details) {
        return SaleResponse.builder()
                .id(sale.getId())
                .customerId(sale.getCustomerId())
                .amount(sale.getAmount())
                .status(sale.getStatus().getName())
                .createdAt(sale.getCreatedAt())
                .updatedAt(sale.getUpdatedAt())
                .deletedAt(sale.getDeletedAt())
                .details(details.stream().map(this::toDetailResponse).toList())
                .build();
    }
}
