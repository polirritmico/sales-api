/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.sales.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request payload for a specific item detail within a sale creation or update")
public class SaleDetailRequest {

    @NotBlank(message = "La descripción no debe estar en blanco")
    @Schema(description = "Product description", example = "Laptop Pro 15", requiredMode = RequiredMode.REQUIRED)
    private String description;

    @NotBlank(message = "El identificator SKU es obligatorio")
    @Schema(description = "Stock Keeping Unit identifier", example = "SKU-12345", requiredMode = RequiredMode.REQUIRED)
    private String sku;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad del producto no puede ser negativa")
    @Schema(description = "Quantity of the product purchased", example = "2", requiredMode = RequiredMode.REQUIRED)
    private Integer quantity;

    @NotNull(message = "El precio unitario es obligatorio")
    @PositiveOrZero(message = "El precio no puede ser negativo")
    @Schema(description = "Unit price of the product", example = "7500", requiredMode = RequiredMode.REQUIRED)
    private Integer unitPrice;
}
