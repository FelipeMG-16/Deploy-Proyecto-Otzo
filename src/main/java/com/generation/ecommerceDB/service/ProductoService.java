package com.generation.ecommerceDB.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.generation.ecommerceDB.model.Producto;


@Service
public class ProductoService {

	//Ya no vamos a crear un ArrayList, ahora vamos a conectar un repository o BD a nuestro Service
	//Para esto creamos esta constante para poder hacer la conexion con ese repositorio (los mismos pasos que en el controller, solo que aqui vamos a apuntar a nuestro repository para que se haga la conexion.
	
	//constante
	private final ProductosRepository productosRepository;
	
	@Autowired
	public ProductoService(ProductosRepository productosRepository) {
		this.productosRepository = productosRepository;
	}//constructor
	
	
	/*
	 * La gran ventaja de trabajar con JPA es que para el funcionamiento de los metodos, no vamos a tener que emplear codigo, si no que vamos a utilizar las consultas o metodos que ya tiene elaboradas. En el caso de que queramos hacer alguna consulta personalizada, tambien lo vamos a poder hacer. 
	 */
	
	//Metodo GET para todos los productos
	public List<Producto> getProductos() {
		return productosRepository.findAll(); //constula que regresa todos los elementos del tipo producto
	}//getProductos

	
	//Metodo GET para un producto con un ID
	public Producto getProducto(Long prodId) {
		return productosRepository.findById(prodId).//no usamos el getById porque si no encuentre el id, nos muestra la excepcion. Por eso usamos un findById para que nos mande un producto, para que en caso de que no exista, no muestre un error y podamos manejarlo con una excepcion.
				
		//esto es por si no lo encuentra...
		orElseThrow(()-> new IllegalStateException("El Producto con el ID" + prodId + "no existe."));
	}//getProducto


	//Metodo DELETE para borrar un producto con un ID
	public void deleteProducto(Long proId) {
		if (productosRepository.existsById(proId)) { //Si la entidad con ese ID existe...
			productosRepository.deleteById(proId);//...borramos esa entidad
		}//if existe
	}//deleteProducto
	
	
	//No podemos agregar productos por id, tenemos que agregarlo por nombre
	//no hay metodo para agregar por nombre, tenemos que crearlo en nuestro repository
		public void addProduct(Producto prod) {
			Optional <Producto> prodByName =
					productosRepository.findByNombre(prod.getNombre()); //busco el nombre y lo asigno a la variable de arriba
			if (prodByName.isPresent()) { //si el producto no existe lanzamos el trow
				throw new IllegalStateException("El producto con el nombre " + prod.getNombre() + " ya existe.");
			}else {
				productosRepository.save(prod); //si no existe, toma el producto y lo guarda
			}//else if
		}//addProduct

	
	//Metodo UPDATE para agregar un nuevo producto
	public void updateProducto(Long prodId, String nombre, String descripcion, Double precio, String url_Imagen) {	
		if (productosRepository.existsById(prodId)) {
			@SuppressWarnings("deprecation")
			Producto p = productosRepository.getById(prodId);
			if (nombre !=null) p.setNombre(nombre);
			if (descripcion !=null) p.setDescripcion(descripcion);
			if (precio !=0) p.setPrecio(precio);
			if (url_Imagen !=null) p.setUrl_Imagen(url_Imagen);
			productosRepository.save(p);
		}else {
			System.out.println("No existe el id " + prodId);
		}
	}//updateProducto

	
	
	
}//ProductoService
