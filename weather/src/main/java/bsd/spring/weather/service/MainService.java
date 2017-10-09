package bsd.spring.weather.service;

import java.util.List;

import bsd.spring.weather.model.Tiempo;
import bsd.spring.weather.model.Usuario;

public abstract interface MainService
{
  boolean login(Usuario paramUsuario);
  
  boolean signup(Usuario paramUsuario);
  
  boolean insertarConsulta(Tiempo paramTiempo, String paramString);
  
  List<Tiempo> getConsultas(String username);
}