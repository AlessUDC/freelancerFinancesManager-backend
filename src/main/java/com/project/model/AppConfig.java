package com.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppConfig {
    @Column(name = "porcentaje_impuesto")
    private BigDecimal taxCow = BigDecimal.ZERO;

    @Column(name = "meta_ingreso_mensual")
    private BigDecimal metaIngresoMensual = BigDecimal.ZERO;

    @Column(name = "limite_gastos")
    private BigDecimal limiteGastos = BigDecimal.ZERO;

    @Embedded
    private AppAlertas alertas = new AppAlertas();
}
