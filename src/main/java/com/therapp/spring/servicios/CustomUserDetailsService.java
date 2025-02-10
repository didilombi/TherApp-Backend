package com.therapp.spring.servicios;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.therapp.spring.modelo.Usuario;

import lombok.RequiredArgsConstructor;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("HOLA");
		Optional<Usuario> user = usuarioService.findByUsername(username);
		return user.get();
		// return usuarioService.findByUsername(username)
		// 		.orElseThrow(() -> new UsernameNotFoundException(username + " no encontrado"));
	}
	
	public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
		return usuarioService.findById(id)
					.orElseThrow(() -> new UsernameNotFoundException("Usuario con ID: " + id + " no encontrado") );
	}

}
