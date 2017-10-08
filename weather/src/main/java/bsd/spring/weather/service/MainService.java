package bsd.spring.weather.service;

import bsd.spring.weather.model.Tiempo;
import bsd.spring.weather.model.Usuario;

public abstract interface MainService
{
  public abstract boolean login(Usuario paramUsuario);
  
  public abstract boolean signup(Usuario paramUsuario);
  
  public abstract boolean insertarConsulta(Tiempo paramTiempo, String paramString);
}