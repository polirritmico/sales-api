/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.sales.mapper;

import cl.duoc.sales.dto.request.SaleCreationRequest;
import cl.duoc.sales.dto.request.SaleDetailRequest;
import cl.duoc.sales.dto.response.SaleDetailResponse;
import cl.duoc.sales.dto.response.SaleResponse;
import cl.duoc.sales.model.Sale;
import cl.duoc.sales.model.SaleDetail;
import cl.duoc.sales.model.SaleStatus;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaleMapper {
    // TODO: Use a better approach to get the default
    private SaleStatus defaultState = new SaleStatus(1, "PENDING");

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

    public Sale saleFromRequest(SaleCreationRequest req) {
        return Sale.builder()
                .customerId(req.getCustomerId())
                .amount(req.getAmount())
                .createdAt(LocalDateTime.now())
                .status(defaultState)
                .build();
    }

    public SaleDetail detailFromRequest(SaleDetailRequest req, Sale sale) {
        return SaleDetail.builder()
                .description(req.getDescription())
                .sku(req.getSku())
                .quantity(req.getQuantity())
                .unitPrice(req.getUnitPrice())
                .sale(sale)
                .build();
    }
}
