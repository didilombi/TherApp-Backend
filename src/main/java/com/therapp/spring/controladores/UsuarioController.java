package com.therapp.spring.controladores;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;
// import com.therapp.spring.modelo.Usuario;
// import com.therapp.spring.repositorios.UsuarioRepository;

// import java.util.List;

/*@RestController
@RequestMapping("/usuarios")*/
public class UsuarioController {
    /* 
    
    ESTA IMPLEMENTACION ES PARA TENER UN BOCETO DE COMO SE DEBERIA DE HACER



    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> TodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario modificarUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setNombre(usuarioDetails.getNombre());
            usuario.setEmail(usuarioDetails.getEmail());
            
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void borrarUsuario(@PathVariable Integer id) {
        usuarioRepository.deleteById(id);
    }*/
}
