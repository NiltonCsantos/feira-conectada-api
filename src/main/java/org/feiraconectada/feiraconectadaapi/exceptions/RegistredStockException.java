package org.feiraconectada.feiraconectadaapi.exceptions;

public class RegistredStockException extends RuntimeException{

    public RegistredStockException() {
        super("Estoque já cadastrado");
    }
}
