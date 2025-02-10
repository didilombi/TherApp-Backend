package com.therapp.spring.dto.converter;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.therapp.spring.dto.GetUserDTO;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.modelo.Rol;

@Component
public class UserDtoConverter {

	public GetUserDTO convertUserEntityToGetUserDto(Usuario usuario) {
		return GetUserDTO.builder()
				.username(usuario.getUsername())
				.fotoPerfil(usuario.getFotoPerfil())
				.rol(usuario.getRol().stream()
							.map(Rol::name)
							.collect(Collectors.toSet())
				)
				.build();
	}
}
