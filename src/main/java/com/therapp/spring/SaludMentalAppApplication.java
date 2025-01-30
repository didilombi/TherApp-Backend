package com.therapp.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import com.therapp.spring.modelo.Rol;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.servicios.TerapeutaService;
import com.therapp.spring.servicios.UsuarioService;

import jakarta.transaction.Transactional;

import com.therapp.spring.modelo.Terapeuta;

@SpringBootApplication
public class SaludMentalAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaludMentalAppApplication.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner initData(UsuarioService usuarioService,TerapeutaService terapeutaService) {
		return args -> {

			// Lista de usuarios iniciales que quiero guardar en la base de datos
			List<Usuario> usuarios = Arrays.asList(
				new Terapeuta("Juan", "JuanIncognito","juan@gmail.com","1234","Sin Imagen",Rol.TERAPEUTA,"12345678A",LocalDate.of(1989, 12, 12), "132456789", "Alicante","Biografia Usuario", "123ABC","Apellidos usuario", "Experiencia en terapia", "Especialidad X", "Español, Inglés"),
				new Usuario("Maria", "MariaIncognito","maria@gmail.com","1234","Sin Imagen",
							Rol.ADMIN,"12345678B",LocalDate.of(1989, 12, 12),
							"132456789","Alicante","Biografia Usuario"),
				new Usuario("Paco", "PacoIncognito","paco@gmail.com","1234","Sin Imagen",
							Rol.ORGANIZACION,"12345678C",LocalDate.of(1989, 12, 12),
							"132456789","Alicante","Biografia Usuario")
			);
	
			// Guardar todos los usuarios en la base de datos
			usuarioService.saveAll(usuarios);
			};
		};

	}
