/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.sales.exception.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Standardized error response payload returned across the API when an exception occurs")
public class ApiErrorResponse {

    @Schema(description = "Timestamp when the error occurred", example = "2026-06-23T21:45:55")
    private LocalDateTime timestamp;

    @Schema(description = "HTTP status code of the error", example = "404")
    private int status;

    @Schema(description = "HTTP status error description", example = "Not Found")
    private String error;

    @Schema(description = "Detailed error message explaining what went wrong", example = "Sale with id 15 not found")
    private String message;

    @Schema(description = "The URI path that was requested when the error occurred", example = "/api/v1/sales/15")
    private String path;

    @Schema(description = "The specific exception class name that was thrown", example = "SaleNotFoundException")
    private String kind;
}
