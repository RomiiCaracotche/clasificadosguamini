package com.clasificados.clasificadosGuamini.Repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.clasificados.clasificadosGuamini.entities.CompraVenta;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import com.clasificados.clasificadosGuamini.enums.Categoria;
import com.clasificados.clasificadosGuamini.enums.CondicionProducto;
import com.clasificados.clasificadosGuamini.enums.EstadoAviso;
import com.clasificados.clasificadosGuamini.repositories.CompraVentaRepository;
import com.clasificados.clasificadosGuamini.repositories.UsuarioRepository;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CompraVentaRepositoryTest {

    @Autowired
    private CompraVentaRepository compraVentaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository; // Necesario si CompraVenta requiere un anunciante (Usuario)

    @Test
    void deberiaGuardarYRecuperarCompraVentaCorrectamente() {
        // Arrange (Creamos un usuario dummy ya que 'anunciante' es obligatorio en Aviso)
        Usuario usuario = new Usuario();
        usuario.setDni("32345678");
        usuario.setEmail("test@test.com");
        usuario.setApellido("gomez");
        usuario.setNombre("rosa");
        usuario.setNombreUsuario("rosagomez");
        usuario.setPassword("12345678");
        usuario.setCelular("2213454343");
        usuario.setLocalidad("la plata");
        usuario = usuarioRepository.save(usuario);

        CompraVenta anuncio = new CompraVenta();
        anuncio.setTitulo("Bicicleta");
        anuncio.setDescripcion("En buen estado");
        anuncio.setLocalidad("Guaminí");
        anuncio.setEstado(EstadoAviso.APROBADO);
        anuncio.setAnunciante(usuario);
        
        // Atributos de CompraVenta
        anuncio.setCategoria(Categoria.DEPORTES);
        anuncio.setPrecio(150000.0);
        anuncio.setCondicion(CondicionProducto.USADO);

        // Act
        CompraVenta guardado = compraVentaRepository.save(anuncio);
        CompraVenta recuperado = compraVentaRepository.findById(guardado.getId()).orElse(null);

        // Assert
        assertNotNull(recuperado);
        assertEquals("Bicicleta", recuperado.getTitulo());
        assertEquals(150000.0, recuperado.getPrecio());
        assertEquals(CondicionProducto.USADO, recuperado.getCondicion());
        assertEquals(Categoria.DEPORTES, recuperado.getCategoria());
    }
    
}
