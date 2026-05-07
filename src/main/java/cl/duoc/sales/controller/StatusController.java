/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.sales.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "System", description = "Service monitoring & status")
public class StatusController {

    @GetMapping("/api/v1/health")
    @Operation(summary = "Check service health", description = "Checks the microservice avaliability")
    public ResponseEntity<Map<String, String>> checkHealth() {
        Map<String, String> res = Map.of("status", "OK", "service", "Sales");
        return ResponseEntity.ok(res);
    }
}
