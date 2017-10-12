package bsd.spring.weather.service;

import bsd.spring.weather.model.Usuario;

public interface UserService {
	
	  boolean login(Usuario paramUsuario);
	  
	  boolean signup(Usuario paramUsuario);
	  
}
