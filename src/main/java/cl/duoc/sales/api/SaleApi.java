/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.sales.api;

import cl.duoc.sales.dto.request.SaleCreationRequest;
import cl.duoc.sales.dto.request.SaleUpdateRequest;
import cl.duoc.sales.dto.response.SaleResponse;
import cl.duoc.sales.dto.response.SaleStatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Sales", description = "Provides sales CRUD operations.")
public interface SaleApi {

    @Operation(summary = "Find sale by ID", description = "Retrieves a specific sale using its unique identifier.")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @ApiResponse(responseCode = "404", description = "Sale not found", content = @Content)
    ResponseEntity<SaleResponse> findSale(@Parameter(description = "ID of the sale") @PathVariable Long id);

    @Operation(summary = "List all sales", description = "Retrieves a full list of all recorded sales in the system.")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    ResponseEntity<List<SaleResponse>> findAllSales();

    @Operation(summary = "Create a new sale", description = "Persists a new sale record into the database.")
    @ApiResponse(responseCode = "200", description = "Sale created successfully")
    @ApiResponse(responseCode = "400", description = "Validation error", content = @Content)
    ResponseEntity<SaleResponse> saveSale(SaleCreationRequest req);

    @Operation(summary = "Replace an existing sale", description = "Replaces an existing sale record matching the id.")
    @ApiResponse(responseCode = "200", description = "Sale replaced successfully")
    @ApiResponse(responseCode = "400", description = "Validation error", content = @Content)
    @ApiResponse(responseCode = "404", description = "Sale or status not found", content = @Content)
    ResponseEntity<SaleResponse> replaceSale(
            @Parameter(description = "ID of the sale") @PathVariable Long id, SaleUpdateRequest req);

    @Operation(summary = "Delete an existing sale", description = "Delete a sale matching id record from the database.")
    @ApiResponse(responseCode = "204", description = "Sale deleted successfully")
    @ApiResponse(responseCode = "404", description = "Sale not found", content = @Content)
    ResponseEntity<Void> deleteSale(@Parameter(description = "ID of the sale") @PathVariable Long id);

    @Operation(
            summary = "Get sale status",
            description = "Retrieves the full list of recorded sale status in the system.")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    ResponseEntity<List<SaleStatusResponse>> findAllSaleStatus();
}
