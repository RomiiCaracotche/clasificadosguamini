package com.clasificados.clasificadosGuamini.entities;

import com.clasificados.clasificadosGuamini.enums.TipoOperacion;
import com.clasificados.clasificadosGuamini.enums.TipoVivienda;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity 
public class Inmueble extends Aviso{

    @NotNull(message = "Este campo es obligatorio")
    @Column(name = "tipo_operacion", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoOperacion tipoOperacion;

    @NotNull(message = "Este campo es obligatorio")
    @Column(name = "tipo_vivienda", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoVivienda tipoVivienda;
    
    @NotNull(message = "Este campo es obligatorio")
    @Positive(message = "El valor debe ser mayor a cero")
    @Column(nullable = false)
    private Double precio;

    @NotNull(message = "Este campo es obligatorio")
    @Positive(message = "El valor debe ser mayor a cero")
    @Column(nullable = false)
    private Integer ambientes;

    @NotNull(message = "Este campo es obligatorio")
    @Positive(message = "El valor debe ser mayor a cero")
    @Column(nullable = false)
    private Integer habitaciones;

    @NotNull(message = "Este campo es obligatorio")
    @Column(nullable = false)
    private Boolean patio;

    @NotNull(message = "Este campo es obligatorio")
    @Positive(message = "El valor debe ser mayor a cero")
    @Column(nullable = false)
    private Integer banos;

    @NotNull(message = "Este campo es obligatorio")
    @Column(nullable = false)
    private Boolean mascotas;

    @NotNull(message = "Este campo es obligatorio")
    @PositiveOrZero(message = "El valor debe ser mayor o igual a cero")
    @Column(nullable = false)
    private Double expensas;

    @NotNull(message = "Este campo es obligatorio")
    @Embedded 
    private Direccion direccion;

    public Inmueble() {
        super();
    }

    public Inmueble(String titulo, String descripcion, String localidad, Usuario anunciante, TipoOperacion tipoOperacion, TipoVivienda tipoVivienda, Double precio, Integer ambientes, Integer habitaciones, Boolean patio, Integer banos, Boolean mascotas, Double expensas, Direccion direccion) {
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
