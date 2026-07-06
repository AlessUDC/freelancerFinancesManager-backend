package com.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class IngresoRequest {

    @NotBlank
    private String proyectoNombre;

    @NotNull
    private BigDecimal montoBruto;

    private BigDecimal retencion;

    @NotNull
    private BigDecimal montoNeto;

    @NotBlank
    private String moneda;

    private String status;

    private LocalDate fecha;
    private LocalDate fechaEmision;
    private LocalDate fechaVencimiento;

    @NotNull
    private Long usuarioId;
}
