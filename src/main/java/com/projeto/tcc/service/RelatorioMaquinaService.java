package com.projeto.tcc.service;

import com.projeto.tcc.dto.RelatorioMaquinaDTO;
import com.projeto.tcc.entities.Maquina;
import com.projeto.tcc.repository.MaquinaRepositoy;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RelatorioMaquinaService {

    @Autowired
    MaquinaRepositoy maquinaRepositoy;

    public void gerarRelatorio(String caminho) throws JRException {

        List<Maquina> maquinas = maquinaRepositoy.findAll();

        List<RelatorioMaquinaDTO> dados = maquinas.stream().map(p -> new RelatorioMaquinaDTO(p)).collect(Collectors.toList());
        System.out.println(maquinas.get(1).getStatus());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dados);

        Map<String, Object> parametros = new HashMap<>();

        parametros.put("titulo", "Relatório de máquinas");

        JasperReport jasperReport = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/relatorio/relatorio_maquinas.jrxml")
        );

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, caminho);

    }
}
