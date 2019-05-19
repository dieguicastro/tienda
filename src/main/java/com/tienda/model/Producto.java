package com.tienda.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "Información del producto")
@Entity
@Table(name = "producto")
public class Producto {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProducto;
	
	
	@ApiModelProperty(notes = "Nombres de prodcuto deben tener mínimo 3 carácteres")
	@Size(min = 3, max = 80, message = "Nombres de prodcuto  deben tener mínimo 3 carácteres")  
	@Column(nullable = false, length = 400 )
	private String nombre;
	
	@ApiModelProperty(notes = "Marcas deben tener mínimo 3 carácteres")
	@Size(min = 3, max = 80, message = "Marcas deben tener mínimo 3 carácteres")
	@Column(nullable = false, length = 400 )
	private String marca;

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	
	


}
