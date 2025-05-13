package com.projeto.tcc.controller;

import com.projeto.tcc.repository.MaquinaRepositoy;
import com.projeto.tcc.service.RelatorioMaquinaService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("relatorios")
public class RelatorioMaquinaController {

    private RelatorioMaquinaService relatorioMaquinaService;
    @Autowired
    private MaquinaRepositoy maquinaRepositoy;

    public RelatorioMaquinaController(RelatorioMaquinaService relatorioMaquinaService) {
        this.relatorioMaquinaService = relatorioMaquinaService;
    }

    @GetMapping
    public ResponseEntity<String> gerarRelatorio(@RequestParam String caminho) throws JRException {
        relatorioMaquinaService.gerarRelatorio(caminho);
        return ResponseEntity.ok("Relatorio gerado com sucesso!" + caminho);
    }
}
