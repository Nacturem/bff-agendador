package com.javanauta.bff_agendador.business;


import com.javanauta.bff_agendador.business.dto.out.TarefasDTOResponse;
import com.javanauta.bff_agendador.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;


    public void enviarEmail (TarefasDTOResponse dto) {
        emailClient.enviarEmail(dto);

    }

}

