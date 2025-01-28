package com.therapp.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.therapp.spring.servicios.TerapeutaService;
import com.therapp.spring.servicios.UsuarioService;

@SpringBootApplication
public class SaludMentalAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaludMentalAppApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(UsuarioService usuarioService,TerapeutaService terapeutaService) {
		
	}

}
