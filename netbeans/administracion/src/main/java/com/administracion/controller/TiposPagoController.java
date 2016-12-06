package com.administracion.controller;


import com.administracion.entidad.Tipopago;
import com.administracion.service.TipoPagoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author edeani
 */
@Controller
@RequestMapping("/tipos-pago")
public class TiposPagoController {
    @Autowired
    private TipoPagoService tipoPagoService;
    
    @RequestMapping("/index.htm")
    public ModelAndView indexTiposPago(){
        List<Tipopago> tipos = tipoPagoService.tiposDePago();
        ModelAndView  mav = new ModelAndView("pagos/index-tipos");
        mav.addObject("tipos", tipos);
        return mav;
    }
    
    @RequestMapping("/ajax/crear.htm")
    @ResponseBody
    public String crearTipoPago(@RequestParam String nombre,@RequestParam String estado){
        try {
            tipoPagoService.crearTipoPago(estado, nombre);
            return "OK";
        } catch (Exception e) {
            return "Error::"+e.getMessage();
        }
    }
    
    @RequestMapping("/ajax/actualizar.htm")
    @ResponseBody
    public String actualizarTipoPago(@RequestParam Integer idtipopago,@RequestParam String nombre,@RequestParam String estado){
        Tipopago tipopago = new Tipopago();
        tipopago.setEstado(estado);
        tipopago.setIdtipo(idtipopago);
        tipopago.setNombre(nombre);
        tipoPagoService.actualizarTiposPago(tipopago);
        return "OK";
    }
    
    @RequestMapping("/ajax/tipo-producto.htm")
    public ModelAndView formatoTipoPago(@RequestParam Integer idtipopago,@RequestParam String nombre,@RequestParam String estado){
        ModelAndView mav = new ModelAndView("pagos/formato-tipo-pago");
        mav.addObject("estado", estado);
        mav.addObject("idtipopago", idtipopago);
        mav.addObject("nombre", nombre);
        
        return mav;
    }
}
