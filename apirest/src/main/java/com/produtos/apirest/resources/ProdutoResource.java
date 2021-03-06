package com.produtos.apirest.resources;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.produtos.apirest.models.Produto;
import com.produtos.apirest.repository.ProdutoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController //anotação do rest
@RequestMapping(value = "/api") //criando URL padrão da aplicação
@Api(value="API REST Produtos")
@CrossOrigin(origins="*") //liberando todos os dominios para acessar a API
public class ProdutoResource {  //classe que vai receber as requisições http ------

	//criando ponto de injeção do repository ---------
	@Autowired
	ProdutoRepository produtoRepository;
	
	//Listando todos os produtos -------
	@GetMapping("/produtos") //mapeando a solicitação http
	@ApiOperation(value="Retorna uma lista de produtos")
	public List<Produto> listaProdutos(){
		return produtoRepository.findAll();
	}
	
	//Listando um produto -------
	@GetMapping("/produto/{id}") //mapeando a solicitação http
	//@PathVariable = define q o produto vai receber o id como parametro --------
	@ApiOperation(value="Retorna um produto unico")
	public Produto listaProdutoUnico(@PathVariable long id){ 
		return produtoRepository.findById(id); //usando o método criado para listar um unico produto
	}
	
	//Salvando produtos -------
	@PostMapping("/produto")
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation(value="Salva um novo produto")
	public Produto salvaProduto(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}
	
	//Deletando produtos -------
	@DeleteMapping("/produto/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation(value="Deleta um produto")
	public void deletaProduto(@PathVariable long id) {  //metodo sem retorno
		produtoRepository.deleteById(id);
	}
	
	//Atualizando produtos -------
	@PutMapping("/produto/{id}") //mapeando a solicitação http
	//@PathVariable = define q o produto vai receber o id como parametro --------
	@ApiOperation(value="Atualiza um produto")
	public Produto atualizaProduto(@PathVariable long id, @RequestBody Produto produto){ 
		Produto produtoAtualizado = atualizar(id, produto);
		return produtoAtualizado;
	}
	
	//Método da camada de serviço
	public Produto atualizar(long pId, Produto pProduto) {
		Produto produtoSalvo = produtoRepository.findById(pId);
		//System.out.println(produtoSalvo);
		BeanUtils.copyProperties(pProduto, produtoSalvo, "id");
		System.out.println(pProduto);
				
		return produtoRepository.save(produtoSalvo);
	}
}
