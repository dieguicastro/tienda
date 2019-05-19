package com.tienda.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.tienda.exception.ModeloNotFoundException;
import com.tienda.model.Persona;
import com.tienda.service.IPersonaService;


@RestController
@RequestMapping("/personas")
public class PersonaController {

	@Autowired
	private IPersonaService service;
	
	@GetMapping  
	public ResponseEntity<List<Persona>> listar() {
		List<Persona> Personas = service.listar(); 
		return new ResponseEntity<List<Persona>>(Personas, HttpStatus.OK);
	}
	

	@GetMapping  ("/{id}") 
	public Resource<Persona> listarPorId( @PathVariable("id") Integer idPersona) {
		Persona p =service.leer(idPersona);
		
		if(p == null)
			throw new ModeloNotFoundException("ID " + idPersona + " No encontrado");
		
		Resource<Persona> resource = new Resource<Persona>(p);
		// arrojará http://localhost:8080/pacientes/{id}
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(idPersona)); // parte informativa de la respuesta
		resource.add(linkTo.withRel("persona-resource"));
		
		return resource;
	}
	
	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Persona> registrar(@Valid @RequestBody Persona c) { 
		 													
		Persona con = service.registrar(c);
		// -- la idea es q imprima la ruta completa del ojeto nuevo creado
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(con.getIdPersona()).toUri();
		return  ResponseEntity.created(location).build();
		
		
	}

	@PutMapping
	public ResponseEntity<Object> modificar(@Valid @RequestBody Persona c) {
		service.modificar(c);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	

	@DeleteMapping ("/{id}") 
	public ResponseEntity<Object> eliminar( @PathVariable("id") Integer idPersona) {
			Persona p =service.leer(idPersona);
		
		if(p == null)
			throw new ModeloNotFoundException("No se puede eliminar, ID " + idPersona + " No encontrado");
		
		else
			service.eliminar(idPersona);
		
		return new ResponseEntity<Object>(HttpStatus.OK); 
	}
	

	
	
	
}
