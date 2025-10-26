package com.projeto.tcc.service;

import com.projeto.tcc.dto.entry.EmployeeDTO;
import com.projeto.tcc.dto.exit.EmployeeResultDTO;
import com.projeto.tcc.dto.mappers.EmployeeMapper;
import com.projeto.tcc.entities.Employee;
import com.projeto.tcc.exceptions.NaoRegistradoException;
import com.projeto.tcc.repository.EmployeeRepository;
import com.projeto.tcc.service.validation.EmployeeValidation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.projeto.tcc.repository.specs.EmployeeSpecs.*;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;
    private final EmployeeValidation validation;

    public Employee saveEmployee(EmployeeDTO dto){
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


    @Transactional
    public void deleteEmployee(Long idFuncionario){
        Employee employee = repository.findById(idFuncionario)
                .orElseThrow(() -> new NaoRegistradoException("Funcionário não encontrado"));

        if (employee.getUser() != null) {
            employee.getUser().setEmployee(null);
            employee.setUser(null);
        }

        repository.delete(employee);
    }

    @Transactional
    public ResponseEntity<byte[]> gerarRelatorioFuncionarios() {
        try {
            // 1️⃣ Busca todos os funcionários
            List<Employee> funcionarios = repository.findAll();

            if (funcionarios.isEmpty()) {
                throw new NaoRegistradoException("Nenhum funcionário encontrado para gerar o relatório");
            }

            // 2️⃣ Converte pra DTO (opcional, mas mantém o padrão do teu projeto)
            List<EmployeeResultDTO> dados = funcionarios.stream()
                    .map(mapper::toDTO)
                    .toList();

            // 3️⃣ Localiza o arquivo do relatório (coloca o .jrxml ou .jasper em src/main/resources/relatorios)
            InputStream relatorioStream = new ClassPathResource("relatorios/relatorio_funcionarios.jrxml").getInputStream();

            // 4️⃣ Compila o relatório
            JasperReport jasperReport = JasperCompileManager.compileReport(relatorioStream);

            // 5️⃣ Preenche os parâmetros (exemplo: logo e título)
            Map<String, Object> params = new HashMap<>();
            params.put("logo", "https://upload.wikimedia.org/wikipedia/commons/a/ab/Logo_Teste.png");
            params.put("titulo", "Relatório de Funcionários");

            // 6️⃣ Cria o datasource com os dados
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados);

            // 7️⃣ Preenche o relatório
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

            // 8️⃣ Exporta pra PDF
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

            // 9️⃣ Retorna o PDF pronto pra download
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=relatorio_funcionarios.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar relatório: " + e.getMessage(), e);
        }
    }
}
