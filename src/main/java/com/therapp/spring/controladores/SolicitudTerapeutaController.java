package com.therapp.spring.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
    public void CrearSolicitud(@RequestBody SolicitudTerapeuta solicitud) {
        Optional<Usuario> u = usuarioRepository.findByEmail(solicitud.getEmail());
        
        u.ifPresent(solicitud::setUsuario);
        
        solicitudTerapeutaService.save(solicitud);
    }
    
    @GetMapping("/recogersolicitudes")
    public List<SolicitudTerapeuta> listarSolicitudes(){
    	
    	List<SolicitudTerapeuta> lista = solicitudTerapeutaService.findAll();
    	for(int i=0; i<lista.size(); i++)
    	{
    		System.out.println(lista.get(i).getEmail());
    	}
    		
    	return solicitudTerapeutaService.findAll();
    }
    
    @PostMapping("/aprobarsolicitud")
    public void AprobarSolicitud(@RequestBody String email) {
    	
    	SolicitudTerapeuta solicitud = solicitudTerapeutaService.findByEmail(email);
    	if(solicitud != null) {
    		Optional<Usuario> u = usuarioRepository.findByEmail(email);
    	}
    }

    
    }

