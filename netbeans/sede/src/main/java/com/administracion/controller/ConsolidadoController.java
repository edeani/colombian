/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author EderArmando
 */
@Controller
@RequestMapping("/{sede:[a-zA-Z]+}/consolidado")
public class ConsolidadoController extends BaseController{
    
    @RequestMapping(value = "/sede.htm")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("reportes/consolidado/consolidado");

        mav.addObject("fechaInicial", new Date());
        mav.addObject("fechaFinal", new Date());
        mav.addObject("titulo", " Reporte Consolidado ");
        return mav;
    }
    
  
}
