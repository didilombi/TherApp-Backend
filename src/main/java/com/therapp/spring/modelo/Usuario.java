package com.therapp.spring.modelo;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) //Herencia en JPA
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

    
    @OneToMany(mappedBy = "usuario")
    private List<Mensaje> mensajes;

    @OneToMany(mappedBy = "usuarioSeguidor")
    private List<Seguidor> usuariosQueSigo;

    @OneToMany(mappedBy = "usuarioSeguido")
    private List<Seguidor> misSeguidores;

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioPublicacion> publicaciones;

    @OneToMany(mappedBy = "usuario")
    private List<ComentarioPublicacion> comentarios;

    @OneToMany(mappedBy = "usuario")
    private List<LikeComentario> likes;

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
    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    public List<Seguidor> getUsuariosQueSigo() {
        return usuariosQueSigo;
    }

    public void setUsuariosQueSigo(List<Seguidor> usuariosQueSigo) {
        this.usuariosQueSigo = usuariosQueSigo;
    }

    public List<Seguidor> getMisSeguidores() {
        return misSeguidores;
    }

    public void setMisSeguidores(List<Seguidor> misSeguidores) {
        this.misSeguidores = misSeguidores;
    }

    public List<UsuarioPublicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<UsuarioPublicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public List<ComentarioPublicacion> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioPublicacion> comentarios) {
        this.comentarios = comentarios;
    }

    public List<LikeComentario> getLikes() {
        return likes;
    }

    public void setLikes(List<LikeComentario> likes) {
        this.likes = likes;
    }


}
