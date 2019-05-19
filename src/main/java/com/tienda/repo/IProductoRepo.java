package com.tienda.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.model.Producto;

public interface IProductoRepo extends JpaRepository<Producto, Integer> {

}
