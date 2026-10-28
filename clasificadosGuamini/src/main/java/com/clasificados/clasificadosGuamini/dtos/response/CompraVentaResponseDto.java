package com.clasificados.clasificadosGuamini.dtos.response;

import com.clasificados.clasificadosGuamini.enums.Categoria;
import com.clasificados.clasificadosGuamini.enums.CondicionProducto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotNull(message = "Este campo es obligatorio")
    private Categoria categoria;

    @NotNull(message = "Este campo es obligatorio")
    @Positive(message = "El valor debe ser mayor a cero") 
    private Double precio;
    
    @NotNull(message = "Este campo es obligatorio")
    private CondicionProducto condicion;
    
    private String marca;
    
    private String modelo;

    private String color;
    
}
