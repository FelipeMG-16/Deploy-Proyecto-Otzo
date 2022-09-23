//Aqui es donde generamos la conexion con nuestra base de datos.

package com.generation.ecommerceDB.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.generation.ecommerceDB.model.Producto;

//nos vamos a conectar a la dependencia, utilizando la palabra extends para traernos las opciones que nos da la dependencia del JPA repository. Esto nos ayuda a que los datos sean persistentes (que no se borren los datos, que se queden almancenados en una base de datos). Gracias a que utilizamos JPA, tendremos ya algunas "consultas" predefinidas, y si queremos hacer algunas consultas adicionales, tambien podemos personalizarlas.


//generics (tipo del dato y tipo del id que utilizamos para conectarnos a la BD
//T = identidad (Producto)
//ID = tipo de id que lo identifica (del tipo Long)

public interface ProductosRepository extends JpaRepository<Producto, Long>{
	
	//Como no tenemos una consulta que nos permita buscar por nombre, vamos a crearla. Para eso utilizamos la anotacion @Query 
	//En esta Query vamos a darle la instruccion de que quiero que haga
	@Query("SELECT p FROM Producto p WHERE p.nombre=?1") //el signo de interrogacion es el parametro.
	Optional<Producto> findByNombre (String nombre);

	/*
	 * Si quisiera buscar algo con dos parametros, seria asi:
	 * 
	 * @Query("SELECT p FROM Producto p WHERE p.nombre=?1 AND p.precio=?2") //el signo de interrogacion es el parametro.
	Optional<Producto> findByNombre (String nombre, double precio); //se lee "voy a buscar un producto por nombre y por precio
	 */
	
	
	
	
}//interfaceRepository












