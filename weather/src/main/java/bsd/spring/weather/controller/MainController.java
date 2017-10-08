package bsd.spring.weather.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bsd.spring.weather.model.Tiempo;
import bsd.spring.weather.model.Usuario;
import bsd.spring.weather.service.MainServiceImpl;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController
{
  @Autowired
  MainServiceImpl loginService = new MainServiceImpl();
  
  @GetMapping({"/"})
  public ModelAndView index(HttpServletRequest req) { ModelAndView modelo = new ModelAndView("login");
    
    if (req.getSession(false) != null) {
      modelo.setViewName("weather");
    } else {
      modelo.addObject("usuario", new Usuario());
    }
    
    return modelo;
  }

  @RequestMapping(value={"/weather"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, consumes={"application/json"})
  public String weatherPost(@RequestBody String tiempo, HttpServletRequest req)
  {
    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HHmm:ssZ").create();
    Tiempo weather = (Tiempo)gson.fromJson(tiempo, Tiempo.class);
    
    loginService.insertarConsulta(weather, req.getSession().getAttribute("username").toString());
    return "weather";
  }
  
  @GetMapping({"/signup"})
  public ModelAndView signUp()
  {
    ModelAndView modelo = new ModelAndView("registro");
    
    modelo.addObject("usuario", new Usuario());
    

    return modelo;
  }
  
  @PostMapping({"/"})
  public ModelAndView login(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest req)
  {
    ModelAndView modelo = new ModelAndView("login");
    
    if (loginService.login(usuario)) {
      req.getSession().setAttribute("username", usuario.getUsername());
      modelo.addObject("user", usuario.getUsername());
      modelo.setViewName("weather");
    } else {
      modelo.addObject("error", Boolean.valueOf(true));
    }
    

    return modelo;
  }
}