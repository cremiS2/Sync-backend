package com.projeto.tcc.service;

import com.projeto.tcc.dto.entry.DepartamentDTO;
import com.projeto.tcc.dto.exit.DepartamentResultDTO;
import com.projeto.tcc.dto.mappers.DepartamentMapper;
import com.projeto.tcc.entities.Departament;
import com.projeto.tcc.exceptions.NaoRegistradoExcpetion;
import com.projeto.tcc.repository.DepartamentRepository;
import com.projeto.tcc.service.validation.DepartamentValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartamentService {

    private final DepartamentRepository repository;
    private final DepartamentMapper mapper;
    private final DepartamentValidation validation;


    public Departament saveDepartament(DepartamentDTO dto){
        var departamentEntidade = mapper.toEntity(dto);
        validation.validarEntidade(departamentEntidade);
        return repository.save(departamentEntidade);
    }

    public DepartamentResultDTO getDepartamentId(Long departamentId){
        return mapper.toDTO(repository.findById(departamentId).orElseThrow(
                () -> new NaoRegistradoExcpetion("Departamento com o id " + departamentId + " não existe"))
        );
    }

    public void deleteDepartament(Long departamentId){
            repository.delete(
                    repository.findById(departamentId)
                            .orElseThrow(() -> new NaoRegistradoExcpetion("Departamento não registrado"))
            );
        }

    }
