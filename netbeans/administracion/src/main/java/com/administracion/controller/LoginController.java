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
 * @author user
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/signin.htm")
    public ModelAndView paginaLogin() {
        return new ModelAndView("index");
    }
    
    @RequestMapping("/403.htm")
    public ModelAndView domicilios403() {
        ModelAndView mav = new ModelAndView("403");
        return mav;
    }
}
