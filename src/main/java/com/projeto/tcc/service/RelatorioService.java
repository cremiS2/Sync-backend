package com.projeto.tcc.service;

import com.projeto.tcc.entities.Employee;
import com.projeto.tcc.entities.Machine;
import com.projeto.tcc.repository.EmployeeRepository;
import com.projeto.tcc.repository.MachineRepository;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final EmployeeRepository employeeRepository;
    private final MachineRepository machineRepository;

    public byte[] gerarRelatorioGeral() throws JRException {
        try {
            // Compilar subrelatórios
            InputStream subFuncStream = new ClassPathResource("relatorio/subreport_funcionarios.jrxml").getInputStream();
            JasperReport subFuncReport = JasperCompileManager.compileReport(subFuncStream);
            
            InputStream subMaqStream = new ClassPathResource("relatorio/subreport_maquinas.jrxml").getInputStream();
            JasperReport subMaqReport = JasperCompileManager.compileReport(subMaqStream);

            // Carregar o template JRXML principal
            InputStream jasperStream = new ClassPathResource("relatorio/relatorio_completo.jrxml").getInputStream();
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);

            // Buscar dados
            List<Employee> funcionarios = employeeRepository.findAll();
            List<Machine> maquinas = machineRepository.findAll();

            // Criar DataSources
            JRBeanCollectionDataSource funcionariosDataSource = new JRBeanCollectionDataSource(funcionarios);
            JRBeanCollectionDataSource maquinasDataSource = new JRBeanCollectionDataSource(maquinas);

            // Parâmetros do relatório
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("titulo", "Relatório Geral - Funcionários e Máquinas");
            parametros.put("FuncionariosDataSource", funcionariosDataSource);
            parametros.put("MaquinasDataSource", maquinasDataSource);
            parametros.put("SUBREPORT_FUNCIONARIOS", subFuncReport);
            parametros.put("SUBREPORT_MAQUINAS", subMaqReport);

            // Preencher o relatório
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());

            // Exportar para PDF
            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            throw new JRException("Erro ao gerar relatório: " + e.getMessage(), e);
        }
    }

    public byte[] gerarRelatorioFuncionarios() throws JRException {
        try {
            InputStream jasperStream = new ClassPathResource("relatorio/relatorio_funcionarios.jrxml").getInputStream();
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);

            List<Employee> funcionarios = employeeRepository.findAll();
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(funcionarios);

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("titulo", "Relatório de Funcionários");
            parametros.put("logo", getClass().getClassLoader().getResource("static/logo.png").getPath());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);
            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            throw new JRException("Erro ao gerar relatório de funcionários: " + e.getMessage(), e);
        }
    }

    public byte[] gerarRelatorioMaquinas() throws JRException {
        try {
            InputStream jasperStream = new ClassPathResource("relatorio/relatorio_maquinas.jrxml").getInputStream();
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);

            List<Machine> maquinas = machineRepository.findAll();
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(maquinas);

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("titulo", "Relatório de Máquinas");
            parametros.put("logo", getClass().getClassLoader().getResource("static/logo.png").getPath());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);
            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            throw new JRException("Erro ao gerar relatório de máquinas: " + e.getMessage(), e);
        }
    }
}
