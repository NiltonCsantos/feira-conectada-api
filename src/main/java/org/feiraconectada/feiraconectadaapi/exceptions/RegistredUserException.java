package org.feiraconectada.feiraconectadaapi.exceptions;

public class RegistredUserException extends RuntimeException{

    public RegistredUserException() {
        super("Usuário já cadastrado");
    }
}
