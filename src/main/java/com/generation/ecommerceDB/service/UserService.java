package com.generation.ecommerceDB.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.ecommerceDB.model.User;
import com.generation.ecommerceDB.utils.SHAUtil;

@Service
public class UserService {
	
	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}//constructor
	
	
	//Metodo para iniciar sesion
	public boolean login(String username, String password) {
		boolean respuesta=false;
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent()) {
			if (user.get().getPassword().equals(password)){
				respuesta=true;
			}//if password
		}//if isPresent
		return respuesta;
	}//login
	
//	//Metodo para iniciar sesion CIFRADA
//	public boolean login(String username, String password) {
//		boolean respuesta=false;
//		Optional<User> user = userRepository.findByUsername(username);
//		if (user.isPresent()) {
//			System.out.println("Password SHA: " + SHAUtil.createHash(password));
//			if (SHAUtil.verifyHash(password, user.get().getPassword()) ) {
//			//if (user.get().getPassword().equals(password)) {
//				respuesta=true;
//			}// if password
//		}//if isPresent
//		return respuesta;
//	}//login

	public List<User> getUsers() {
		return userRepository.findAll();
	}//getUsers

	public User getUser(Long userId) {
		return userRepository.findById(userId).orElseThrow(
			()-> new IllegalStateException("El usuario con el id " + userId + " no existe")
			);
	}// getUser

	public void deleteUser(Long userId) {
		if(userRepository.existsById(userId)) {
			userRepository.deleteById(userId);
		} else {
			throw new IllegalStateException("El usuario con el id " + userId + " no existe");
		} //else 
	} // deleteUser

	public void addUser(User user) {
		Optional<User> userByName = userRepository.findByUsername(user.getUsername());
		if (userByName.isPresent()) {
			throw new IllegalStateException("El usuario con el nombre " + user.getUsername() 
		+ " ya existe, puedes intentar con otro usuario"); 
		}// if
		user.setPassword( SHAUtil.createHash(user.getPassword()) );	
		userRepository.save(user);	
	}// addUser

	
	public void updateUser(Long userId, String currentPassword, String newPassword) {
		if (userRepository.existsById(userId)) {
			@SuppressWarnings("deprecation")
			User user = userRepository.getById(userId);
			if ((newPassword !=null) && (currentPassword !=null)) {
				if ( (SHAUtil.verifyHash(currentPassword, user.getPassword() ) ) && 
					(! SHAUtil.verifyHash(newPassword, user.getPassword()) ) ) {
					user.setPassword(SHAUtil.createHash(newPassword));	
					userRepository.save(user);
				} else {
					throw new IllegalStateException("Contraseña incorrecta");	
				}//else // if equals
			}else {
				throw new IllegalStateException("Contraseñas nulas");	
			}//else  // !=null
		}else {
			throw new IllegalStateException("Usuario no encontrado " + userId);	
		}//else //if existsById
		
	} // updateUser
	
	
}
