/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.administracion.dto.ProductoDetailDto;
import com.administracion.dto.ProductoDto;
import com.administracion.entidad.Categoria;
import com.administracion.service.ProductoService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/productos")
public class ProductosController extends BaseController {

    @Autowired
    private ProductoService productoService;

    private List<Categoria> categorias;
    
    @Autowired
    private void setCategorias(){
        categorias=productoService.listCategory();
    }
    
    @ModelAttribute("categorias")
    public List<Categoria> getCategorias(){
        return categorias;
    }
    
    @RequestMapping("/inventario.htm")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("productos/inventario");
        List<ProductoDto> productos = productoService.listAllPage(1);
        mav.addObject("productos", productos);
        return mav;
    }

    @RequestMapping(value = "/ingresar-producto.htm", method = RequestMethod.GET)
    public ModelAndView paginaIngresarProducto() {
        ModelAndView mav = new ModelAndView("productos/detalleProducto");
        ProductoDetailDto producto = new ProductoDetailDto();
        producto.setEstado("A");
        setBasicModel(mav, producto);
        mav.addObject("producto", producto);
        mav.addObject("estado", "N");
        return mav;
    }

    @RequestMapping(value = "/ingresar-producto.htm", method = RequestMethod.POST)
    public ModelAndView ingresarProducto(@ModelAttribute @Valid ProductoDetailDto productoDetailDto, BindingResult binding) {
        if (binding.hasErrors()) {
            return setMavOnErrorIngresarProducto(productoDetailDto);
        } else {
            try {
                productoService.crearProductoAdministrador(productoDetailDto);
                return new ModelAndView("redirect:/productos/inventario.htm");
            } catch (Exception e) {
                System.out.println("ingresarProducto::" + e.getMessage());
                ModelAndView mavex = setMavOnErrorIngresarProducto(productoDetailDto);
                mavex.addObject("mensaje", "No se pudo crear el producto. Intente m&aacute;s tarde.");
                return mavex;
            }
        }
    }
    
    private ModelAndView setMavOnErrorIngresarProducto(ProductoDetailDto productoDetailDto){
        ModelAndView mav = new ModelAndView("productos/detalleProducto");
            setBasicModel(mav, productoDetailDto);
            mav.addObject("producto", productoDetailDto);
            mav.addObject("estado", "N");
            return mav;
    }
    
    @RequestMapping("/eliminar-producto.htm")
    public @ResponseBody
    String eliminarProducto(@RequestParam Integer idproducto) {
        productoService.eliminarProductoXid(idproducto);
        return "OK";
    }
}
