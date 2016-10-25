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
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.validation.Valid;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/productos")
public class ProductosController extends BaseController implements ServletContextAware {

    @Autowired
    private ProductoService productoService;

    private ServletContext servletContext;

    private List<Categoria> categorias;
    private static final String EXT_JPEG = ".jpeg";
    private static final String EXT_PNG = ".png";

    @Autowired
    private void setCategorias() {
        categorias = productoService.listCategory();
    }

    @ModelAttribute("categorias")
    public List<Categoria> getCategorias() {
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
    public ModelAndView ingresarProducto(@ModelAttribute @Valid ProductoDetailDto productoDetailDto, BindingResult binding,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        if (binding.hasErrors()) {
            return setMavOnErrorIngresarProducto(productoDetailDto);
        } else {
            try {
                productoService.crearProductoAdministrador(productoDetailDto);
                subirImagenes(image, productoDetailDto.getIdproducto().toString());
                return new ModelAndView("redirect:/productos/inventario.htm");
            } catch (Exception e) {
                System.out.println("ingresarProducto::" + e.getMessage());
                ModelAndView mavex = setMavOnErrorIngresarProducto(productoDetailDto);
                mavex.addObject("mensaje", "No se pudo crear el producto. Intente m&aacute;s tarde.");
                return mavex;
            }
        }
    }

    private ModelAndView setMavOnErrorIngresarProducto(ProductoDetailDto productoDetailDto) {
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

    @RequestMapping(value = "/upload-image", method = RequestMethod.POST)
    @ResponseBody
    public String subirImagenes(@RequestParam(value = "image", required = false) MultipartFile image, @RequestParam(required = false) String nombreImagen) {
        if (image != null) {
            if (!image.getContentType().equals("image/jpeg") && !image.getContentType().equals("image/png")) {
                return "Solo se aceptan imágenes JPG y PNG";
            } else {
                if (image.getContentType().equals("image/jpeg")) {
                    nombreImagen += EXT_JPEG;
                } else if (image.getContentType().equals("image/png")) {
                    nombreImagen += EXT_PNG;
                }
                File file = new File(servletContext.getRealPath("/") + "/"
                        + nombreImagen);

                try {
                    FileUtils.writeByteArrayToFile(file, image.getBytes());
                } catch (IOException ex) {
                    Logger.getLogger(ProductosController.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Go to the location:  " + file.toString()
                        + " on your computer and verify that the image has been stored.");
            }
            return "OK";
        } else {
            return "Imágen Vacía";
        }
    }

    @Override
    public void setServletContext(ServletContext sc) {
        this.servletContext = sc;
    }

}
