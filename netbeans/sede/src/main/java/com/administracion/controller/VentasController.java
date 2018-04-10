/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.administracion.service.jsf.VentasColombianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author EderArmando
 */
@Controller
@RequestMapping("/{sede:[a-zA-Z]+}/ventas")
public class VentasController {
    @Autowired
    private VentasColombianService ventasColombianService;
    
    public ModelAndView getVentasMesa(@RequestParam String fi,@RequestParam String ff,@PathVariable String sede){
        return null;
    }
    public ModelAndView getVentasDomicilios(@RequestParam String fi,@RequestParam String ff,@PathVariable String sede){
        return null;
    }
    public ModelAndView getVentasMostrador(@RequestParam String fi,@RequestParam String ff,@PathVariable String sede){
        return null;
    }
    public ModelAndView getVentasTotal(@RequestParam String fi,@RequestParam String ff,@PathVariable String sede){
        return null;
    }
}
