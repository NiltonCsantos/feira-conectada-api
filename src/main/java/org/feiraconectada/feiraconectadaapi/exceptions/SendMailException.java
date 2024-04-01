package org.feiraconectada.feiraconectadaapi.exceptions;

import org.springframework.mail.MailException;

public class SendMailException extends MailException {
    public SendMailException() {
        super("Email não pode ser enviadp");
    }
}
