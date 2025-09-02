package com.projeto.tcc.service;

import com.projeto.tcc.dto.entry.DepartmentDTO;
import com.projeto.tcc.dto.exit.DepartmentResultDTO;
import com.projeto.tcc.dto.mappers.DepartmentMapper;
import com.projeto.tcc.entities.Department;
import com.projeto.tcc.exceptions.NaoRegistradoException;
import com.projeto.tcc.repository.DepartmentRepository;
import com.projeto.tcc.service.validation.DepartmentValidation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import static com.projeto.tcc.repository.specs.DepartmentSpecs.*;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository repository;
    private final DepartmentMapper mapper;
    private final DepartmentValidation validation;


    public Department saveDepartment(DepartmentDTO dto){
        var departmentEntidade = mapper.toEntity(dto);
        validation.validarEntidade(departmentEntidade);
        departmentEntidade.setStatus(validation.statusDepartment(dto));
        return repository.save(departmentEntidade);
    }

    public DepartmentResultDTO getDepartmentId(Long departmentId){
        return mapper.toDTO(repository.findById(departmentId).orElseThrow(
                () -> new NaoRegistradoException("Departamento com o id " + departmentId + " não existe"))
        );
    }

    public boolean checkEntity(Long departmentId){
       return repository.existsById(departmentId);
    }

    public void deleteDepartment(Long departmentId){
            repository.delete(
                    repository.findById(departmentId)
                            .orElseThrow(() -> new NaoRegistradoException("Departamento não registrado"))
            );
        }

    public void updateDepartment(Long idDepartment, DepartmentDTO departmentDTO){
        repository.findById(idDepartment).map(
                d -> {
                    mapper.updateEntity(d, departmentDTO);
                    validation.validarEntidade(d);
                    if(departmentDTO.status() != null){
                        d.setStatus(validation.statusDepartment(departmentDTO));
                    }
                    return repository.save(d);
                })
                .orElseThrow(() -> new NaoRegistradoException("não encontrado"));
    }


    public Page<DepartmentResultDTO> pesquisa(
            String name,
            String status,
            BigDecimal budget,
            Integer pageNumber,
            Integer pageSize
    ){

        Specification<Department> specs = Specification.where((root, query, cb) -> cb.conjunction());

        if(name != null){
            specs = specs.and(nameLike(name));
        }
//        if(location != null){
//            specs = specs.and(locationLike(location));
//        }
        if(status != null){
            specs = specs.and(statusLike(status));
        }
        if(budget != null){
            specs = specs.and(budgetEqual(budget));
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return repository.findAll(specs, pageable).map(mapper::toDTO);
    }

    }
