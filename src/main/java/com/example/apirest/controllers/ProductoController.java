package com.example.apirest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apirest.entities.Producto;
import com.example.apirest.repositories.ProductoRepository;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private ProductoRepository productoRepository;

	@GetMapping
	public List<Producto> obtenerProductos() {
		return productoRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Producto> getById(@PathVariable Long id) {

	    Optional<Producto> product = productoRepository.findById(id);

	    if (product.isPresent()) {
	        return ResponseEntity.ok(product.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

	@PostMapping
	public Producto crearProducto(@RequestBody Producto producto) {
		return productoRepository.save(producto);

	}

	@PutMapping("/{id}")
	public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto detallesProducto) {
		Producto producto = productoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("No se encontro el producto con ID: " + id));
		producto.setNombre(detallesProducto.getNombre());
		producto.setPrecio(detallesProducto.getPrecio());
		return productoRepository.save(producto);

	}

	@DeleteMapping("/{id}")
	public String eliminarPorId(@PathVariable Long id) {
		Producto producto = productoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("No se encontro el producto con ID: " + id));
		productoRepository.deleteById(id);
		return "El Producto con ID: " + producto.getId() + " fue eliminado ";
	}

}
