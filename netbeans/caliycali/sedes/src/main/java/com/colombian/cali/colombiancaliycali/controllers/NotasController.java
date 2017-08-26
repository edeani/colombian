/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.controllers;

import com.colombian.cali.colombiancaliycali.dto.NotasDto;
import com.colombian.cali.colombiancaliycali.services.NotasDebitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author EderArmando
 */
@Controller
@RequestMapping("/notas")
public class NotasController extends BaseController{
    
    @Autowired
    private NotasDebitoService notasDebitoService;
    
    @RequestMapping("/debito.htm")
    public ModelAndView indexDebito(){
        ModelAndView mav = new ModelAndView("contabilidad/notas/debito");
        NotasDto notasDto = new NotasDto();
        setBasicModel(mav, notasDto);
        mav.addObject("notasDto", notasDto);
        mav.addObject("titulo", "Notas Debito");
        return mav;
    }
}
