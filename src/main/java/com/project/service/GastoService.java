package com.project.service;

import com.project.dto.GastoRequest;
import com.project.model.Gasto;
import com.project.model.Usuario;
import com.project.repository.GastoRepository;
import com.project.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GastoService {

    private final GastoRepository gastoRepository;
    private final UsuarioRepository usuarioRepository;

    public List<Gasto> getAllByUsuarioId(Long usuarioId) {
        return gastoRepository.findByUsuarioId(usuarioId);
    }

    public Gasto create(GastoRequest request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + request.getUsuarioId()));

        Gasto gasto = new Gasto();
        gasto.setConcepto(request.getConcepto());
        gasto.setMonto(request.getMonto());
        gasto.setMoneda(request.getMoneda());
        gasto.setCategoria(request.getCategoria());
        gasto.setEsDeducible(request.getEsDeducible() != null ? request.getEsDeducible() : false);
        gasto.setEsRecurrente(request.getEsRecurrente() != null ? request.getEsRecurrente() : false);
        gasto.setCantidad(request.getCantidad() != null ? request.getCantidad() : 1);
        gasto.setFecha(request.getFecha());
        gasto.setUsuario(usuario);

        return gastoRepository.save(gasto);
    }

    public Gasto update(Long id, GastoRequest request, Long requestingUserId) {
        Gasto gasto = gastoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gasto no encontrado con ID: " + id));

        // Verificar que el gasto pertenece al usuario autenticado
        if (!gasto.getUsuario().getId().equals(requestingUserId)) {
            throw new AccessDeniedException("No tienes permiso para modificar este gasto");
        }

        gasto.setConcepto(request.getConcepto());
        gasto.setMonto(request.getMonto());
        gasto.setMoneda(request.getMoneda());
        gasto.setCategoria(request.getCategoria());
        gasto.setEsDeducible(request.getEsDeducible() != null ? request.getEsDeducible() : false);
        gasto.setEsRecurrente(request.getEsRecurrente() != null ? request.getEsRecurrente() : false);
        gasto.setCantidad(request.getCantidad() != null ? request.getCantidad() : 1);
        gasto.setFecha(request.getFecha());

        return gastoRepository.save(gasto);
    }

    public void delete(Long id, Long requestingUserId) {
        Gasto gasto = gastoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gasto no encontrado con ID: " + id));

        // Verificar que el gasto pertenece al usuario autenticado
        if (!gasto.getUsuario().getId().equals(requestingUserId)) {
            throw new AccessDeniedException("No tienes permiso para eliminar este gasto");
        }

        gastoRepository.delete(gasto);
    }
}

