package bsd.spring.weather.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bsd.spring.weather.model.Tiempo;
import bsd.spring.weather.service.WeatherService;
import bsd.spring.weather.service.WeatherServiceImpl;
/*
 * Controlador que maneja la funcionalidad de consulta del tiempo e historial.
 */
@Controller
public class WeatherController {

	private List<Tiempo> consultas = new ArrayList<>();
	
	@Autowired
	WeatherService weatherService = new WeatherServiceImpl();
	
	/*
	 * M�todo que controla las peticiones POST de "/weather".
	 * Se recibe mediante la anotaci�n @RequestBody el JSON construido por el script de Angular que contiene la informaci�n de la consulta
	 * realizada por el usuario. Mediante el objeto Gson de la dependencia gson transformo el json en una clase pojo, en este caso, mi modelo Tiempo.
	 * Tras insertar la consulta en la base de datos, se establece un control sobre la sesi�n para que el historial sepa si se ha realizado
	 * una consulta o no.
	 */
	@RequestMapping(value = { "/weather" }, method = { RequestMethod.POST }, consumes = { "application/json" })
	public String weatherPost(@RequestBody String tiempo, HttpServletRequest req) {
		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy'T'HHmm:ssZ").create();
		Tiempo weather = (Tiempo) gson.fromJson(tiempo, Tiempo.class);

		weatherService.insertarConsulta(weather, req.getSession().getAttribute("username").toString());
		
		/*
		 * Si el atributo de sesi�n booleano "haConsulado" es nulo (el usuario no ha hecho una consulta a�n) o el atributo de sesi�n
		 * es falso (el usuario viene del historial) se setea a true
		 */
		if(req.getSession().getAttribute("haConsultado") == null || req.getSession().getAttribute("haConsultado").equals(false) )
			req.getSession().setAttribute("haConsultado", true);
		
		return "weather";
	}
	
	/*
	 * M�todo que maneja las peticiones GET de "/historial".
	 * 
	 * Mediante un atributo de sesi�n controlo si se ha hecho una consulta nueva desde que se hizo la
	 * petici�n a la base de datos con la finalidad de no tener que realizar una petici�n a la bd en
	 * caso de que no la haya hecho.
	 * 
	 * Despues de pasar los filtros, se recogen de la base de datos las consultas del usuario y se a�aden
	 * a una lista que despu�s se enviar� a la vista.
	 */
	@GetMapping({ "/historial" })
	public ModelAndView historial(HttpServletRequest req) {

		ModelAndView modelo = new ModelAndView("historial");
		
		/*
		 * Si el usuario intenta acceder al historial desde la url sin haber iniciado sesi�n se le redirecciona a "/"
		 */
		if (req.getSession(false) != null && req.getSession().getAttribute("username") != null) {

			/*
			 * Si el atributo de sesi�n booleano "haConsulado" es nulo (el usuario no ha hecho una consulta a�n) o 
			 * es verdadero (el usuario ha realizado una consulta) se setea a false.
			 */
			if(req.getSession().getAttribute("haConsultado") == null || req.getSession().getAttribute("haConsultado").equals(true)) {
				
				consultas = weatherService.getConsultas(req.getSession().getAttribute("username").toString());
				modelo.addObject("consultas", consultas);
				req.getSession().setAttribute("haConsultado", false);
				
			} else {
				modelo.addObject("consultas", consultas);

			}
			
			/*
			 * Si el atributo de sesi�n "haConsultado" existe y el n�mero de consultas son superiores a 10, se eliminan todas las consultas de la bd.
			 */
			if(req.getSession().getAttribute("haConsultado") == null && consultas.size() >= 10) {	
					weatherService.eliminarConsultas();
			}

		} else {
			modelo.setViewName("redirect:/");
		}

		return modelo;

	}
	
}
