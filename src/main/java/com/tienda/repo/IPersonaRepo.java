package com.tienda.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.model.Persona;

public interface IPersonaRepo extends JpaRepository<Persona, Integer> {

}
