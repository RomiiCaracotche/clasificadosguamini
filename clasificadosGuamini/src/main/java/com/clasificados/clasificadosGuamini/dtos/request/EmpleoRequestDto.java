package com.clasificados.clasificadosGuamini.dtos.request;

import com.clasificados.clasificadosGuamini.enums.DisponibilidadHoraria;
import com.clasificados.clasificadosGuamini.enums.TipoEmpleo;

public class EmpleoRequestDto extends AvisoRequestDto {

    private TipoEmpleo tipoEmpleo;
    
    private String rubro; 

    private DisponibilidadHoraria disponibilidadHoraria; 
    
    private String requisitos;


    public EmpleoRequestDto(String titulo, String descripcion, String localidad, Long id_Anunciante) {
        super(titulo, descripcion, localidad, id_Anunciante);
    }

    public EmpleoRequestDto(String titulo, String descripcion, String localidad, Long id_Anunciante, TipoEmpleo tipoEmpleo, String rubro, DisponibilidadHoraria disponibilidadHoraria, String requisitos) {
        super(titulo, descripcion, localidad, id_Anunciante);
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
