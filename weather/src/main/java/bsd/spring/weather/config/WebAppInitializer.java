package bsd.spring.weather.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
 *  Clase que lleva la configuraci�n del ServletDispatcher de Spring MVC y del entorno de la aplicaci�n web
 */

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


	// Devoluci�n de todas las clases (ninguna en este caso) de configuraci�n para componentes NO web de la app.
	// El m�todo a pesar de esar vac�o debe de ser implementado de manera obligatoria 
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {};
	}
	

	// Devoluci�n de todas las clases (una en este caso) de configuraci�n para componentes web de la app
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class};
	}

	// Devoluci�n de todos los patrones de llamada (uno en este caso) asociados a DispatcherServlet de Spring MVC	
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	
	

}
