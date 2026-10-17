package com.clasificados.clasificadosGuamini.entities;

import com.clasificados.clasificadosGuamini.enums.DisponibilidadHoraria;
import com.clasificados.clasificadosGuamini.enums.TipoEmpleo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity 
@Table(name ="empleos")
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


    public Empleo() {
        super();
    }

    public Empleo(String titulo, String descripcion, String localidad, Usuario anunciante, TipoEmpleo tipoEmpleo, String rubro, DisponibilidadHoraria disponibilidadHoraria, String requisitos) {
        super(titulo, descripcion, localidad, anunciante);
        this.tipoEmpleo = tipoEmpleo;
        this.rubro = rubro;
        this.disponibilidadHoraria = disponibilidadHoraria;
        this.requisitos = requisitos;
    }

    public TipoEmpleo getTipoEmpleo() {
        return tipoEmpleo;
    }

    public void setTipoEmpleo(TipoEmpleo tipoEmpleo) {
        this.tipoEmpleo = tipoEmpleo;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public DisponibilidadHoraria getDisponibilidadHoraria() {
        return disponibilidadHoraria;
    }

    public void setDisponibilidadHoraria(DisponibilidadHoraria disponibilidadHoraria) {
        this.disponibilidadHoraria = disponibilidadHoraria;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

}
