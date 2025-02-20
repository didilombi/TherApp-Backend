package com.therapp.spring.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.therapp.spring.modelo.SolicitudTerapeuta;
import com.therapp.spring.modelo.Terapeuta;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.repositorios.TerapeutaRepository;
import com.therapp.spring.repositorios.UsuarioRepository;
import com.therapp.spring.servicios.SolicitudTerapeutaService;
import com.therapp.spring.servicios.TerapeutaService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@CrossOrigin(origins = "http://localhost:4200") // Permite solicitudes desde Angular
@RestController
@RequestMapping("/api/solicitudes")
@Tag(name = "Terapeutas")
public class SolicitudTerapeutaController {

    @Autowired
    SolicitudTerapeutaService solicitudTerapeutaService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TerapeutaService terapeutaService;

    @PostMapping("/enviarsolicitud")
    public void CrearSolicitud(@RequestBody SolicitudTerapeuta solicitud) {
        if(solicitudTerapeutaService.findByEmail(solicitud.getEmail()) == null){
            Optional<Usuario> u = usuarioRepository.findByEmail(solicitud.getEmail());
        
            u.ifPresent(solicitud::setUsuario);
        
            solicitudTerapeutaService.save(solicitud);
        }
        else{
            System.out.println("Ya has enviado una solicitud");
        }
        
    }
    
    @GetMapping("/recogersolicitudes")
    public List<SolicitudTerapeuta> listarSolicitudes(){

    	return solicitudTerapeutaService.findAll();
    }
    
    @PostMapping("/aprobarsolicitud")
    public void AprobarSolicitud(@RequestBody String email) {
        SolicitudTerapeuta solicitud = solicitudTerapeutaService.findByEmail(email);
        if (solicitud != null) {
            Optional<Usuario> u = usuarioRepository.findByEmail(email);
            u.ifPresent(usuario -> {
                Terapeuta t = new Terapeuta();
                t.setUsuario(usuario);
                t.setApellidos(solicitud.getApellidos());
                t.setEspecialidad(solicitud.getEspecialidad());
                t.setExperiencia(solicitud.getExperiencia());
                t.setNColegiado(solicitud.getNColegiado());
                t.setPrecio(solicitud.getPrecio());
                t.setPremium(false);
                terapeutaService.save(t);

                // Eliminar la solicitud después de aprobarla
                solicitudTerapeutaService.delete(solicitud);
            });
        }
    }

    @PostMapping("/rechazarsolicitud")
    public void RechazarSolicitud(@RequestBody String email) {
        SolicitudTerapeuta solicitud = solicitudTerapeutaService.findByEmail(email);
        if (solicitud != null) {
            // Eliminar la solicitud después de rechazarla
            solicitudTerapeutaService.delete(solicitud);
        }
    }
}




