/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.sales.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SaleStatusNotFoundException extends RuntimeException {
    public SaleStatusNotFoundException(String status) {
        String msg = "Not found passed status: " + status;
        log.error(msg);
        super(msg);
    }
}
