package com.projeto.tcc.service;

import com.projeto.tcc.dto.MaquinaDTO;
import com.projeto.tcc.entities.Funcionario;
import com.projeto.tcc.entities.Maquina;
import com.projeto.tcc.entities.Setor;
import com.projeto.tcc.enuns.StatusMaquina;
import com.projeto.tcc.repository.FuncionarioRepository;
import com.projeto.tcc.repository.MaquinaRepositoy;
import com.projeto.tcc.repository.SetorRepository;
import com.projeto.tcc.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MaquinaService {

    private MaquinaRepositoy maquinaRepositoy;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private SetorRepository setorRepository;

    public MaquinaService(MaquinaRepositoy maquinaRepositoy) {
        this.maquinaRepositoy = maquinaRepositoy;
    }

    public void salvarMaquina(MaquinaDTO maquinaDTO) {
        Maquina maquina = new Maquina();
        maquina.setStatus(StatusMaquina.OPERANDO);
        maquina.setNome(maquinaDTO.nome());
        var func = new Funcionario();
        func.setNome(maquinaDTO.nome());
        func = funcionarioRepository.save(func);
        var setor = new Setor();
        setor.setNome("Teste");
        setor.setDescricao("etetedvgvgd");
        setor = setorRepository.save(setor);
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(func);
        setor.setFuncionarios(funcionarios);
        maquina.setSetor(setor);
        maquina.setUltima_manutencao(LocalDate.now());
        maquinaRepositoy.save(maquina);
    }
}

