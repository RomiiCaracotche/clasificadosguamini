package com.clasificados.clasificadosGuamini.entities;

import com.clasificados.clasificadosGuamini.enums.TipoOperacion;
import com.clasificados.clasificadosGuamini.enums.TipoVivienda;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name ="inmuebles")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Inmueble extends Aviso {

    @Column(name = "tipo_operacion", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoOperacion tipoOperacion;

    @Column(name = "tipo_vivienda", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoVivienda tipoVivienda;
    
    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Integer ambientes;

    @Column(nullable = false)
    private Integer habitaciones;

    @Column(nullable = false)
    private Boolean patio;

    @Column(nullable = false)
    private Integer banos;

    @Column(nullable = false)
    private Boolean mascotas;

    @Column(nullable = false)
    private Double expensas;

    @Embedded 
    private Direccion direccion;
    
}
