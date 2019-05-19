package com.tienda.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.model.Persona;
import com.tienda.repo.IPersonaRepo;
import com.tienda.service.IPersonaService;

@Service
public class PersonaServiceImpl implements IPersonaService {

	
	@Autowired
	private IPersonaRepo repo;
	
	
	@Override
	public Persona registrar(Persona t) {
		return repo.save(t);
	}

	@Override
	public Persona modificar(Persona t) {
		return repo.save(t);
	}

	@Override
	public Persona leer(Integer id) {
		return repo.findOne(id);
	}

	@Override
	public List<Persona> listar() {
		return repo.findAll();
	}

	@Override
	public void eliminar(Integer id) {
		 repo.delete(id);
	}

}
