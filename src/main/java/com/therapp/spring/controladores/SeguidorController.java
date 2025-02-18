package com.therapp.spring.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.therapp.spring.servicios.SeguidorService;

@RestController
@RequestMapping("/api/usuarios")
public class SeguidorController {

    @Autowired
    private final SeguidorService seguidorService;

    public SeguidorController(SeguidorService seguidorService) {
        this.seguidorService = seguidorService;
    }

    @PostMapping("/{seguidoId}/seguir")
    public void seguirUsuario(@PathVariable Long seguidoId, @RequestParam Long seguidorId) {
        seguidorService.seguirUsuario(seguidorId, seguidoId);
    }

    @DeleteMapping("/{seguidoId}/dejarDeSeguir")
    public void dejarDeSeguirUsuario(@PathVariable Long seguidoId, @RequestParam Long seguidorId) {
        seguidorService.dejarDeSeguirUsuario(seguidorId, seguidoId);
    }

    @GetMapping("/{seguidoId}/{usuarioId}")
    public Boolean estaSiguiendo(@PathVariable Long usuarioId, @PathVariable Long seguidoId) {
        return seguidorService.estaSiguiendo(usuarioId, seguidoId);
    }
    

}
