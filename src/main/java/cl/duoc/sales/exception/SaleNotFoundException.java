/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.sales.exception;

public class SaleNotFoundException extends RuntimeException {
    public SaleNotFoundException(Long saleId) {
        super("Sale with id '" + saleId + "' not found in the DB.");
    }
}
