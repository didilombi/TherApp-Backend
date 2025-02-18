package com.therapp.spring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.therapp.spring.servicios.LikePublicacionService;

@RestController
@RequestMapping("/api/publicaciones")
public class LikePublicacionController {

    private final LikePublicacionService likePublicacionService;

    @Autowired
    public LikePublicacionController(LikePublicacionService likePublicacionService) {
        this.likePublicacionService = likePublicacionService;
    }

    @PostMapping("/{publicacionId}/likes")
    public void darLike(@PathVariable Long publicacionId, @RequestParam Long usuarioId) {
        likePublicacionService.darLike(publicacionId, usuarioId);
    }

    @DeleteMapping("/{publicacionId}/likes")
    public void quitarLike(@PathVariable Long publicacionId, @RequestParam Long usuarioId) {
        likePublicacionService.quitarLike(publicacionId, usuarioId);
    }
}
