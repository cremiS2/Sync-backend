package com.projeto.tcc.service;

import com.projeto.tcc.dto.entry.MachineDTO;
import com.projeto.tcc.dto.exit.MachineResultDTO;
import com.projeto.tcc.dto.mappers.MachineMapper;
import com.projeto.tcc.entities.Machine;
import com.projeto.tcc.enums.StatusMachine;
import com.projeto.tcc.exceptions.NaoRegistradoException;
import com.projeto.tcc.repository.MachineRepository;
import com.projeto.tcc.service.validation.MachineValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static com.projeto.tcc.repository.specs.MachineSpecs.*;

@Service
@RequiredArgsConstructor
public class MachineService {

    private final MachineRepository machineRepository;
    private final MachineMapper mapper;
    private final MachineValidation validation;


    public Machine salvarMaquina(MachineDTO machineDTO) {
        Machine machineEntidade = mapper.toEntity(machineDTO);
        validation.validarInformacoes(machineEntidade, machineDTO);
        machineEntidade.setStatus(StatusMachine.valueOf(machineDTO.status().toUpperCase()));
        return machineRepository.save(machineEntidade);
    }

    public MachineResultDTO getMaquinaId(Long idMaquina){
        return mapper.toDTO(machineRepository.findById(idMaquina)
                .orElseThrow(() -> new NaoRegistradoException("Máquina com o id " + idMaquina + " não cadastrada!"))
                );
    }

    public Machine getIdReturnMaquina(Long idMaquina){
        return machineRepository.findById(idMaquina)
                .orElseThrow(() -> new NaoRegistradoException(
                        "Máquina com o id "
                                + idMaquina + " não cadastrada!"));
    }

    //Arrumar uma forma melhor de fazer isso depois!
    public void updateMaquina(Long idMaquina, MachineDTO dto){
        Machine machine = getIdReturnMaquina(idMaquina);
        validation.validarInformacoes(mapper.toEntity(dto), dto);
        if(dto.status() != null){
            machine.setStatus(StatusMachine.valueOf(dto.status().toUpperCase()));
        }
        mapper.updateEntityFromDto(dto, machine);


        machineRepository.save(machine);
    }

    public Page<Machine> pesquisa(
            String nomeMaquina,
            Integer numeroSerie,
            String nomeUnidadeLocal,
            String nomeModelo,
            String nomeSetor,
            String statusMaquina,
            String nomeFuncionario,
            Integer numeroPagina,
            Integer tamanhoPagina
    ) {
        Specification<Machine> specs = Specification.where(((root, query, cb) -> cb.conjunction()));
        if (nomeMaquina != null) {
            specs = specs.and(nomeMaquinaLike(nomeMaquina));
        }
        if (numeroSerie != null) {
            specs = specs.and(numeroSerieLike(numeroSerie));
        }
        if (nomeUnidadeLocal != null) {
            specs = specs.and(localLike(nomeUnidadeLocal));
        }
        if (nomeModelo != null) {
            specs = specs.and(modeloMaquinaLike(nomeModelo));
        }
        if (nomeSetor != null) {
            specs = specs.and(nomeSetorLike(nomeSetor));
        }
        if (statusMaquina != null) {
            specs = specs.and(statusMaquinaLike(statusMaquina));
        }
        if (nomeFuncionario != null) {
            specs = specs.and(nomeFuncionarioLike(nomeFuncionario));
        }

        Pageable pageableRequest = PageRequest.of(numeroPagina, tamanhoPagina);

        return machineRepository.findAll(specs, pageableRequest);

    }


    public void deletarMaquina(Long idMaquina){
        var entidade = getIdReturnMaquina(idMaquina);
        machineRepository.delete(entidade);
    }
}

