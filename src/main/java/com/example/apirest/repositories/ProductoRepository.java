package com.example.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apirest.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
