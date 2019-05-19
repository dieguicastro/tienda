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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.tienda.exception.ModeloNotFoundException;
import com.tienda.model.Venta;
import com.tienda.service.IVentaService;


@RestController
@RequestMapping("/ventas")
public class VentaController {

	@Autowired
	private IVentaService service;
	
	@GetMapping  
	public ResponseEntity<List<Venta>> listar() {
		List<Venta> Ventas = service.listar(); 
		return new ResponseEntity<List<Venta>>(Ventas, HttpStatus.OK);
	}
	

	@GetMapping  ("/{id}") 
	public Resource<Venta> listarPorId( @PathVariable("id") Integer idVenta) {
		Venta p =service.leer(idVenta);
		
		if(p == null)
			throw new ModeloNotFoundException("ID " + idVenta + " No encontrado");
		
		Resource<Venta> resource = new Resource<Venta>(p);
		// arrojará http://localhost:8080/pacientes/{id}
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(idVenta)); // parte informativa de la respuesta
		resource.add(linkTo.withRel("Venta-resource"));
		
		return resource;
	}
	
	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Venta> registrar(@Valid @RequestBody Venta v) { 
		 													
		Venta con = service.registrar(v);
		// -- la idea es q imprima la ruta completa del ojeto nuevo creado
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(con.getIdVenta()).toUri();
		return  ResponseEntity.created(location).build();
		
		
	}
	
	
	
	
}
