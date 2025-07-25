package com.projeto.tcc.service;

import com.projeto.tcc.dto.entrada.LocalDTO;
import com.projeto.tcc.dto.mappers.LocalMapper;
import com.projeto.tcc.dto.pesquisa.LocalResultadoDTO;
import com.projeto.tcc.entities.Local;
import com.projeto.tcc.exceptions.NaoRegistradoExcpetion;
import com.projeto.tcc.repository.LocalRepository;
import com.projeto.tcc.service.validation.LocalValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocalService {

    private final LocalRepository repository;
    private final LocalMapper mapper;
    private final LocalValidation validation;


    public Local salvarLocal(LocalDTO dto){
        var localEntidade = mapper.toEntity(dto);
        validation.validarEntidade(localEntidade);
        return repository.save(localEntidade);
    }

    public LocalResultadoDTO getLocalId(Long idLocal){
        return mapper.toDTO(repository.findById(idLocal).orElseThrow(
                () -> new NaoRegistradoExcpetion("Local com o id " + idLocal + " não existe"))
        );
    }
}
