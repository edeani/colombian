/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.administracion.datasources.GenericDataSource;
import com.administracion.entidad.Sedes;
import com.administracion.service.SedesService;
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
    
    @RequestMapping(value = "/signin.htm")
    public ModelAndView paginaLogin(HttpSession session) {
        session.setAttribute("path", base_datos_principal);
        return new ModelAndView("index");
    }
    
    @RequestMapping(value = "/{sede:[a-zA-Z]+}/signin.htm")
    public ModelAndView paginaLoginSede(HttpServletRequest request,@PathVariable String sede) {
        HttpSession session = request.getSession();
        Sedes s = sedesService.findSedeXName(sede);
        if(s==null){
            return new ModelAndView("redirect:/404.htm");
        }else{
            request.getSession().setAttribute("path",sede);
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
