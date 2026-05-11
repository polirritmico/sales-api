/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.sales.controller;

import cl.duoc.sales.dto.request.SaleCreationRequest;
import cl.duoc.sales.dto.response.SaleResponse;
import cl.duoc.sales.service.SaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Sales", description = "Provides sales CRUD operations.")
public class SaleController {
    private final SaleService service;

    @GetMapping("/{id}")
    @Operation(summary = "Find sale by ID", description = "Retrieves a specific sale using its unique identifier.")
    public ResponseEntity<SaleResponse> findSale(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    @Operation(summary = "List all sales", description = "Retrieves a full list of all recorded sales in the system.")
    public ResponseEntity<List<SaleResponse>> findAllSales() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    @Operation(summary = "Create a new sale", description = "Persists a new sale record into the database.")
    public ResponseEntity<SaleResponse> saveSale(@Valid @RequestBody SaleCreationRequest req) {
        return ResponseEntity.ok(service.saveSale(req));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Replace an existing sale", description = "Replaces an existing sale record matching the id.")
    public ResponseEntity<SaleResponse> replaceSale(
            @PathVariable Long id, @Valid @RequestBody SaleCreationRequest req) {
        return ResponseEntity.ok(service.replaceSale(id, req));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing sale", description = "Delete a sale matching id record from the database.")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        service.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
}
