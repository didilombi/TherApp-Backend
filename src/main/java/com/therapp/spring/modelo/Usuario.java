package com.therapp.spring.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String username;
    private String email;
    private String clave;
    private String fotoPerfil;
    
    @ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING) // Para que sea un String
	private Set<Rol> rol;

    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return rol
				.stream()
				.map(ur -> new SimpleGrantedAuthority("ROLE_" + ur.name()))
							.collect(Collectors.toList());
	}

    private String dni;
    private LocalDate fechaNacimiento;
    private String telefono;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();
    
    private String ubicacion;
    private String biografia;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Terapeuta terapeuta;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Organizacion organizacion;

    @OneToMany(mappedBy = "emisor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mensaje> mensajesEnviados;

    @OneToMany(mappedBy = "receptor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mensaje> mensajesRecibidos;

    public Terapeuta getTerapeuta() {
        return terapeuta;
    }

    @OneToMany(mappedBy = "usuarioSeguidor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Evita la serialización de la lista de seguidores
    private List<Seguidor> usuariosQueSigo;

    @OneToMany(mappedBy = "usuarioSeguido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Evita la serialización de seguidores
    private List<Seguidor> misSeguidores;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LikePublicacion> likesPublicaciones;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComentarioPublicacion> comentarios;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LikeComentario> likesComentarios;

    

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Usuario() {} //constructor vacio

    public Usuario(String nombre, String username,String email, String clave, String fotoPerfil, Set<Rol> rol,String dni,LocalDate fechaNacimiento,String telefono, String ubicacion) {
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.clave = clave;
        this.fotoPerfil = fotoPerfil;
        this.rol = rol;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.fechaRegistro = LocalDateTime.now();
        this.ubicacion = ubicacion;
    }
}
