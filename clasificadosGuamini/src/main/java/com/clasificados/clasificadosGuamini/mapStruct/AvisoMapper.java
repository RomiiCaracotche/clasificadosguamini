package com.clasificados.clasificadosGuamini.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import com.clasificados.clasificadosGuamini.dtos.request.AvisoRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.AvisoResponseDto;
import com.clasificados.clasificadosGuamini.entities.Aviso;

@Mapper(componentModel = "spring")
public interface AvisoMapper {

    AvisoResponseDto toDto(Aviso entity);

    Aviso toEntity(AvisoRequestDto dto);

    void updateEntityFromDto(AvisoRequestDto dto, @MappingTarget Aviso entity);

}
