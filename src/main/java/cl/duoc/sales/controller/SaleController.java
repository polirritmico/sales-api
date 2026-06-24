/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.sales.controller;

import cl.duoc.sales.api.SaleApi;
import cl.duoc.sales.dto.request.SaleCreationRequest;
import cl.duoc.sales.dto.request.SaleUpdateRequest;
import cl.duoc.sales.dto.response.SaleResponse;
import cl.duoc.sales.dto.response.SaleStatusResponse;
import cl.duoc.sales.service.SaleService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
public class SaleController implements SaleApi {
    private final SaleService service;

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponse> findSale(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<SaleResponse>> findAllSales() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<SaleResponse> saveSale(@Valid @RequestBody SaleCreationRequest req) {
        return ResponseEntity.ok(service.saveSale(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleResponse> replaceSale(@PathVariable Long id, @Valid @RequestBody SaleUpdateRequest req) {
        return ResponseEntity.ok(service.replaceSale(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        service.deleteSale(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status")
    public ResponseEntity<List<SaleStatusResponse>> findAllSaleStatus() {
        return ResponseEntity.ok(service.findAllSaleStatus());
    }
}
