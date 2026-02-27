package com.javanauta.bff_agendador.business;


import com.javanauta.bff_agendador.business.dto.EnderecoDTO;
import com.javanauta.bff_agendador.business.dto.TelefoneDTO;
import com.javanauta.bff_agendador.business.dto.UsuarioDTO;
import com.javanauta.bff_agendador.infraescture.client.UsuarioClient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;



    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {

        return client.salvaUsuario(usuarioDTO);

    }
    public String login(UsuarioDTO usuarioDTO){
        return client.login(usuarioDTO);
    }

    public UsuarioDTO buscarUsuarioPorEmail(String email,String token) {
        return client.buscaUsuarioPorEmail(email, token);

    }

    public void deletarUsuarioPorEmail(String email, String token) {
        client.deletarUsuarioPorEmail(email, token);

    }
    public UsuarioDTO atualizaDadosUsuario(String token, UsuarioDTO dto) {
        return client.atualizaDadoUsuario(dto, token);

    }

    public EnderecoDTO atualizaEndereco(Long id, EnderecoDTO dto, String token){
        return client.atualizaEndereco(dto, id, token);
    }

    public TelefoneDTO atualizaTelefone(Long id,TelefoneDTO dto, String token){
        return client.atualizaTelefone(dto, id, token);
    }


    public EnderecoDTO cadastraEndereco(EnderecoDTO dto, String token){
        return client.cadastraEndereco(dto, token);

    }
    public TelefoneDTO cadastraTelefone(String token, TelefoneDTO dto){

        return client.cadastraTelefone(dto,token);
    }





}
