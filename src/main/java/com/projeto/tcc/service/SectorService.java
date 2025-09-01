package com.projeto.tcc.service;

import com.projeto.tcc.dto.entry.SectorDTO;
import com.projeto.tcc.dto.mappers.SectorMapper;
import com.projeto.tcc.dto.exit.SectorResultDTO;
import com.projeto.tcc.entities.Sector;
import com.projeto.tcc.exceptions.NaoRegistradoException;
import com.projeto.tcc.repository.SectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SectorService {

    private final SectorRepository repository;
    private final SectorMapper mapper;

    public Sector criarSetor(SectorDTO dto){
        Sector sector = mapper.toEntity(dto);
        System.out.println(dto.department());
        return repository.save(sector);
    }

    public SectorResultDTO getSetorId(Long idSetor){
        return mapper.toDTO(repository.findById(idSetor).orElseThrow(
                () -> new NaoRegistradoException("Setor com o id " + idSetor + " não existe")));
    }
    public Sector getIdReturnEnity(Long idSetor){
        return repository.findById(idSetor).orElseThrow(() -> new NaoRegistradoException("Setor com o id " + idSetor + " não encontrado" ));
    }

}
