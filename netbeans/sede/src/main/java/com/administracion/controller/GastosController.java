/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.administracion.dto.GastosDto;
import com.administracion.dto.ReporteGastosDto;
import com.administracion.service.jsf.GastosColombianService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author EderArmando
 */
@Controller
@RequestMapping("/{sede:[a-zA-Z]+}/gastos")
public class GastosController {

    @Autowired
    private GastosColombianService gastosColombianService;

    @RequestMapping(value = "/index.htm", method = RequestMethod.GET)
    public ModelAndView indexGastos() {
        ModelAndView mav = new ModelAndView("/reportes/colombian/gastos/gastos");
        mav.addObject("fecha", new Date());
        return mav;
    }

    @RequestMapping(value = "/ajax/generar-reporte.htm")
    public ModelAndView consultarGastos(@RequestParam String fechaInicial, @RequestParam String fechaFinal,
            @RequestParam(value = "nombreSede") String subSedeCredencial,
            @PathVariable String sede) {
        ModelAndView mav = new ModelAndView("/reportes/colombian/gastos/datosGastos");
        List<GastosDto> gastos =gastosColombianService.gastos(fechaInicial, fechaFinal, subSedeCredencial);
        mav.addObject("gastos", gastos);
        
        return mav;
    }
}
