package com.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SuscripcionRequest {

    @NotBlank
    private String servicio;

    @NotNull
    private BigDecimal monto;

    @NotBlank
    private String moneda;

    private String ciclo;

    private LocalDate proximaRenovacion;

    private String status;

    @NotNull
    private Long usuarioId;
}
