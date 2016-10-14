/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.controller;

import domicilios.dto.PedidoClienteDto;
import domicilios.dto.ProductoClienteDto;
import domicilios.entidad.Tipopago;
import domicilios.entidad.Usuario;
import domicilios.service.PedidoService;
import domicilios.service.TipoPagoService;
import domicilios.service.autorizacion.SecurityService;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/compras")
public class PedidosController extends BaseController {

    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private TipoPagoService tipoPagoService;
    
    @Autowired
    private PedidoService pedidoService;

    private static final String SESSIONCOMPRA = "#{session.getAttribute('pedido')}";
    
    private static final String ESTADO_COMPRA = "A";

    List<Tipopago> tiposPago;
    
    @Autowired
    private void tiposDePago(){
        this.tiposPago=tipoPagoService.tiposDePago();
    }
    
    @RequestMapping(value = "/pedido.htm", method = RequestMethod.GET)
    public ModelAndView pedido(@Value(SESSIONCOMPRA) PedidoClienteDto pedidoDto, HttpSession session) {
        ModelAndView mav ;
       
        if (securityService.getCurrentUser() != null) {
            mav = new ModelAndView("compra/pedido");
            if (pedidoDto == null) {
                return new ModelAndView("compra/pedidoVacio");
            } else if (pedidoDto.getProductos() == null) {
                return new ModelAndView("compra/pedidoVacio");
            }else if(pedidoDto.getProductos().isEmpty()){
                return new ModelAndView("compra/pedidoVacio");
            }
            Usuario usuario = securityService.getCurrentUser();
            pedidoDto.setNombre(usuario.getNombreusuario());
            if(usuario.getCedula()!=null){
                pedidoDto.setCedula(usuario.getCedula());
            }
            if(usuario.getDireccion()!=null){
                pedidoDto.setDireccion(usuario.getDireccion());
            }
            if(usuario.getTelefono()!=null){
                pedidoDto.setTelefono(usuario.getTelefono());
            }
            
            setBasicModel(mav, pedidoDto);
            mav.addObject("pedido", pedidoDto);
       }else{
            mav = new ModelAndView("redirect:/signin.htm");
        }
        return mav;
    }

    @RequestMapping(value = "/pedido.htm", method = RequestMethod.POST)
    public ModelAndView guardarPedido(@ModelAttribute @Valid PedidoClienteDto pedidoClienteDto, BindingResult binding,@Value(SESSIONCOMPRA) PedidoClienteDto pedidoDto) {

        if (binding.hasErrors()) {
            ModelAndView mavError = new ModelAndView("compra/pedido");
            setBasicModel(mavError, pedidoClienteDto);
            return mavError;
        } else {
            ModelAndView mav = new ModelAndView("compra/finalizacion");
            try {
                pedidoService.guardarPedido(pedidoClienteDto, securityService.getCurrentUser());
                pedidoDto=null;
            } catch (Exception e) {
                mav = new ModelAndView("redirect:/compras/pedido.htm");
                mav.addObject("mensaje", "Ocurri&oacute un problema al realizar la operaci&oacute;");
                return mav;
            }
            return mav;
        }
    }

    @RequestMapping("/ajax/resumen.htm")
    public ModelAndView resumenPedido(@Value(SESSIONCOMPRA) PedidoClienteDto pedidoDto, HttpSession session) {
        if (pedidoDto == null) {
            pedidoDto = new PedidoClienteDto();
            pedidoDto.setTotal(0F);
            pedidoDto.setEstado(ESTADO_COMPRA);
            session.setAttribute("pedido", pedidoDto);
        }

        ModelAndView mav = new ModelAndView("compra/listCarResumen");
        mav.addObject("pedido", pedidoDto);

        return mav;
    }

    @RequestMapping("/ajax/carrito/eliminar.htm")
    public @ResponseBody
    String eliminarProductoCarrito(@Value(SESSIONCOMPRA) PedidoClienteDto pedidoDto, HttpSession session, @RequestParam Integer idproducto) {

        List<ProductoClienteDto> productos;

        if (pedidoDto != null) {
            productos = pedidoDto.getProductos();
            for (ProductoClienteDto producto : productos) {
                if (Objects.equals(producto.getIdproducto(), idproducto)) {
                    productos.remove(producto);
                    pedidoDto.setTotal(pedidoDto.getTotal() - producto.getTotal());
                    break;
                }
            }
            if (productos.isEmpty()) {
                pedidoDto = null;
            }
        }

        return "OK";
    }
    @ModelAttribute("tiposPago")
    private List<Tipopago> tiposPago(){
        return tiposPago;
    }
}
