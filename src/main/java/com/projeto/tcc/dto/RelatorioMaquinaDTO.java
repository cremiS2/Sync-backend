package com.projeto.tcc.dto;

import com.projeto.tcc.entities.Maquina;
import com.projeto.tcc.enuns.StatusMaquina;

import java.time.LocalDate;

public class RelatorioMaquinaDTO {

    private String nome;
    private LocalDate ultima_manutencao;
    private String setor;
    private StatusMaquina status;

    public RelatorioMaquinaDTO(Maquina maquina) {
        this.nome = maquina.getNome();
        this.ultima_manutencao = maquina.getUltima_manutencao();
        this.setor = maquina.getSetor().getNome();
        this.status = maquina.getStatus();


    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getUltima_manutencao() {
        return ultima_manutencao;
    }

    public void setUltima_manutencao(LocalDate ultima_manutencao) {
        this.ultima_manutencao = ultima_manutencao;
    }

    public StatusMaquina getStatus() {
        return status;
    }

    public void setStatus(StatusMaquina status) {
        this.status = status;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
}
