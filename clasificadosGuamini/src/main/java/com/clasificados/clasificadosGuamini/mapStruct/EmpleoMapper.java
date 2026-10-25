package com.clasificados.clasificadosGuamini.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.MappingConstants;
import com.clasificados.clasificadosGuamini.dtos.request.EmpleoRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.EmpleoResponseDto;
import com.clasificados.clasificadosGuamini.entities.Empleo;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmpleoMapper {

    EmpleoResponseDto toDto(Empleo entity);

    @Mapping(target = "id", ignore = true) 
    @Mapping(target = "fecha", ignore = true) 
    @Mapping(target = "estado", ignore = true) 
    @Mapping(target = "anunciante", ignore = true)
    Empleo toEntity(EmpleoRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fecha", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "anunciante", ignore = true)
    void updateEntityFromDto(EmpleoRequestDto dto, @MappingTarget Empleo entity);

}
