package com.clasificados.clasificadosGuamini.dtos.response;

import java.time.LocalDateTime;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import com.clasificados.clasificadosGuamini.enums.DisponibilidadHoraria;
import com.clasificados.clasificadosGuamini.enums.TipoEmpleo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmpleoResponseDto extends AvisoResponseDto {

    @NotNull(message = "Este campo es obligatorio")
    private TipoEmpleo tipoEmpleo;
    
    @NotBlank(message = "Este campo es obligatorio")
    private String rubro; 

    @NotNull(message = "Este campo es obligatorio")
    private DisponibilidadHoraria disponibilidadHoraria; 
    
    private String requisitos;
    
    
    public EmpleoResponseDto() {
        super();
    }

    public EmpleoResponseDto(Long id, String titulo, String descripcion, LocalDateTime fecha, String localidad, Usuario anunciante, TipoEmpleo tipoEmpleo, String rubro, DisponibilidadHoraria disponibilidadHoraria, String requisitos) {
        super(id, titulo, descripcion, fecha, localidad, anunciante);
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
