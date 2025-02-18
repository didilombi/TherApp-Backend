package com.therapp.spring.controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.therapp.spring.modelo.SolicitudTerapeuta;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.repositorios.UsuarioRepository;
import com.therapp.spring.servicios.SolicitudTerapeutaService;

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

    @PostMapping("/enviarsolicitud")
    public void CrearSolicitud(@RequestBody String email, String apellidos, String nColegiado, String experiencia, String especialidad,
    String idiomas, int precio){

        SolicitudTerapeuta solicitud = new SolicitudTerapeuta();
        solicitud.setApellidos(apellidos);
        solicitud.setNColegiado(nColegiado);
        solicitud.setExperiencia(experiencia);
        solicitud.setEspecialidad(especialidad);
        solicitud.setIdiomas(idiomas);
        solicitud.setPrecio(precio);
        Optional<Usuario> u = usuarioRepository.findByEmail(email);
        u.ifPresent(user -> {
            solicitud.setUsuario(user);
        });
        solicitudTerapeutaService.save(solicitud);
    }
    

    
    }

