package com.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ingresos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "proyecto_nombre")
    private String proyectoNombre;

    @NotNull
    @Column(name = "monto_bruto")
    private BigDecimal montoBruto;

    @Column(name = "retencion")
    private BigDecimal retencion = BigDecimal.ZERO;

    @NotNull
    @Column(name = "monto_neto")
    private BigDecimal montoNeto;

    @NotBlank
    @Column(name = "moneda", length = 3)
    private String moneda = "USD";

    @Column(name = "status")
    private String status = "PENDIENTE";

    @Column(name = "fecha")
    private LocalDate fecha = LocalDate.now();

    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;

    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
