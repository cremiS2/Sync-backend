package com.projeto.tcc.service;

import com.projeto.tcc.dto.entry.SectorDTO;
import com.projeto.tcc.dto.mappers.SectorMapper;
import com.projeto.tcc.dto.exit.SectorResultDTO;
import com.projeto.tcc.entities.Sector;
import com.projeto.tcc.exceptions.NaoRegistradoException;
import com.projeto.tcc.repository.SectorRepository;
import com.projeto.tcc.service.validation.SectorValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SectorService {

    private final SectorRepository repository;
    private final SectorMapper mapper;
    private final SectorValidation validation;


    public Sector createSector(SectorDTO dto){

        Sector sector = mapper.toEntity(dto);
        validation.validarInformacoes(sector, dto);
        return repository.save(sector);
    }

    public SectorResultDTO getSectorId(Long idSetor){
        return mapper.toDTO(repository.findById(idSetor).orElseThrow(
                () -> new NaoRegistradoException("Setor com o id " + idSetor + " não existe")));
    }

    public void deleteSector(Long sectorId){
        repository.deleteById(sectorId);
    }
}
