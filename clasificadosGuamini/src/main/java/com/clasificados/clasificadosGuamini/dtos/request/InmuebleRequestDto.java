package com.clasificados.clasificadosGuamini.dtos.request;

import com.clasificados.clasificadosGuamini.enums.TipoOperacion;
import com.clasificados.clasificadosGuamini.enums.TipoVivienda;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data 
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class InmuebleRequestDto extends AvisoRequestDto {

    @NotNull(message = "El tipo de operacion es obligatorio")
    private TipoOperacion tipoOperacion;

    @NotNull(message = "El tipo de vivienda es obligatorio")
    private TipoVivienda tipoVivienda;
    
    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El valor debe ser mayor a cero")
    private Double precio;

    @NotNull(message = "La cantidad de ambientes es obligatorio")
    @Positive(message = "El valor debe ser mayor a cero")
    private Integer ambientes;

    @NotNull(message = "La cantidad de habitaciones es obligatorio")
    @Positive(message = "El valor debe ser mayor a cero")
    private Integer habitaciones;

    @NotNull(message = "El patio es obligatorio")
    private Boolean patio;

    @NotNull(message = "La cantidad de baños es obligatorio")
    @Positive(message = "El valor debe ser mayor a cero")
    private Integer banos;

    @NotNull(message = "El campo admite mascotas es obligatorio")
    private Boolean mascotas;

    @NotNull(message = "El campo posee expensas es obligatorio")
    @PositiveOrZero(message = "El valor debe ser mayor o igual a cero")
    private Double expensas;

    @NotNull(message = "La direccion es obligatoria")
    private String direccion;

}
