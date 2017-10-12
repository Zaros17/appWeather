package bsd.spring.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsd.spring.weather.dao.UserDao;
import bsd.spring.weather.dao.UserDaoImpl;
import bsd.spring.weather.model.Usuario;

/*
 * Servicio que implementa la funcionalidad del dao de User
 */

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDaoImpl = new UserDaoImpl();
	
	public boolean login(Usuario usuario) {
		return userDaoImpl.validLogin(usuario);
	}

	public boolean signup(Usuario usuario) {
		return userDaoImpl.insertarUsuario(usuario);
	}
}
