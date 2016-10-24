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
public class HomeController{
    
    
    @RequestMapping("home.htm")
    public ModelAndView inicio(){
        ModelAndView mav = new ModelAndView("home");
        return mav;
    }
}
