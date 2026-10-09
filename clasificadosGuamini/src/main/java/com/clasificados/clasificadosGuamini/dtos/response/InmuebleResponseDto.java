package com.clasificados.clasificadosGuamini.dtos.response;

import java.time.LocalDateTime;
import com.clasificados.clasificadosGuamini.entities.Direccion;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import com.clasificados.clasificadosGuamini.enums.TipoOperacion;
import com.clasificados.clasificadosGuamini.enums.TipoVivienda;

public class InmuebleResponseDto extends AvisoResponseDto {

    private TipoOperacion tipoOperacion;

    private TipoVivienda tipoVivienda;
    
    private Double precio;

    private Integer ambientes;

    private Integer habitaciones;

    private Boolean patio;

    private Integer banos;

    private Boolean mascotas;

    private Double expensas;

    private Direccion direccion;

    
    public InmuebleResponseDto() {
       super();
    }

    public InmuebleResponseDto(Long id, String titulo, String descripcion, LocalDateTime fecha, String localidad, Usuario anunciante, TipoOperacion tipoOperacion, TipoVivienda tipoVivienda, Double precio, Integer ambientes, Integer habitaciones, Boolean patio, Integer banos, Boolean mascotas, Double expensas, Direccion direccion) {
        super(id, titulo, descripcion, fecha, localidad, anunciante);
        this.tipoOperacion = tipoOperacion;
        this.tipoVivienda = tipoVivienda;
        this.precio = precio;
        this.ambientes = ambientes;
        this.habitaciones = habitaciones;
        this.patio = patio;
        this.banos = banos;
        this.mascotas = mascotas;
        this.expensas = expensas;
        this.direccion = direccion;
    }


    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public TipoVivienda getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(TipoVivienda tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(Integer ambientes) {
        this.ambientes = ambientes;
    }

    public Integer getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(Integer habitaciones) {
        this.habitaciones = habitaciones;
    }

    public Boolean getPatio() {
        return patio;
    }

    public void setPatio(Boolean patio) {
        this.patio = patio;
    }

    public Integer getBanos() {
        return banos;
    }

    public void setBanos(Integer banos) {
        this.banos = banos;
    }

    public Boolean getMascotas() {
        return mascotas;
    }

    public void setMascotas(Boolean mascotas) {
        this.mascotas = mascotas;
    }

    public Double getExpensas() {
        return expensas;
    }

    public void setExpensas(Double expensas) {
        this.expensas = expensas;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

}
