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
 * @author Jose Efren
 */
@Controller
@RequestMapping("/{sede:[a-zA-Z]+}/cierre")
public class CierreController extends BaseController{
    
    @RequestMapping(value = "/index.do")
    public ModelAndView inicioCrearCierre(){
        ModelAndView mav = new ModelAndView("conabilidad/cierres/cierreSede");
        return mav;
    }
}
