/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.administracion.dto.PedidoDto;
import com.administracion.service.PedidoService;
import com.administracion.util.Util;
import java.util.Date;
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
@RequestMapping("/pedidos")
public class PedidosController {
     
    @Autowired
    private PedidoService pedidoService;
    
    @RequestMapping("/domicilios.htm")
    public ModelAndView indexDomicilios(){
        
        String fecha = Util.dateTostring(new Date());
        List<PedidoDto> pedidos = pedidoService.findPedidosXPage(1, 20, fecha , fecha);
        
        ModelAndView mav =new ModelAndView("pedido/ordenes");
        mav.addObject("pedidos", pedidos);
        return mav;
    }
}
