package com.javanauta.bff_agendador.business;




import com.javanauta.bff_agendador.business.dto.in.LoginRequestDTO;
import com.javanauta.bff_agendador.business.dto.out.TarefasDTOResponse;
import com.javanauta.bff_agendador.business.enums.StatusNotificacao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CronService {

    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora(){
        String token = login (converterRequestDTO());

        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        LocalDateTime horaFuturaMaisCinco = LocalDateTime.now().plusHours(1).plusMinutes(5);

        List<TarefasDTOResponse> listaTarefas = tarefasService.buscaTarefasAgendadasPorPeriodo(horaFutura, horaFuturaMaisCinco,token);

        listaTarefas.forEach(tarefa -> {
        emailService.enviarEmail(tarefa);
        tarefasService.alteraStatus(StatusNotificacao.NOTIFICADO, tarefa.getId(),
                    token);

      });


    }

    public String login(LoginRequestDTO dto) {
       return  usuarioService.login(dto);
    }

    public LoginRequestDTO converterRequestDTO(){
        return LoginRequestDTO.builder()
                .email(email)
                .senha(senha)
                .build();

    }
}

