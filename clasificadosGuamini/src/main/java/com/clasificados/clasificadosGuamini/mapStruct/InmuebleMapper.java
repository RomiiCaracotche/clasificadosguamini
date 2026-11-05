package com.clasificados.clasificadosGuamini.mapStruct;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.MappingConstants;
import com.clasificados.clasificadosGuamini.dtos.request.Inmueble.InmuebleRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.InmuebleResponseDto;
import com.clasificados.clasificadosGuamini.entities.Inmueble;
import com.clasificados.clasificadosGuamini.entities.Usuario;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InmuebleMapper {

    @Mapping(source = "anunciante.id", target = "idAnunciante")
    InmuebleResponseDto toDto(Inmueble entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaExpiracion", ignore = true)
    @Mapping(target = "anunciante", source = "usuario")
    @Mapping(target = "localidad", source = "dto.localidad")
    @Mapping(target = "imagenes", ignore = true)
    Inmueble toEntity(InmuebleRequestDto dto, Usuario usuario);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaExpiracion", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "anunciante", ignore = true)
    @Mapping(target = "imagenes", ignore = true)
    void updateEntityFromDto(InmuebleRequestDto dto, @MappingTarget Inmueble entity);

    List<InmuebleResponseDto> toResponseDtoList(List<Inmueble> inmuebles);
    
} 