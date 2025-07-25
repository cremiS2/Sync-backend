package com.projeto.tcc.service;

import com.projeto.tcc.dto.entrada.MaquinaDTO;
import com.projeto.tcc.dto.mappers.MaquinaMapper;
import com.projeto.tcc.dto.pesquisa.MaquinaResultadoDTO;
import com.projeto.tcc.entities.Maquina;
import com.projeto.tcc.exceptions.NaoRegistradoExcpetion;
import com.projeto.tcc.repository.MaquinaRepositoy;
import com.projeto.tcc.service.validation.MaquinaValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static com.projeto.tcc.repository.specs.MaquinaSpecs.*;

@Service
@RequiredArgsConstructor
public class MaquinaService {

    private final MaquinaRepositoy maquinaRepositoy;
    private final MaquinaMapper mapper;
    private final MaquinaValidation validation;


    public Maquina salvarMaquina(MaquinaDTO maquinaDTO) {
        Maquina maquinaEntidade = mapper.toEntity(maquinaDTO);
        validation.validarEntidade(maquinaEntidade);
        return maquinaRepositoy.save(maquinaEntidade);
    }

    public MaquinaResultadoDTO getMaquinaId(Long idMaquina){
        return mapper.toDTO(maquinaRepositoy.findById(idMaquina)
                .orElseThrow(() -> new NaoRegistradoExcpetion("Máquina com o id " + idMaquina + " não cadastrada!"))
                );
    }

    public Maquina getIdReturnMaquina(Long idMaquina){
        return maquinaRepositoy.findById(idMaquina)
                .orElseThrow(() -> new NaoRegistradoExcpetion(
                        "Máquina com o id "
                                + idMaquina + " não cadastrada!"));
    }

    public void updateMaquina(Long idMaquina, MaquinaDTO dto){
        Maquina maquina = getIdReturnMaquina(idMaquina);
        mapper.updateEntityFromDto(dto, maquina);
        if(dto.funcionarioOperando() == null){
            maquina.setFuncionarioOperando(null);
        }
        validation.validarEntidade(maquina);
        maquinaRepositoy.save(maquina);
    }

    public Page<Maquina> pesquisa(
            String nomeMaquina,
            Integer numeroSerie,
            String nomeUnidadeLocal,
            String nomeModelo,
            String nomeSetor,
            String statusMaquina,
            String nomeFuncionario,
            Integer numeroPagina,
            Integer tamanhoPagina
    ) {
        Specification<Maquina> specs = Specification.where(((root, query, cb) -> cb.conjunction()));
        if (nomeMaquina != null) {
            specs = specs.and(nomeMaquinaLike(nomeMaquina));
        }
        if (numeroSerie != null) {
            specs = specs.and(numeroSerieLike(numeroSerie));
        }
        if (nomeUnidadeLocal != null) {
            specs = specs.and(localLike(nomeUnidadeLocal));
        }
        if (nomeModelo != null) {
            specs = specs.and(modeloMaquinaLike(nomeModelo));
        }
        if (nomeSetor != null) {
            specs = specs.and(nomeSetorLike(nomeSetor));
        }
        if (statusMaquina != null) {
            specs = specs.and(statusMaquinaLike(statusMaquina));
        }
        if (nomeFuncionario != null) {
            specs = specs.and(nomeFuncionarioLike(nomeFuncionario));
        }

        Pageable pageableRequest = PageRequest.of(numeroPagina, tamanhoPagina);

        return maquinaRepositoy.findAll(specs, pageableRequest);

    }


    public void deletarMaquina(Long idMaquina){
        var entidade = getIdReturnMaquina(idMaquina);
        maquinaRepositoy.delete(entidade);
    }
}

