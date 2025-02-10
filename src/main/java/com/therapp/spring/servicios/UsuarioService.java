package com.therapp.spring.servicios;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import com.therapp.spring.modelo.Usuario;
import com.therapp.spring.repositorios.UsuarioRepository;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepositorio;
   // 1️⃣ Ruta base donde se almacenarán los archivos
   private static final Path rootLocation = Paths.get("uploads");

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public List<Usuario> findAll() {
        return usuarioRepositorio.findAll();
    }

    public Usuario findById(Integer id) {
        return usuarioRepositorio.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    //este metodo se encarga de guardar una lista de usuarios predefinidos en la base de datos
    public void saveAll(Iterable<Usuario> usuarios) {

        usuarioRepositorio.saveAll(usuarios);
    }

    // public void delete(Usuario u) {
    //     terapeutaService.deleteByUsuario(u);
    //     usuarioRepositorio.delete(u);
    // }

     // 2️⃣ Método para guardar foto: 
     public void guardarFoto(Integer id, MultipartFile file) throws Exception {
        Usuario usuario = usuarioRepositorio.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + id));

        // 3️⃣ Creamos la carpeta "uploads" si no existe
        if (!Files.exists(rootLocation)) {
            Files.createDirectories(rootLocation);
        }

        // 4️⃣ Generamos un nombre de archivo
        String filename = "usuario_" + id + "_" + file.getOriginalFilename();

        // 5️⃣ Resolvemos la ruta final y normalizamos
        Path destinationFile = rootLocation.resolve(filename).normalize();

        // 6️⃣ Copiamos el contenido del MultipartFile (InputStream) al archivo
        Files.copy(
            file.getInputStream(),
            destinationFile,
            StandardCopyOption.REPLACE_EXISTING
        );

        // 7️⃣ Guardamos la ruta en la DB, p. ej. "uploads/usuario_5_foto.jpg"
        // Esto asume que en la entidad Usuario existe un campo "fotoRuta"
        usuario.setFotoPerfil(destinationFile.toString());
        usuarioRepositorio.save(usuario);
    }

    public void deleteById(Integer id) {
        usuarioRepositorio.deleteById(id);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepositorio.findByEmail(email);
        
    }
}
