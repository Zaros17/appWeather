package bsd.spring.weather.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsd.spring.weather.dao.WeatherDao;
import bsd.spring.weather.dao.WeatherDaoImpl;
import bsd.spring.weather.model.Tiempo;
import bsd.spring.weather.model.Usuario;

/*
 * Servicio que implementa la funcionalidad del dao de Weather
 */

@Service
public class WeatherServiceImpl implements WeatherService {
	@Autowired
	WeatherDao weatherDaoImpl = new WeatherDaoImpl();

	public WeatherServiceImpl() {
	}	

	public boolean insertarConsulta(Tiempo weather, String username) {
		return weatherDaoImpl.insertarConsulta(weather, username);
	}

	public List<Tiempo> getConsultas(String username) {
		return weatherDaoImpl.getConsultas(username);
	}
	
	public void eliminarConsultas() {
		weatherDaoImpl.eliminarConsultas();
	}
}
