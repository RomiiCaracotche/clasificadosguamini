package com.clasificados.clasificadosGuamini.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import com.clasificados.clasificadosGuamini.dtos.request.CompraVentaRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.CompraVentaResponseDto;
import com.clasificados.clasificadosGuamini.entities.CompraVenta;

@Mapper(componentModel = "spring")
public interface CompraVentaMapper {

    CompraVentaResponseDto toDto(CompraVenta entity);

    CompraVenta toEntity(CompraVentaRequestDto dto);

    void updateEntityFromDto(CompraVentaRequestDto dto, @MappingTarget CompraVenta entity);

}
