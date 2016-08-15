/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.controller;

import domicilios.dto.ProductoDto;
import domicilios.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author edeani
 */
@Controller
@RequestMapping("/contenido")
public class ProductosController {
    
    @Value("${producto.cantidad}")
    Integer cantidadPagina;
    
    @Autowired
    private ProductoService productoService;
    
    private Integer paginas=0;
    
    @Autowired
    public void setTotalProductos(){
        this.paginas=productoService.numeroProducto()/this.cantidadPagina;
    }
    
    @RequestMapping("/productos.htm")
    public ModelAndView productos(){
        List<ProductoDto> productos = productoService.listAllPage(1);
        ModelAndView mav = new ModelAndView("productos/contenidoProductos");
        
        mav.addObject("productos", productos);
        mav.addObject("actualPage", 1);
        
        return mav;
    }
    
    @RequestMapping("/ajax/productosxpagina.htm")
    public ModelAndView productosPagina(@RequestParam Integer page){
        List<ProductoDto> productos = productoService.listAllPage(page);
        ModelAndView mav = new ModelAndView("productos/listaProductosFront");
        mav.addObject("productos", productos);
        mav.addObject("actualPage", page);
        return mav;
    }
    
    @ModelAttribute("pages")
    public Integer cantidadProductos(){
        return paginas;
    }
}
