package bsd.spring.weather.dao;

import bsd.spring.weather.model.Tiempo;
import bsd.spring.weather.model.Usuario;

public abstract interface MainDao {
	boolean validLogin(Usuario paramUsuario);

	boolean insertarUsuario(Usuario paramUsuario);

	boolean insertarConsulta(Tiempo paramTiempo, String paramString);
}