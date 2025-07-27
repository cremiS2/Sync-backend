package com.projeto.tcc.dto.mappers;

import com.projeto.tcc.dto.entrada.MaquinaDTO;
import com.projeto.tcc.dto.pesquisa.MaquinaResultadoDTO;
import com.projeto.tcc.entities.Maquina;
import com.projeto.tcc.repository.FuncionarioRepository;
import com.projeto.tcc.repository.LocalRepository;
import com.projeto.tcc.repository.ModeloMaquinasRepository;
import com.projeto.tcc.repository.SetorRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {ModeloMaquinasMapper.class, FuncionarioMapper.class, LocalMapper.class})
public abstract class MaquinaMapper  {

    @Autowired
    SetorRepository setorRepository;
    @Autowired
    ModeloMaquinasRepository modeloMaquinasRepository;
    @Autowired
    LocalRepository localRepository;
    @Autowired
    FuncionarioRepository funcionarioRepository;


    //CREATE

    @Mapping(target = "setor",
            expression =
                    "java(dto.setor() == null ? null : setorRepository.findById(dto.setor()).orElse(null))")

    @Mapping(target = "modeloMaquina",
            expression =
                    "java(dto.modeloMaquina() == null ? null : modeloMaquinasRepository.findById(dto.modeloMaquina()).orElse(null))")

    @Mapping(target = "localMaquina",
            expression =
                    "java(dto.localMaquina() == null ? null : localRepository.findById(dto.localMaquina()).orElse(null))")

    @Mapping(target = "funcionarioOperando",
            expression =
                    "java(dto.funcionarioOperando() == null ? null : funcionarioRepository.findById(dto.funcionarioOperando()).orElse(null))")
    abstract public Maquina toEntity(MaquinaDTO dto);


    // TRANSFORMAÇÃO

    abstract public MaquinaResultadoDTO toDTO(Maquina maquina);


    //UPDATE
    @Mapping(target = "setor",
            expression =
                    "java(dto.setor() == null ? maquina.getSetor() : setorRepository.findById(dto.setor()).orElse(null))")

    @Mapping(target = "modeloMaquina",
            expression =
                    "java(dto.modeloMaquina() == null ? maquina.getModeloMaquina() : modeloMaquinasRepository.findById(dto.modeloMaquina()).orElse(null))")

    @Mapping(target = "localMaquina",
            expression =
                    "java(dto.localMaquina() == null ? maquina.getLocalMaquina() : localRepository.findById(dto.localMaquina()).orElse(null))")

    @Mapping(target = "funcionarioOperando",
            expression =
                    "java(dto.funcionarioOperando() == null ? maquina.getFuncionarioOperando() : funcionarioRepository.findById(dto.funcionarioOperando()).orElse(null))")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateEntityFromDto(MaquinaDTO dto, @MappingTarget Maquina maquina);





}
