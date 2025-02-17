/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;


import com.administracion.dto.NotasDetalleDto;
import com.administracion.dto.NotasDto;
import com.administracion.dto.SedesDto;
import com.administracion.entidad.ControlNotasCredito;
import com.administracion.entidad.ControlNotasDebito;
import com.administracion.service.autorizacion.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.administracion.service.NotasService;
import com.administracion.service.autorizacion.ConnectsAuth;
import com.mycompany.util.ConstantsColombianJsf;
import com.mycompany.util.Formatos;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author EderArmando
 */
@Controller
@RequestMapping("/{sede:[a-zA-Z]+}/notas")
public class NotasController extends BaseController {

    @Autowired
    private NotasService notasService;
    
    @Autowired
    private ConnectsAuth connectsAuth;
    
    @Autowired
    private SecurityService securityServiceNotasController;

    @RequestMapping("/debito.htm")
    public ModelAndView indexDebito() {
        ModelAndView mav = new ModelAndView("contabilidad/notas/debito");
        NotasDto notasDto = new NotasDto();
        setBasicModel(mav, notasDto);
        mav.addObject("notasDto", notasDto);
        mav.addObject("titulo", "Notas Debito");
        return mav;
    }

    @RequestMapping("/ajax/debito/guardar.htm")
    public @ResponseBody
    String guardarNotasDebito(@ModelAttribute NotasDto notasDto,@PathVariable String sede) {
        int idComprobanteDebito =0;
        try {
           idComprobanteDebito= notasService.guardarNotaDebito(sede, notasDto,securityServiceNotasController.getCurrentUser());
        } catch (Exception e) {
            System.out.println("Error guardarNotasDebito::" + e.getMessage());
            return "Error";
        }
        return ""+idComprobanteDebito;
    }

    @RequestMapping("/credito.htm")
    public ModelAndView indexCredito() {
        ModelAndView mav = new ModelAndView("contabilidad/notas/credito");
        NotasDto notasDto = new NotasDto();
        setBasicModel(mav, notasDto);
        mav.addObject("notasDto", notasDto);
        mav.addObject("titulo", "Notas Cr&eacute;dito");
        return mav;
    }

    @RequestMapping("/ajax/credito/guardar.htm")
    public @ResponseBody
    String guardarNotasCredito(@ModelAttribute NotasDto notasDto, @PathVariable String sede) {
        int idComprobante =0;
        try {

            idComprobante = notasService.guardarNotaCredito(sede, notasDto,
                     securityServiceNotasController.getCurrentUser());
        } catch (Exception e) {
            System.out.println("Error guardarNotasCredito::" + e.getMessage());
            return "Error";
        }
        return ""+idComprobante;
    }
    
    @RequestMapping("/credito/comprobante.htm")
    public ModelAndView comprobanteNotasCredito(@RequestParam Integer idComprobante, @PathVariable String sede) {
        ModelAndView mavComprobanteNC = null;
        List<NotasDetalleDto> detalleComprobante = notasService.consultarNotaCredito(sede,idComprobante == null ? 0 : idComprobante.intValue());
        ControlNotasCredito controlNC = notasService.consultarControlNotaCredito(sede, idComprobante);
        
        if (detalleComprobante != null) {
            if (!detalleComprobante.isEmpty()) {
                JRDataSource datos = new JRBeanCollectionDataSource(detalleComprobante);
                Map<String, Object> parameterMap = new HashMap<>();
                parameterMap.put("datos", datos);
                parameterMap.put("idcomprobante", idComprobante);
                parameterMap.put("fecha",(new Formatos()).dateTostring( controlNC.getFecha(), ConstantsColombianJsf.Formatos.FORMAT_DATE));
                parameterMap.put("titulo", "Comprobante Notas Credito");
                SedesDto sedesDto = connectsAuth.findSedeXName(sede);
                parameterMap.put("sede", sedesDto.getSede());
                parameterMap.put("nombresede", sedesDto.getTitulo());
                parameterMap.put("slogan", sedesDto.getSlogan());
                parameterMap.put("usuario", securityServiceNotasController.getCurrentUser().getUsername());
                mavComprobanteNC = new ModelAndView("notasSede", parameterMap);
                return mavComprobanteNC;
            }
        }
        mavComprobanteNC = new ModelAndView("redirect:/" + sede + "/traslados/reporte/traslados.htm");
        mavComprobanteNC.addObject("mensaje", "Se encontrar&oacute;n 0 registros");

        return mavComprobanteNC;
    }
    
    
    @RequestMapping("/debito/comprobante.htm")
    public ModelAndView comprobanteNotasDebito(@RequestParam Integer idComprobante, @PathVariable String sede) {
        ModelAndView mavComprobanteNC = null;
        List<NotasDetalleDto> detalleComprobante = notasService.consultarNotaDebito(sede,idComprobante == null ? 0 : idComprobante.intValue());
        ControlNotasDebito controlND = notasService.consultarControlNotaDebito(sede, idComprobante);
        
        if (detalleComprobante != null) {
            if (!detalleComprobante.isEmpty()) {
                JRDataSource datos = new JRBeanCollectionDataSource(detalleComprobante);
                Map<String, Object> parameterMap = new HashMap<>();
                parameterMap.put("datos", datos);
                parameterMap.put("idcomprobante", idComprobante);
                parameterMap.put("fecha",(new Formatos()).dateTostring(controlND.getFecha(), ConstantsColombianJsf.Formatos.FORMAT_DATE));
                parameterMap.put("titulo", "Comprobante Notas Debito");
                SedesDto sedesDto = connectsAuth.findSedeXName(sede);
                parameterMap.put("sede", sedesDto.getSede());
                parameterMap.put("nombresede", sedesDto.getTitulo());
                parameterMap.put("slogan", sedesDto.getSlogan());
                parameterMap.put("usuario", securityServiceNotasController.getCurrentUser().getUsername());
                mavComprobanteNC = new ModelAndView("notasSede", parameterMap);
                return mavComprobanteNC;
            }
        }
        mavComprobanteNC = new ModelAndView("redirect:/" + sede + "/traslados/reporte/traslados.htm");
        mavComprobanteNC.addObject("mensaje", "Se encontrar&oacute;n 0 registros");

        return mavComprobanteNC;
    }
}
