/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.administracion.service.autorizacion.ConnectsAuth;
import com.administracion.service.jsf.VentasColombianService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author EderArmando
 */
@Controller
@RequestMapping("/{sede:[a-zA-Z]+}/ventas")
public class VentasController {

    @Autowired
    private VentasColombianService ventasColombianService;
    @Autowired
    private ConnectsAuth connectsAuth;

    @RequestMapping("/index-ventas.htm")
    public ModelAndView indexVentas() {
        ModelAndView mav = new ModelAndView("ventas/reporte_ventas");
        mav.addObject("fecha", new Date());;
        return mav;
    }

    @RequestMapping("/ajax/ventasmesa.htm")
    public ModelAndView getVentasMesa(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fi,
             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date ff, @RequestParam Integer idSubsede, @PathVariable String sede) {
        ModelAndView mav = new ModelAndView("ventas/tabla_ventas");
        mav.addObject("titulo", "Reporte Ventas Mesa");
        mav.addObject("clase", "Mesa");
        mav.addObject("datosVenta", ventasColombianService.ventasMesa(
                connectsAuth.findSubsedeXId(idSubsede).getSede(), fi, ff));
        return mav;
    }

    @RequestMapping("/ajax/ventasdomicilio.htm")
    public ModelAndView getVentasDomicilios(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fi,
             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date ff, @RequestParam Integer idSubsede, @PathVariable String sede) {
        ModelAndView mav = new ModelAndView("ventas/tabla_ventas");
        mav.addObject("titulo", "Reporte Ventas Domicilio");
        mav.addObject("clase", "Domicilio");
        mav.addObject("datosVenta", ventasColombianService.ventasDomicilio(connectsAuth.findSubsedeXId(idSubsede).getSede(),
                 fi, ff));
        return mav;
    }

    @RequestMapping("/ajax/ventasmostrador.htm")
    public ModelAndView getVentasMostrador(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fi,
             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date ff, @RequestParam Integer idSubsede, @PathVariable String sede) {
        ModelAndView mav = new ModelAndView("ventas/tabla_ventas");
        mav.addObject("titulo", "Reporte Ventas Llevar");
        mav.addObject("clase", "Llevar");
        mav.addObject("datosVenta", ventasColombianService.ventasMostrador(connectsAuth.findSubsedeXId(idSubsede).getSede(),
                 fi, ff));
        return mav;
    }

    @RequestMapping("/ajax/ventastotal.htm")
    public ModelAndView getVentasTotal(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fi,
             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date ff, @RequestParam Integer idSubsede, @PathVariable String sede) {
        ModelAndView mav = new ModelAndView("ventas/tabla_ventas");
        mav.addObject("titulo", "Reporte Venta Total");
        mav.addObject("clase", "Total");
        mav.addObject("datosVenta", ventasColombianService.totalVentas(connectsAuth.findSubsedeXId(idSubsede).getSede(),
                 fi, ff));
        return mav;
    }
}
