package com.javanauta.bff_agendador.infraescture.client;


import com.javanauta.bff_agendador.business.dto.EnderecoDTO;
import com.javanauta.bff_agendador.business.dto.TelefoneDTO;
import com.javanauta.bff_agendador.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {


    @PostMapping("/login")
    String login(UsuarioDTO usuarioDTO);


    @GetMapping
    UsuarioDTO buscaUsuarioPorEmail(@RequestParam("email") String email,
                                    @RequestHeader("Authorization") String token);


    @PostMapping
    UsuarioDTO salvaUsuario(@RequestBody UsuarioDTO usuarioDTO);


    @DeleteMapping("/{email}")
    Void deletarUsuarioPorEmail(@PathVariable String email,
                                @RequestHeader("Authorization") String token);


    @PutMapping
    UsuarioDTO atualizaDadoUsuario(@RequestBody UsuarioDTO dto,
                                   @RequestHeader("Authorization") String token);


    @PutMapping("/endereco")
    EnderecoDTO atualizaEndereco(@RequestBody EnderecoDTO dto,
                                 @RequestParam("id") Long id,
                                 @RequestHeader("Authorization") String token);


    @PutMapping("/telefone")
    TelefoneDTO atualizaTelefone(@RequestBody TelefoneDTO dto,
                                                        @RequestParam("id") Long id,
                                                        @RequestHeader("Authorization") String token);


    @PostMapping("/endereco")
    EnderecoDTO cadastraEndereco(@RequestBody EnderecoDTO dto,
                                 @RequestHeader("Authorization") String token);


    @PostMapping("/telefone")
    TelefoneDTO cadastraTelefone(@RequestBody TelefoneDTO dto,
                                 @RequestHeader("Authorization") String token);


}