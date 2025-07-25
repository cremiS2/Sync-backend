package com.projeto.tcc.service;

import com.projeto.tcc.dto.entrada.SetorDTO;
import com.projeto.tcc.dto.mappers.SetorMapper;
import com.projeto.tcc.dto.pesquisa.SetorResultadoDTO;
import com.projeto.tcc.entities.Setor;
import com.projeto.tcc.exceptions.NaoRegistradoExcpetion;
import com.projeto.tcc.repository.SetorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SetorService {

    private final SetorRepository repository;
    private final SetorMapper mapper;

    public Setor criarSetor(SetorDTO dto){
        Setor setor = mapper.toEntity(dto);
        return repository.save(setor);
    }

    public SetorResultadoDTO getSetorId(Long idSetor){
        return mapper.toDTO(repository.findById(idSetor).orElseThrow(
                () -> new NaoRegistradoExcpetion("Setor com o id " + idSetor + " não existe")));
    }

}
