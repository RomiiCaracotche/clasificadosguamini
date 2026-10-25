package com.clasificados.clasificadosGuamini.entities;

import com.clasificados.clasificadosGuamini.enums.DisponibilidadHoraria;
import com.clasificados.clasificadosGuamini.enums.TipoEmpleo;
import jakarta.persistence.Column;
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
@Table(name ="empleos")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Empleo extends Aviso {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoEmpleo tipoEmpleo;
    
    @Column(nullable = false)
    private String rubro; 

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DisponibilidadHoraria disponibilidadHoraria; 
    
    @Column(columnDefinition = "TEXT")
    private String requisitos;

}
