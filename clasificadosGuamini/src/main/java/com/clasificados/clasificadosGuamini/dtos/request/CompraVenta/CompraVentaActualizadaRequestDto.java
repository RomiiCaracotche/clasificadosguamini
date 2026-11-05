package com.clasificados.clasificadosGuamini.dtos.request.CompraVenta;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.clasificados.clasificadosGuamini.enums.Categoria;
import com.clasificados.clasificadosGuamini.enums.CondicionProducto;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompraVentaActualizadaRequestDto {
    private String titulo;
    private String descripcion;
    private String localidad;

    @Positive(message = "El valor debe ser mayor a cero") 
    private Double precio;
    private Categoria categoria;
    private CondicionProducto condicion;
    private String marca;
    private String modelo;
    private String color;
    @Size(min = 1, max = 5, message = "Debe subir entre 1 y 5 imágenes") 
    private List<MultipartFile> imagenes;
}
