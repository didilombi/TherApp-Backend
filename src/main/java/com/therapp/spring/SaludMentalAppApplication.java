package com.therapp.spring;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
<<<<<<< HEAD
import org.springframework.transaction.annotation.Transactional;

import com.therapp.spring.modelo.Publicacion;
=======
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
>>>>>>> a77ffc3 (implementacion de los DTO de terapeuta y usuario)
import com.therapp.spring.modelo.Rol;
import com.therapp.spring.modelo.RolPublicacion;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.servicios.PublicacionService;
import com.therapp.spring.servicios.UsuarioService;

@SpringBootApplication
public class SaludMentalAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaludMentalAppApplication.class, args);
    }

    @Bean
    @Transactional
    CommandLineRunner initData(UsuarioService usuarioService, PublicacionService publicacionService) {
        return args -> {
            // Crear un nuevo usuario
            Usuario usuario1 = new Usuario(
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

            Usuario usuario2 = new Usuario(
                "Ana", 
                "AnaColab", 
                "ana@colab.com", 
                "password", 
                "Sin Imagen", 
                Rol.USUARIO, 
                "12345678X", 
                LocalDate.of(1990, 8, 20), 
                "987654321", 
                "Barcelona", 
                "Biografia de Ana"
            );

<<<<<<< HEAD
            // Guardar los usuarios en la base de datos
            usuarioService.save(usuario1);
            usuarioService.save(usuario2);

            // Crear una nueva publicación
            Publicacion publicacion = new Publicacion();
            publicacion.setTexto("Esta es una nueva publicación");
            publicacion.setFechaPublicacion("2023-10-01");
=======
			usuarioService.saveAll(usuarios); //Guardar todos los usuarios en la base de datos

			
			/* 
				LOS CAMBIOS QUE HIZO SERGIO
			
			
			Usuario usuarionuevo = usuarioService.findByEmail("paco@gmail.com");
			usuarioService.transform(usuarionuevo);
			Terapeuta t = new Terapeuta(usuarionuevo,"numero de colegiado nuevo","Apellido nuevo", "Experiencia en terapia nueva", "Especialidad X nueva", "Español, Inglés nuevo");
			usuarioService.delete(usuarionuevo);
			usuarioService.save(t);
			*/
>>>>>>> a77ffc3 (implementacion de los DTO de terapeuta y usuario)

            // Guardar la publicación en la base de datos
            publicacion = publicacionService.guardarPublicacion(publicacion);

            // Guardar la relación con el usuario como autor
            publicacionService.crearPublicacion(usuario1, publicacion, RolPublicacion.AUTOR);

            // Añadir un colaborador a la publicación
            publicacionService.crearPublicacion(usuario2, publicacion, RolPublicacion.COLABORADOR);
        };
    }
}
