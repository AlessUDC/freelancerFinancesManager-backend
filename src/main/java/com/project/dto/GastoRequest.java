package com.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class GastoRequest {

    @NotBlank
    private String concepto;

    @NotNull
    private BigDecimal monto;

    @NotBlank
    private String moneda;

    private String categoria;

    private Boolean esDeducible;

    private Boolean esRecurrente;

    private Integer cantidad;

    private LocalDate fecha;

    @NotNull
    private Long usuarioId;
}
