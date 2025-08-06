package com.projeto.tcc.service;


import com.projeto.tcc.dto.entry.MachineModelDTO;
import com.projeto.tcc.dto.exit.MachineModelResultDTO;
import com.projeto.tcc.dto.mappers.MachineModelMapper;
import com.projeto.tcc.entities.MachineModel;
import com.projeto.tcc.exceptions.NaoRegistradoExcpetion;
import com.projeto.tcc.repository.MachineModelRepository;
import com.projeto.tcc.service.validation.ModeloMaquinasValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MachineModelService {

    private final MachineModelRepository repository;
    private final MachineModelMapper mapper;
    private final ModeloMaquinasValidation validation;

    public MachineModel salvarModelo(MachineModelDTO dto){
        MachineModel modelo = mapper.toEntity(dto);
        validation.validarEntidade(modelo);
        return repository.save(modelo);
    }

    public MachineModelResultDTO getModeloMaquinaId(Long idModelo){
        return mapper.toDTO(repository.findById(idModelo).orElseThrow(
                () -> new NaoRegistradoExcpetion("Modelo de máquina com o id " + idModelo + " não existe")));
    }
}
