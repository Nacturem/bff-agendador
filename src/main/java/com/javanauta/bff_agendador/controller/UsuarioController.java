package com.javanauta.bff_agendador.controller;


import com.javanauta.bff_agendador.business.UsuarioService;
import com.javanauta.bff_agendador.business.dto.EnderecoDTO;
import com.javanauta.bff_agendador.business.dto.TelefoneDTO;
import com.javanauta.bff_agendador.business.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvaUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));

    }

    //verifica login e senha e gera um token token para autenticar login e senha
    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDTO) {
       return usuarioService.login(usuarioDTO);
    }


    @GetMapping // get nao precisa de uri
    public ResponseEntity<UsuarioDTO> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                           @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email,token));

    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletarUsuarioPorEmail(@PathVariable String email,
                                                       @RequestHeader("Authorization") String token) {
        usuarioService.deletarUsuarioPorEmail(email,token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> atualizaDadoUsuario(@RequestBody UsuarioDTO dto,
                                                          @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token,dto));
    }
    @PutMapping("/endereco")
    public ResponseEntity<EnderecoDTO> atualizaEndereco(@RequestBody EnderecoDTO dto,
                                                        @RequestParam("id")Long id,
                                                        @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto, token));

    }
    @PutMapping("/telefone")
    public ResponseEntity<TelefoneDTO> atualizaTelefone(@RequestBody TelefoneDTO dto,
                                                        @RequestParam("id")Long id,
                                                        @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto, token));

    }

    @PostMapping("/endereco")
    public ResponseEntity<EnderecoDTO> cadastraEndereco(@RequestBody EnderecoDTO dto,
                                                        @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(usuarioService.cadastraEndereco(dto,token));

    }

    @PostMapping("/telefone")
    public ResponseEntity<TelefoneDTO> cadastraTelefone(@RequestBody TelefoneDTO dto,
                                                        @RequestHeader("Authorization")
                                                        String token) {
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, dto));

    }



}








