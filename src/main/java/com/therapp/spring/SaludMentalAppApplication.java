package com.therapp.spring;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.therapp.spring.modelo.Rol;
import com.therapp.spring.modelo.Terapeuta;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.servicios.TerapeutaService;
import com.therapp.spring.servicios.UsuarioService;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class SaludMentalAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaludMentalAppApplication.class, args);
    }

    @Bean
    @Transactional
    CommandLineRunner initData(UsuarioService usuarioService, TerapeutaService terapeutaService) {
        return args -> {
            // Crear un nuevo usuario
            Usuario nuevoUsuario = new Usuario(
                "Ana", 
                "AnaNormal", 
                "ana@gmail.com", 
                "1234", 
                "Sin Imagen", 
                Rol.TERAPEUTA, 
                "12345678D", 
                LocalDate.of(1990, 1, 1), 
                "987654321", 
                "Valencia", 
                "Biografia de Ana"
            );

            // Guardar el nuevo usuario en la base de datos
            usuarioService.save(nuevoUsuario);

            // Crear un nuevo terapeuta asociado al usuario
            Terapeuta nuevoTerapeuta = new Terapeuta();
            nuevoTerapeuta.setUsuario(nuevoUsuario);
            nuevoTerapeuta.setnColegiado("12345");
            nuevoTerapeuta.setApellidos("Perez");
            nuevoTerapeuta.setExperiencia("5 años");
            nuevoTerapeuta.setEspecialidad("Psicología");
            nuevoTerapeuta.setIdiomas("Español, Inglés");

            // Guardar el nuevo terapeuta en la base de datos
            terapeutaService.save(nuevoTerapeuta);
        };
    }
}
