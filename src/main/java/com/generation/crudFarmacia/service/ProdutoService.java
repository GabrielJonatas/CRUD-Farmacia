package com.generation.crudFarmacia.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.crudFarmacia.model.Produto;
import com.generation.crudFarmacia.model.ProdutoPromocao;
import com.generation.crudFarmacia.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Optional<ProdutoPromocao> calcularPromocao(Long id) {
		
		ProdutoPromocao produtoPromocao = null;
		
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if(produto.isPresent()) {
			Produto produtoNotOptional = produto.get();
			
			BigDecimal precoAtual = produtoNotOptional.getPreco();
			
		    BigDecimal desconto = BigDecimal.valueOf(produtoNotOptional.getDesconto()).divide(BigDecimal.valueOf(100));
		    BigDecimal novoPreco = precoAtual.subtract(precoAtual.multiply(desconto)).setScale(2);
		    
		    produtoPromocao = new ProdutoPromocao(id, 
		    		produtoNotOptional.getNome(), produtoNotOptional.getDescricao(), novoPreco, 
		    		produtoNotOptional.getQuantidadeEstoque(), produtoNotOptional.getCategoria());
		    
		    return Optional.of(produtoPromocao);
		} else {
			return Optional.empty();
		}
	}
	
}
