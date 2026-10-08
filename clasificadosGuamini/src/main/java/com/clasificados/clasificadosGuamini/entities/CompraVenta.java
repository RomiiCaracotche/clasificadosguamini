package com.clasificados.clasificadosGuamini.entities;

import com.clasificados.clasificadosGuamini.enums.Categoria;
import com.clasificados.clasificadosGuamini.enums.EstadoProducto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity 
public class CompraVenta extends Aviso{

    @NotNull(message = "Este campo es obligatorio")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @NotNull(message = "Este campo es obligatorio")
    @Positive(message = "El valor debe ser mayor a cero") 
    @Column(nullable = false)
    private Double precio;
    
    @NotNull(message = "Este campo es obligatorio")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoProducto estado;
    
    private String marca;
    
    private String modelo;

    private String color;

    public CompraVenta() {
        super();
    }

    public CompraVenta(String titulo, String descripcion, String localidad, Usuario anunciante, Categoria categoria, Double precio, EstadoProducto estado, String marca, String modelo, String color) {
        super(titulo, descripcion, localidad, anunciante);
        this.categoria = categoria;
        this.precio = precio;
        this.estado = estado;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public EstadoProducto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProducto estado) {
        this.estado = estado;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
}
