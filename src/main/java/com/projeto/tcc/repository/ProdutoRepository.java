package com.projeto.tcc.repository;

import com.projeto.tcc.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository  extends JpaRepository<Produto, Long>{

    Optional<Produto> findByCodigoProdutoAndNomeProduto(String codigoProduto, String nomeProduto);
}
