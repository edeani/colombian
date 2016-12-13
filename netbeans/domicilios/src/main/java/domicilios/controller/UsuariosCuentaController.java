/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.controller;

import domicilios.dto.PedidoViewDto;
import domicilios.entidad.Usuario;
import domicilios.service.PedidoService;
import domicilios.service.autorizacion.SecurityService;
import domicilios.util.Util;
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
@RequestMapping("/user")
public class UsuariosCuentaController {
    
    @Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private SecurityService securityService;
    
    @RequestMapping("/pedidos.htm")
    public ModelAndView misPedidos(){
        
        String fecha = Util.dateTostring(new Date());
        Usuario usuario = securityService.getCurrentUser();
        List<PedidoViewDto> pedidos = pedidoService.findPedidosXPageUsuario(1, 5, usuario.getIdusuario());

        ModelAndView mav = new ModelAndView("pedido/ordenes");
        mav.addObject("pedidos", pedidos);
        return mav;
        
    }
}
