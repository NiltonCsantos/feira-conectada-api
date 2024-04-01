package org.feiraconectada.feiraconectadaapi.exceptions;

public class InternalErrorException extends RuntimeException{

    public InternalErrorException() {
        super("Algo deu errado em nosso servidor, por favor contate a equipe de desenvolvimento");
    }

}
