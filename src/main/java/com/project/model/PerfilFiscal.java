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
public class PerfilFiscal {
    @Column(name = "razon_social")
    private String razonSocial;

    @Column(name = "ruc_nif")
    private String rucNif;

    @Column(name = "direccion_fiscal", columnDefinition = "TEXT")
    private String direccionFiscal;
}
