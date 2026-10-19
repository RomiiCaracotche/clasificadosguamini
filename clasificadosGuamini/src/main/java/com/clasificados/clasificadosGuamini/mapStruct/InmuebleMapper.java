package com.clasificados.clasificadosGuamini.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import com.clasificados.clasificadosGuamini.dtos.request.InmuebleRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.InmuebleResponseDto;
import com.clasificados.clasificadosGuamini.entities.Inmueble;

@Mapper(componentModel = "spring")
public interface InmuebleMapper {

    InmuebleResponseDto toDto(Inmueble entity);

    Inmueble toEntity(InmuebleRequestDto dto);

    void updateEntityFromDto(InmuebleRequestDto dto, @MappingTarget Inmueble entity);
    
} 