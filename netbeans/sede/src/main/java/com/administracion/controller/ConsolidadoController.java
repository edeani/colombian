/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.administracion.dto.ComprobanteCierreSedesDto;
import com.administracion.dto.ReporteConsolidadoDto;
import com.administracion.service.ReporteService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author EderArmando
 */
@Controller
@RequestMapping("/{sede:[a-zA-Z]+}/consolidado")
public class ConsolidadoController extends BaseController {
    
    @Autowired
    private ReporteService reporteService;
    
    @RequestMapping(value = "/sede.htm")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("reportes/consolidado/consolidado");

        mav.addObject("fechaInicial", new Date());
        mav.addObject("fechaFinal", new Date());
        mav.addObject("titulo", " Reporte Consolidado ");
        return mav;
    }

    @RequestMapping(value = "/consolidadoPDF.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteConsolidadoPDF(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false, value = "fechaInicial") String fechaInicial, @RequestParam(required = false, value = "fechaFinal") String fechaFinal) {

        List<ReporteConsolidadoDto> reporte = reporteService.reporteConsolidado(fechaInicial, fechaFinal);
        ModelAndView mav = null;
        if (reporte.size() > 0) {
            JRDataSource datos = new JRBeanCollectionDataSource(reporte);
            Map<String, Object> parameterMap = new HashMap<>();
            parameterMap.put("datos", datos);
            parameterMap.put("fechaInicial", fechaInicial);
            parameterMap.put("fechaFinal", fechaFinal);
            mav = new ModelAndView("consolidado", parameterMap);
        } else {
            mav = new ModelAndView("redirect:/consolidado/sede.htm");
            mav.addObject("mensaje", "Se encontrar&oacute;n 0 registros");
        }
        return mav;
    }
    
    @RequestMapping(value = "/comprobante/sede.htm")
    public ModelAndView comprobanteConsolidadoSedeInicio() {
        ModelAndView mav = new ModelAndView("contabilidad/cierres/cierreSede");
        ComprobanteCierreSedesDto comprobanteCierreSedesDto = new ComprobanteCierreSedesDto();
        setBasicModel(mav, comprobanteCierreSedesDto);
        mav.addObject("comprobanteCierreSedesDto", comprobanteCierreSedesDto);
        return mav;
    }
    
    @RequestMapping(value = "/comprobante/reporte/sede.htm")
    public ModelAndView reporteConsolidadoSedeInicio() {
        ModelAndView mav = new ModelAndView("reportes/cierres/cierreSede");
        return mav;
    }
}
