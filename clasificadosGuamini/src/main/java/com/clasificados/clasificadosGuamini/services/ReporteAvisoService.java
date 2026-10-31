package com.clasificados.clasificadosGuamini.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.clasificados.clasificadosGuamini.dtos.request.ReporteAvisoRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.ReporteAvisoResponseDto;
import com.clasificados.clasificadosGuamini.entities.Aviso;
import com.clasificados.clasificadosGuamini.entities.ReporteAviso;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import com.clasificados.clasificadosGuamini.enums.EstadoAviso;
import com.clasificados.clasificadosGuamini.mapStruct.ReporteAvisoMapper;
import com.clasificados.clasificadosGuamini.repositories.AvisoRepository;
import com.clasificados.clasificadosGuamini.repositories.ReporteAvisoRepository;
import com.clasificados.clasificadosGuamini.repositories.UsuarioRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service 
@RequiredArgsConstructor 
@Data 
@Transactional(readOnly = true)
public class ReporteAvisoService {
    
    private final AvisoRepository avisoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ReporteAvisoRepository reporteAvisoRepository;
    private final ReporteAvisoMapper reporteAvisoMapper;

    @Transactional
    public void reportarAviso(Long id, ReporteAvisoRequestDto dto, String nombreUsuario) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Aviso aviso =  avisoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aviso no encontrado"));

        if (aviso.getAnunciante().getId().equals(usuario.getId())) {
            throw new RuntimeException("No puedes reportar tu propia publicación");
        }

        if (reporteAvisoRepository.existsByUsuarioAndAviso(usuario, aviso)) {
            throw new RuntimeException("No puedes reportar mas de una vez el mismo aviso");
        }

        ReporteAviso reporte = new ReporteAviso();
        reporte.setDescripcion(dto.getDescripcion());
        reporte.setAviso(aviso);
        reporte.setUsuario(usuario);
        reporteAvisoRepository.save(reporte);

        long cantidadReportes = reporteAvisoRepository.countByAvisoId(id);
        if (cantidadReportes >= 3) { 
            aviso.setEstado(EstadoAviso.EN_REVISION); 
            avisoRepository.save(aviso);
        }
    }
    

    public List<ReporteAvisoResponseDto> listarReportados(){
        List<ReporteAviso> reportes = reporteAvisoRepository.findAllByEstadoAviso(EstadoAviso.EN_REVISION);
        return reporteAvisoMapper.toResponseDtoList(reportes);
    }

    @Transactional
    public String eliminarReportado(Long id){
        Aviso aviso =  avisoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aviso no encontrado"));
        reporteAvisoRepository.deleteByAvisoId(id);
        avisoRepository.delete(aviso);
        return "Se elimino correctamente el aviso con id:" +id;
    }

}
