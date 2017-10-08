package bsd.spring.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/*
 * Clase que lleva la configuraci�n de los componentes web de Spring
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"bsd.spring.weather"})
public class WebConfig extends WebMvcConfigurerAdapter{

	/*
	 * M�todo para devolver un bean gestionado por el contenedor de Spring donde
	 * se configura el objeto ViewResolver para asociar el nombre l�gico de las vistas con 
	 * los ficheros f�sicos.
	 */
	
	@Bean
	public ViewResolver resolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		
		resolver.setViewClass(JstlView.class); // Habilitar JSTL 
		resolver.setPrefix("/WEB-INF/vistas/"); // Configurar path donde se encuentran las vistas
		resolver.setSuffix(".jsp"); // Configurar extensi�n de las vistas
		
		return resolver;
	}
	
	/*
	 * M�todo para indicar que los recursos est�ticos tiene
	 * que servirlos el servidor de aplicaciones en vez del Servlet
	 * de Spring MVC.
	 */
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry
	      .addResourceHandler("/static/**")
	      .addResourceLocations("/static/");
	}
	
}