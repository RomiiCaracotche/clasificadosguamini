package com.clasificados.clasificadosGuamini.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Embeddable 
public class Direccion {

    @NotBlank(message = "Este campo es obligatorio")
    @Column(nullable = false)
    private String ciudad;

    @NotBlank(message = "Este campo es obligatorio")
    @Column(nullable = false)
    private String calle;

    @NotBlank(message = "Este campo es obligatorio")
    @Column(nullable = false)
    private String numero;

    private String piso;

    private String departamento;

    @NotNull(message = "Este campo es obligatorio")
    @Column(name="codigo_postal", nullable = false)
    private Integer codigoPostal;

}
