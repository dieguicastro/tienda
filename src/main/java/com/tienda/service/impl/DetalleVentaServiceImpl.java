package com.tienda.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.model.DetalleVenta;
import com.tienda.repo.IDetalleVentaRepo;
import com.tienda.service.IDetalleVentaService;

@Service
public class DetalleVentaServiceImpl implements IDetalleVentaService {

	
	@Autowired
	private IDetalleVentaRepo repo;



	@Override
	public DetalleVenta registrar(DetalleVenta t) {
		return repo.save(t);
	}

	@Override
	public DetalleVenta modificar(DetalleVenta t) {
		return repo.save(t);
	}

	@Override
	public DetalleVenta leer(Integer id) {
		return repo.findOne(id);
	}

	@Override
	public List<DetalleVenta> listar() {
		return repo.findAll();
	}

	@Override
	public void eliminar(Integer id) {
		 repo.delete(id);
	}

}
