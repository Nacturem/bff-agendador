package com.javanauta.bff_agendador.business;


import com.javanauta.bff_agendador.business.dto.in.EnderecoDTORequest;
import com.javanauta.bff_agendador.business.dto.in.LoginRequestDTO;
import com.javanauta.bff_agendador.business.dto.in.TelefoneDTORequest;
import com.javanauta.bff_agendador.business.dto.in.UsuarioDTORequest;
import com.javanauta.bff_agendador.business.dto.out.EnderecoDTOResponse;
import com.javanauta.bff_agendador.business.dto.out.TelefoneDTOResponse;
import com.javanauta.bff_agendador.business.dto.out.UsuarioDTOResponse;
import com.javanauta.bff_agendador.business.dto.out.ViaCepDTOResponse;
import com.javanauta.bff_agendador.infrastructure.client.UsuarioClient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;



    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO) {
        return client.salvaUsuario(usuarioDTO);

    }
    public String login(LoginRequestDTO usuarioDTO){
        return client.login(usuarioDTO);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {
        return client.buscaUsuarioPorEmail(email, token);

    }

    public void deletarUsuarioPorEmail(String email, String token) {
        client.deletarUsuarioPorEmail(email, token);

    }
    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest dto) {
        return client.atualizaDadoUsuario(dto, token);

    }

    public EnderecoDTOResponse atualizaEndereco(Long id, EnderecoDTORequest dto, String token){
        return client.atualizaEndereco(dto, id, token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long id, TelefoneDTORequest dto, String token){
        return client.atualizaTelefone(dto, id, token);
    }


    public EnderecoDTOResponse cadastraEndereco(EnderecoDTORequest dto, String token){
        return client.cadastraEndereco(dto, token);

    }
    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest dto){

        return client.cadastraTelefone(dto,token);
    }

    public ViaCepDTOResponse buscaEnderecoPorCep(String cep){
        return client.buscarDadosCep(cep);
    }





}
