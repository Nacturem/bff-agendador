package com.javanauta.bff_agendador.infrastructure.client.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException (String mensagem ){
        super(mensagem);
    }

    public BusinessException (String mensagem, Throwable throwable){
        super(mensagem,throwable);

    }


}
