package com.project.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private java.time.LocalDate fechaNacimiento;
    private String email;
    private String password;
}
