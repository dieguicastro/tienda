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

@ApiModel(description = "Información de la Persona")
@Entity
@Table(name = "persona")
public class Persona {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPersona;
	
	
	@ApiModelProperty(notes = "Nombres deben tener mínimo 3 carácteres")
	@Size(min = 3, max = 80, message = "Nombres deben tener mínimo 3 carácteres")  
	@Column(nullable = false, length = 400 )
	private String nombres;
	
	@ApiModelProperty(notes = "Apellidos deben tener mínimo 3 carácteres")
	@Size(min = 3, max = 80, message = "Apellidos deben tener mínimo 3 carácteres")
	@Column(nullable = false, length = 400 )
	private String apellidos;


	public Integer getIdPersona() {
		return idPersona;
	}


	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}


	public String getNombres() {
		return nombres;
	}


	public void setNombres(String nombres) {
		this.nombres = nombres;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	
	
	

}
