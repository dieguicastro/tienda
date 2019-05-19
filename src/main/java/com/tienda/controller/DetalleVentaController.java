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
import com.tienda.model.DetalleVenta;
import com.tienda.service.IDetalleVentaService;



@RestController
@RequestMapping("/detalles")
public class DetalleVentaController {

	@Autowired
	private IDetalleVentaService service;
	
	@GetMapping  
	public ResponseEntity<List<DetalleVenta>> listar() {
		List<DetalleVenta> Ventas = service.listar(); 
		return new ResponseEntity<List<DetalleVenta>>(Ventas, HttpStatus.OK);
	}
	

	@GetMapping  ("/{id}") 
	public Resource<DetalleVenta> listarPorId( @PathVariable("id") Integer idDetalle) {
		DetalleVenta p =service.leer(idDetalle);
		
		if(p == null)
			throw new ModeloNotFoundException("ID " + idDetalle + " No encontrado");
		
		Resource<DetalleVenta> resource = new Resource<DetalleVenta>(p);
		// arrojará http://localhost:8080/pacientes/{id}
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(idDetalle)); // parte informativa de la respuesta
		resource.add(linkTo.withRel("DetalleVenta-resource"));
		
		return resource;
	}
	
	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<DetalleVenta> registrar(@Valid @RequestBody DetalleVenta p) { 
		 													
		DetalleVenta con = service.registrar(p);
		// -- la idea es q imprima la ruta completa del ojeto nuevo creado
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(con.getIdDetalleVenta()).toUri();
		return  ResponseEntity.created(location).build();
		
		
	}
	
	
	
	
}
