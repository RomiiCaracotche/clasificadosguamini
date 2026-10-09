package com.clasificados.clasificadosGuamini.dtos.request;

import com.clasificados.clasificadosGuamini.enums.Categoria;
import com.clasificados.clasificadosGuamini.enums.EstadoProducto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CompraVentaRequestDto extends AvisoRequestDto{

    @NotNull(message = "Este campo es obligatorio")
    private Categoria categoria;

    @NotNull(message = "Este campo es obligatorio")
    @Positive(message = "El valor debe ser mayor a cero") 
    private Double precio;
    
    @NotNull(message = "Este campo es obligatorio")
    private EstadoProducto estado;
    
    private String marca;
    
    private String modelo;

    private String color;

    
    public CompraVentaRequestDto() {
        super();
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
