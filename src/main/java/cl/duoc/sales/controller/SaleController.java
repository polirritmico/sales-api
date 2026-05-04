/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.sales.controller;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sales")
@RequiredArgsConstructor
public class SaleController {

    @GetMapping("/check-health")
    public ResponseEntity<Map<String, String>> checkHealth() {
        Map<String, String> res =
                Map.of(
                        "message",
                        "SalesMicroservice Status OK.",
                        "timestamp",
                        LocalDateTime.now().toString());
        return ResponseEntity.ok(res);
    }
}
