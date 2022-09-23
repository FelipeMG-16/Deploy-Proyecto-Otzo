package com.generation.ecommerceDB.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.generation.ecommerceDB.model.Producto;
import com.generation.ecommerceDB.service.ProductoService;


@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })

@RestController
@RequestMapping(path="/api/productos/")
public class ProductoController {
	
	//Creamos la constante de nuestro servicio
	public final ProductoService prodService;
	
	
	@Autowired //Inyeccion de dependencias
	public ProductoController(ProductoService prodService) {
		this.prodService = prodService;
	}//constructor
	
	
	
	// Metodo GET para todos los productos
	@GetMapping
	public List<Producto> getProductos() {
		return prodService.getProductos();
	}// getProductos

	
	// Metodo GET para un producto por id
	@GetMapping(path = "{prodId}")
	public Producto getProducto(@PathVariable("prodId") Long prodId) {
		return prodService.getProducto(prodId);
	}// getProducto

	
	// Metodo POST para agregar un producto
	@PutMapping(path = "{prodId}")
	public void updateProducto(@PathVariable("prodId") Long prodId,
		@RequestParam(required = false) String nombre,
		@RequestParam(required = false) String descripcion,
		@RequestParam(required = false) Double precio,
		@RequestParam(required = false) String url_Imagen) {
		prodService.updateProducto(prodId, nombre, descripcion, precio, url_Imagen);
	}// updateProducto
	

	// Metodo DELETE un producto por id
	@DeleteMapping(path = "{prodId}")
	public void deleteProducto(@PathVariable("prodId") Long prodId) {
		prodService.getProducto(prodId);
	}// deleteProducto

	
	// Metodo POST agregar un producto
	@PostMapping
	public void addProducto(@RequestBody Producto prod) {
		prodService.addProduct(prod);
	}// addProducto

}
