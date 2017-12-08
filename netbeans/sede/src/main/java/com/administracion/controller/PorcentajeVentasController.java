/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.administracion.service.PorcentajeVentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author EderArmando
 */
@Controller
@RequestMapping("/porcentajeventas")
public class PorcentajeVentasController extends BaseController{
    @Autowired
    private PorcentajeVentasService porcentajeVentasService;
    
    @RequestMapping("/ajax/generar/detalle.htm")
    public @ResponseBody String generarPorcentajeVentas(@RequestParam Integer mes){
        try {
            porcentajeVentasService.generarPorcentajeVentas(getPropiedades().leerPropiedad(), mes);
            porcentajeVentasService.generarDetallePorcentajeVentas(getPropiedades().leerPropiedad(), mes);
        } catch (Exception e) {
            System.out.println("Error::generarPorcentajeVentas"+e.getMessage());
            return "Se produjo un error al ejecutar el proceso";
        }
        return "ok";
    }
    

}
