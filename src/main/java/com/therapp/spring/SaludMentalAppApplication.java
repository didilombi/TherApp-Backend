package com.therapp.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import com.therapp.spring.modelo.Rol;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.servicios.TerapeutaService;
import com.therapp.spring.servicios.UsuarioService;
import com.therapp.spring.modelo.Terapeuta;

@SpringBootApplication
public class SaludMentalAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaludMentalAppApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(UsuarioService usuarioService,TerapeutaService terapeutaService) {
		return args -> {

			// Lista de usuarios iniciales que quiero guardar en la base de datos
			List<Usuario> usuarios = Arrays.asList(
				new Terapeuta("Juan", "JuanIncognito","juan@gmail.com","1234","Sin Imagen",Rol.TERAPEUTA,"12345678A",LocalDate.of(1989, 12, 12),"123ABC", "Apellidos", "Experiencia en terapia", "Especialidad X", "Español, Inglés","132456789","Alicante","Biografia Usuario"),
				new Usuario("Maria", "MariaIncognito","maria@gmail.com","1234","Sin Imagen",
							Rol.ADMIN,"12345678B",LocalDate.of(1989, 12, 12),
							"132456789","Alicante","Biografia Usuario")
			);
	
			// Guardar todos los usuarios en la base de datos
			usuarioService.saveAll(usuarios);

			// // Filtrar los usuarios con rol TERAPEUTA y los convertimos a Terapeuta
			// List<Terapeuta> terapeutas = usuarios.stream() //stream() filtra y transforma la lista, y me evita usar bucles, ademas de evita crear una lista nueva de terapeutas donde tendria que guardar los datos. de esta forma se ahorra codigo y memoria
			// .filter(u -> u.getRol() == Rol.TERAPEUTA) // Filtrar solo terapeutas de la lista de usuarios de arriba
			// .map(u -> new Terapeuta(
			// 	u.getNombre(), u.getNombreUsuario(), u.getEmail(), u.getClave(), u.getFotoPerfil(),
			// 	u.getRol(), u.getDni(), u.getFechaNacimiento(), u.getTelefono(), u.getUbicacion(),
			// 	u.getBiografia(),
			// 	"123ABC", "Apellidos", "Experiencia en terapia", "Especialidad X", "Español, Inglés"
			// ))
			// .toList(); // y aqui lo volvemos a transformar en una lista para poder iterar o manejarlo
	
			// terapeutaService.saveAll(terapeutas);
			};
		};

	}
