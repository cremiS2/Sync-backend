package com.projeto.tcc.service.validation;

import com.projeto.tcc.entities.Produto;
import com.projeto.tcc.exceptions.ConflitoCampoException;
import com.projeto.tcc.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProdutoValidation {

    private final ProdutoRepository repository;

    public void validarEntidade(Produto produto){
        if(existeBoolean(produto)){
            throw new ConflitoCampoException("Informações já cadastradas!");
        }
    }

    private boolean existeBoolean(Produto produto){
        var procura = repository.findByCodigoProdutoAndNomeProduto(
                produto.getCodigoProduto(),
                produto.getNomeProduto()
        );

        if(produto.getId() == null){
            return procura.isPresent();
        }

        return procura.
                map(p -> !p.getId().equals(produto.getId())
                ).orElse(false);
    }

}
