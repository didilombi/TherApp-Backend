package com.therapp.spring.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.therapp.spring.modelo.ContenidoPublicacion;
import com.therapp.spring.modelo.Publicacion;
import com.therapp.spring.modelo.RolPublicacion;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.servicios.PublicacionService;
import com.therapp.spring.servicios.UsuarioService;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    private final PublicacionService publicacionService;
    private final UsuarioService usuarioService;

    @Autowired
    public PublicacionController(PublicacionService publicacionService, UsuarioService usuarioService) {
        this.publicacionService = publicacionService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Publicacion> getAllPublicaciones() {
        return publicacionService.findAll();
    }

    @GetMapping("/{id}")
    public Publicacion getPublicacionById(@PathVariable Integer id) {
        return publicacionService.findById(id);
    }

    @PostMapping
    public Publicacion crearPublicacion(@RequestBody Publicacion publicacion, @RequestParam Integer usuarioId, @RequestBody List<ContenidoPublicacion> contenidos) {
        Usuario usuario = usuarioService.findById(usuarioId);
        return publicacionService.crearPublicacion(usuario, publicacion, contenidos, RolPublicacion.AUTOR);
    }

    @PutMapping("/{id}")
    public Publicacion actualizarPublicacion(@PathVariable Integer id, @RequestBody Publicacion publicacion) {
        publicacion.setId(id);
        return publicacionService.update(publicacion);
    }

    @DeleteMapping("/{id}")
    public void borrarPublicacion(@PathVariable Integer id) {
        publicacionService.deleteById(id);
    }

    @PostMapping("/{id}/colaboradores")
    public void agregarColaborador(@PathVariable Integer id, @RequestParam Integer usuarioId) {
        Publicacion publicacion = publicacionService.findById(id);
        Usuario usuario = usuarioService.findById(usuarioId);
        publicacionService.agregarColaborador(publicacion, usuario);
    }

    @DeleteMapping("/{id}/colaboradores")
    public void eliminarColaborador(@PathVariable Integer id, @RequestParam Integer usuarioId) {
        Publicacion publicacion = publicacionService.findById(id);
        Usuario usuario = usuarioService.findById(usuarioId);
        publicacionService.eliminarColaborador(publicacion, usuario);
    }

    @PostMapping("/{id}/contenidos")
    public void agregarContenido(@PathVariable Integer id, @RequestBody ContenidoPublicacion contenido) {
        Publicacion publicacion = publicacionService.findById(id);
        publicacionService.agregarContenido(publicacion, contenido);
    }
}
