package com.tienda.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.model.Producto;
import com.tienda.repo.IProductoRepo;
import com.tienda.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService {

	
	@Autowired
	private IProductoRepo repo;
	
	
	@Override
	public Producto registrar(Producto t) {
		return repo.save(t);
	}

	@Override
	public Producto modificar(Producto t) {
		return repo.save(t);
	}

	@Override
	public Producto leer(Integer id) {
		return repo.findOne(id);
	}

	@Override
	public List<Producto> listar() {
		return repo.findAll();
	}

	@Override
	public void eliminar(Integer id) {
		 repo.delete(id);
	}

}
