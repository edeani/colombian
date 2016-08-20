/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombian.cali.colombiancaliycali.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jose Efren
 */
@Controller
@RequestMapping("/imprimir")
public class ImprimirComprobantesController extends BaseController{
    @RequestMapping(value = "/comprobante/pago.htm")
    public ModelAndView indexImpresionProveedor(){
        ModelAndView mav = new ModelAndView("reportes/consolidado/imprimir/comprobante_index");
        mav.addObject("titulo", "Impresi&oacute;n Comprobantes de Pago");
        return mav;
    }
}
