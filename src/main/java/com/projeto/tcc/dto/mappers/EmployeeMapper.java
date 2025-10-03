package com.projeto.tcc.dto.mappers;

import com.projeto.tcc.dto.entry.EmployeeDTO;
import com.projeto.tcc.dto.exit.EmployeeResultDTO;
import com.projeto.tcc.entities.Employee;
import com.projeto.tcc.repository.SectorRepository;
import com.projeto.tcc.repository.UserRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {SectorMapper.class, UserMapper.class})
public abstract class EmployeeMapper {

    @Autowired
    SectorRepository sectorRepository;
    @Autowired
    UserRepository userRepository;



    @Mapping(target = "user",
            expression =
                    "java(dto.user() == null ? null : userRepository.findById(dto.user()).orElse(null))"
    )
    @Mapping(target = "sector",
            expression =
                    "java(dto.sector() == null ? null : sectorRepository.findById(dto.sector()).orElse(null))")
    @Mapping(target = "shift",
            ignore = true)
    @Mapping(target = "status",
            ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract public Employee toEntity(EmployeeDTO dto);





    abstract public EmployeeResultDTO toDTO(Employee employee);





    @Mapping(target = "user",
            expression =
                    "java(dto.user() == null ? entidade.getUser() : userRepository.findById(dto.user()).orElse(null))"
    )
    @Mapping(target = "sector",
            expression =
                    "java(dto.sector() == null ? entidade.getSector() : sectorRepository.findById(dto.sector()).orElse(null))")
    @Mapping(target = "shift",
            ignore = true)
    @Mapping(target = "status",
            ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateFuncionario(EmployeeDTO dto, @MappingTarget Employee entidade);
}
