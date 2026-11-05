package com.clasificados.clasificadosGuamini.Controllers;

import com.clasificados.clasificadosGuamini.controllers.CompraVentaController;
import com.clasificados.clasificadosGuamini.dtos.request.CompraVenta.CompraVentaActualizadaRequestDto;
import com.clasificados.clasificadosGuamini.dtos.request.CompraVenta.CompraVentaRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.CompraVentaResponseDto;
import com.clasificados.clasificadosGuamini.enums.Categoria;
import com.clasificados.clasificadosGuamini.enums.CondicionProducto;
import com.clasificados.clasificadosGuamini.services.CompraVentaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.ArgumentMatchers.*;

@WebMvcTest(CompraVentaController.class)
class CompraVentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    private CompraVentaService compraVentaService;


    @Test
    void deberiaListarCompraVentasActivasExitosamente() throws Exception {
        // Arrange
        CompraVentaResponseDto dto = new CompraVentaResponseDto();
        dto.setId(1L);
        dto.setTitulo("Bicicleta usada");

        when(compraVentaService.listarCompraVentasActivas()).thenReturn(List.of(dto));

        // Act & Assert
        mockMvc.perform(get("/api/compraventas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].titulo").value("Bicicleta usada"));
    }

    @Test
    void deberiaListarTodasLasCompraVentasExitosamente() throws Exception {
        // Arrange
        CompraVentaResponseDto dto = new CompraVentaResponseDto();
        dto.setId(2L);
        dto.setTitulo("Notebook");

        when(compraVentaService.listarCompraVentas()).thenReturn(List.of(dto));

        // Act & Assert
        mockMvc.perform(get("/api/compraventas/todas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(2))
                .andExpect(jsonPath("$[0].titulo").value("Notebook"));
    }

    @Test
    void deberiaMostrarCompraVentaPorIdExitosamente() throws Exception {
        // Arrange
        CompraVentaResponseDto dto = new CompraVentaResponseDto();
        dto.setId(5L);
        dto.setTitulo("Celular");

        when(compraVentaService.mostrarCompraVenta(5L)).thenReturn(dto);

        // Act & Assert
        mockMvc.perform(get("/api/compraventas/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.titulo").value("Celular"));
    }

    @Test
    void deberiaCrearCompraVentaExitosamenteViaPost() throws Exception {
        // Arrange
        CompraVentaRequestDto requestDto = new CompraVentaRequestDto();
        requestDto.setTitulo("Celular nuevo");
        requestDto.setLocalidad("Guaminí");
        requestDto.setPrecio(500000.0);
        requestDto.setCategoria(Categoria.TECNOLOGIA);
        requestDto.setDescripcion("Libre de fábrica y en caja sellada");
        requestDto.setCondicion(CondicionProducto.NUEVO);

        MockMultipartFile compraventaPart = new MockMultipartFile(
                "compraventa", 
                "", 
                MediaType.APPLICATION_JSON_VALUE, 
                objectMapper.writeValueAsString(requestDto).getBytes()
        );

        MockMultipartFile imagenPart = new MockMultipartFile(
                "imagenes", 
                "test.jpg", 
                MediaType.IMAGE_JPEG_VALUE, 
                "contenido de prueba".getBytes()
        );

        CompraVentaResponseDto respuestaMock = new CompraVentaResponseDto();
        respuestaMock.setId(10L);
        respuestaMock.setTitulo("Celular nuevo");

        when(compraVentaService.crearCompraVenta(any(CompraVentaRequestDto.class), anyList()))
                .thenReturn(respuestaMock);

        // Act & Assert
        mockMvc.perform(multipart("/api/compraventas")
                        .file(compraventaPart)
                        .file(imagenPart))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.titulo").value("Celular nuevo"));
    }

    @Test
    void deberiaModificarCompraVentaExitosamenteViaPatch() throws Exception {
        // Arrange
        CompraVentaActualizadaRequestDto requestDto = new CompraVentaActualizadaRequestDto();
        // Setea los campos que acepte tu DTO de actualización

        MockMultipartFile compraventaPart = new MockMultipartFile(
                "compraventa", 
                "", 
                MediaType.APPLICATION_JSON_VALUE, 
                objectMapper.writeValueAsString(requestDto).getBytes()
        );

        CompraVentaResponseDto respuestaMock = new CompraVentaResponseDto();
        respuestaMock.setId(3L);
        respuestaMock.setTitulo("Título Actualizado");

        // Para simular solicitudes PATCH con multipart en MockMvc se usa multipart con .with(request -> { request.setMethod("PATCH"); return request; })
        when(compraVentaService.modificarCompraVenta(eq(3L), any(CompraVentaActualizadaRequestDto.class), any()))
                .thenReturn(respuestaMock);

        // Act & Assert
        mockMvc.perform(multipart("/api/compraventas/3")
                        .file(compraventaPart)
                        .with(request -> {
                            request.setMethod("PATCH");
                            return request;
                        }))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.titulo").value("Título Actualizado"));
    }

    @Test
    void deberiaEliminarCompraVentaExitosamente() throws Exception {
        // Arrange
        when(compraVentaService.eliminarCompraVenta(1L)).thenReturn("Aviso eliminado exitosamente");

        // Act & Assert
        mockMvc.perform(delete("/api/compraventas/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Aviso eliminado exitosamente"));
    }

}
