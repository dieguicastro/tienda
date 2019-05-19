package com.tienda.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.model.Venta;
import com.tienda.repo.IVentaRepo;
import com.tienda.service.IVentaService;

@Service
public class VentaServiceImpl implements IVentaService {

	
	@Autowired
	private IVentaRepo repo;
	
	
	@Override
	public Venta registrar(Venta t) {
		return repo.save(t);
	}

	@Override
	public Venta modificar(Venta t) {
		return repo.save(t);
	}

	@Override
	public Venta leer(Integer id) {
		return repo.findOne(id);
	}

	@Override
	public List<Venta> listar() {
		return repo.findAll();
	}

	@Override
	public void eliminar(Integer id) {
		 repo.delete(id);
	}

}
