package com.generation.ecommerceDB.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Tenemos que relacionar esta clase Producto con la tabla, osea que sea una entidad

@Entity //Para convertir en una entidad
@Table(name="Producto") //Para relacionarla con una tabla llamada Producto
public class Producto { //POJO Plain Old Java Objects

	// 1. Atributos
	
	@Id //Especifica la primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Identity es primary key y autoincremental
	@Column (name="id", unique= true, nullable = false)
	private Long id;
	private String nombre;
	private String descripcion;
	private String url_Imagen;
	private Double precio;

	// 2. Constructor con campos
	public Producto(Long id, String nombre, String descripcion, String url_Imagen, Double precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.url_Imagen = url_Imagen;
		this.precio = precio;
	}// constructor con campos

	// 3. Contructor vacio
	public Producto() {
	}// constructor vacio

	// 4. Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrl_Imagen() {
		return url_Imagen;
	}

	public void setUrl_Imagen(String url_Imagen) {
		this.url_Imagen = url_Imagen;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	// 5. toString
	@Override
	public String toString() {
		return "ProductoController [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", url_Imagen="
				+ url_Imagen + ", precio=" + precio + "]";
	}

}
