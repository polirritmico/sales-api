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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request payload for updating an existing sale")
public class SaleUpdateRequest {

    @NotNull(message = "La venta debe estar asociada a un cliente")
    @Positive(message = "La id del usuario no puede ser negativa")
    @Schema(description = "Customer unique identifier", example = "1", requiredMode = RequiredMode.REQUIRED)
    private Long customerId;

    @NotEmpty(message = "El estado es obligatorio")
    @Schema(
            description = "Target status to update the sale",
            example = "COMPLETED",
            requiredMode = RequiredMode.REQUIRED)
    private String status;

    @NotNull(message = "El valor de venta es obligatorio")
    @Positive(message = "El valor de venta no puede ser negativo")
    @Schema(description = "Total amount of the sale", example = "15000", requiredMode = RequiredMode.REQUIRED)
    private Integer amount;

    @PastOrPresent(message = "La fecha de borrado no puede ser en el futuro")
    @Schema(description = "Record deletion timestamp, omit to keep active")
    private LocalDateTime deletedAt;

    @NotNull(message = "Los detalles de venta son obligatorios")
    @Valid
    @Schema(description = "Updated list of items included in the sale", requiredMode = RequiredMode.REQUIRED)
    private List<SaleDetailRequest> details;
}
