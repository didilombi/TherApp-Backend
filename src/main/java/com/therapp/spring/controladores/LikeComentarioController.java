package com.therapp.spring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.therapp.spring.servicios.LikeComentarioService;

@RestController
@RequestMapping("/api/comentarios")
public class LikeComentarioController {

    private final LikeComentarioService likeComentarioService;

    @Autowired
    public LikeComentarioController(LikeComentarioService likeComentarioService) {
        this.likeComentarioService = likeComentarioService;
    }

    @PostMapping("/{comentarioId}/likes")
    public void darLike(@PathVariable Integer comentarioId, @RequestParam Integer usuarioId) {
        likeComentarioService.darLike(comentarioId, usuarioId);
    }

    @DeleteMapping("/{comentarioId}/likes")
    public void quitarLike(@PathVariable Integer comentarioId, @RequestParam Integer usuarioId) {
        likeComentarioService.quitarLike(comentarioId, usuarioId);
    }
}
