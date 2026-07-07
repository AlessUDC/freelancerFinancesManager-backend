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
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private java.time.LocalDate fechaNacimiento;
    private String cuentaBancaria;
    private String email;
    private String rol;
    
    private String monedaBase;
    private String zonaHoraria;
    private PerfilFiscal perfilFiscal;
    private AppConfig appConfig;
}
