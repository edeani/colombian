/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.adiministracion.mapper.PedidoClienteDtoMapper;
import com.administracion.dto.PedidoClienteDto;
import com.administracion.dto.PedidoDto;
import com.administracion.entidad.Detallepedido;
import com.administracion.entidad.Pedido;
import com.administracion.service.PedidoService;
import com.administracion.util.Util;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author edeani
 */
@Controller
@RequestMapping("/pedidos")
public class PedidosController extends BaseController{

    @Autowired
    private PedidoService pedidoService;
    
    @RequestMapping("/domicilios.htm")
    public ModelAndView indexDomicilios() {

        String fecha = Util.dateTostring(new Date());
        List<PedidoDto> pedidos = pedidoService.findPedidosXPage(1, 20, fecha, fecha);

        ModelAndView mav = new ModelAndView("pedido/ordenes");
        mav.addObject("pedidos", pedidos);
        return mav;
    }

    /**
     *
     * @param idpedido
     * @param estado
     * @return
     */
    @RequestMapping("/ajax/{estado:aprobar|rechazar}.htm")
    @ResponseBody
    public String aprobarPedido(@RequestParam Long idpedido, @PathVariable String estado) {
        try {
            if (estado.equals("aprobar")) {
                estado = "A";
            } else {
                estado = "R";
            }
            pedidoService.updateEstado(idpedido, estado);
        } catch (Exception e) {
            return "Error::"+e.getMessage();
        }
        return "OK";
    }
    
    @RequestMapping("/ajax/ver-detalle.htm")
    public ModelAndView verDetalleDomicilio(@RequestParam Long idpedido,
            @RequestParam String fecha){
       
       PedidoClienteDto pedidoDto = new PedidoClienteDto();
       //Seteo los productos del pedido
       List<Detallepedido> detallepedidos = pedidoService.listDetallePedido(idpedido);
       pedidoDto.setProductos(PedidoClienteDtoMapper.listDetallePedidoToProductoClienteDto(detallepedidos));
       /**
        * Traigo los datos generales del pedido
        */
       Pedido pedido = pedidoService.findById(idpedido);
       
       pedidoDto.setIdusuario(pedido.getIdusuario().getIdusuario());
       pedidoDto.setCedula(pedido.getIdusuario().getCedula());
       pedidoDto.setComentarios(pedido.getComentarios());
       pedidoDto.setIdpedido(pedido.getIdpedido());
       pedidoDto.setEstado(pedido.getEstadopedido());
       pedidoDto.setMedioPago(pedido.getIdtipopago().getIdtipo());
       pedidoDto.setNombre(pedido.getIdusuario().getNombreusuario());
       pedidoDto.setTelefono(pedido.getIdusuario().getTelefono());
       pedidoDto.setTotal(pedido.getTotalpedido());
       pedidoDto.setDireccion(pedido.getDireccion());
             
       ModelAndView mav = new ModelAndView("pedido/cuadroDetalle");
       
       setBasicModel(mav, pedidoDto);
       mav.addObject("pedido", pedidoDto);
       mav.addObject("fecha", fecha);
       mav.addObject("correo", pedido.getIdusuario().getCorreo());
       mav.addObject("tipopago", pedido.getIdtipopago().getNombre());
       return mav;
    }
    
    @RequestMapping("/ajax/actualizar.htm")
    @ResponseBody
    public String actualizarPedido(@ModelAttribute PedidoClienteDto pedidoClienteDto){
        try {
            pedidoService.actualizarPedidoAdmin(pedidoClienteDto);
        } catch (Exception e) {
            return "Error::"+e.getMessage();
        }
        return "OK";
    }
}
