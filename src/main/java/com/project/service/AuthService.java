package com.project.service;

import com.project.dto.LoginRequest;
import com.project.dto.LoginResponse;
import com.project.dto.RegisterRequest;
import com.project.model.Usuario;
import com.project.repository.UsuarioRepository;
import com.project.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public Usuario registrar(RegisterRequest request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));

        return usuarioRepository.save(usuario);
    }

    public LoginResponse login(LoginRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(request.getEmail());

        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Credenciales inválidas");
        }

        Usuario usuario = usuarioOpt.get();
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        String token = jwtTokenProvider.generateToken(usuario.getEmail(), usuario.getId());

        return new LoginResponse(token, usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getRol(), usuario.getMonedaBase(), usuario.getZonaHoraria(), usuario.getPerfilFiscal(), usuario.getAppConfig());
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @jakarta.transaction.Transactional
    public Usuario actualizar(Long id, com.project.dto.UpdateUserRequest request) {
        Usuario usuario = obtenerPorId(id);

        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        
        if (request.getMonedaBase() != null) usuario.setMonedaBase(request.getMonedaBase());
        if (request.getZonaHoraria() != null) usuario.setZonaHoraria(request.getZonaHoraria());
        if (request.getPerfilFiscal() != null) usuario.setPerfilFiscal(request.getPerfilFiscal());
        if (request.getAppConfig() != null) usuario.setAppConfig(request.getAppConfig());

        if (request.getPassword() != null && !request.getPassword().trim().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        return usuarioRepository.save(usuario);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
