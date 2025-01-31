package com.therapp.spring;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import com.therapp.spring.modelo.ComentarioPublicacion;
import com.therapp.spring.modelo.ContenidoPublicacion;
import com.therapp.spring.modelo.Publicacion;
import com.therapp.spring.modelo.Rol;
import com.therapp.spring.modelo.RolPublicacion;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.servicios.ComentarioPublicacionService;
import com.therapp.spring.servicios.LikeComentarioService;
import com.therapp.spring.servicios.LikePublicacionService;
import com.therapp.spring.servicios.PublicacionService;
import com.therapp.spring.servicios.UsuarioService;

@SpringBootApplication
public class TherAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TherAppApplication.class, args);
    }

    @Bean
    @Transactional
    CommandLineRunner initData(UsuarioService usuarioService, PublicacionService publicacionService, LikePublicacionService likePublicacionService, ComentarioPublicacionService comentarioPublicacionService, LikeComentarioService likeComentarioService) {
        return args -> {
            // Crear usuarios
            Usuario usuario1 = new Usuario("Carlos", "CarlosOrg", "carlos@org.com", "password", "Sin Imagen", Rol.ORGANIZACION, "87654321X", LocalDate.of(1985, 5, 15), "123456789", "Madrid", "Biografia de Carlos");
            Usuario usuario2 = new Usuario("Ana", "AnaColab", "ana@colab.com", "password", "Sin Imagen", Rol.USUARIO, "12345678X", LocalDate.of(1990, 8, 20), "987654321", "Barcelona", "Biografia de Ana");

            // Guardar los usuarios en la base de datos
            usuarioService.save(usuario1);
            usuarioService.save(usuario2);

            // Crear contenidos multimedia
            ContenidoPublicacion foto1 = new ContenidoPublicacion();
            foto1.setTipo("foto");
            foto1.setUrl("http://example.com/foto1.jpg");

            ContenidoPublicacion foto2 = new ContenidoPublicacion();
            foto2.setTipo("foto");
            foto2.setUrl("http://example.com/foto2.jpg");

            ContenidoPublicacion foto3 = new ContenidoPublicacion();
            foto3.setTipo("foto");
            foto3.setUrl("http://example.com/foto3.jpg");

            ContenidoPublicacion foto4 = new ContenidoPublicacion();
            foto4.setTipo("foto");
            foto4.setUrl("http://example.com/foto4.jpg");

            ContenidoPublicacion video = new ContenidoPublicacion();
            video.setTipo("video");
            video.setUrl("http://example.com/video.mp4");

            // Crear una nueva publicación
            Publicacion publicacion = new Publicacion();
            publicacion.setTexto("Esta es una nueva publicación con fotos y video");
            publicacion.setFechaPublicacion("2023-10-01");

            // Guardar la publicación en la base de datos y asignar los contenidos multimedia
            publicacion = publicacionService.crearPublicacion(usuario1, publicacion, Arrays.asList(foto1, foto2, foto3, foto4, video), RolPublicacion.AUTOR);

            // Añadir un colaborador a la publicación
            publicacionService.agregarColaborador(publicacion, usuario2);

            // Dar like a la publicación
            likePublicacionService.darLike(publicacion.getId(), usuario1.getId());
            likePublicacionService.darLike(publicacion.getId(), usuario2.getId());

            // Agregar comentarios a la publicación
            ComentarioPublicacion comentario1 = comentarioPublicacionService.agregarComentario(publicacion.getId(), usuario1.getId(), "Este es un comentario de Carlos.");
            ComentarioPublicacion comentario2 = comentarioPublicacionService.agregarComentario(publicacion.getId(), usuario2.getId(), "Este es un comentario de Ana.");

            // Dar like a los comentarios
            likeComentarioService.darLike(comentario1.getId(), usuario2.getId());
            likeComentarioService.darLike(comentario2.getId(), usuario1.getId());

            // Agregar respuestas a los comentarios
            comentarioPublicacionService.agregarRespuesta(comentario1.getId(), usuario2.getId(), "Esta es una respuesta de Ana al comentario de Carlos.");
            comentarioPublicacionService.agregarRespuesta(comentario2.getId(), usuario1.getId(), "Esta es una respuesta de Carlos al comentario de Ana.");
        };
    }
}
