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
@Schema(description = "Response payload representing a possible status for a sale")
public class SaleStatusResponse {

    @Schema(description = "Unique identifier of the status", example = "1")
    private Integer id;

    @Schema(description = "Name of the status", example = "COMPLETED")
    private String name;
}
