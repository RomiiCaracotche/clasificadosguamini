package com.clasificados.clasificadosGuamini.Entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.clasificados.clasificadosGuamini.entities.CompraVenta;
import com.clasificados.clasificadosGuamini.enums.Categoria;
import com.clasificados.clasificadosGuamini.enums.CondicionProducto;
import com.clasificados.clasificadosGuamini.enums.EstadoAviso;

public class CompraVentaTest {
    
    @Test
    void deberiaCrearCompraVentaCorrectamenteConSusAtributosYHeredados() {
        // Arrange & Act
        CompraVenta compraventa = new CompraVenta();
        
        // Atributos heredados de Aviso
        compraventa.setTitulo("Bicicleta Mountain Bike");
        compraventa.setDescripcion("Rodado 29 en excelente estado, casi sin uso");
        compraventa.setLocalidad("Guaminí");
        compraventa.setEstado(EstadoAviso.APROBADO);
        
        // Atributos propios de CompraVenta
        compraventa.setCategoria(Categoria.DEPORTES); 
        compraventa.setPrecio(150000.0);
        compraventa.setCondicion(CondicionProducto.USADO);
        compraventa.setMarca("Specialized");
        compraventa.setModelo("Rockhopper");
        compraventa.setColor("Negro");

        // Assert - Verificación de atributos heredados
        assertNotNull(compraventa);
        assertEquals("Bicicleta Mountain Bike", compraventa.getTitulo());
        assertEquals("Rodado 29 en excelente estado, casi sin uso", compraventa.getDescripcion());
        assertEquals("Guaminí", compraventa.getLocalidad());
        assertEquals(EstadoAviso.APROBADO, compraventa.getEstado());

        // Assert - Verificación de atributos propios y el enum corregido
        assertEquals(Categoria.DEPORTES, compraventa.getCategoria());
        assertEquals(150000.0, compraventa.getPrecio());
        assertEquals(CondicionProducto.USADO, compraventa.getCondicion());
        assertEquals("Specialized", compraventa.getMarca());
        assertEquals("Rockhopper", compraventa.getModelo());
        assertEquals("Negro", compraventa.getColor());
    }

    @Test
    void deberiaEjecutarPrePersistCorrectamente() {
        // Arrange
        CompraVenta compraventa = new CompraVenta();

        // Act
        compraventa.prePersist();

        // Assert - Verifica que se asignen los valores por defecto del ciclo de vida
        assertEquals(EstadoAviso.APROBADO, compraventa.getEstado());
        assertNotNull(compraventa.getFechaCreacion());
        assertNotNull(compraventa.getFechaExpiracion());
        
        // Verifica que la fecha de expiración sea posterior a la de creación (30 días después)
        assertTrue(compraventa.getFechaExpiracion().isAfter(compraventa.getFechaCreacion()));
    }
    
}
