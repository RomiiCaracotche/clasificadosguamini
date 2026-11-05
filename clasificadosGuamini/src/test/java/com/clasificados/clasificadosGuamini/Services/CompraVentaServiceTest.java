package com.clasificados.clasificadosGuamini.Services;

import com.clasificados.clasificadosGuamini.dtos.request.CompraVenta.CompraVentaRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.CompraVentaResponseDto;
import com.clasificados.clasificadosGuamini.entities.CompraVenta;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import com.clasificados.clasificadosGuamini.enums.EstadoAviso;
import com.clasificados.clasificadosGuamini.mapStruct.CompraVentaMapper;
import com.clasificados.clasificadosGuamini.repositories.CompraVentaRepository;
import com.clasificados.clasificadosGuamini.services.CompraVentaService;
import com.clasificados.clasificadosGuamini.utils.FileStorageUtils;
import com.clasificados.clasificadosGuamini.utils.SecurityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompraVentaServiceTest {

    @Mock
    private CompraVentaRepository compraVentaRepository;

    @Mock
    private CompraVentaMapper compraVentaMapper;

    @InjectMocks
    private CompraVentaService compraVentaService;

    private CompraVenta compraVenta;

    private CompraVentaResponseDto responseDto;

    @Mock
    private SecurityUtils securityUtils;

    @Mock
    private FileStorageUtils fileStorageUtils;


    @BeforeEach
    void setUp() {
        compraVenta = new CompraVenta();
        compraVenta.setId(1L);
        compraVenta.setTitulo("Notebook usada");
        compraVenta.setPrecio(350000.0);
        compraVenta.setEstado(EstadoAviso.APROBADO);

        responseDto = new CompraVentaResponseDto();
        responseDto.setId(1L);
        responseDto.setTitulo("Notebook usada");
    }

    @Test
    void deberiaBuscarCompraVentaPorIdExitosamente() {
        // Arrange
        when(compraVentaRepository.findById(1L)).thenReturn(Optional.of(compraVenta));
        when(compraVentaMapper.toDto(compraVenta)).thenReturn(responseDto);

        // Act
        CompraVentaResponseDto resultado = compraVentaService.mostrarCompraVenta(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Notebook usada", resultado.getTitulo());
        
        verify(compraVentaRepository, times(1)).findById(1L);
        verify(compraVentaMapper, times(1)).toDto(compraVenta);
    }

    @Test
    void deberiaLanzarExcepcionCuandoNoEncuentraCompraVentaPorId() {
        // Arrange
        when(compraVentaRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            compraVentaService.mostrarCompraVenta(99L);
        });

        verify(compraVentaRepository, times(1)).findById(99L);
    }

    @Test
    void deberiaListarTodasLasCompraVentasExitosamente() {
        // Arrange
        when(compraVentaRepository.findAll()).thenReturn(List.of(compraVenta));
        when(compraVentaMapper.toResponseDtoList(anyList())).thenReturn(List.of(responseDto));

        // Act
        List<CompraVentaResponseDto> resultados = compraVentaService.listarCompraVentas();

        // Assert
        assertNotNull(resultados);
        assertEquals(1, resultados.size());
        assertEquals("Notebook usada", resultados.get(0).getTitulo());

        verify(compraVentaRepository, times(1)).findAll();
    }

   @Test
    void deberiaCrearCompraVentaExitosamente() throws Exception {
        // Arrange
        CompraVentaRequestDto requestDto = new CompraVentaRequestDto();
        requestDto.setTitulo("Notebook usada");

        Usuario usuarioMock = new Usuario(); // O la instancia de usuario que uses
        usuarioMock.setId(1L);

        MultipartFile archivoMock = mock(MultipartFile.class);
        when(archivoMock.isEmpty()).thenReturn(false);
        doReturn("imagenes/notebook.jpg").when(fileStorageUtils).guardarArchivoEnDisco(any(MultipartFile.class)); 

        when(securityUtils.obtenerUsuarioActual()).thenReturn(usuarioMock);
        when(compraVentaMapper.toEntity(eq(requestDto), any(Usuario.class))).thenReturn(compraVenta);
        when(compraVentaRepository.save(any(CompraVenta.class))).thenReturn(compraVenta);
        when(compraVentaMapper.toDto(any(CompraVenta.class))).thenReturn(responseDto);

        // Act - Pasamos una lista con un archivo para que supere la validación de archivos
        CompraVentaResponseDto resultado = compraVentaService.crearCompraVenta(requestDto, List.of(archivoMock));

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(compraVentaRepository, times(1)).save(any(CompraVenta.class));
    }

    @Test
    void deberiaEliminarCompraVentaExitosamente() {
        // Arrange
        Long idAviso = 1L;

        // 1. Mockeamos para que pase la validación de seguridad sin lanzar excepciones
        doNothing().when(securityUtils).verificarPropietarioOAdmin(idAviso);

        // 2. Mockeamos la búsqueda para que encuentre el registro
        when(compraVentaRepository.findById(idAviso)).thenReturn(Optional.of(compraVenta));

        // 3. (Opcional pero recomendado) Mockeamos el borrado físico si hace falta, 
        // aunque al ser un método void en JpaRepository suele funcionar por defecto con mocks.
        doNothing().when(compraVentaRepository).delete(compraVenta);

        // Act
        String resultado = compraVentaService.eliminarCompraVenta(idAviso);

        // Assert
        assertNotNull(resultado);
        assertEquals("La compraventa con id 1 se eliminó correctamente", resultado);
        
        // Verificamos que se hayan invocado los métodos correspondientes
        verify(securityUtils, times(1)).verificarPropietarioOAdmin(idAviso);
        verify(compraVentaRepository, times(1)).findById(idAviso);
        verify(compraVentaRepository, times(1)).delete(compraVenta);
    }

}