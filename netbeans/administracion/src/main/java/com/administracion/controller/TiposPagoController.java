package com.administracion.controller;


import com.administracion.entidad.Tipopago;
import com.administracion.service.TipoPagoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author edeani
 */
@Controller
@RequestMapping("/tipos-pago")
public class TiposPagoController {
    @Autowired
    private TipoPagoService tipoPagoService;
    
    @RequestMapping("/index.htm")
    public ModelAndView indexTiposPago(){
        List<Tipopago> tipos = tipoPagoService.tiposDePago();
        ModelAndView  mav = new ModelAndView("pagos/index-tipos");
        mav.addObject("tipos", tipos);
        return mav;
    }
}
