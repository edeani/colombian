/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.menu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author 10 Spring Creators
 */
@Controller
public class IndexController {
    
    @GetMapping("/")
    public String indexMenu(){
        return "menu";
    }
}
