/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.controller;

import domicilios.dto.PedidoClienteDto;
import domicilios.dto.ProductoClienteDto;
import domicilios.dto.ProductoDto;
import domicilios.entidad.Categoria;
import domicilios.service.ProductoService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    private Integer paginas = 0;

    private static Integer CANTIDAD_PRODUCTO = 1;

    private static final String SESSIONCOMPRA = "#{session.getAttribute('pedido')}";

    @Autowired
    public void setTotalProductos() {
        this.paginas = (productoService.numeroProducto() / this.cantidadPagina) + 1;
    }

    @RequestMapping("/productos.htm")
    public ModelAndView productos() {
        List<ProductoDto> productos = productoService.listAllPage(1);
        ModelAndView mav = new ModelAndView("productos/contenidoProductos");
        List<Categoria> categorias = productoService.listCategory();
        mav.addObject("productos", productos);
        mav.addObject("actualPage", 1);
        mav.addObject("categorias", categorias);
        return mav;
    }

    @RequestMapping("/ajax/productosxpagina.htm")
    public ModelAndView productosPagina(@RequestParam Integer page) {
        List<ProductoDto> productos = productoService.listAllPage(page);
        ModelAndView mav = new ModelAndView("productos/listaProductosFront");
        mav.addObject("productos", productos);
        mav.addObject("actualPage", page);
        return mav;
    }

    @RequestMapping("/ajax/carrito/agregar.htm")
    public ModelAndView agregarCarrito(@Value(SESSIONCOMPRA) PedidoClienteDto pedidoDto, @RequestParam Integer idproducto,
            @RequestParam Float precioProducto, @RequestParam String nombreProducto) {
        List<ProductoClienteDto> productoClienteDto;
        if (pedidoDto == null) {
            pedidoDto = new PedidoClienteDto();
            productoClienteDto = new ArrayList<>();
            pedidoDto.setProductos(productoClienteDto);
            pedidoDto.setTotal(0F);
        } else if (pedidoDto.getProductos() != null) {
            productoClienteDto = pedidoDto.getProductos();
        } else {
            productoClienteDto = new ArrayList<>();
            pedidoDto.setProductos(productoClienteDto);
        }

        ProductoClienteDto addedProducto = new ProductoClienteDto(idproducto, nombreProducto, precioProducto, CANTIDAD_PRODUCTO, precioProducto * CANTIDAD_PRODUCTO);
        productoClienteDto.add(addedProducto);

        /**
         * Actualizo el pedido
         */
        pedidoDto.setTotal(addedProducto.getTotal() + pedidoDto.getTotal());

        ModelAndView mav = new ModelAndView("compra/productoResumen");
        mav.addObject("producto", addedProducto);
        return mav;
    }

    @RequestMapping("/ajax/carrito/actualizar.htm")
    public @ResponseBody
    String actualizarCarrito(@Value(SESSIONCOMPRA) PedidoClienteDto pedidoDto, @RequestParam Integer idproducto,
            @RequestParam Float precioProducto) {

        List<ProductoClienteDto> productoClienteDto = pedidoDto.getProductos();
        for (ProductoClienteDto productoClienteDto1 : productoClienteDto) {
            if (Objects.equals(productoClienteDto1.getIdproducto(), idproducto)) {
                productoClienteDto1.setCantidad(productoClienteDto1.getCantidad() + 1);
                productoClienteDto1.setTotal(productoClienteDto1.getPrecio() * productoClienteDto1.getCantidad());
                break;
            }
        }

        pedidoDto.setTotal(pedidoDto.getTotal() + precioProducto);

        return "OK";
    }

    
    @ModelAttribute("pages")
    public Integer cantidadProductos() {
        return paginas;
    }
}
