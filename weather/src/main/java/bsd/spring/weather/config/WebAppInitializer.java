package bsd.spring.weather.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
 *  Clase que lleva la configuración del ServletDispatcher de Spring MVC y del entorno de la aplicación web
 */

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


	// Devolución de todas las clases (ninguna en este caso) de configuración para componentes NO web de la app.
	// El método a pesar de esar vacío debe de ser implementado de manera obligatoria 
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {};
	}
	

	// Devolución de todas las clases (una en este caso) de configuración para componentes web de la app
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class};
	}

	// Devolución de todos los patrones de llamada (uno en este caso) asociados a DispatcherServlet de Spring MVC	
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	
	

}
