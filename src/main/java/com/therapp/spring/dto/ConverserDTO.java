package com.therapp.spring.dto;

import com.therapp.spring.modelo.Rol;
import com.therapp.spring.modelo.Usuario;

public class ConverserDTO {
    //convierte una Entidad a un DTO
    //aqui habran metodos que será convertUsertoUserDTO,etc...
    // Convierte un Usuario en UsuarioDTO
    public static UsuarioDTO convertUsuarioToUsuarioDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre(usuario.getNombre());
        dto.setNombreUsuario(usuario.getNombreUsuario());
        dto.setEmail(usuario.getEmail());
        return dto;
    }

    // Convierte un Terapeuta en TerapeutaDTO
    public static TerapeutaDTo convertUsuarioToTerapeutaDTO(UsuarioDTO usuarioDTO) {
        TerapeutaDTo terapeutaDTO = new TerapeutaDTo();
        terapeutaDTO.setNombre(usuarioDTO.getNombre());
        terapeutaDTO.setNombreUsuario(usuarioDTO.getNombreUsuario());
        terapeutaDTO.setEmail(usuarioDTO.getEmail());
        terapeutaDTO.setRol(Rol.TERAPEUTA); // Asigna el rol correcto
        return terapeutaDTO;
    }
}
