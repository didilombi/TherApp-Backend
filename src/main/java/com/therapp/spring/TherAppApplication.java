package com.therapp.spring;

import java.time.LocalDate;
import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Date;
import com.therapp.spring.modelo.ComentarioPublicacion;
import com.therapp.spring.modelo.ContenidoPublicacion;
import com.therapp.spring.modelo.Mensaje;
import com.therapp.spring.modelo.Publicacion;
import com.therapp.spring.modelo.Rol;
import com.therapp.spring.modelo.RolPublicacion;
import com.therapp.spring.modelo.Terapeuta;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.servicios.ComentarioPublicacionService;
import com.therapp.spring.servicios.LikeComentarioService;
import com.therapp.spring.servicios.LikePublicacionService;
import com.therapp.spring.servicios.MensajeService;
import com.therapp.spring.servicios.PublicacionService;
import com.therapp.spring.servicios.SeguidorService;
import com.therapp.spring.servicios.UsuarioService;
import com.therapp.spring.servicios.TerapeutaService;

@SpringBootApplication
public class TherAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TherAppApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(UsuarioService usuarioService, PublicacionService publicacionService, LikePublicacionService likePublicacionService, ComentarioPublicacionService comentarioPublicacionService, LikeComentarioService likeComentarioService, SeguidorService seguidorService, TerapeutaService terapeutaService, MensajeService mensajeService) {
        return args -> {
            // Crear usuarios
            Usuario usuario1 = new Usuario("Carlos", "CarlosOrg", "carlos@org.com", "password", "Sin Imagen", Rol.ORGANIZACION, "87654321X", LocalDate.of(1985, 5, 15), "123456789", "Madrid");
            Usuario usuario2 = new Usuario("Ana", "AnaColab", "ana@colab.com", "password", "Sin Imagen", Rol.USUARIO, "12345678X", LocalDate.of(1990, 8, 20), "987654321", "Barcelona");

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

            // Seguir usuarios
            seguidorService.seguirUsuario(usuario1.getId(), usuario2.getId());
            seguidorService.seguirUsuario(usuario2.getId(), usuario1.getId());

            // Crear un Usuario
            Usuario usuario = new Usuario();
            usuario.setNombre("Juan Pérez");
            usuario.setNombreUsuario("juanperez");
            usuario.setEmail("juan.perez@example.com");
            usuario.setClave("123456");
            usuario.setRol(Rol.USUARIO);
            usuario.setDni("12345678A");
            usuario.setFechaNacimiento(LocalDate.of(1990, 5, 20));
            usuario.setTelefono("123456789");
            usuario.setUbicacion("Ciudad Ejemplo");
            usuario.setBiografia("Usuario de prueba para chat");

            // Guardar el usuario en la BD
            usuarioService.save(usuario);

            // Crear un Terapeuta relacionado con el usuario
            Terapeuta terapeuta = new Terapeuta();
            terapeuta.setNColegiado("COLEGIADO-1234");
            terapeuta.setApellidos("Gómez López");
            terapeuta.setExperiencia("5 años de experiencia en psicoterapia");
            terapeuta.setEspecialidad("Psicología Clínica");
            terapeuta.setIdiomas("Español, Inglés");
            terapeuta.setUsuario(usuario);  // Relacionamos al usuario
            terapeuta.setPrecio(50);
            terapeuta.setPremium(true);
    

            // Guardar el terapeuta en la BD
            terapeutaService.save(terapeuta);

            Usuario usuario4 = new Usuario("Manolo", "Manoloeldelbombo", "manolo@bombo.com", "12345678", "Sin Imagen", Rol.USUARIO, "12345678X", LocalDate.of(1955, 8, 20), "987654321", "Madrid");

            usuarioService.save(usuario4);

            Terapeuta terapeuta2 = new Terapeuta();
            terapeuta2.setNColegiado("COLEGIADO-1111");
            terapeuta2.setApellidos("Cabeza de Huevo");
            terapeuta2.setExperiencia("20 años trabajando en las clínicas más reputadas de España");
            terapeuta2.setEspecialidad("Trastornos de la conducta alimentaria, Psicología clínica");
            terapeuta2.setIdiomas("Español, Francés");
            terapeuta2.setUsuario(usuario4);
            terapeuta2.setPrecio(65);
            terapeuta2.setPremium(true);

            terapeutaService.save(terapeuta2);

            Usuario usuario5 = new Usuario("Carla", "carla211", "carla211@gmail.com", "12345678", "Sin Imagen", Rol.USUARIO, "12345678X", LocalDate.of(1999, 8, 20), "987654321", "Bilbao");

            usuarioService.save(usuario4);

            Terapeuta terapeuta3 = new Terapeuta();
            terapeuta3.setNColegiado("COLEGIADO-7651");
            terapeuta3.setApellidos("García López");
            terapeuta3.setExperiencia("Recién graduada, 6 meses de prácticas en clínica");
            terapeuta3.setEspecialidad("Dependencia emocional, maltrato en pareja, violencia de género");
            terapeuta3.setIdiomas("Español, Inglés, Alemán");
            terapeuta3.setUsuario(usuario5);
            terapeuta3.setPrecio(40);
            terapeuta3.setPremium(false);

            terapeutaService.save(terapeuta3);

            // Enviar un mensaje de prueba
            // usuario1 (Carlos) envía un mensaje a "usuario" (Juan Pérez)
            Mensaje mensaje = new Mensaje();
            mensaje.setContenido("Hola, este es un mensaje de prueba.");
            mensaje.setFechaEnvio(new Date());
            mensaje.setVisto(false);

            // Emisor = usuario1 (Carlos, ID=1)
            mensaje.setEmisor(usuario1);
            // Receptor = usuario (Juan Pérez, ID=?)
            mensaje.setReceptor(usuario);

            // Guardar el mensaje en la BD
            mensajeService.save(mensaje);

            System.out.println("Datos de prueba insertados correctamente.");
        };
    }
}

