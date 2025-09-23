package com.projeto.tcc.service;

import com.projeto.tcc.dto.entry.SectorDTO;
import com.projeto.tcc.dto.mappers.SectorMapper;
import com.projeto.tcc.dto.exit.SectorResultDTO;
import com.projeto.tcc.entities.Sector;
import com.projeto.tcc.exceptions.NaoRegistradoException;
import com.projeto.tcc.repository.SectorRepository;
import com.projeto.tcc.service.validation.SectorValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import static com.projeto.tcc.repository.specs.SectorSpecs.*;

@Service
@RequiredArgsConstructor
public class SectorService {

    private final SectorRepository repository;
    private final SectorMapper mapper;
    private final SectorValidation validation;


    public Long createSector(SectorDTO dto){

        Sector sector = mapper.toEntity(dto);
        validation.validarInformacoes(sector, dto);
        return repository.save(sector).getId();
    }

    public SectorResultDTO getSectorId(Long idSetor){
        return mapper.toDTO(repository.findById(idSetor).orElseThrow(
                () -> new NaoRegistradoException("Setor com o id " + idSetor + " não existe")));
    }

    public void deleteSector(Long sectorId){
        repository.deleteById(repository.findById(sectorId).orElseThrow(() -> new NaoRegistradoException("Setor não encontrado")).getId());
    }

    public Page<SectorResultDTO> pesquisa(
            String departmentName,
            String sectorName,
            Integer pageSize,
            Integer pageNumber
    ){
        Specification<Sector> specs = Specification.where((
                root, query, cb) -> cb.conjunction());

        if(departmentName != null){
            specs = specs.and(departmentNameLike(departmentName));
        }
        if(sectorName != null){
            specs = specs.and(sectorNameLike(sectorName));
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return repository.findAll(specs, pageable).map(mapper::toDTO);
    }

    public void updateSector(Long idSector, SectorDTO sectorDTO){
        Sector sector = repository.findById(idSector).orElseThrow(
                () -> new NaoRegistradoException("Setor com o id " + idSector + " não encontrado")
        );
        validation.validarInformacoes(sector, sectorDTO);
        mapper.updateEnity(sector, sectorDTO);
        repository.save(sector);
    }
}
