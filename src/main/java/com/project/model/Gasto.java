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
@Table(name = "gastos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "concepto")
    private String concepto;

    @NotNull
    @Column(name = "monto")
    private BigDecimal monto;

    @NotBlank
    @Column(name = "moneda", length = 3)
    private String moneda = "USD";

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "es_deducible")
    private Boolean esDeducible = false;

    @Column(name = "es_recurrente")
    private Boolean esRecurrente = false;

    @Column(name = "cantidad")
    private Integer cantidad = 1;

    @Column(name = "fecha")
    private LocalDate fecha = LocalDate.now();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
