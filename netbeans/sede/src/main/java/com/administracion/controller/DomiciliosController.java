/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.administracion.service.autorizacion.ConnectsAuth;
import com.administracion.service.jsf.DomiciliosDiaColombianService;
import com.administracion.service.jsf.OrdenesDomiciliosColombianService;
import com.mycompany.dto.OrdenesDomiciliosDto;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
@RequestMapping("/{sede:[a-zA-Z]+}/domicilios")
public class DomiciliosController {
    
    @Autowired
    private OrdenesDomiciliosColombianService ordenesDomiciliosColombianService;
    @Autowired
    private DomiciliosDiaColombianService domiciliosDiaColombianService;
    @Autowired
    private ConnectsAuth connectsAuth;
    @RequestMapping("/index-domicilios.htm")
    public ModelAndView indexDomicilios(){
        ModelAndView mav = new ModelAndView("reportes/colombian/domicilios/reporte_domicilios");
        mav.addObject("fecha", new Date());
        return mav;
    }
    
    @RequestMapping("/ajax/consultar.htm")
    public ModelAndView getDomicilios(@RequestParam String fi,
             @RequestParam String ff, @RequestParam Integer idSubsede, @PathVariable String sede) {
        ModelAndView mav = new ModelAndView("reportes/colombian/domicilios/tabla_domicilios");
        mav.addObject("clase", "Domicilios");
        List<OrdenesDomiciliosDto> ordenesDOm = ordenesDomiciliosColombianService.domiciliosordenes(fi, ff, connectsAuth.findSubsedeXId(idSubsede).getSede());
        mav.addObject("datos", ordenesDOm);
        mav.addObject("totalDom", ordenesDOm == null?0:ordenesDOm.size());
        return mav;
    }
    
    @RequestMapping("/domicilios-dia.htm")
    public ModelAndView indexDomiciliosPorDia(){
        ModelAndView mav = new ModelAndView("reportes/colombian/domicilios/reporte_domicilios_dia");
        mav.addObject("fecha", new Date());;
        return mav;
    }
    
    @RequestMapping("/ajax/consultar-dia.htm")
    public ModelAndView getDomiciliosPorDia(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fi,
             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date ff, @RequestParam Integer idSubsede, @PathVariable String sede) {
        ModelAndView mav = new ModelAndView("reportes/colombian/domicilios/tabla_domicilios_dia");
        mav.addObject("clase", "Domicilios");
        mav.addObject("datos", domiciliosDiaColombianService.domicilioDia(fi, ff, connectsAuth.findSubsedeXId(idSubsede).getSede()));
        return mav;
    }
}
