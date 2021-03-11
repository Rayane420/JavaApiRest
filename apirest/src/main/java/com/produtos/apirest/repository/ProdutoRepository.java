package com.produtos.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.produtos.apirest.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	//Método personalizado para buscar um unico produto pelo id -------
		Produto findById(long id);
	
}
