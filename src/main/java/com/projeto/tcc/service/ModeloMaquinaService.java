package com.projeto.tcc.service;


import com.projeto.tcc.dto.nao_utilizadas.ModeloMaquinasDTO;
import com.projeto.tcc.dto.mappers.ModeloMaquinasMapper;
import com.projeto.tcc.dto.pesquisa.ModeloMaquinaResultadoDTO;
import com.projeto.tcc.entities.ModeloMaquinas;
import com.projeto.tcc.exceptions.NaoRegistradoExcpetion;
import com.projeto.tcc.repository.ModeloMaquinasRepository;
import com.projeto.tcc.service.validation.ModeloMaquinasValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModeloMaquinaService {

    private final ModeloMaquinasRepository repository;
    private final ModeloMaquinasMapper mapper;
    private final ModeloMaquinasValidation validation;

    public ModeloMaquinas salvarModelo(ModeloMaquinasDTO dto){
        ModeloMaquinas modelo = mapper.toEntity(dto);
        validation.validarEntidade(modelo);
        return repository.save(modelo);
    }

    public ModeloMaquinaResultadoDTO getModeloMaquinaId(Long idModelo){
        return mapper.toDTO(repository.findById(idModelo).orElseThrow(
                () -> new NaoRegistradoExcpetion("Modelo de máquina com o id " + idModelo + " não existe")));
    }
}
