package com.produtos.apirest.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.produtos.apirest.models.Produto;
import com.produtos.apirest.repository.ProdutoRepository;

@RestController //anotação do rest
@RequestMapping(value = "/api") //criando URI padrão da aplicação
public class ProdutoResource {  //classe que vai receber as requisições http ------

	//criando ponto de injeção do repository ---------
	@Autowired
	ProdutoRepository produtoRepository;
	
	//Listando todos os produtos -------
	@GetMapping("/produtos") //determinando que a URI vai ser /produtos
	public List<Produto> listaProdutos(){
		return produtoRepository.findAll();
	}
	
	//Listando um produto -------
	@GetMapping("/produto/{id}") //determinando que a URI vai ser /produto/ id do produto que eu vou listar
	//@PathVariable = define q o produto vai receber o id como parametro --------
	public Produto listaProdutoUnico(@PathVariable(value = "id")long id){ 
		return produtoRepository.findById(id); //usando o método criado para listar um unico produto
	}
	
	//Salvando produtos -------
	@PostMapping("/produto")
	public Produto salvaProduto(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}
	
	//Deletando produtos -------
	@DeleteMapping("/produto")
	public void deletaProduto(@RequestBody Produto produto) {  //metodo do tipo void pois não vai retornar nada
		produtoRepository.delete(produto);
	}
	
	//Atualizando produtos -------
	@PutMapping("/produto")
	public Produto atualizaProduto(@RequestBody Produto produto) {  //metodo retorna um produto atualizado
		return produtoRepository.save(produto); //salvando pelo id
	}
	
}
