/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.administracion.datasources.GenericDataSource;
import com.administracion.entidad.Sedes;
import com.administracion.service.SedesService;
import com.administracion.service.autorizacion.AccesosSubsedes;
import com.administracion.service.autorizacion.PathEntry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author user
 */
@Controller
public class LoginController {

    @Value("${basededatos}")
    private String base_datos_principal;
    
    @Autowired
    private SedesService sedesService;
    
    @Autowired
    private GenericDataSource genericDataSource;
    
    @Autowired
    private AccesosSubsedes accesosSubsedes_;
    
    
    /**
     * Carga base de datos principal si no se le especifica sede
     * @param session
     * @return 
     */
    @RequestMapping(value = "/signin.htm")
    public ModelAndView paginaLogin(HttpSession session) {
        session.setAttribute("path", base_datos_principal);
        return new ModelAndView("index");
    }
    
    /**
     * Cargar base de datos y sede seleccionada en el PATH
     * @param request
     * @param sede
     * @return 
     */
    @RequestMapping(value = "/{sede:[a-zA-Z]+}/signin.htm")
    public ModelAndView paginaLoginSede(HttpServletRequest request,@PathVariable String sede) {
        HttpSession session = request.getSession();
        Sedes s = sedesService.findSedeXName(sede);
        if(s==null){
            return new ModelAndView("redirect:/404.htm");
        }else{
            request.getSession().setAttribute("path",sede);
            accesosSubsedes_.setPath(sede);
            genericDataSource.updateGenericDataSource(s);
        }
        
       return new ModelAndView("index");
    }
    
    @RequestMapping("/403.htm")
    public ModelAndView sedes403() {
        ModelAndView mav = new ModelAndView("403");
        return mav;
    }
    
    @RequestMapping("/404.htm")
    public ModelAndView  notfound() {
        ModelAndView mav = new ModelAndView("404");
        return mav;
    }
}
