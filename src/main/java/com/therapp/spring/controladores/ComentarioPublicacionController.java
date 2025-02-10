package com.therapp.spring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.therapp.spring.modelo.ComentarioPublicacion;
import com.therapp.spring.servicios.ComentarioPublicacionService;

@RestController
@RequestMapping("/api/publicaciones")
public class ComentarioPublicacionController {

    private final ComentarioPublicacionService comentarioPublicacionService;

    @Autowired
    public ComentarioPublicacionController(ComentarioPublicacionService comentarioPublicacionService) {
        this.comentarioPublicacionService = comentarioPublicacionService;
    }

    @PostMapping("/{publicacionId}/comentarios")
    public ComentarioPublicacion agregarComentario(@PathVariable Long publicacionId, @RequestParam Long usuarioId, @RequestBody String contenido) {
        return comentarioPublicacionService.agregarComentario(publicacionId, usuarioId, contenido);
    }

    @PostMapping("/comentarios/{comentarioPadreId}/respuestas")
    public ComentarioPublicacion agregarRespuesta(@PathVariable Long comentarioPadreId, @RequestParam Long usuarioId, @RequestBody String contenido) {
        return comentarioPublicacionService.agregarRespuesta(comentarioPadreId, usuarioId, contenido);
    }

    @DeleteMapping("/comentarios/{comentarioId}")
    public void eliminarComentario(@PathVariable Long comentarioId) {
        comentarioPublicacionService.eliminarComentario(comentarioId);
    }
}
