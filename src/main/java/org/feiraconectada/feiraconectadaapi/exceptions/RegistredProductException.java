package org.feiraconectada.feiraconectadaapi.exceptions;

public class RegistredProductException extends RuntimeException{
    public RegistredProductException() {
        super("Produto já registrado");
    }
}
