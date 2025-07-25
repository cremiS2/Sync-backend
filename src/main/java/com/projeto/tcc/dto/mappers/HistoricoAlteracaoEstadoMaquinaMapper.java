package com.projeto.tcc.dto.mappers;


import com.projeto.tcc.dto.pesquisa.HistoricoAlteracaoEstadoMaquinaResultadoDTO;
import com.projeto.tcc.entities.HistoricoAlteracaoEstadoMaquina;
import com.projeto.tcc.repository.FuncionarioRepository;
import com.projeto.tcc.repository.MaquinaRepositoy;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {MaquinaMapper.class, FuncionarioMapper.class})
public abstract class HistoricoAlteracaoEstadoMaquinaMapper {

    @Autowired
    FuncionarioRepository funcionarioRepository;
    @Autowired
    MaquinaRepositoy maquinaRepositoy;

    abstract public HistoricoAlteracaoEstadoMaquinaResultadoDTO toDTO(HistoricoAlteracaoEstadoMaquina historicoEntity);

}
