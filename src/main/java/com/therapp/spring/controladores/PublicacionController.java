package com.therapp.spring.controladores;

import java.util.ArrayList;
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
import org.springframework.web.multipart.MultipartFile;

import com.therapp.spring.dto.PublicacionDTO;
import com.therapp.spring.modelo.Publicacion;
import com.therapp.spring.modelo.RolPublicacion;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.servicios.ContenidoPublicacionService;
import com.therapp.spring.servicios.PublicacionService;
import com.therapp.spring.servicios.SeguidorService;
import com.therapp.spring.servicios.UsuarioPublicacionService;
import com.therapp.spring.servicios.UsuarioService;


@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    private final PublicacionService publicacionService;
    private final UsuarioService usuarioService;
    private final ContenidoPublicacionService contenidoPublicacionService;

    @Autowired
    public PublicacionController(PublicacionService publicacionService, UsuarioService usuarioService, ContenidoPublicacionService contenidoPublicacionService) {
        this.publicacionService = publicacionService;
        this.usuarioService = usuarioService;
        this.contenidoPublicacionService = contenidoPublicacionService;
    }
    
    @Autowired
    UsuarioPublicacionService usuarioPublicacionService;

    @Autowired
    SeguidorService seguidorService;
    
    @GetMapping
    public List<Publicacion> getAllPublicaciones() {
        return publicacionService.findAll();
    }

    @GetMapping("/{id}")
    public Publicacion getPublicacionById(@PathVariable Long id) {
        return publicacionService.findById(id);
    }

    @PostMapping
    public Publicacion crearPublicacion(@RequestParam Long usuarioId, @RequestParam("texto") String texto, @RequestParam(value = "archivos", required = false) List<MultipartFile> archivos) {
        Usuario usuario = usuarioService.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Publicacion publicacion = new Publicacion();
        publicacion.setTexto(texto);
        Publicacion nuevaPublicacion = publicacionService.crearPublicacion(usuario, publicacion, List.of(), RolPublicacion.AUTOR);

        if (archivos != null) {
            for (MultipartFile archivo : archivos) {
                contenidoPublicacionService.saveFile(archivo, nuevaPublicacion.getId());
            }
        }

        return nuevaPublicacion;
    }

    @PutMapping("/{id}")
    public Publicacion actualizarPublicacion(@PathVariable Long id, @RequestBody Publicacion publicacion) {
        publicacion.setId(id);
        return publicacionService.update(publicacion);
    }

    @DeleteMapping("/{id}")
    public void borrarPublicacion(@PathVariable Long id) {
        publicacionService.deleteById(id);
    }

    @PostMapping("/{id}/colaboradores")
    public void agregarColaborador(@PathVariable Long id, @RequestParam Long usuarioId) {
        Publicacion publicacion = publicacionService.findById(id);
        Usuario usuario = usuarioService.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario not found"));
        publicacionService.agregarColaborador(publicacion, usuario);
    }

    @DeleteMapping("/{id}/colaboradores")
    public void eliminarColaborador(@PathVariable Long id, @RequestParam Long usuarioId) {
        Publicacion publicacion = publicacionService.findById(id);
        Usuario usuario = usuarioService.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario not found"));
        publicacionService.eliminarColaborador(publicacion, usuario);
    }

    @PostMapping("/{id}/contenidos")
    public void agregarContenido(@PathVariable Long id, @RequestParam("archivo") MultipartFile archivo) {
        Publicacion publicacion = publicacionService.findById(id);
        contenidoPublicacionService.saveFile(archivo, publicacion.getId());
    }

    @DeleteMapping("/{id}/contenidos/{contenidoId}")
    public void eliminarContenido(@PathVariable Long id, @PathVariable Long contenidoId) {
        Publicacion publicacion = publicacionService.findById(id);
        publicacionService.eliminarContenido(publicacion, contenidoId);
    }

    @GetMapping("/buscarpublicaciones/{id}")
    public List<PublicacionDTO> buscarPublicacionesPorUsuario(@PathVariable("id") Long id){
     
        List<Long> listaid = usuarioPublicacionService.obtenerPublicacionIdsPorUsuario(id);
        System.out.println("ID recibido en el backend: " + id);
        List<PublicacionDTO> lista = new ArrayList<>();
        
        for(int i = 0; i < listaid.size(); i++) {
            PublicacionDTO publi = new PublicacionDTO();
            publi.setTexto(publicacionService.findById(listaid.get(i)).getTexto());
            publi.setFechaPublicacion(publicacionService.findById(listaid.get(i)).getFechaPublicacion());
            publi.setTipo(publicacionService.findTipo(listaid.get(i)));
            publi.setUrl(publicacionService.findUrl(listaid.get(i)));
            lista.add(publi);
        }
        
//        for (Long pubId : listaid) {
//            lista.add(publicacionService.findById(pubId));
//        }
        
        return lista;
    }

    @GetMapping("/buscarpublicacionesdeseguidos/{id}")
    public List<Publicacion> buscarPublicacionesDeSeguidos(@PathVariable("id") Long id){
        List<Publicacion> lista = new ArrayList<>();

        List<Long> idSeguidos = seguidorService.buscarIdSeguidos(id);

        for(int i = 0; i < idSeguidos.size(); i++) {
            Publicacion publi = new Publicacion();
            publi.setTexto(publicacionService.findById(idSeguidos.get(i)).getTexto());
            publi.setFechaPublicacion(publicacionService.findById(idSeguidos.get(i)).getFechaPublicacion());
            lista.add(publi);
        }

        return lista;
    }
    
}
