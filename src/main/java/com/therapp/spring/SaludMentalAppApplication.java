package com.therapp.spring;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.therapp.spring.modelo.Organizaciones;
import com.therapp.spring.modelo.Rol;
import com.therapp.spring.modelo.Terapeuta;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.servicios.TerapeutaService;
import com.therapp.spring.servicios.UsuarioService;
import com.therapp.spring.servicios.OrganizacionesService;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class SaludMentalAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaludMentalAppApplication.class, args);
    }

    @Bean
    @Transactional
    CommandLineRunner initData(UsuarioService usuarioService, TerapeutaService terapeutaService, OrganizacionesService organizacionesService) {
        return args -> {
            // Crear un nuevo usuario
            Usuario nuevoUsuario = new Usuario(
                "Carlos", 
                "CarlosOrg", 
                "carlos@org.com", 
                "password", 
                "Sin Imagen", 
                Rol.ORGANIZACION, 
                "87654321X", 
                LocalDate.of(1985, 5, 15), 
                "123456789", 
                "Madrid", 
                "Biografia de Carlos"
            );

            // Guardar el nuevo usuario en la base de datos
            usuarioService.save(nuevoUsuario);

            // Crear una nueva organización asociada al usuario
            Organizaciones nuevaOrganizacion = new Organizaciones();
            nuevaOrganizacion.setUsuario(nuevoUsuario);
            nuevaOrganizacion.setCif("B12345678");
            nuevaOrganizacion.setDireccion("Calle Falsa 123");
            nuevaOrganizacion.setTelefono("987654321");
            nuevaOrganizacion.setEmail("contacto@org.com");
            nuevaOrganizacion.setDescripcion("Descripción de la organización");
            nuevaOrganizacion.setSitioweb("www.org.com");

            // Guardar la nueva organización en la base de datos
            // Asumiendo que tienes un servicio para organizaciones
            organizacionesService.save(nuevaOrganizacion);
        };
    }
}
