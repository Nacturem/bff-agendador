package com.javanauta.bff_agendador.infrastructure.client.exceptions;

public class UnathorizedException extends RuntimeException {

    public UnathorizedException(String mensagem){
        super(mensagem);
    }

    public UnathorizedException(String mensagem, Throwable throwable){
        super(mensagem,throwable);
    }





}
