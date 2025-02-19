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
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        Optional<Usuario> user = usuarioService.findByIdentifier(identifier);
        return user.orElseThrow(() -> new UsernameNotFoundException(identifier + " no encontrado"));
    }

    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        return usuarioService.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario con ID: " + id + " no encontrado"));
    }
}
