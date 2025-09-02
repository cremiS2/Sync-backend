package com.projeto.tcc.service;

import com.projeto.tcc.dto.entry.EmployeeDTO;
import com.projeto.tcc.dto.exit.EmployeeResultDTO;
import com.projeto.tcc.dto.mappers.EmployeeMapper;
import com.projeto.tcc.entities.Employee;
import com.projeto.tcc.exceptions.NaoRegistradoException;
import com.projeto.tcc.repository.EmployeeRepository;
import com.projeto.tcc.service.validation.EmployeeValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static com.projeto.tcc.repository.specs.EmployeeSpecs.*;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;
    private final EmployeeValidation validation;

    public Employee criarFuncionario(EmployeeDTO dto){
        Employee employee = mapper.toEntity(dto);
        validation.validarEntidade(employee, dto);
        return repository.save(employee);
    }

    public EmployeeResultDTO getFuncionarioId(Long idFuncionario){
        return mapper.toDTO(repository.findById(idFuncionario).orElseThrow(
                () -> new NaoRegistradoException("Funcionário com o id " + idFuncionario + " não existe")));
    }

    public Employee getFuncionarioEntity(Long id){
        return repository.findById(id).orElse(null);
    }

    public void upadateFuncionario(Long id, EmployeeDTO employeeDTO){
        Employee employee = getFuncionarioEntity(id);
        mapper.updateFuncionario(employeeDTO, employee);
        validation.validarEntidade(employee, employeeDTO);
        repository.save(employee);
    }

    public Page<EmployeeResultDTO> pesquisa(
            String name,
            Integer emolyeeID,
            String sector,
            String shift,
            Integer pageNumber,
            Integer pageSize
    ){

        Specification<Employee> specs = Specification.where((root, query, cb) -> cb.conjunction());

        if(name != null){
            specs = specs.and(nameLike(name));
        }
        if(emolyeeID != null){
            specs = specs.and(employeeEqual(emolyeeID));
        }

        if(sector != null){
            specs =  specs.and(sectorLike(sector));
        }
        if(shift != null){
            specs = specs.and(shiftEqual(shift));
        }


        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return repository.findAll(specs,pageable)
                .map(mapper::toDTO);

    }

    public void deletarFuncionario(Long idFuncionario){
        repository.delete(getFuncionarioEntity(idFuncionario));
    }
}
