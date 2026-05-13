/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.sales.exception;

public class SaleStatusNotFoundException extends RuntimeException {
    public SaleStatusNotFoundException(String status) {
        super("Not found passed status: " + status);
    }
}
