package com.javanauta.bff_agendador.infraescture.client.config;

import com.javanauta.bff_agendador.infraescture.client.exceptions.BusinessException;
import com.javanauta.bff_agendador.infraescture.client.exceptions.ConflictException;
import com.javanauta.bff_agendador.infraescture.client.exceptions.ResourceNotFoundException;
import com.javanauta.bff_agendador.infraescture.client.exceptions.UnathorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {


    @SneakyThrows
    @Override
    public Exception decode(String s, Response response) {
ri


        String mensagemErro = mensagemErro(response);

        switch (response.status()) {
            case 409:
                return new ConflictException("Erro " + mensagemErro);
            case 403:
                return new ResourceNotFoundException("Erro " + mensagemErro);
            case 401:
                return new UnathorizedException("Erro " + mensagemErro);
            case 400:
                return new IllegalArgumentException("Erro " + mensagemErro);
            default:
                return new BusinessException("Erro " + mensagemErro);
        }
    }

    private String mensagemErro(Response response) {

        try {
            if (Objects.isNull(response.body())) {
                return "";
            }
            return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
