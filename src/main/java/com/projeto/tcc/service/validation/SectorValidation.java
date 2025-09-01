package com.projeto.tcc.service.validation;

import com.projeto.tcc.dto.entry.SectorDTO;
import com.projeto.tcc.entities.Sector;
import com.projeto.tcc.exceptions.ConflitoCampoException;
import com.projeto.tcc.exceptions.NaoRegistradoException;
import com.projeto.tcc.repository.SectorRepository;
import com.projeto.tcc.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SectorValidation {

    @Autowired
    private final SectorRepository sectorRepository;
    @Autowired
    private final DepartmentService departmentService;

    public void validarInformacoes(Sector sector, SectorDTO sectorDTO){
        validarEntidade(sector);
        if(sectorDTO.department() !=  null){
            if(!departmentService.checkEntity(sectorDTO.department())){
                throw new NaoRegistradoException("Departamento com id " + sectorDTO.department() + " não registrado" );
            }
        }

    }

    private void validarEntidade(Sector sector){
        if(existeSetor(sector)){
            throw new ConflitoCampoException("Setor com as informações fornecidas já cadastrado");
        }
    }

    private boolean existeSetor(Sector sector){
        var sectorFound = sectorRepository.findByNameAndDepartment(sector.getName(),
                sector.getDepartment());

        if(sector.getId() == null){
            return sectorFound.isPresent();
        }

        return sectorFound.isPresent() && !sector.getId().equals(sectorFound.get().getId());
    }
}
