/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.administracion.service.jsf.CuadreColombianService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author 10 Spring Creators
 */
@Controller
@RequestMapping("/{sede:[a-zA-Z]+}/cuadre")
public class CuadreController {
    @Autowired
    private CuadreColombianService cuadreColombianService;
    
    @RequestMapping("/index-cuadre.htm")
    public ModelAndView indexCuadre(){
        ModelAndView mav = new ModelAndView("reportes/colombian/cuadre/reporte_cuadre");
        
        mav.addObject("fecha", new Date());
        return mav;
    }
}
