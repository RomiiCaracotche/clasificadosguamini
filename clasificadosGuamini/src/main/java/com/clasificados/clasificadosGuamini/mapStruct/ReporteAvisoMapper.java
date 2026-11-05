package com.clasificados.clasificadosGuamini.mapStruct;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import com.clasificados.clasificadosGuamini.dtos.request.Aviso.ReporteAvisoRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.ReporteAvisoResponseDto;
import com.clasificados.clasificadosGuamini.entities.ReporteAviso;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReporteAvisoMapper {

    ReporteAvisoResponseDto toDto(ReporteAviso entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaReporte", ignore = true)
    @Mapping(target = "aviso", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    ReporteAviso toEntity(ReporteAvisoRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaReporte", ignore = true)
    @Mapping(target = "aviso", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    void updateEntityFromDto(ReporteAvisoRequestDto dto, @MappingTarget ReporteAviso entity);

    List<ReporteAvisoResponseDto> toResponseDtoList(List<ReporteAviso> reportes);
    
}
