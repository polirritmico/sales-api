/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.sales.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response payload representing a specific item detail within a sale")
public class SaleDetailResponse {

    @Schema(description = "Unique identifier of the sale detail", example = "1")
    private Long id;

    @Schema(description = "Product description", example = "Laptop Pro 15")
    private String description;

    @Schema(description = "Stock Keeping Unit identifier", example = "SKU-12345")
    private String sku;

    @Schema(description = "Quantity of the product purchased", example = "2")
    private Integer quantity;

    @Schema(description = "Unit price of the product", example = "7500")
    private Integer unitPrice;
}
