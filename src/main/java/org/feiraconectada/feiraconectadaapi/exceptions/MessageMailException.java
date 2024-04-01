package org.feiraconectada.feiraconectadaapi.exceptions;

import jakarta.mail.MessagingException;

public class MessageMailException extends MessagingException {
    public MessageMailException() {
        super("Não foi possível enviar mensagem de email");
    }
}
