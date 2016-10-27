/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

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
     
    @RequestMapping("/listado.htm")
    public ModelAndView indexDomicilios(){
        ModelAndView mav =new ModelAndView("pedidos/ordenes");
        return mav;
    }
}
