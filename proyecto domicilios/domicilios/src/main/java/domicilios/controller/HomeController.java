package domicilios.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginDomicilios(){
    	ModelAndView mav = new ModelAndView("login");
		return  mav;
	}
	
    @RequestMapping("/")
	public ModelAndView inicioDomicilios(){
    	ModelAndView mav = new ModelAndView("inicio");
    	mav.addObject("mensaje", "Estoy en Inicio");
		return  mav;
	}
    
    @RequestMapping("/403")
    public ModelAndView domicilios403(){
    	ModelAndView mav = new ModelAndView("inicio");
    	mav.addObject("mensaje", "Estoy en 403");
		return  mav;
	}
    
    @RequestMapping("/logoutSuccessful")
    public ModelAndView domicilioslogout(){
    	ModelAndView mav = new ModelAndView("inicio");
    	mav.addObject("mensaje", "Deslogueado");
		return  mav;
	}
    
    @RequestMapping("/userInfo")
    public ModelAndView domiciliosuser(){
    	ModelAndView mav = new ModelAndView("inicio");
    	mav.addObject("mensaje", "Ingreso Ok");
		return  mav;
	}
}
