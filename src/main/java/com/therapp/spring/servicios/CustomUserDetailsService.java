package com.therapp.spring.servicios;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return usuarioService.findByUsername(username)
				.map(usuario -> (UserDetails) usuario)
				.orElseThrow(() -> new UsernameNotFoundException(username + " no encontrado"));
	}
	
	public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
		return usuarioService.findById(id)
					.map(usuario -> (UserDetails) usuario)
					.orElseThrow(() -> new UsernameNotFoundException("Usuario con ID: " + id + " no encontrado") );
	}

}
