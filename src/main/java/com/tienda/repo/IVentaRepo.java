package com.tienda.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.model.Venta;

public interface IVentaRepo extends JpaRepository<Venta, Integer> {

}
