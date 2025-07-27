package com.projeto.tcc.dto.mappers;

import com.projeto.tcc.dto.entrada.FuncionarioDTO;
import com.projeto.tcc.dto.pesquisa.FuncionarioResultadoDTO;
import com.projeto.tcc.entities.Funcionario;
import com.projeto.tcc.repository.EscalaFuncionarioRepository;
import com.projeto.tcc.repository.SetorRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {EscalaFuncionarioMapper.class, SetorMapper.class})
public abstract class FuncionarioMapper {

    @Autowired
    EscalaFuncionarioRepository repository;
    @Autowired
    SetorRepository setorRepository;

    @Mapping(target = "escalaFuncionario",
            expression =
                    "java(dto.escalaFuncionario() == null ? null : repository.findById(dto.escalaFuncionario()).orElse(null))")

    @Mapping(target = "setor",
            expression =
                    "java(dto.setor() == null ? null : setorRepository.findById(dto.setor()).orElse(null))")

    @Mapping(target = "roles",
            ignore = true)
    abstract public Funcionario toEntity(FuncionarioDTO dto);


    abstract public FuncionarioResultadoDTO toDTO(Funcionario funcionario);


    @Mapping(target = "escalaFuncionario",
            expression =
                    "java(dto.escalaFuncionario() == null ? entidade.getEscalaFuncionario() : repository.findById(dto.escalaFuncionario()).orElse(null))")

    @Mapping(target = "setor",
            expression =
                    "java(dto.setor() == null ? entidade.getSetor() : setorRepository.findById(dto.setor()).orElse(null))")

    @Mapping(target = "roles",
            ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateFuncionario(FuncionarioDTO dto,@MappingTarget Funcionario entidade);
}
