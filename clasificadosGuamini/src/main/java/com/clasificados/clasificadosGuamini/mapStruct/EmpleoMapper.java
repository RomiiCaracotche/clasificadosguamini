package com.clasificados.clasificadosGuamini.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import com.clasificados.clasificadosGuamini.dtos.request.EmpleoRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.EmpleoResponseDto;
import com.clasificados.clasificadosGuamini.entities.Empleo;

@Mapper(componentModel = "spring")
public interface EmpleoMapper {

    EmpleoResponseDto toDto(Empleo entity);

    Empleo toEntity(EmpleoRequestDto dto);

    void updateEntityFromDto(EmpleoRequestDto dto, @MappingTarget Empleo entity);

}
