package com.therapp.spring;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.therapp.spring.modelo.CategoriaVideos;
import com.therapp.spring.modelo.ComentarioPublicacion;
import com.therapp.spring.modelo.ContenidoPublicacion;
import com.therapp.spring.modelo.Mensaje;
import com.therapp.spring.modelo.Publicacion;
import com.therapp.spring.modelo.Rol;
import com.therapp.spring.modelo.RolPublicacion;
import com.therapp.spring.modelo.Terapeuta;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.modelo.Video;
import com.therapp.spring.servicios.CategoriaService;
import com.therapp.spring.servicios.ComentarioPublicacionService;
import com.therapp.spring.servicios.LikeComentarioService;
import com.therapp.spring.servicios.LikePublicacionService;
import com.therapp.spring.servicios.MensajeService;
import com.therapp.spring.servicios.PublicacionService;
import com.therapp.spring.servicios.SeguidorService;
import com.therapp.spring.servicios.TerapeutaService;
import com.therapp.spring.servicios.UsuarioService;
import com.therapp.spring.servicios.VideoService;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class TherAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TherAppApplication.class, args);
    }

    @Bean
    @Transactional
    CommandLineRunner initData(UsuarioService usuarioService, VideoService videoService, CategoriaService categoriaService, PublicacionService publicacionService, LikePublicacionService likePublicacionService, ComentarioPublicacionService comentarioPublicacionService, LikeComentarioService likeComentarioService, SeguidorService seguidorService, TerapeutaService terapeutaService, MensajeService mensajeService, PasswordEncoder passwordEncoder) {
        return args -> {
            //Crear usuarios
            // Usuario usuario1 = new Usuario();
            // usuario1.setNombre("Luis");
            // usuario1.setUsername("luisterapeuta");
            // usuario1.setEmail("luis.terapeuta@example.com");
            // usuario1.setClave(passwordEncoder.encode("12345678"));
            // usuario1.setRol(Set.of(Rol.USER));
            // usuario1.setFotoPerfil("assets/terapeuta1.jpg");
            // usuario1.setFechaNacimiento(LocalDate.of(1990, 5, 20));
            // usuario1.setTelefono("123456789");
            // usuario1.setUbicacion("Elche");
            // usuarioService.save(usuario1);

            // Terapeuta terapeutaLuis = new Terapeuta();
            // terapeutaLuis.setUsuario(usuario1);
            // terapeutaLuis.setNColegiado("2578");
            // terapeutaLuis.setApellidos("Pérez López");
            // terapeutaLuis.setExperiencia("12 años");
            // terapeutaLuis.setEspecialidad("Depresión en personas mayores");
            // terapeutaLuis.setIdiomas("Español");
            // terapeutaLuis.setPrecio(48);
            // terapeutaLuis.setPremium(false);
            // terapeutaService.save(terapeutaLuis);

            // Usuario usuario2 = new Usuario();
            // usuario2.setNombre("Ana");
            // usuario2.setUsername("anaterapeuta");
            // usuario2.setEmail("ana.terapeuta@example.com");
            // usuario2.setClave(passwordEncoder.encode("12345678"));
            // usuario2.setRol(Set.of(Rol.USER));
            // usuario2.setFotoPerfil("assets/terapeuta3.jpg");
            // usuario2.setFechaNacimiento(LocalDate.of(1990, 5, 20));
            // usuario2.setTelefono("123456789");
            // usuario2.setUbicacion("Valencia");
            // usuarioService.save(usuario2);

            // // Crear terapeuta a partir del usuario Ana
            // Terapeuta terapeutaAna = new Terapeuta();
            // terapeutaAna.setUsuario(usuario2);
            // terapeutaAna.setNColegiado("1234562");
            // terapeutaAna.setApellidos("Colab Oradora");
            // terapeutaAna.setExperiencia("5 años");
            // terapeutaAna.setEspecialidad("Psicología");
            // terapeutaAna.setIdiomas("Español, Inglés");

            // // Guardar el terapeuta en la base de datos
            // terapeutaService.save(terapeutaAna);

            // // Crear contenidos multimedia
            // ContenidoPublicacion foto1 = new ContenidoPublicacion();
            // foto1.setTipo("foto");
            // foto1.setUrl("http://example.com/foto1.jpg");

            // ContenidoPublicacion foto2 = new ContenidoPublicacion();
            // foto2.setTipo("foto");
            // foto2.setUrl("http://example.com/foto2.jpg");

            // ContenidoPublicacion foto3 = new ContenidoPublicacion();
            // foto3.setTipo("foto");
            // foto3.setUrl("http://example.com/foto3.jpg");

            // ContenidoPublicacion foto4 = new ContenidoPublicacion();
            // foto4.setTipo("foto");
            // foto4.setUrl("http://example.com/foto4.jpg");

            // ContenidoPublicacion video = new ContenidoPublicacion();
            // video.setTipo("video");
            // video.setUrl("http://example.com/video.mp4");

            // // Crear una nueva publicación
            // Publicacion publicacion = new Publicacion();
            // publicacion.setTexto("Esta es una nueva publicación con fotos y video");
            // publicacion.setFechaPublicacion("2023-10-01");

            // // Guardar la publicación en la base de datos y asignar los contenidos multimedia
            // publicacion = publicacionService.crearPublicacion(usuario1, publicacion, Arrays.asList(foto1, foto2, foto3, foto4, video), RolPublicacion.AUTOR);

            // // Añadir un colaborador a la publicación
            // publicacionService.agregarColaborador(publicacion, usuario2);

            // // Dar like a la publicación
            // likePublicacionService.darLike(publicacion.getId(), usuario1.getId());
            // likePublicacionService.darLike(publicacion.getId(), usuario2.getId());

            // // Agregar comentarios a la publicación
            // ComentarioPublicacion comentario1 = comentarioPublicacionService.agregarComentario(publicacion.getId(), usuario1.getId(), "Este es un comentario de Carlos.");
            // ComentarioPublicacion comentario2 = comentarioPublicacionService.agregarComentario(publicacion.getId(), usuario2.getId(), "Este es un comentario de Ana.");

            // // Dar like a los comentarios
            // likeComentarioService.darLike(comentario1.getId(), usuario2.getId());
            // likeComentarioService.darLike(comentario2.getId(), usuario1.getId());

            // // Agregar respuestas a los comentarios
            // comentarioPublicacionService.agregarRespuesta(comentario1.getId(), usuario2.getId(), "Esta es una respuesta de Ana al comentario de Carlos.");
            // comentarioPublicacionService.agregarRespuesta(comentario2.getId(), usuario1.getId(), "Esta es una respuesta de Carlos al comentario de Ana.");

            // // Seguir usuarios
            // seguidorService.seguirUsuario(usuario1.getId(), usuario2.getId());
            // seguidorService.seguirUsuario(usuario2.getId(), usuario1.getId());

            // //Crear un Usuario
            // Usuario usuario = new Usuario();
            // usuario.setNombre("Juan");
            // usuario.setUsername("juanperez");
            // usuario.setEmail("juan.perez@example.com");
            // usuario.setClave(passwordEncoder.encode("12345678"));
            // usuario.setRol(Set.of(Rol.USER));
            // usuario.setFotoPerfil("assets/terapeuta1.jpg");
            // usuario.setFechaNacimiento(LocalDate.of(1990, 5, 20));
            // usuario.setTelefono("123456789");
            // usuario.setUbicacion("Estambul");

            // // Guardar el usuario en la BD
            // usuarioService.save(usuario);

            // // Crear un Terapeuta relacionado con el usuario
            // Terapeuta terapeuta = new Terapeuta();
            // terapeuta.setNColegiado("COLEGIADO-1234");
            // terapeuta.setApellidos("Pérez Gómez");
            // terapeuta.setExperiencia("5 años de experiencia en psicoterapia");
            // terapeuta.setEspecialidad("Psicología Clínica");
            // terapeuta.setIdiomas("Español, Inglés");
            // terapeuta.setUsuario(usuario);  // Relacionamos al usuario
            // terapeuta.setPrecio(50);
            // terapeuta.setPremium(true);
    

            // // Guardar el terapeuta en la BD
            // terapeutaService.save(terapeuta);

            // Usuario usuario4 = new Usuario();
            // usuario4.setNombre("Manolo");
            // usuario4.setUsername("manoloterapeuta");
            // usuario4.setEmail("manolo@terapeuta.com");
            // usuario4.setClave(passwordEncoder.encode("12345678"));
            // usuario4.setTelefono("334422999");
            // usuario4.setFotoPerfil("assets/terapeuta2.jpg");
            // usuario4.setRol(Set.of(Rol.USER));
            // usuario4.setFechaNacimiento(LocalDate.of(1955, 8, 20));
            // usuario4.setUbicacion("Ávila");

            // usuarioService.save(usuario4);

            // Terapeuta terapeuta2 = new Terapeuta();
            // terapeuta2.setNColegiado("COLEGIADO-1111");
            // terapeuta2.setApellidos("Ortiz Lucas");
            // terapeuta2.setExperiencia("20 años trabajando en las clínicas más reputadas de España");
            // terapeuta2.setEspecialidad("Trastornos de la conducta alimentaria, Psicología clínica");
            // terapeuta2.setIdiomas("Español, Francés");
            // terapeuta2.setUsuario(usuario4);
            // terapeuta2.setPrecio(65);
            // terapeuta2.setPremium(true);

            // terapeutaService.save(terapeuta2);

            // Usuario usuario5 = new Usuario();
            // usuario5.setNombre("Carla");
            // usuario5.setUsername("carla211");
            // usuario5.setEmail("carla@terapeuta.com");
            // usuario5.setClave(passwordEncoder.encode("12345678"));
            // usuario5.setTelefono("334422999");
            // usuario5.setFotoPerfil("assets/terapeuta3.jpg");
            // usuario5.setRol(Set.of(Rol.USER));
            // usuario5.setFechaNacimiento(LocalDate.of(1995, 8, 20));
            // usuario5.setUbicacion("Pamplona");
            // usuarioService.save(usuario5);

            // Terapeuta terapeuta3 = new Terapeuta();
            // terapeuta3.setNColegiado("COLEGIADO-7651");
            // terapeuta3.setApellidos("García López");
            // terapeuta3.setExperiencia("Recién graduada, 6 meses de prácticas en clínica");
            // terapeuta3.setEspecialidad("Dependencia emocional, maltrato en pareja, violencia de género");
            // terapeuta3.setIdiomas("Español, Inglés, Alemán");
            // terapeuta3.setUsuario(usuario5);
            // terapeuta3.setPrecio(40);
            // terapeuta3.setPremium(false);

            // terapeutaService.save(terapeuta3);

            // Usuario usuario6 = new Usuario();
            // usuario6.setNombre("Rosa");
            // usuario6.setUsername("rosaterapeuta");
            // usuario6.setEmail("rosa@terapeuta.com");
            // usuario6.setClave(passwordEncoder.encode("12345678"));
            // usuario6.setTelefono("334422999");
            // usuario6.setFotoPerfil("assets/terapeuta3.jpg");
            // usuario6.setRol(Set.of(Rol.USER));
            // usuario6.setFechaNacimiento(LocalDate.of(1997, 1, 2));
            // usuario6.setUbicacion("Alicante");
            // usuarioService.save(usuario6);

            // Terapeuta terapeuta4 = new Terapeuta();
            // terapeuta4.setNColegiado("COLEGIADO-4433");
            // terapeuta4.setApellidos("García Candela");
            // terapeuta4.setExperiencia("3 años de experiencia en clínica propia");
            // terapeuta4.setEspecialidad("Estrés en exámenes, ansiedad generalizada");
            // terapeuta4.setIdiomas("Español, Francés, Inglés");
            // terapeuta4.setUsuario(usuario6);
            // terapeuta4.setPrecio(50);
            // terapeuta4.setPremium(true);

            // terapeutaService.save(terapeuta4);

            // Usuario usuario7 = new Usuario();

            // usuario7.setNombre("Mariano");
            // usuario7.setUsername("marianoterapeuta");
            // usuario7.setEmail("mariano@terapeuta.com");
            // usuario7.setClave(passwordEncoder.encode("12345678"));
            // usuario7.setTelefono("334422999");
            // usuario7.setFotoPerfil("assets/terapeuta4.jpg");
            // usuario7.setRol(Set.of(Rol.USER));
            // usuario7.setFechaNacimiento(LocalDate.of(1955, 8, 20));
            // usuario7.setUbicacion("Coruña");
            // usuarioService.save(usuario7);

            // Terapeuta terapeuta5 = new Terapeuta();
            // terapeuta5.setNColegiado("COLEGIADO-0001");
            // terapeuta5.setApellidos("Roca Díaz");
            // terapeuta5.setExperiencia("15 años de psicólogo en el ejército");
            // terapeuta5.setEspecialidad("Estres post traumático, Despersonalización");
            // terapeuta5.setIdiomas("Español, Chino, Inglés");
            // terapeuta5.setUsuario(usuario7);
            // terapeuta5.setPrecio(55);
            // terapeuta5.setPremium(true);

            // terapeutaService.save(terapeuta5);
            // // Enviar un mensaje de prueba
            // // usuario1 (Carlos) envía un mensaje a "usuario" (Juan Pérez)
            // Mensaje mensaje = new Mensaje();
            // mensaje.setContenido("Hola, este es un mensaje de prueba.");
            // mensaje.setFechaEnvio(new Date());
            // mensaje.setVisto(false);

            // // Emisor = usuario1 (Carlos, ID=1)
            // mensaje.setEmisor(usuario1);
            // // Receptor = usuario (Juan Pérez, ID=?)
            // mensaje.setReceptor(usuario);

            // // Guardar el mensaje en la BD
            // mensajeService.save(mensaje);
            // // Guardar el mensaje en la BD
            // mensajeService.save(mensaje);
            // // Guardar el mensaje en la BD
            // mensajeService.save(mensaje);

            // Video video1 = new Video();
            // video1.setTitulo("Vídeo de prueba");
            // video1.setDescripcion("Un conejo");
            // video1.setFechaPublicacion(LocalDateTime.now());
            // video1.setUrl("https://www.w3schools.com/html/mov_bbb.mp4");
            // videoService.save(video1);

            // CategoriaVideos cat1 = new CategoriaVideos();
            // cat1.setNombre("Prueba");

            // categoriaService.save(cat1);

            // Set<CategoriaVideos> set1 = new HashSet<>();
            // set1.add(cat1);

            // Set<Video> set2 = new HashSet<>();
            // set2.add(video1);

            // video1.setCategorias(set1);
            // cat1.setVideos(set2);

            // // video1.setCategorias(new Set<CategoriaVideos> = );

            // CategoriaVideos cat1 = new CategoriaVideos();
            // cat1.setNombre("Prueba");

            // categoriaService.save(cat1);

            // Set<CategoriaVideos> set1 = new HashSet<>();
            // set1.add(cat1);

            // Set<Video> set2 = new HashSet<>();
            // set2.add(video1);

            // video1.setCategorias(set1);
            // cat1.setVideos(set2);

            // // video1.setCategorias(new Set<CategoriaVideos> = );


            // System.out.println("Datos de prueba insertados correctamente.");
        };
    }
}
