package com.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombres;

    @NotBlank
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @NotBlank
    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "fecha_nacimiento")
    private java.time.LocalDate fechaNacimiento;

    @Column(name = "cuenta_bancaria")
    private String cuentaBancaria;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @com.fasterxml.jackson.annotation.JsonProperty(access = com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String rol = "USER";

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "moneda_base")
    private String monedaBase = "USD";

    @Column(name = "zona_horaria")
    private String zonaHoraria = "America/Lima";

    @Embedded
    private PerfilFiscal perfilFiscal;

    @Embedded
    private AppConfig appConfig;
}
