/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.sales.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request payload for creating a new sale")
public class SaleCreationRequest {

    @NotNull(message = "La venta debe estar asociada a un cliente")
    @Positive(message = "La id del usuario no puede ser negativa")
    @Schema(description = "Customer unique identifier", example = "1", requiredMode = RequiredMode.REQUIRED)
    private Long customerId;

    @NotNull(message = "El valor de venta es obligatorio")
    @Positive(message = "El valor de venta no puede ser negativo")
    @Schema(description = "Total amount of the sale", example = "15000", requiredMode = RequiredMode.REQUIRED)
    private Integer amount;

    @NotNull(message = "Los detalles de venta son obligatorios")
    @Valid
    @Schema(description = "List of items included in the sale", example = "1", requiredMode = RequiredMode.REQUIRED)
    private List<SaleDetailRequest> details;
}
