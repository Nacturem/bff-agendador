package com.javanauta.bff_agendador.controller;


import com.javanauta.bff_agendador.business.UsuarioService;
import com.javanauta.bff_agendador.business.dto.EnderecoDTO;
import com.javanauta.bff_agendador.business.dto.TelefoneDTO;
import com.javanauta.bff_agendador.business.dto.UsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro e login de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salvar usuários", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvao com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTO> salvaUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));

    }

    //verifica login e senha e gera um token token para autenticar login e senha
    @PostMapping("/login")
    @Operation(summary = "login usuários", description = "login do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário logado")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String login(@RequestBody UsuarioDTO usuarioDTO) {
       return usuarioService.login(usuarioDTO);
    }


    @GetMapping // get nao precisa de uri
    @Operation(summary = "Buscar dados do usuários por email", description = "busca de dados do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "404", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTO> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                           @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email,token));

    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deletar usuários por id", description = "deleta usuário")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletarUsuarioPorEmail(@PathVariable String email,
                                                       @RequestHeader("Authorization") String token) {
        usuarioService.deletarUsuarioPorEmail(email,token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados do usuários", description = "atualiza dados do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTO> atualizaDadoUsuario(@RequestBody UsuarioDTO dto,
                                                          @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token,dto));
    }
    @PutMapping("/endereco")
    @Operation(summary = "Atualizar endereço do usuários", description = "atualiza endereço1 do usuário")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTO> atualizaEndereco(@RequestBody EnderecoDTO dto,
                                                        @RequestParam("id")Long id,
                                                        @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto, token));

    }
    @PutMapping("/telefone")
    @Operation(summary = "Atualizar telefone do usuários", description = "atualiza endereço1 do usuário")
    @ApiResponse(responseCode = "200", description = "telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTO> atualizaTelefone(@RequestBody TelefoneDTO dto,
                                                        @RequestParam("id")Long id,
                                                        @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto, token));

    }

    @PostMapping("/endereco")
    @Operation(summary = "Salvar endereço usuários", description = "Salva endereço suário")
    @ApiResponse(responseCode = "200", description = "Endereço salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTO> cadastraEndereco(@RequestBody EnderecoDTO dto,
                                                        @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.cadastraEndereco(dto,token));

    }

    @PostMapping("/telefone")
    @Operation(summary = "Salvar telefone usuários", description = "Salva telefone usuário")
    @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTO> cadastraTelefone(@RequestBody TelefoneDTO dto,
                                                        @RequestHeader("Authorization")
                                                        String token) {
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, dto));

    }



}








