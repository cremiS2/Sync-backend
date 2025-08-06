package com.projeto.tcc.dto.mappers;

import com.projeto.tcc.dto.entry.UserDTO;
import com.projeto.tcc.dto.exit.UserResultDTO;
import com.projeto.tcc.entities.User;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface UserMapper {

    UserResultDTO toDTO(User user);
    User toEntity(UserDTO dto);
}
