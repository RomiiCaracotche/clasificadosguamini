package com.clasificados.clasificadosGuamini.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import com.clasificados.clasificadosGuamini.dtos.request.CompraVentaRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.CompraVentaResponseDto;
import com.clasificados.clasificadosGuamini.entities.CompraVenta;
import com.clasificados.clasificadosGuamini.entities.Usuario;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CompraVentaMapper {

    @Mapping(source = "anunciante.id", target = "idAnunciante")
    CompraVentaResponseDto toDto(CompraVenta entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fecha", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "localidad", source = "dto.localidad")
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "anunciante", source = "usuario")
    CompraVenta toEntity(CompraVentaRequestDto dto, Usuario usuario);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fecha", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "anunciante", ignore = true)
    void updateEntityFromDto(CompraVentaRequestDto dto, @MappingTarget CompraVenta entity);

}
