package com.project.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Implementación de UserDetails que añade el campo {@code userId} extraído del claim JWT.
 * Al almacenarlo en el principal del SecurityContextHolder, cualquier capa de la aplicación
 * puede obtener el ID del usuario autenticado sin necesidad de ir a la base de datos ni
 * confiar en parámetros enviados por el cliente.
 */
public class CustomUserDetails implements UserDetails {

    private final Long userId;
    private final String email;
    private final String password;

    public CustomUserDetails(Long userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    /** ID del usuario tal como está almacenado en la base de datos y en el claim JWT. */
    public Long getUserId() {
        return userId;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
