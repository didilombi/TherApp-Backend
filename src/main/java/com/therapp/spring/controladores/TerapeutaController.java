package com.therapp.spring.controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.therapp.spring.dto.TerapeutaMostrarDTO;
import com.therapp.spring.modelo.Terapeuta;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.servicios.TerapeutaService;
import com.therapp.spring.servicios.UsuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@CrossOrigin(origins = "http://localhost:4200") // Permite solicitudes desde Angular
@RestController
@RequestMapping("/api/terapeutas")
@Tag(name = "Terapeutas") // Anotación para detectar el tag de terapeutas
public class TerapeutaController {

    private final TerapeutaService terapeutaService;
    private final UsuarioService usuarioService;

    @Autowired
    public TerapeutaController(TerapeutaService terapeutaService, UsuarioService usuarioService) {
        this.terapeutaService = terapeutaService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Terapeuta> getAllTerapeutas() {
        return terapeutaService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Terapeuta> getTerapeutaById(@PathVariable Long id) {
        return terapeutaService.findById(id);
    }
    @PostMapping
    public Terapeuta addTerapeuta(@RequestBody Terapeuta terapeuta) {
        Usuario usuario = usuarioService.findById(terapeuta.getUsuario().getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        terapeuta.setUsuario(usuario);
        return terapeutaService.save(terapeuta);
    }
    @DeleteMapping("/{id}")
    public void borrarTerapeuta(@PathVariable Long id) {
        terapeutaService.deleteById(id);
    }

    @GetMapping("/terapeutas")
    public List<TerapeutaMostrarDTO> getTerapeutasDTO() {
        List<Terapeuta> terapeutas = getAllTerapeutas();
        List<TerapeutaMostrarDTO> terapeutasParaPasar = new ArrayList<>();

        for(int i =0; i < terapeutas.size(); i++){
            terapeutasParaPasar.add(new TerapeutaMostrarDTO(terapeutas.get(i)));
        }
        return terapeutasParaPasar;
    }

    @PostMapping("/cambiarPremium")
    public void cambiarPremium(@RequestBody String email){
        Optional<Usuario> u = usuarioService.findByEmail(email);
        u.ifPresent(usuario -> {
                Optional<Terapeuta> t = terapeutaService.findByUsuario(usuario);
                t.ifPresent(terapeuta -> {
                    if(terapeuta.isPremium()){
                        terapeuta.setPremium(false);
                    }
                    else{
                        terapeuta.setPremium(true);
                    }
                    terapeutaService.save(terapeuta);
                });
        });
    }

    @PostMapping("/hacerpremium")
    public void hacerPremium(@RequestBody String email){
        Optional<Usuario> u = usuarioService.findByEmail(email);
        u.ifPresent(usuario -> {
            Optional<Terapeuta> t = terapeutaService.findByUsuario(usuario);
            t.ifPresent(terapeuta -> {
                terapeuta.setPremium(true);
                terapeutaService.save(terapeuta);
            });
        });
    }
    
    @PostMapping("/cancelarPremium")
    public void cancelarPremium(@RequestBody String email) {
        Optional<Usuario> u = usuarioService.findByEmail(email);
        u.ifPresent(usuario -> {
            Optional<Terapeuta> t = terapeutaService.findByUsuario(usuario);
            t.ifPresent(terapeuta -> {
                terapeuta.setPremium(false);
                terapeutaService.save(terapeuta);
            });
        });
    }

    @GetMapping("/esPremium/{usuarioId}")
    public ResponseEntity<Boolean> esPremium(@PathVariable Long usuarioId) {
        Optional<Usuario> usuario = usuarioService.findById(usuarioId);
        if (usuario.isPresent()) {
            Optional<Terapeuta> terapeuta = terapeutaService.findByUsuario(usuario.get());
            if (terapeuta.isPresent()) {
                return ResponseEntity.ok(terapeuta.get().isPremium());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }
}
