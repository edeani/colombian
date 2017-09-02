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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    
    @RequestMapping("/debito/guardar.htm")
    public @ResponseBody String guardarNotasDebito(@ModelAttribute NotasDto notasDto){
        try {
            notasDebitoService.guardarNotaDebito(notasDto.getSede(), notasDto);
        } catch (Exception e) {
            System.out.println("Error guardarNotasDebito::"+e.getMessage());
            return "Error";
        }
        return "ok";
    }
    
    @RequestMapping("/credito.htm")
    public ModelAndView indexCredito(){
        ModelAndView mav = new ModelAndView("contabilidad/notas/credito");
        NotasDto notasDto = new NotasDto();
        setBasicModel(mav, notasDto);
        mav.addObject("notasDto", notasDto);
        mav.addObject("titulo", "Notas Cr&eacute;dito");
        return mav;
    }
    
    @RequestMapping("/credito/guardar.htm")
    public @ResponseBody String guardarNotasCredito(@ModelAttribute NotasDto notasDto){
        try {
            notasDebitoService.guardarNotaCredito(notasDto.getSede(), notasDto);
        } catch (Exception e) {
            System.out.println("Error guardarNotasCredito::"+e.getMessage());
            return "Error";
        }
        return "ok";
    }
}
