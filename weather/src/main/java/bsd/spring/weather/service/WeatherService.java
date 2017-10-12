package bsd.spring.weather.service;

import java.util.List;

import bsd.spring.weather.model.Tiempo;

public interface WeatherService {
	boolean insertarConsulta(Tiempo paramTiempo, String paramString);

	List<Tiempo> getConsultas(String username);
	
	void eliminarConsultas();
}