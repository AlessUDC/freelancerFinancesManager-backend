package com.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.project.model.AppConfig;
import com.project.model.PerfilFiscal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private Long id;
    private String nombre;
    private String email;
    private String rol;
    
    private String monedaBase;
    private String zonaHoraria;
    private PerfilFiscal perfilFiscal;
    private AppConfig appConfig;
}
