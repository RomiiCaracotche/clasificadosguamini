package com.clasificados.clasificadosGuamini.mapStruct;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.clasificados.clasificadosGuamini.dtos.response.AvisoResponseDto;
import com.clasificados.clasificadosGuamini.entities.Aviso;

@Mapper(componentModel = "spring", uses = {InmuebleMapper.class, EmpleoMapper.class, CompraVentaMapper.class})
public interface AvisoMapper {

    @Mapping(target = "idAnunciante", source = "anunciante.id")
    AvisoResponseDto toResponseDto(Aviso aviso);

    List<AvisoResponseDto> toResponseDtoList(List<Aviso> avisos);
}
