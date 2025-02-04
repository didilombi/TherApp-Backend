package com.therapp.spring.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String nombreUsuario;
    private String email;
    private String clave;
    private String fotoPerfil;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    private String dni;
    private LocalDate fechaNacimiento;
    private String telefono;
    private LocalDateTime fechaRegistro;
    private String ubicacion;
    private String biografia;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Terapeuta terapeuta;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Organizacion organizacion;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsuarioPublicacion> publicaciones;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LikePublicacion> likesPublicaciones;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComentarioPublicacion> comentarios;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LikeComentario> likesComentarios;

    @OneToMany(mappedBy = "usuarioSeguidor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seguidor> usuariosQueSigo;

    @OneToMany(mappedBy = "usuarioSeguido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seguidor> misSeguidores;

    public Usuario() {} //constructor vacio

    public Usuario(String nombre, String nombreUsuario,String email, String clave, String fotoPerfil, Rol rol,String dni,LocalDate fechaNacimiento,String telefono, String ubicacion, String biografia) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.clave = clave;
        this.fotoPerfil = fotoPerfil;
        this.rol = rol;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.fechaRegistro = LocalDateTime.now();
        this.ubicacion = ubicacion;
        this.biografia = biografia;
    }
}
