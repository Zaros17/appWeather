package bsd.spring.weather.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/*
 * Controlador de errores de la aplicación Spring MVC
 * Será llamado por el contenedor de Spring cuando se produzca un error
 */

@Controller
public class ErrorController {
 
	/*
	 * Método que administra los errores producidos por peticiones GET. Se recogen los principales tipos de errores
	 * Devuelve un ModelAndView con la vista asociada a "error" y como modelo tiene asociado el mensaje de error
	 */
    @GetMapping("errors")
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {
         
        ModelAndView errorPage = new ModelAndView("error");
        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);
 
        switch (httpErrorCode) {
            case 400: {
                errorMsg = "Http Error Code: 400. Sintaxis incorrecta";
                break;
            }
            case 401: {
                errorMsg = "Http Error Code: 401. Desautorizado";
                break;
            }
            case 404: {
                errorMsg = "Http Error Code: 404. Recurso no encontrado";
                break;
            }
            case 405: {
                errorMsg = "Http Error Code: 405. Método no permitido";
                break;
            }
            case 500: {
                errorMsg = "Http Error Code: 500. Error interno del servidor";
                break;
            }
        }
        
        errorPage.addObject("errorMsg", errorMsg);
        return errorPage;
    }
    
    /*
     * Mismo método que el anterior pero en este caso para administrar los errores producidos por peticiones POST
     */
    @PostMapping("errors")
    public ModelAndView renderErrorPageMethodPost(HttpServletRequest httpRequest) {
         
        ModelAndView errorPage = new ModelAndView("error");
        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);
 
        switch (httpErrorCode) {
            case 400: {
                errorMsg = "Http Error Code: 400. Sintaxis incorrecta";
                break;
            }
            case 401: {
                errorMsg = "Http Error Code: 401. Desautorizado";
                break;
            }
            case 404: {
                errorMsg = "Http Error Code: 404. Recurso no encontrado";
                break;
            }
            case 405: {
                errorMsg = "Http Error Code: 405. Método no permitido";
                break;
            }
            case 500: {
                errorMsg = "Http Error Code: 500. Error interno del servidor";
                break;
            }
        }
        
        errorPage.addObject("errorMsg", errorMsg);
        return errorPage;
    }
    
    /*
     * Método privado que devuelve el código de error HTTP. Únicamente se usa en esta clase cuyo fin
     * es tener el código de los dos métodos anteriores más limpio. 
     */
    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
          .getAttribute("javax.servlet.error.status_code");
    }
}
