/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;


import com.administracion.service.ClasePagoService;
import com.administracion.service.jsf.CierreColombianService;
import com.administracion.util.Formatos;
import com.mycompany.dto.TiempoRealDto;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/{sede:[a-zA-Z]+}/tiemporeal")
public class TiempoRealController extends BaseController {

    @Autowired
    private CierreColombianService cierreColombianService;

    @Autowired
    private ClasePagoService clasePagoService;

    @RequestMapping("/cierres.htm")
    public ModelAndView pagina() {
        ModelAndView mav = new ModelAndView("reportes/colombian/tiempoReal/tiempoReal");
        return mav;
    }

    @RequestMapping("/ajax/calcular.htm")
    public ModelAndView calcularTiempoReal(@RequestParam String fecha,@PathVariable String sede,@RequestParam(required = false,value = "sede") String subsede) {
        ModelAndView mav = new ModelAndView("reportes/colombian/tiempoReal/datosTiempoReal");

        TiempoRealDto cierreDiario = new TiempoRealDto();   
        Double cajaInicial = cierreColombianService.cierreDiario(Formatos.StringDateToDate(fecha),subsede);
        cierreDiario.setCajaInicial(cajaInicial);
        cierreDiario.setVentas(cierreColombianService.cierreVentas(Formatos.StringDateToDate(fecha),subsede));
        cierreDiario.setGastos(cierreColombianService.cierreGastos(Formatos.StringDateToDate(fecha),subsede));
        cierreDiario.setConsignaciones(cierreColombianService.cierreConsignaciones(Formatos.StringDateToDate(fecha),subsede));
        cierreDiario.setListaConsignaciones(cierreColombianService.cierreListaConsignaciones(Formatos.StringDateToDate(fecha),subsede));
        cierreDiario.setCajaFinal(cierreDiario.getVentas() + cierreDiario.getCajaInicial() - cierreDiario.getConsignaciones() - cierreDiario.getGastos());
        if (clasePagoService.findClasePagoById(1,sede).getEstado().equals("A")) {
            cierreDiario.setPagosTarjetas(cierreColombianService.cierrePagosConTarjetas(Formatos.StringDateToDate(fecha),subsede));
            cierreDiario.setCajaFinal(cierreDiario.getCajaFinal() - cierreDiario.getPagosTarjetas());
        }
        if (clasePagoService.findClasePagoById(2,sede).getEstado().equals("A")) {
            cierreDiario.setDescuentos(cierreColombianService.cierreDescuentos(Formatos.StringDateToDate(fecha),subsede));
            cierreDiario.setCajaFinal(cierreDiario.getCajaFinal() - cierreDiario.getDescuentos());
        }
        
        mav.addObject("cierreDiario", cierreDiario);

        return mav;
    }

}
