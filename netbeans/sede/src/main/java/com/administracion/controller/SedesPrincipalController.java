/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.administracion.entidad.Sedes;
import com.administracion.service.SedesService;
import com.administracion.service.autorizacion.AccesosSubsedes;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author EderArmando
 */
@Controller
@RequestMapping("/sedes")
public class SedesPrincipalController {

    @Autowired
    private SedesService sedesService;
    
    @Autowired
    private AccesosSubsedes accesosSubsedes;

    @RequestMapping("/ajax/byuser.htm")
    public ModelAndView buscarSedesXUsuario(@RequestParam String username) {
        ModelAndView mav = new ModelAndView("util/formSelect");
        mav.addObject("datos", sedesService.listaSedesOptionByUsername(username));
        return mav;
    }

    @RequestMapping(value = "/ajax/setSedeSession.htm")
    public @ResponseBody
    String setSedeReportesColombian(@RequestParam String nombreSede,
            HttpSession session) {
        session.setAttribute("path", nombreSede);
        accesosSubsedes.setPath(nombreSede);
        //securityService.getCurrentUser().setIdsedes(sede); 
        return "ok";
    }
}
