package bsd.spring.weather.dao;

import bsd.spring.weather.model.Usuario;

public interface UserDao {
	
	boolean validLogin(Usuario paramUsuario);

	boolean insertarUsuario(Usuario paramUsuario);
}
