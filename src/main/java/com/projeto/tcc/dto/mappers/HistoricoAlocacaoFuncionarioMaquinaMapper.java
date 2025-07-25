package com.projeto.tcc.dto.mappers;

import com.projeto.tcc.dto.pesquisa.HistoricoAlteracaoEstadoMaquinaResultadoDTO;
import com.projeto.tcc.entities.HistoricoAlteracaoEstadoMaquina;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {FuncionarioMapper.class, MaquinaMapper.class})
public interface HistoricoAlocacaoFuncionarioMaquinaMapper {

    HistoricoAlteracaoEstadoMaquinaResultadoDTO toDTO(HistoricoAlteracaoEstadoMaquina entity);

}
