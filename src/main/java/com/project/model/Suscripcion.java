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
@Table(name = "suscripciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Suscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "servicio")
    private String servicio;

    @NotNull
    @Column(name = "monto")
    private BigDecimal monto;

    @NotBlank
    @Column(name = "moneda", length = 3)
    private String moneda = "USD";

    @Column(name = "ciclo")
    private String ciclo = "MENSUAL";

    @Column(name = "proxima_renovacion")
    private LocalDate proximaRenovacion;

    @Column(name = "status")
    private String status = "ACTIVA";

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
