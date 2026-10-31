package com.clasificados.clasificadosGuamini.dtos.request;

import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class AvisoRequestDto {

    @NotBlank(message = "El titulo es obligatorio")
    private String titulo;

    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;

    @NotBlank(message = "La localidad es obligatoria")
    private String localidad;

    @Size(min = 1, max = 5, message = "Debe subir entre 1 y 5 imágenes")
    private List<String> imagenes;

}
