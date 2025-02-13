package com.therapp.spring.dto;

import com.therapp.spring.modelo.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class ConversacionDTO {

    public ConversacionDTO(Usuario usuario) {
        id = usuario.getId();
        fotoPerfil = usuario.getFotoPerfil();
        username = usuario.getUsername();
    }

    private Long id;
    private String fotoPerfil;
    private String username;
}
