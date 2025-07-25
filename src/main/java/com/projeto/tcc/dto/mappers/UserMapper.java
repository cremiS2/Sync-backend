package com.projeto.tcc.dto.mappers;

import com.projeto.tcc.dto.entrada.UsuarioDTO;
import com.projeto.tcc.entities.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UsuarioDTO toDTO(Usuario usuario);
    Usuario toEntity(UsuarioDTO dto);
}
