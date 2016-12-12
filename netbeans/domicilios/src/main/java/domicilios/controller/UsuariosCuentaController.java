/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.controller;

import domicilios.util.Util;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author edeani
 */
@Controller
@RequestMapping("/user")
public class UsuariosCuentaController {
    
    @RequestMapping("/pedidos.htm")
    public ModelAndView misPedidos(){
        
        String fecha = Util.dateTostring(new Date());
        //List<PedidoViewDto> pedidos = pedidoService.findPedidosXPage(1, 20, fecha, fecha);

        ModelAndView mav = new ModelAndView("pedido/ordenes");
        //mav.addObject("pedidos", pedidos);
        return mav;
        
    }
}
