package com.clasificados.clasificadosGuamini.mapStruct;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.clasificados.clasificadosGuamini.dtos.request.CompraVenta.CompraVentaActualizadaRequestDto;
import com.clasificados.clasificadosGuamini.dtos.request.CompraVenta.CompraVentaRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.CompraVentaResponseDto;
import com.clasificados.clasificadosGuamini.entities.CompraVenta;
import com.clasificados.clasificadosGuamini.entities.Usuario;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CompraVentaMapper {

    @Mapping(source = "anunciante.id", target = "idAnunciante")
    CompraVentaResponseDto toDto(CompraVenta entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaExpiracion", ignore = true)
    @Mapping(target = "localidad", source = "dto.localidad")
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "anunciante", source = "usuario")
    @Mapping(target = "imagenes", ignore = true)
    CompraVenta toEntity(CompraVentaRequestDto dto, Usuario usuario);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaExpiracion", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "anunciante", ignore = true)
    @Mapping(target = "imagenes", ignore = true)
    void updateEntityFromDto(CompraVentaActualizadaRequestDto dto, @MappingTarget CompraVenta entity);

    List<CompraVentaResponseDto> toResponseDtoList(List<CompraVenta> avisos);

}
