package com.projeto.tcc.service.nao_utilizadas;

import com.projeto.tcc.dto.nao_utilizadas.EscalaFuncionarioDTO;
import com.projeto.tcc.dto.mappers.EscalaFuncionarioMapper;
import com.projeto.tcc.dto.pesquisa.EscalaFuncionarioResultadoDTO;
import com.projeto.tcc.entities.Turno;
import com.projeto.tcc.exceptions.NaoRegistradoExcpetion;
import com.projeto.tcc.repository.EscalaFuncionarioRepository;
import com.projeto.tcc.service.validation.EscalaValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EscalaService {

    private final EscalaFuncionarioRepository repository;
    private final EscalaFuncionarioMapper mapper;
    private final EscalaValidation validation;

    public Turno criarEscalaFuncionario(EscalaFuncionarioDTO dto){
        Turno turno = mapper.toEntity(dto);
        validation.validarEntidade(turno);
        return repository.save(turno);
    }

    public EscalaFuncionarioResultadoDTO getEscalaId(Long idEscala){
        return mapper.toDTO(repository.findById(idEscala).orElseThrow(
                () -> new NaoRegistradoExcpetion("Escala com o id " + idEscala + " não existe")));
    }

    public Turno getIdReturnEntity(Long idEscala){
        return repository.findById(idEscala).orElseThrow(() -> new NaoRegistradoExcpetion("Escala com o id " + idEscala + " não encontrada"));
    }

}
