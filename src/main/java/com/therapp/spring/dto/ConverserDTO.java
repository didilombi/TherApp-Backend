// package com.therapp.spring.dto;

// import com.therapp.spring.modelo.Rol;
// import com.therapp.spring.modelo.Terapeuta;
// import com.therapp.spring.modelo.Usuario;
// import com.therapp.spring.dto.TerapeutaDTO;

// public class ConverserDTO {
//     //convierte una Entidad a un DTO
//     //aqui habran metodos que será convertUsertoUserDTO,etc...
//     // Convierte un Usuario en UsuarioDTO
//     public static UsuarioDTO convertUsuarioToUsuarioDTO(Usuario usuario) {
//         UsuarioDTO dto = new UsuarioDTO();
//         dto.setNombre(usuario.getNombre());
//         dto.setUsername(usuario.getUsername());
//         dto.setEmail(usuario.getEmail());
//         return dto;
//     }

//     // Convierte un Terapeuta en TerapeutaDTO
//     public static TerapeutaDTO convertUsuarioToTerapeutaDTO(UsuarioDTO usuarioDTO) {
//         TerapeutaDTO terapeutaDTO = new TerapeutaDTO();
//         terapeutaDTO.setNombre(usuarioDTO.getNombre());
//         terapeutaDTO.setUsername(usuarioDTO.getUsername());
//         terapeutaDTO.setEmail(usuarioDTO.getEmail());
//         terapeutaDTO.setRol(Rol.TERAPEUTA); // Asigna el rol correcto
//         return terapeutaDTO;
//     }
// }
