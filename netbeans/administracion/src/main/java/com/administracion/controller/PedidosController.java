/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.administracion.dto.PedidoDto;
import com.administracion.entidad.Detallepedido;
import com.administracion.service.PedidoService;
import com.administracion.service.autorizacion.SecurityService;
import com.administracion.util.Util;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class PedidosController {

    @Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private SecurityService securityService;

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
        }
        return "OK";
    }
    
    @RequestMapping("/ajax/ver-detalle.htm")
    public ModelAndView verDetalleDomicilio(@RequestParam Long idpedido){
       List<Detallepedido> detallepedidos = pedidoService.listDetallePedido(idpedido);
    
       ModelAndView mav = new ModelAndView("pedido/cuadroDetalle");
       mav.addObject("detalle", detallepedidos);
       mav.addObject("idpedido", idpedido);
       mav.addObject("nombre", securityService.getCurrentUser().getNombreusuario());
       
       return mav;
    }
}
