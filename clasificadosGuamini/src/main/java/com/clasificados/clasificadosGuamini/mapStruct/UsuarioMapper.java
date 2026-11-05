package com.clasificados.clasificadosGuamini.mapStruct;

import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.MappingConstants;
import com.clasificados.clasificadosGuamini.dtos.request.Usuario.UsuarioActualizadoRequestDto;
import com.clasificados.clasificadosGuamini.dtos.request.Usuario.UsuarioRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.UsuarioResponseDto;
import com.clasificados.clasificadosGuamini.entities.Usuario;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {

    UsuarioResponseDto toDto(Usuario entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "favoritos", ignore = true)
    @Mapping(target = "avisos", ignore = true)
    @Mapping(target = "admin", constant = "false")
    @Mapping(target = "activo", constant = "true")
    Usuario toEntity(UsuarioRequestDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaRegistro", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "favoritos", ignore = true)
    @Mapping(target = "avisos", ignore = true)
    @Mapping(target = "admin", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "dni", ignore = true)          
    @Mapping(target = "nombreUsuario", ignore = true)
    void updateEntityFromDto(UsuarioActualizadoRequestDto dto, @MappingTarget Usuario entity);

    List<UsuarioResponseDto> toResponseDtoList(List<Usuario> usuarios);
}

