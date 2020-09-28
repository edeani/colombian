/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.menu.controller;

import com.colombian.menu.components.MenuData;
import com.colombian.menu.dto.CategoriaDto;
import com.colombian.menu.service.MenuService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author 10 Spring Creators
 */
@Controller
public class IndexController {
    
    @Autowired
    private MenuService menuService;
    
    @GetMapping("/")
    public String indexMenu(Model model){
        
        MenuData menu = menuService.getMenu();
        
        model.addAttribute("menuColombian",menu);
        return "menu";
    }
    

}
