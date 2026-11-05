package com.clasificados.clasificadosGuamini.dtos.request.Inmueble;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.clasificados.clasificadosGuamini.enums.TipoOperacion;
import com.clasificados.clasificadosGuamini.enums.TipoVivienda;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class InmuebleActualizadoRequestDto {
    private String titulo;
    private String descripcion;
    private String localidad;

    private TipoOperacion tipoOperacion;
    private TipoVivienda tipoVivienda;
    
    @Positive(message = "El valor debe ser mayor a cero")
    private Double precio;

    @Positive(message = "El valor debe ser mayor a cero")
    private Integer ambientes;

    @Positive(message = "El valor debe ser mayor a cero")
    private Integer habitaciones;

    private Boolean patio;

    @Positive(message = "El valor debe ser mayor a cero")
    private Integer banos;

    private Boolean mascotas;

    @PositiveOrZero(message = "El valor debe ser mayor o igual a cero")
    private Double expensas;

    private String direccion;

    @Size(min = 1, max = 5, message = "Debe subir entre 1 y 5 imágenes") 
    private List<MultipartFile> imagenes;

}
