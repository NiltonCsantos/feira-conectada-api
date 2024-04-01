package org.feiraconectada.feiraconectadaapi.exceptions;

public class RegistredUserAddressException extends RuntimeException {

    public RegistredUserAddressException() {
        super("Esse endereço já está atribuido a este usuário.");
    }
}
