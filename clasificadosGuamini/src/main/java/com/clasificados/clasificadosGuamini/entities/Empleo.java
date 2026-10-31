package com.clasificados.clasificadosGuamini.entities;

import com.clasificados.clasificadosGuamini.enums.DisponibilidadHoraria;
import com.clasificados.clasificadosGuamini.enums.TipoEmpleo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "empleos") 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empleo extends Aviso {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoEmpleo tipoEmpleo;
    
    @Column(nullable = false)
    private String rubro; 

    @Column(nullable = false, length = 500)
    @Enumerated(EnumType.STRING)
    private DisponibilidadHoraria disponibilidadHoraria; 
    
    @Column(columnDefinition = "TEXT")
    private String requisitos;

}
