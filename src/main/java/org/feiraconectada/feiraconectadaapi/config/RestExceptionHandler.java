package org.feiraconectada.feiraconectadaapi.config;

import org.feiraconectada.feiraconectadaapi.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<RestErrorMessage> userNotFound(NotFoundException notFoundException){
        final RestErrorMessage message= new RestErrorMessage(HttpStatus.NOT_FOUND, notFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(InternalErrorException.class)
    private ResponseEntity<RestErrorMessage> internalError(){

        InternalErrorException internalErrorException = new InternalErrorException();

        RestErrorMessage message= new RestErrorMessage(HttpStatus.BAD_REQUEST, internalErrorException.getMessage());

        return ResponseEntity.badRequest().body(message);


    }

    @ExceptionHandler(RegistredUserException.class)
    private ResponseEntity<RestErrorMessage> registredUser(){
        System.out.println("USUÀEIODKNK");
        RegistredUserException exception= new RegistredUserException();
        RestErrorMessage message= new RestErrorMessage(HttpStatus.CONFLICT,exception.getMessage() );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }


    @ExceptionHandler(TokenException.class)
    private  ResponseEntity<RestErrorMessage> expiredToken(){
        TokenException tokenException= new TokenException();

        System.out.println("PASSANDO AQUi");

        RestErrorMessage message= new RestErrorMessage(HttpStatus.UNAUTHORIZED, tokenException.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
    }

    @ExceptionHandler(SendMailException.class)
    private ResponseEntity<RestErrorMessage> sendEmail(){
        SendMailException exception= new SendMailException();
        RestErrorMessage message= new RestErrorMessage(HttpStatus.CONFLICT,exception.getMessage() );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(MessageMailException.class)
    private ResponseEntity<RestErrorMessage> sendMessageMail(){

        MessageMailException exception= new MessageMailException();
        RestErrorMessage message= new RestErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage() );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

    @ExceptionHandler(RegistredUserAddressException.class)
    private ResponseEntity<RestErrorMessage> registredUserAddress(){

        RegistredUserAddressException exception= new RegistredUserAddressException();

        RestErrorMessage message= new RestErrorMessage(HttpStatus.CONFLICT, exception.getMessage());

        return  ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(RegistredStockException.class)
    public ResponseEntity<RestErrorMessage> registredStock(){
        RegistredStockException registredStockException= new RegistredStockException();

        RestErrorMessage message= new RestErrorMessage(HttpStatus.CONFLICT, registredStockException.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(RegistredProductException.class)
    public ResponseEntity<RestErrorMessage> registredProduct(){

        RegistredProductException registredProductException= new RegistredProductException();

        RestErrorMessage restErrorMessage= new RestErrorMessage(HttpStatus.CONFLICT,
                registredProductException.getMessage() );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(restErrorMessage);

    }

}
