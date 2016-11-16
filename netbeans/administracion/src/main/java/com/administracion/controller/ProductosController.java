/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.administracion.dto.ProductoAutocompletarDto;
import com.administracion.dto.ProductoDetailDto;
import com.administracion.dto.ProductoDto;
import com.administracion.entidad.Categoria;
import com.administracion.entidad.Producto;
import com.administracion.enumeration.ExtencionesEnum;
import com.administracion.service.ProductoService;
import com.administracion.util.LectorPropiedades;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
    private static final String PROPIEDAD_PATHIMG = "path.img";
    private static final String PROPIEDADES_COLOMBIAN = "colombian.properties";

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
    public ModelAndView ingresarProducto(@ModelAttribute @Valid ProductoDetailDto productoDetailDto, BindingResult binding) {
        if (binding.hasErrors()) {
            return setMavOnErrorIngresarProducto(productoDetailDto, "N");
        } else {
            try {
                productoService.crearProductoAdministrador(productoDetailDto);
                subirImagenes(productoDetailDto.getImagen(), productoDetailDto.getIdproducto().toString());
                return new ModelAndView("redirect:/productos/editar-producto.htm?idproducto=" + productoDetailDto.getIdproducto() + "&origen=G");
            } catch (Exception e) {
                System.out.println("ingresarProducto::" + e.getMessage());
                ModelAndView mavex = setMavOnErrorIngresarProducto(productoDetailDto, "N");
                mavex.addObject("mensaje", "No se pudo crear el producto. Intente m&aacute;s tarde.");
                return mavex;
            }
        }
    }

    @RequestMapping(value = "/editar-producto.htm", method = RequestMethod.GET)
    public ModelAndView paginaEditarProducto(@RequestParam Integer idproducto, @RequestParam(required = false) String origen) {
        ModelAndView mav = new ModelAndView("productos/detalleProducto");
        Producto producto = productoService.findProductoXid(idproducto);
        ProductoDetailDto productoDetailDto = new ProductoDetailDto();
        if (producto != null) {
            productoDetailDto.setDescripcion(producto.getDescripcion());
            productoDetailDto.setEstado(producto.getEstado());
            productoDetailDto.setIdproducto(producto.getIdproducto());
            productoDetailDto.setRutaImagen(producto.getImagen());
            productoDetailDto.setNombreproducto(producto.getNombreproducto());
            productoDetailDto.setTipo(producto.getTipo());
            productoDetailDto.setPrecioproducto(producto.getPrecioproducto());

            if(origen!=null){
                if(origen.equals("G"))
              mav.addObject("mensaje", "Guardado");
                
            }
            
        } else {
            productoDetailDto.setEstado("A");
            mav.addObject("mensaje", "Producto no encontrado");
        }

        setBasicModel(mav, productoDetailDto);
        mav.addObject("producto", productoDetailDto);
        mav.addObject("estado", "E");
        return mav;
    }

    @RequestMapping(value = "/editar-producto.htm", method = RequestMethod.POST)
    public ModelAndView editarProducto(@ModelAttribute @Valid ProductoDetailDto productoDetailDto, BindingResult binding) {
        if (binding.hasErrors()) {
            return setMavOnErrorIngresarProducto(productoDetailDto, "E");
        } else {
            productoService.actualizarProductoAdministrador(productoDetailDto);
            try {
                subirImagenes(productoDetailDto.getImagen(), productoDetailDto.getIdproducto().toString());
                ModelAndView mav = new ModelAndView("productos/detalleProducto");
                setBasicModel(mav, productoDetailDto);
                mav.addObject("producto", productoDetailDto);
                mav.addObject("estado", "E");
                mav.addObject("mensaje", "Guardado!");
                return mav;
            } catch (Exception e) {
                ModelAndView mavex = setMavOnErrorIngresarProducto(productoDetailDto, "E");
                mavex.addObject("mensaje", "No se pudo crear la imagen producto. Intente m&aacute;s tarde.");
                return mavex;
            }
        }
    }

    private ModelAndView setMavOnErrorIngresarProducto(ProductoDetailDto productoDetailDto, String estado) {
        ModelAndView mav = new ModelAndView("productos/detalleProducto");
        setBasicModel(mav, productoDetailDto);
        mav.addObject("producto", productoDetailDto);
        mav.addObject("estado", estado);
        return mav;
    }

    @RequestMapping("/eliminar-producto.htm")
    public @ResponseBody
    String eliminarProducto(@RequestParam Integer idproducto) {
        productoService.eliminarProductoXid(idproducto);
        return "OK";
    }

    @RequestMapping("/activar-producto.htm")
    public @ResponseBody
    String activarProducto(@RequestParam Integer idproducto) {
        productoService.activarProductoXid(idproducto);
        return "OK";
    }
    
    @RequestMapping("/ajax/autocompletar.htm")
    @ResponseBody
    public String autocompletarProducto(@RequestParam String term){
        List<ProductoAutocompletarDto> productos = productoService.autocompletarProducto(term);
        String json = "[]";
        if(productos!=null){
            Gson gson = new Gson();
            json = gson.toJson(productos);
        }
        return json;
    }
    
    @RequestMapping("/ajax/admin/nuevo-producto.htm")
    public ModelAndView htmlNuevoProducto(){
      return new ModelAndView("pedido/newProductoOrden");
    }
    
    @RequestMapping("/ajax/admin/content-producto.htm")
    public ModelAndView htmlElementoNuevoProducto(@RequestParam String jsonProducto,@RequestParam Integer sizeFilas){
      ModelAndView mav = new ModelAndView("pedido/newProductoOrden");
      Gson gson = new Gson();
      ProductoAutocompletarDto producto = gson.fromJson(jsonProducto, ProductoAutocompletarDto.class);
      mav.addObject("producto", producto);
      mav.addObject("sizeFilas", sizeFilas);
      return mav;
    }
    @RequestMapping(value = "/upload-image", method = RequestMethod.POST)
    @ResponseBody
    public String subirImagenes(@RequestParam(value = "imagen", required = false) MultipartFile image, @RequestParam(required = false) String nombreImagen) throws Exception {
        if (image != null) {
            if (!image.isEmpty()) {
                LectorPropiedades le = new LectorPropiedades();
                try {
                    String extension = "";
                    String nombreCompleto = nombreImagen;
                    if (image.getContentType().contains("jpeg")) {
                        nombreCompleto += ExtencionesEnum.JPG.getExt();
                    } else if (image.getContentType().contains("png")) {
                        nombreCompleto += ExtencionesEnum.PNG.getExt();
                    } else if (image.getContentType().contains("gif")) {
                        nombreCompleto += ExtencionesEnum.GIF.getExt();
                    } else {
                        nombreCompleto = image.getOriginalFilename();
                    }
                    FileCopyUtils.copy(image.getBytes(), new File(le.leerPropiedad(PROPIEDADES_COLOMBIAN, PROPIEDAD_PATHIMG) + nombreCompleto));
                } catch (IOException ex) {
                    Logger.getLogger(ProductosController.class.getName()).log(Level.SEVERE, null, ex);
                    throw new Exception("Falló carga imagen");
                }
                return "OK";
            }
        }
        return "Imágen Vacía";

    }


}
