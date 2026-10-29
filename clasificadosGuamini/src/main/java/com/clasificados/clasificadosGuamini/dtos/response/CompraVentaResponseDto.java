package com.clasificados.clasificadosGuamini.dtos.response;

import com.clasificados.clasificadosGuamini.enums.Categoria;
import com.clasificados.clasificadosGuamini.enums.CondicionProducto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode;

@Getter
@Setter 
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CompraVentaResponseDto extends AvisoResponseDto {

    private Categoria categoria;

    private Double precio;
    
    private CondicionProducto condicion;
    
    private String marca;
    
    private String modelo;

    private String color;
    
}
