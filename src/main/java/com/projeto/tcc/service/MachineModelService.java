package com.projeto.tcc.service;


import com.projeto.tcc.dto.entry.MachineModelDTO;
import com.projeto.tcc.dto.exit.MachineModelResultDTO;
import com.projeto.tcc.dto.mappers.MachineModelMapper;
import com.projeto.tcc.entities.MachineModel;
import com.projeto.tcc.exceptions.NaoRegistradoException;
import com.projeto.tcc.repository.MachineModelRepository;
import com.projeto.tcc.service.validation.MachineModelValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MachineModelService {

    private final MachineModelRepository repository;
    private final MachineModelMapper mapper;
    private final MachineModelValidation validation;

    public MachineModel saveModel(MachineModelDTO dto){
        MachineModel modelo = mapper.toEntity(dto);
        validation.validarEntidade(modelo, dto);
        return repository.save(modelo);
    }

    public MachineModelResultDTO getModeloMaquinaId(Long idModel){
        return mapper.toDTO(repository.findById(idModel).orElseThrow(
                () -> new NaoRegistradoException("Modelo de máquina com o id " + idModel + " não existe")));
    }

    public Page<MachineModelResultDTO> getAllModels(Integer pageSize, Integer pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    public void updateMachineModel(Long idModel, MachineModelDTO dto){
        MachineModel machineModel = repository.findById(idModel).orElseThrow(() -> new NaoRegistradoException("Modelo com o id " + idModel + " não encontrado"));
        validation.validarEntidade(machineModel, dto);
        mapper.updateEntity(dto, machineModel);
        repository.save(machineModel);
    }

    public void deleteMachineModel(Long idModel){
       repository.delete(repository
               .findById(idModel)
               .orElseThrow(
                       () -> new NaoRegistradoException("modelo de máquina não encontrada")));
    }


}
