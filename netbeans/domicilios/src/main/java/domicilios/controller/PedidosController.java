/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.controller;

import domicilios.dto.PedidoClienteDto;
import domicilios.dto.ProductoClienteDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/compras")
public class PedidosController extends BaseController{
    private static final String SESSIONCOMPRA = "#{session.getAttribute('pedido')}";
    
    @RequestMapping("/pedido.htm")
    public ModelAndView pedido(@Value(SESSIONCOMPRA) PedidoClienteDto pedidoDto){
        ModelAndView mav = new ModelAndView("compra/pedido");
        return mav;
    }
    
    @RequestMapping("/ajax/resumen.htm")
    public ModelAndView resumenPedido(@Value(SESSIONCOMPRA) PedidoClienteDto pedidoDto,HttpSession session){
        if(pedidoDto==null){
            pedidoDto = new PedidoClienteDto();
            pedidoDto.setTotal(0F);
            session.setAttribute("pedido", pedidoDto);
        }
        
        ModelAndView mav = new  ModelAndView("compra/listCarResumen");
        mav.addObject("pedido", pedidoDto);
        
        return mav;
    }
    
    @RequestMapping("/ajax/carrito/eliminar.htm")
    public @ResponseBody String eliminarProductoCarrito(@Value(SESSIONCOMPRA) PedidoClienteDto pedidoDto,HttpSession session,@RequestParam Integer idproducto){
        
        List<ProductoClienteDto> productos;
        
        if(pedidoDto!=null){
             productos = pedidoDto.getProductos();
             for (ProductoClienteDto producto : productos) {
                if(Objects.equals(producto.getIdproducto(), idproducto)){
                    productos.remove(producto);
                    pedidoDto.setTotal(pedidoDto.getTotal()-producto.getTotal());
                    break;
                }
            }
            if(productos.isEmpty()){
                pedidoDto=null;
            }
        }
        
        return "OK";
    }
}
