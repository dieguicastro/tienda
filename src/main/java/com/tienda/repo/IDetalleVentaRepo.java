package com.tienda.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.model.DetalleVenta;


public interface IDetalleVentaRepo extends JpaRepository<DetalleVenta, Integer> {

}
