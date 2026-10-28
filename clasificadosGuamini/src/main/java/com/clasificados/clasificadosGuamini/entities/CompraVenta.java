package com.clasificados.clasificadosGuamini.entities;

import com.clasificados.clasificadosGuamini.enums.Categoria;
import com.clasificados.clasificadosGuamini.enums.CondicionProducto;
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
@Table(name = "compraventas") 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompraVenta extends Aviso {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @Column(nullable = false)
    private Double precio;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CondicionProducto condicion;
    
    private String marca;
    
    private String modelo;

    private String color;
    
}
