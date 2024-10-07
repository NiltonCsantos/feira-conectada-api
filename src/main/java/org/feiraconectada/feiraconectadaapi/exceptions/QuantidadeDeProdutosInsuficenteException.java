package org.feiraconectada.feiraconectadaapi.exceptions;

public class QuantidadeDeProdutosInsuficenteException extends RuntimeException{
    public QuantidadeDeProdutosInsuficenteException(String message) {
        super("O produto" + message + "Não possui quantidade suficiente");
    }
}
