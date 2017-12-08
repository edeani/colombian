/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;


import com.administracion.service.jsf.MesasyllevarColombianService;
import com.administracion.util.Formatos;
import com.mycompany.mapper.Mesasyllevar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author EderArmando
 */
@Controller
@RequestMapping("/mesasyllevar")
public class MesasYLlevarController extends BaseController {

    @Autowired
    private MesasyllevarColombianService mesasyllevarColombianService;

    @RequestMapping(value = "/ordenes.htm")
    public ModelAndView paginaMesasyLlevar() {
        return new ModelAndView("reportes/colombian/mesasyllevar/formMesas"); 
    }

    @RequestMapping(value = "/colombian/ajax/consultar.htm")
    public ModelAndView paginaMesasyLlevar(@RequestParam String fechaInicial, @RequestParam String fechaFinal) {
        
        List<Mesasyllevar> mesas = mesasyllevarColombianService.mesas(Formatos.StringDateToDate(fechaInicial), Formatos.StringDateToDate(fechaFinal));
       
        ModelAndView mav = new ModelAndView("reportes/colombian/mesasyllevar/datosMesas");
        mav.addObject("mesas", mesas);
        return mav;
    }
}
