/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.sales.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SaleNotFoundException extends RuntimeException {
    public SaleNotFoundException(Long saleId) {
        String msg = "Sale with id '" + saleId + "' not found in the DB.";
        log.error(msg);
        super(msg);
    }
}
