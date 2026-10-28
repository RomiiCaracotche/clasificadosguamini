package com.clasificados.clasificadosGuamini.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.MappingConstants;
import com.clasificados.clasificadosGuamini.dtos.request.InmuebleRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.InmuebleResponseDto;
import com.clasificados.clasificadosGuamini.entities.Inmueble;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InmuebleMapper {

    @Mapping(target = "idAnunciante", source = "anunciante.id")
    InmuebleResponseDto toDto(Inmueble entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fecha", expression = "java(java.time.LocalDateTime.now())")
    /* @Mapping(target = "anunciante", source = "usuario") */
    @Mapping(target = "localidad", source = "dto.localidad")
    @Mapping(target = "estado", ignore = true)
    Inmueble toEntity(InmuebleRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fecha", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "anunciante", ignore = true)
    void updateEntityFromDto(InmuebleRequestDto dto, @MappingTarget Inmueble entity);
    
} 