package com.project.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Utilidades estáticas para acceder al usuario autenticado en el contexto de seguridad.
 * Evita duplicar la misma lógica de cast en cada controlador y servicio.
 */
public final class SecurityUtils {

    private SecurityUtils() {
        // Clase de utilidad — no instanciar
    }

    /**
     * Retorna el ID del usuario autenticado extraído del JWT.
     *
     * @throws AccessDeniedException si no hay sesión autenticada activa (no debería
     *                               ocurrir en endpoints protegidos por Spring Security).
     */
    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails)) {
            throw new AccessDeniedException("No hay una sesión autenticada activa");
        }
        return ((CustomUserDetails) authentication.getPrincipal()).getUserId();
    }
}
