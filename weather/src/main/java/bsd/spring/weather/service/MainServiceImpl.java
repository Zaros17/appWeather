package bsd.spring.weather.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsd.spring.weather.dao.MainDaoImpl;
import bsd.spring.weather.model.Tiempo;
import bsd.spring.weather.model.Usuario;

@Service
public class MainServiceImpl implements MainService
{
  @Autowired
  MainDaoImpl loginDaoImpl = new MainDaoImpl();
  
  public MainServiceImpl() {}
  
  public boolean login(Usuario usuario) {
    if (loginDaoImpl.validLogin(usuario)) {
      return true;
    }
    return false;
  }
  public boolean signup(Usuario usuario)
  {
    return false;
  }
  
  public boolean insertarConsulta(Tiempo weather, String username)
  {
    return loginDaoImpl.insertarConsulta(weather, username);
  }

@Override
public List<Tiempo> getConsultas(String username) {
	return loginDaoImpl.getConsultas(username);
}
}
