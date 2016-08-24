/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.controller;

import domicilios.entidad.Producto;
import domicilios.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author user
 */
@Controller
public class HomeController{
    
    @Autowired
    private ProductoService productoService;
    
    @RequestMapping("/home.htm")
    public ModelAndView inicio(){
        ModelAndView mav = new ModelAndView("home/inicio");
        /*List<Producto> productos = productoService.listAll();
        mav.addObject("productos", productos);*/
        return mav;
    }
}
