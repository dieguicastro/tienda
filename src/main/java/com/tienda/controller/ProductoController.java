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
import com.tienda.model.Producto;
import com.tienda.service.IProductoService;


@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private IProductoService service;
	
	@GetMapping  
	public ResponseEntity<List<Producto>> listar() {
		List<Producto> Productos = service.listar(); 
		return new ResponseEntity<List<Producto>>(Productos, HttpStatus.OK);
	}
	

	@GetMapping  ("/{id}") 
	public Resource<Producto> listarPorId( @PathVariable("id") Integer idProducto) {
		Producto p =service.leer(idProducto);
		
		if(p == null)
			throw new ModeloNotFoundException("ID " + idProducto + " No encontrado");
		
		Resource<Producto> resource = new Resource<Producto>(p);
		// arrojará http://localhost:8080/pacientes/{id}
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(idProducto)); // parte informativa de la respuesta
		resource.add(linkTo.withRel("Producto-resource"));
		
		return resource;
	}
	
	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Producto> registrar(@Valid @RequestBody Producto p) { 
		 													
		Producto con = service.registrar(p);
		// -- la idea es q imprima la ruta completa del ojeto nuevo creado
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(con.getIdProducto()).toUri();
		return  ResponseEntity.created(location).build();
		
		
	}

	@PutMapping
	public ResponseEntity<Object> modificar(@Valid @RequestBody Producto p) {
		service.modificar(p);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	

	@DeleteMapping ("/{id}") 
	public ResponseEntity<Object> eliminar( @PathVariable("id") Integer idProducto) {
			Producto p =service.leer(idProducto);
		
		if(p == null)
			throw new ModeloNotFoundException("No se puede eliminar, ID " + idProducto + " No encontrado");
		
		else
			service.eliminar(idProducto);
		
		return new ResponseEntity<Object>(HttpStatus.OK); 
	}
	

	
	
	
}
