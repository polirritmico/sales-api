/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.sales.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response payload representing a Sale")
public class SaleResponse {

    @Schema(description = "Unique identifier of the sale", example = "1")
    private Long id;

    @Schema(description = "Customer unique identifier", example = "1")
    private Long customerId;

    @Schema(description = "Total amount of the sale", example = "15000")
    private Integer amount;

    @Schema(description = "Current status of the sale", example = "PENDING")
    private String status;

    @Schema(description = "List of items included in the sale")
    private List<SaleDetailResponse> details;

    @Schema(description = "Record creation timestamp", example = "2026-06-23T21:04:00")
    private LocalDateTime createdAt;

    @Schema(description = "Record last update timestamp", example = "2026-06-23T21:04:00")
    private LocalDateTime updatedAt;

    @Schema(description = "Record deletion timestamp, null if active")
    private LocalDateTime deletedAt;
}
