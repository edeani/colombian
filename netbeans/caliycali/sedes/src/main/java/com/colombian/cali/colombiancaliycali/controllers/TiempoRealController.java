/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.controllers;

import com.colombia.cali.colombiancaliycali.util.Formatos;
import com.colombian.cali.colombiancaliycali.services.colombianjsf.CierreColombianService;
import com.mycompany.dto.TiempoRealDto;
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
@RequestMapping("/tiemporeal")
public class TiempoRealController extends BaseController{
    
    @Autowired
    private CierreColombianService cierreColombianService;
    
    @RequestMapping("/cierres.htm")
    public ModelAndView pagina(){
        ModelAndView mav = new ModelAndView("reportes/colombian/tiempoReal/tiempoReal");
        return mav;
    }
    
    @RequestMapping("/ajax/calcular.htm")
    public ModelAndView calcularTiempoReal(@RequestParam String fecha){
        ModelAndView mav = new ModelAndView("reportes/colombian/tiempoReal/datosTiempoReal");
        
        TiempoRealDto cierreDiario =  new TiempoRealDto();
        Double cajaInicial=cierreColombianService.cierreDiario(Formatos.StringDateToDate(fecha));
        cierreDiario.setCajaInicial(cajaInicial);
        cierreDiario.setVentas(cierreColombianService.cierreVentas(Formatos.StringDateToDate(fecha)));
        cierreDiario.setGastos(cierreColombianService.cierreGastos(Formatos.StringDateToDate(fecha)));
        cierreDiario.setConsignaciones(cierreColombianService.cierreConsignaciones(Formatos.StringDateToDate(fecha)));
        cierreDiario.setListaConsignaciones(cierreColombianService.cierreListaConsignaciones(Formatos.StringDateToDate(fecha)));
        cierreDiario.setPagosTarjetas(cierreColombianService.cierrePagosConTarjetas(Formatos.StringDateToDate(fecha)));
        cierreDiario.setDescuentos(cierreColombianService.cierreDescuentos(Formatos.StringDateToDate(fecha)));
        cierreDiario.setCajaFinal(cierreDiario.getVentas()+cierreDiario.getCajaInicial()-cierreDiario.getConsignaciones()-cierreDiario.getGastos()
                -cierreDiario.getDescuentos()-cierreDiario.getPagosTarjetas());
        
        
        mav.addObject("cierreDiario", cierreDiario);
        
        return mav;
    }
    
}
