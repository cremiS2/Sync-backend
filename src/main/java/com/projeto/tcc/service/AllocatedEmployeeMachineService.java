package com.projeto.tcc.service;

import com.projeto.tcc.dto.entry.AllocatedEmployeeMachineDTO;
import com.projeto.tcc.dto.exit.AllocatedEmployeeMachineResultDTO;
import com.projeto.tcc.dto.mappers.AllocatedEmployeeMachineMapper;
import com.projeto.tcc.entities.AllocatedEmployeeMachine;
import com.projeto.tcc.entities.User;
import com.projeto.tcc.exceptions.NaoRegistradoException;
import com.projeto.tcc.repository.AllocatedEmployeesMachineRepository;
import com.projeto.tcc.security.CustomUserDetails;
import com.projeto.tcc.service.validation.AllocatedEmployeesMachineValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import static com.projeto.tcc.repository.specs.AllocatedEmployeeMachineSpecs.*;

@Service
@RequiredArgsConstructor
public class AllocatedEmployeeMachineService {

    private final AllocatedEmployeesMachineRepository repository;
    private final AllocatedEmployeeMachineMapper mapper;
    private final AllocatedEmployeesMachineValidation validation;
    private final UserService userService;



    public Long createAllocatedEmployees(AllocatedEmployeeMachineDTO dto) {

        AllocatedEmployeeMachine allocatedEmployeeMachine = mapper.toEntity(dto);

        // Validar primeiro antes de tentar acessar propriedades
        validation.validEntity(allocatedEmployeeMachine);

        Authentication employeeChanged =  SecurityContextHolder.getContext().getAuthentication();
        User userChanged = userService.findByEmail(employeeChanged.getName());

        // Apenas define o changedEmployee se o usuário tiver um employee associado
        if (userChanged != null && userChanged.getEmployee() != null) {
            allocatedEmployeeMachine.setChangedEmployee(userChanged.getEmployee());
        }

        // Garantir que o employee não é null antes de acessar
        if (allocatedEmployeeMachine.getEmployee() != null) {
            allocatedEmployeeMachine.getEmployee().setAvailability(false);
        }

        return repository.save(allocatedEmployeeMachine).getId();
    }

    public Page<AllocatedEmployeeMachineResultDTO> search(
            String nameEmployee,
            String nameEmployeeChanged,
            Integer pageSize,
            Integer pageNumber){

        Specification<AllocatedEmployeeMachine> specs = Specification.where((root, query, cb) -> cb.conjunction());

        if(nameEmployee != null){
            specs = specs.and(employeeLike(nameEmployee));
        }
        if(nameEmployee != null){
            specs = specs.and(employeeLike(nameEmployeeChanged));
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return repository.findAll(specs, pageable).map(mapper::toDTO);
    }


    private AllocatedEmployeeMachine getAllocatedById(Long id){
        return repository.findById(id).orElseThrow(() -> new NaoRegistradoException("Alocação não encontrada"));
    }

    public void deleteAllocation(Long id) {
        AllocatedEmployeeMachine allocation = getAllocatedById(id);
        
        // Liberar o funcionário
        if (allocation.getEmployee() != null) {
            allocation.getEmployee().setAvailability(true);
        }
        
        repository.delete(allocation);
    }


}
