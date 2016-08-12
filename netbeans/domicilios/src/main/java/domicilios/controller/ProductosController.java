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
 * @author edeani
 */
@Controller
@RequestMapping("/contenido")
public class ProductosController {
    
    @Autowired
    private ProductoService productoService;
    public ModelAndView productos(){
        List<Producto> productos = productoService.listAll();
        ModelAndView mav = new ModelAndView("productos/contenidoProductos");
        mav.addObject("productos", productos);
        return mav;
    }
}
