package com.therapp.spring.controladores;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.therapp.spring.dto.GetUserDTO;
import com.therapp.spring.dto.converter.UserDtoConverter;
import com.therapp.spring.modelo.Rol;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.seguridad.jwt.JwtProvider;
import com.therapp.spring.seguridad.jwt.model.JwtUserResponse;
import com.therapp.spring.seguridad.jwt.model.LoginRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider tokenProvider;
    private final UserDtoConverter converter;

    @PostMapping("/auth/login")
    public ResponseEntity<JwtUserResponse> login(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = null;

        try {
            authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    loginRequest.getUsername(),
                                    loginRequest.getPassword()
                            ));
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Usuario usuario = (Usuario) authentication.getPrincipal();
        String jwtToken = tokenProvider.generateToken(authentication);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertUserEntityAndTokenToJwtUserResponse(usuario, jwtToken));
    }

    @GetMapping("/user/me")
    public GetUserDTO me(@AuthenticationPrincipal Usuario usuario) {
        return converter.convertUserEntityToGetUserDto(usuario);
    }

    private JwtUserResponse convertUserEntityAndTokenToJwtUserResponse(Usuario usuario, String jwtToken) {
        return JwtUserResponse.jwtUserResponseBuilder()
                .username(usuario.getUsername())
                .fotoPerfil(usuario.getFotoPerfil())
                .rol(usuario.getRol().stream().map(Rol::name).collect(Collectors.toSet()))
                .token(jwtToken)
                .build();
    }
}
