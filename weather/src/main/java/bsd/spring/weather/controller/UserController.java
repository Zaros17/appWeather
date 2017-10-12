package bsd.spring.weather.controller;

import bsd.spring.weather.model.Usuario;
import bsd.spring.weather.service.UserService;
import bsd.spring.weather.service.UserServiceImpl;
import bsd.spring.weather.validator.UsuarioValidator;


import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/*
 * Controlador que maneja la funcionalidad del registro, login y logout
 */

@Controller
public class UserController {
	
	@Autowired
	UserService userService = new UserServiceImpl();
	
	@Autowired
	private UsuarioValidator userValidator;
	
	/*
	 * Crear validador personalizado en el controlador mediante la
	 * anotación @InitBinder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}

	/*
	 * Método que controla las peticiones GET de "/"
	 * Si el usuario no tiene sesión (no ha logeado) se le carga la vista "login" y se enlaza al modelo Usuario, 
	 * de lo contrario se carga la vista "weather"
	 */
	@GetMapping({ "/" })
	public ModelAndView index(HttpServletRequest req) {
		ModelAndView modelo = new ModelAndView("login");

		if (req.getSession(false) != null && req.getSession().getAttribute("username") != null) {
			modelo.setViewName("weather");
		} else {
			modelo.addObject("usuario", new Usuario());
		}

		return modelo;
	}

	/*
	 * Método que maneja las peticiones GET de "/signup".
	 * Se carga la vista "registro" y se enlaza al modelo Usuario
	 */
	@GetMapping({ "/signup" })
	public ModelAndView signUp() {
		
		ModelAndView modelo = new ModelAndView("registro");

		modelo.addObject("usuario", new Usuario());

		return modelo;
	}
	
	/*
	 * Método que controla las peticiones POST de "/signup".
	 * Recibe como argumentos el modelo Usuario que se había enlazado anteriormente y 
	 * los campos que no pertenecen al modelo mediante la anotación @RequestParam
	 * 
	 * Si pasa el proceso de validación, se le redirecciona a "login", de lo contrario se le vuelve
	 * a cargar la vista "registro" con los errores que se han cometido
	 */
	@PostMapping("/signup")
	public String signUpPost(@ModelAttribute("usuario") @Validated Usuario user, BindingResult result,
			@RequestParam("passwordre") String passwordre, @RequestParam("dia") String dia, @RequestParam("mes") String mes,
			@RequestParam("year") String año, HttpServletRequest req) {
		
		req.getSession().invalidate();
		boolean valido = !result.hasErrors() && UsuarioValidator.validarRepass(user.getPassword(), passwordre) &&
				UsuarioValidator.validarDia(dia, mes, año) && UsuarioValidator.validarAño(año);
		
		if (valido) {
			user.setFechaNacimiento(dia+"/"+mes+"/"+año);
			if(userService.signup(user)) {
				return "redirect:/";
			}
			else {
				req.setAttribute("error", "Usuario o email ya existe");
				return "registro";
			}
			
		} else {
			req.setAttribute("error", "Fecha de nacimiento inválida");
			return "registro";
		}
	}
	
	/*
	 * Método que controla las peticiones POST de "/".
	 * Si los datos introducidos en el login son correctos, se crea un atributo en la nueva sesión
	 * del usuario que contendrá su username y se le carga la vista de "weather"
	 * De lo contrario se le vuelve a cargar la vista y se envía un booleano para que se muestre
	 * un mensaje de error en la vista
	 */
	@PostMapping({ "/" })
	public ModelAndView login(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest req) {
		ModelAndView modelo = new ModelAndView("login");

		if (userService.login(usuario)) {
			req.getSession().setAttribute("username", usuario.getUsername());
			modelo.setViewName("weather");
		} else {
			modelo.addObject("error", true);
		}

		return modelo;
	}
	
	/*
	 * Método que controla las peticiones GET de "/logout".
	 * Se elimina la sesión que tuviera el usuario y se le redirecciona a "/"
	 */
	@GetMapping("/logout")
	public String logOut(HttpServletRequest req) {
		
		req.getSession().invalidate();
		return "redirect:/";
	}
}