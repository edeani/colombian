/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.controller;


import com.administracion.dto.ItemsDTO;
import com.administracion.service.BeneficiariosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jose Efren
 */
@Controller
@RequestMapping("/beneficiarios")
public class BeneficiariosController extends BaseController{
    
    @Autowired
    private BeneficiariosService beneficiariosSevice;
    @RequestMapping(value = "/ajax/select.htm")
    public ModelAndView beneficiariosSelect(){
        ModelAndView mav = new ModelAndView("util/formSelect");
        List<ItemsDTO> datos = beneficiariosSevice.buscarBeneficiariosSelect(getPropiedades().leerPropiedad());
        mav.addObject("datos", datos);
        
        return mav;
    }
}
