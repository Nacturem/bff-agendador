package com.javanauta.bff_agendador.controller;

import com.javanauta.bff_agendador.business.TarefasService;
import com.javanauta.bff_agendador.business.dto.in.TarefasDTORequest;
import com.javanauta.bff_agendador.business.dto.out.TarefasDTOResponse;
import com.javanauta.bff_agendador.business.enums.StatusNotificacao;
import com.javanauta.bff_agendador.infrastructure.client.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastro e tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Salvar tarefas", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> gravarTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca tarefas por Período", description = "Busca tarefas cadastradas por periodo")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário não encontrado")
    public ResponseEntity<List<TarefasDTOResponse>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String token) {


        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));

    }

    @GetMapping
    @Operation(summary = "Busca tarefas por email de usuário", description = "Busca tarefas por email")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas por usuário")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Email não encontrado")
    @ApiResponse(responseCode = "401", description = "Usuário não encontrado")
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorEmail(@RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(tarefasService.buscaTarefasPorEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefas ", description = "Deleta tarefas")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário não encontrado")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id,
                                                  @RequestHeader(name = "Authorization", required = false) String token) {

        tarefasService.deletaTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera status de tarefas", description = "Altera status tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Status de tarefas alteradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário não encontrado")
    public ResponseEntity<TarefasDTOResponse> alteraStatusNotificacao(@RequestParam("status") StatusNotificacao status,
                                                                     @RequestParam("id") String id,
                                                                     @RequestHeader(name = "Authorization", required = false) String token) {


        return ResponseEntity.ok(tarefasService.alteraStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Altera dados tarefas", description = "Altera dados tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Tarefas alteradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa id não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário não encontrado")
    public ResponseEntity<TarefasDTOResponse> updateTarefas(@RequestBody TarefasDTORequest dto,
                                                           @RequestParam("id") String id,
                                                           @RequestHeader(name = "Authorization", required = false) String token) {


        return ResponseEntity.ok(tarefasService.updateTarefas(dto, id, token));

    }
}

