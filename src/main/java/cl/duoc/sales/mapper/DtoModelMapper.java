/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.sales.mapper;

import cl.duoc.sales.dto.request.SaleCreationRequest;
import cl.duoc.sales.dto.request.SaleDetailRequest;
import cl.duoc.sales.dto.request.SaleUpdateRequest;
import cl.duoc.sales.dto.response.SaleDetailResponse;
import cl.duoc.sales.dto.response.SaleResponse;
import cl.duoc.sales.dto.response.SaleStatusResponse;
import cl.duoc.sales.model.Sale;
import cl.duoc.sales.model.SaleDetail;
import cl.duoc.sales.model.SaleStatus;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DtoModelMapper {
    public SaleDetailResponse toDetailResponse(SaleDetail detail) {
        return SaleDetailResponse.builder()
                .id(detail.getId())
                .description(detail.getDescription())
                .sku(detail.getSku())
                .quantity(detail.getQuantity())
                .unitPrice(detail.getUnitPrice())
                .build();
    }

    public SaleResponse toSaleResponse(Sale sale, List<SaleDetail> details) {
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

    public SaleStatusResponse toSaleStatusResponse(SaleStatus sale) {
        return SaleStatusResponse.builder()
                .id(sale.getId())
                .name(sale.getName())
                .build();
    }

    public Sale saleFromCreationRequest(SaleCreationRequest req, SaleStatus currentStatus) {
        return Sale.builder()
                .customerId(req.getCustomerId())
                .amount(req.getAmount())
                .createdAt(LocalDateTime.now())
                .status(currentStatus)
                .build();
    }

    public Sale saleFromUpdateRequest(SaleUpdateRequest req, SaleStatus currentState) {
        return Sale.builder()
                .customerId(req.getCustomerId())
                .amount(req.getAmount())
                .createdAt(LocalDateTime.now())
                .status(currentState)
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

    public List<SaleDetail> detailsFromCreationRequest(SaleCreationRequest req, Sale sale) {
        return req.getDetails().stream()
                .map(detail -> detailFromRequest(detail, sale))
                .toList();
    }

    public List<SaleDetail> detailsFromUpdateRequest(SaleUpdateRequest req, Sale sale) {
        return req.getDetails().stream()
                .map(detail -> detailFromRequest(detail, sale))
                .toList();
    }

    public List<SaleDetail> extractDetails(SaleUpdateRequest req, Sale sale) {
        return req.getDetails().stream()
                .map(detail -> detailFromRequest(detail, sale))
                .toList();
    }
}
