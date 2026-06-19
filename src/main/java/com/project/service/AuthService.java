package com.project.service;

import com.project.dto.LoginRequest;
import com.project.dto.RegisterRequest;
import com.project.model.Usuario;
import com.project.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrar(RegisterRequest request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        // Nota: En un entorno real, aquí usaríamos BCrypt para hashear la contraseña
        usuario.setPassword(request.getPassword()); 
        
        return usuarioRepository.save(usuario);
    }

    public Usuario login(LoginRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(request.getEmail());

        if (usuarioOpt.isPresent() && usuarioOpt.get().getPassword().equals(request.getPassword())) {
            return usuarioOpt.get();
        }

        throw new RuntimeException("Credenciales inválidas");
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @jakarta.transaction.Transactional
    public Usuario actualizar(Long id, RegisterRequest request) {
        Usuario usuario = obtenerPorId(id);
        
        System.out.println("Actualizando usuario ID: " + id);
        System.out.println("Nuevo nombre: " + request.getNombre());
        
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        
        if (request.getPassword() != null && !request.getPassword().trim().isEmpty()) {
            usuario.setPassword(request.getPassword());
        }
        
        return usuarioRepository.save(usuario);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
