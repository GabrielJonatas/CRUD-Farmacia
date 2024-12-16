package com.generation.crudFarmacia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.crudFarmacia.model.Produto;
import com.generation.crudFarmacia.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public boolean checarExistenciaCategoria(Produto produto) {
		
		return categoriaRepository.findById(produto.getCategoria().getId())
				.map(categoria -> categoria.getStatus())
				.orElse(false);

	}
}
