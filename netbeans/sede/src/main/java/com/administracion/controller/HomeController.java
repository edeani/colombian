/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.administracion.service.autorizacion.AccesosSubsedes;
import com.administracion.service.autorizacion.ConnectsAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/{sede:[a-zA-Z]+}")
public class HomeController{
    
    @Autowired
    private ConnectsAuth connectsAuth;
    @Autowired
    private AccesosSubsedes accesosSubsedes;
    @RequestMapping("/home.htm")
    public ModelAndView inicio(@PathVariable(value = "sede") String sedePath){
        if(accesosSubsedes.getMultiple()){
            ModelAndView mav = new ModelAndView("homeGeneric");
            mav.addObject("userSede", connectsAuth.findUserNameXSede(sedePath));
            return mav;
        }else{
            return new ModelAndView("home");
        }
        
    }
}
