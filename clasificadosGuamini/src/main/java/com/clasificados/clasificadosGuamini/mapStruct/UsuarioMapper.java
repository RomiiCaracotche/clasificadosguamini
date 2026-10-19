package com.clasificados.clasificadosGuamini.mapStruct;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import com.clasificados.clasificadosGuamini.dtos.request.UsuarioRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.UsuarioResponseDto;
import com.clasificados.clasificadosGuamini.entities.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioResponseDto toDto(Usuario entity);

    Usuario toEntity(UsuarioRequestDto dto);

    void updateEntityFromDto(UsuarioRequestDto dto, @MappingTarget Usuario entity);
}

