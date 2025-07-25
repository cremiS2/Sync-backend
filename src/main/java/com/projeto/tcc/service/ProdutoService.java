package com.projeto.tcc.service;

import com.projeto.tcc.dto.entrada.ProdutoDTO;
import com.projeto.tcc.dto.mappers.ProdutoMapper;
import com.projeto.tcc.dto.pesquisa.ProdutoResultadoDTO;
import com.projeto.tcc.entities.Maquina;
import com.projeto.tcc.entities.Produto;
import com.projeto.tcc.exceptions.NaoRegistradoExcpetion;
import com.projeto.tcc.repository.ProdutoRepository;
import com.projeto.tcc.service.validation.ProdutoValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;
    private final ProdutoValidation validation;
    private final MaquinaService service;


    public Produto salvarProduto(ProdutoDTO dto){
        Produto produtoEntity = mapper.toEntity(dto);
        List<Maquina> maquinas =
        dto.maquinasId()
                .stream()
                .map(service::getIdReturnMaquina)
                .toList();
        produtoEntity.setMaquinas(maquinas);
        validation.validarEntidade(produtoEntity);
        return repository.save(produtoEntity);
    }

    public ProdutoResultadoDTO getProdutoId(Long IdProduto){
        return mapper.toDTO(repository.findById(IdProduto)
                .orElseThrow(() -> new NaoRegistradoExcpetion("Produto com id " + IdProduto + " não cadastrado")));
    }
}
