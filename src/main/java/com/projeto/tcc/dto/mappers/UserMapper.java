package com.projeto.tcc.dto.mappers;

import com.projeto.tcc.dto.entry.UserDTO;
import com.projeto.tcc.dto.exit.UserResultDTO;
import com.projeto.tcc.entities.User;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface UserMapper {

    UserResultDTO toDTO(User user);

    @Mapping(target = "roles",
            ignore = true)
    @Mapping(target = "password", ignore = true)
    User toEntity(UserDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "password",
            ignore = true)
    @Mapping(target = "roles",
            ignore = true)
    void updateEntidade(@MappingTarget User user, UserDTO userDTO);
}
