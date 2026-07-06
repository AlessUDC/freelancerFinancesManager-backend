package com.project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppAlertas {
    @Column(name = "alertas_renovaciones")
    private Boolean renovaciones7Dias = true;

    @Column(name = "alertas_inyectar_suscripciones")
    private Boolean inyectarSuscripciones = true;
}
