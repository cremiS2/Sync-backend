package com.projeto.tcc.service;

import com.projeto.tcc.dto.entrada.EscalaFuncionarioDTO;
import com.projeto.tcc.dto.mappers.EscalaFuncionarioMapper;
import com.projeto.tcc.dto.pesquisa.EscalaFuncionarioResultadoDTO;
import com.projeto.tcc.entities.EscalaFuncionario;
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

    public EscalaFuncionario criarEscalaFuncionario(EscalaFuncionarioDTO dto){
        EscalaFuncionario escalaFuncionario = mapper.toEntity(dto);
        validation.validarEntidade(escalaFuncionario);
        return repository.save(escalaFuncionario);
    }

    public EscalaFuncionarioResultadoDTO getEscalaId(Long idEscala){
        return mapper.toDTO(repository.findById(idEscala).orElseThrow(
                () -> new NaoRegistradoExcpetion("Escala com o id " + idEscala + " não existe")));
    }

    public EscalaFuncionario getIdReturnEntity(Long idEscala){
        return repository.findById(idEscala).orElseThrow(() -> new NaoRegistradoExcpetion("Escala com o id " + idEscala + " não encontrada"));
    }

}
