package com.clasificados.clasificadosGuamini.dtos.request;

import com.clasificados.clasificadosGuamini.enums.Categoria;
import com.clasificados.clasificadosGuamini.enums.CondicionProducto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data 
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CompraVentaRequestDto extends AvisoRequestDto {

    @NotNull(message = "La categoria es obligatoria")
    private Categoria categoria;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El valor debe ser mayor a cero") 
    private Double precio;
    
    @NotNull(message = "El estado es obligatorio")
    private CondicionProducto estado;
    
    private String marca;
    
    private String modelo;

    private String color;

}
