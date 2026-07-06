package com.project.dto;

import com.project.model.AppConfig;
import com.project.model.PerfilFiscal;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    @NotBlank
    private String nombre;

    @NotBlank
    @Email
    private String email;

    private String password; // Optional
    private String monedaBase;
    private String zonaHoraria;
    
    private PerfilFiscal perfilFiscal;
    private AppConfig appConfig;
}
