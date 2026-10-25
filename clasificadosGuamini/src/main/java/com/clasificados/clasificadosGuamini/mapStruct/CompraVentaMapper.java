package com.clasificados.clasificadosGuamini.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import com.clasificados.clasificadosGuamini.dtos.request.CompraVentaRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.CompraVentaResponseDto;
import com.clasificados.clasificadosGuamini.entities.CompraVenta;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CompraVentaMapper {

    CompraVentaResponseDto toDto(CompraVenta entity);

    @Mapping(target = "id", ignore = true) 
    @Mapping(target = "fecha", ignore = true) 
    @Mapping(target = "estado", ignore = true) 
    @Mapping(target = "anunciante", ignore = true)
    CompraVenta toEntity(CompraVentaRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fecha", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "anunciante", ignore = true)
    void updateEntityFromDto(CompraVentaRequestDto dto, @MappingTarget CompraVenta entity);

}
