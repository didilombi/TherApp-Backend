package com.therapp.spring.controladores;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.therapp.spring.modelo.Organizacion;
import com.therapp.spring.modelo.SolicitudOrganizacion;
import com.therapp.spring.modelo.SolicitudTerapeuta;
import com.therapp.spring.modelo.Terapeuta;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.repositorios.UsuarioRepository;
import com.therapp.spring.servicios.OrganizacionesService;
import com.therapp.spring.servicios.SolicitudOrganizacionService;

@RestController
@RequestMapping("/api/organizaciones")
public class OrganizacionesController {

    private final OrganizacionesService organizacionesService;

    @Autowired
    public OrganizacionesController(OrganizacionesService organizacionesService) {
        this.organizacionesService = organizacionesService;
    }
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired
    SolicitudOrganizacionService solicitudOrganizacionService;

    @GetMapping
    public List<Organizacion> getAllOrganizaciones() {
        return organizacionesService.findAll();
    }

    @PostMapping
    public Organizacion crearOrganizacion(@RequestBody Organizacion organizacion) {
        return organizacionesService.save(organizacion);
    }

    @DeleteMapping("/{id}")
    public void borrarOrganizacion(@PathVariable Long id) {
        organizacionesService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Organizacion getOrganizacionById(@PathVariable Long id) {
        return organizacionesService.findById(id);
    }
    
    @PostMapping("solicitudorganizacion")
    public void CrearSolicitud(@RequestBody SolicitudOrganizacion solicitud) {
        if(solicitudOrganizacionService.findByCif(solicitud.getCif()) == null){
            Optional<Usuario> u = usuarioRepository.findByEmail(solicitud.getEmail());
        
            u.ifPresent(solicitud::setUsuario);
        
            solicitudOrganizacionService.save(solicitud);
        }
        else{
            System.out.println("Ya has enviado una solicitud");
        }
        
    }

    @GetMapping("/recogersolicitudes")
    public List<SolicitudOrganizacion> listarSolicitudes(){
    		
    	return solicitudOrganizacionService.findAll();
    }

    @PostMapping("/aprobarsolicitud")
    public void AprobarSolicitud(@RequestBody Map<String, String> datos) {

        System.out.println(datos.get("email"));
    	
    	SolicitudOrganizacion solicitud = solicitudOrganizacionService.findByCif(datos.get("cif"));
    	if(solicitud != null) {
    		Optional<Usuario> u = usuarioRepository.findByEmail(datos.get("email"));
            u.ifPresent(usuario -> {
                Organizacion o = new Organizacion();
                o.setUsuario(usuario);
                o.setCif(solicitud.getCif());
                o.setDireccion(solicitud.getDireccion());
                o.setTelefono(solicitud.getTelefono());
                o.setEmail(solicitud.getEmail());
                o.setWeb(solicitud.getWeb());
                organizacionesService.save(o);
            }
            );
            solicitudOrganizacionService.delete(solicitud);
    	}

        
    }

    @PostMapping("/rechazarsolicitud")
    public void RechazarSolicitud(@RequestBody String cif) {
    	
    	SolicitudOrganizacion solicitud = solicitudOrganizacionService.findByCif(cif);
    	if(solicitud != null) {
                solicitudOrganizacionService.delete(solicitud);
            }
    	}
    
}
