package com.generation.ecommerceDB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.generation.ecommerceDB.model.User;
import com.generation.ecommerceDB.service.UserService;


@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })
@RestController
@RequestMapping(path="/api/login/")
public class LoginController {
	
	private final UserService userService;
	@Autowired
	public LoginController(UserService userService) {
		this.userService = userService;
	}//constructor
	
	
	//Metodo POST
	@PostMapping
	public String Login(@RequestBody User user) {
		String respuesta="Nombre de usuario o contrasenia incorrectos";
		if (userService.login(user.getUsername(), user.getPassword())) {
			respuesta = "OK, usuario validado. Acceso correcto";
		}//if
		return respuesta;
		}//LoginController

}//LoginController
