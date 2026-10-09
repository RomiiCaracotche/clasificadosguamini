package com.clasificados.clasificadosGuamini.dtos.request;

import com.clasificados.clasificadosGuamini.entities.Direccion;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import com.clasificados.clasificadosGuamini.enums.TipoOperacion;
import com.clasificados.clasificadosGuamini.enums.TipoVivienda;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class InmuebleRequestDto extends AvisoRequestDto {

    @NotNull(message = "Este campo es obligatorio")
    private TipoOperacion tipoOperacion;

    @NotNull(message = "Este campo es obligatorio")
    private TipoVivienda tipoVivienda;
    
    @NotNull(message = "Este campo es obligatorio")
    @Positive(message = "El valor debe ser mayor a cero")
    private Double precio;

    @NotNull(message = "Este campo es obligatorio")
    @Positive(message = "El valor debe ser mayor a cero")
    private Integer ambientes;

    @NotNull(message = "Este campo es obligatorio")
    @Positive(message = "El valor debe ser mayor a cero")
    private Integer habitaciones;

    @NotNull(message = "Este campo es obligatorio")
    private Boolean patio;

    @NotNull(message = "Este campo es obligatorio")
    @Positive(message = "El valor debe ser mayor a cero")
    private Integer banos;

    @NotNull(message = "Este campo es obligatorio")
    private Boolean mascotas;

    @NotNull(message = "Este campo es obligatorio")
    @PositiveOrZero(message = "El valor debe ser mayor o igual a cero")
    private Double expensas;

    @NotNull(message = "Este campo es obligatorio")
    private Direccion direccion;

    
    public InmuebleRequestDto() {
       super();
    }

    public InmuebleRequestDto(String titulo, String descripcion, String localidad, Usuario anunciante, TipoOperacion tipoOperacion, TipoVivienda tipoVivienda, Double precio, Integer ambientes, Integer habitaciones, Boolean patio, Integer banos, Boolean mascotas, Double expensas, Direccion direccion) {
        super(titulo, descripcion, localidad, anunciante);
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
