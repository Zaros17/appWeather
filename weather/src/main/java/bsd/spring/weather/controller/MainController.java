package bsd.spring.weather.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bsd.spring.weather.model.Tiempo;
import bsd.spring.weather.model.Usuario;
import bsd.spring.weather.service.MainServiceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	private List<Tiempo> consultas = new ArrayList<>();
	
	@Autowired
	MainServiceImpl loginService = new MainServiceImpl();

	@GetMapping({ "/" })
	public ModelAndView index(HttpServletRequest req) {
		ModelAndView modelo = new ModelAndView("login");

		if (req.getSession(false) != null) {
			modelo.setViewName("weather");
		} else {
			modelo.addObject("usuario", new Usuario());
		}

		return modelo;
	}

	@RequestMapping(value = { "/weather" }, method = { RequestMethod.POST }, consumes = { "application/json" })
	public String weatherPost(@RequestBody String tiempo, HttpServletRequest req) {
		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HHmm:ssZ").create();
		Tiempo weather = (Tiempo) gson.fromJson(tiempo, Tiempo.class);

		loginService.insertarConsulta(weather, req.getSession().getAttribute("username").toString());
		
		if(req.getSession().getAttribute("haConsultado") == null || req.getSession().getAttribute("haConsultado").equals(false) )
			req.getSession().setAttribute("haConsultado", true);
		
		return "weather";
	}

	@GetMapping({ "/signup" })
	public ModelAndView signUp() {
		ModelAndView modelo = new ModelAndView("registro");

		modelo.addObject("usuario", new Usuario());

		return modelo;
	}

	@GetMapping({ "/historial" })
	public ModelAndView historial(HttpServletRequest req) {

		ModelAndView modelo = new ModelAndView("historial");
	
		// Si no existe o es igual a true ya que ha consultado, significa que no ha consultado el historial aún por lo que se hace la petición a la BD y se crea atributo de sesión.
		// De lo contrario, significa que ya ha consultado el historial y no se hace nada
		if(req.getSession().getAttribute("haConsultado") == null || req.getSession().getAttribute("haConsultado").equals(true)) {
			
			consultas = loginService.getConsultas(req.getSession().getAttribute("username").toString());
			modelo.addObject("consultas", consultas);
			req.getSession().setAttribute("haConsultado", false);
			
		} else {
			modelo.addObject("consultas", consultas);
		}

		return modelo;
	}

	@PostMapping({ "/" })
	public ModelAndView login(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest req) {
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