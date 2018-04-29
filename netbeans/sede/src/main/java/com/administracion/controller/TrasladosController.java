/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.administracion.dto.DetalleFacturaDTO;
import com.administracion.dto.DetalleFacturaTrasladoDTO;
import com.administracion.dto.SedesDto;
import com.administracion.dto.TrasladosDto;
import com.administracion.service.FacturasService;
import com.administracion.service.SedesService;
import com.administracion.service.autorizacion.ConnectsAuth;
import com.administracion.util.Formatos;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jose Efren
 */
@Controller
@RequestMapping("/{sede:[a-zA-Z]+}/traslados")
public class TrasladosController extends BaseController {

    @Autowired
    SedesService sedesService;
    @Autowired
    FacturasService facturasService;
    @Autowired
    private ConnectsAuth connectsAuth;

    private final String estadoFactura = "A";

    @RequestMapping("/home.htm")
    public ModelAndView home() {

        ModelAndView mav = new ModelAndView("facturacion/traslados/factura");
        DetalleFacturaTrasladoDTO detalleFacturaTrasladoDTO = new DetalleFacturaTrasladoDTO();
        mav.addObject("detalleFacturaTrasladoDTO", detalleFacturaTrasladoDTO);
        mav.addObject("titulo", "Traslados");
        setBasicModel(mav, detalleFacturaTrasladoDTO);
        return mav;
    }

    @RequestMapping(value = "/ajax/guardarTraslado.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    String guardarTraslado(@Valid DetalleFacturaTrasladoDTO detalleFacturaTrasladoDTO,
            @PathVariable String sede) {

        ModelAndView mav = new ModelAndView("facturacion/traslados/formFactura");
        //Hago el traslado
        List<DetalleFacturaDTO> facturasTrasladadas;
        facturasTrasladadas = facturasService.trasladarFactura(sede, detalleFacturaTrasladoDTO);
        String facturas = facturasTrasladadas.get(0).getNumeroFactura() + "@" + facturasTrasladadas.get(1).getNumeroFactura();
        mav.addObject("titulo", "Traslados");
        setBasicModel(mav, detalleFacturaTrasladoDTO);
        return facturas;
    }

    @RequestMapping("/ajax/formFactura.htm")
    public ModelAndView iniciarFormFactura() {
        ModelAndView mav = new ModelAndView("facturacion/traslados/formFactura");
        DetalleFacturaTrasladoDTO detalleFacturaTrasladoDTO = new DetalleFacturaTrasladoDTO();
        mav.addObject("detalleFacturaTrasladoDTO", detalleFacturaTrasladoDTO);
        mav.addObject("titulo", "Traslados");
        setBasicModel(mav, detalleFacturaTrasladoDTO);
        return mav;
    }

    @RequestMapping("/reporte/traslados.htm")
    public ModelAndView indexReporteTraslados(@RequestParam(required = false, value = "mensaje") String mensaje) {
        ModelAndView mav = new ModelAndView("reportes/traslados/reporte_traslados");
        mav.addObject("fechaInicial", new Date());
        mav.addObject("fechaFinal", new Date());
        mav.addObject("titulo", "Reporte Traslados");
        mav.addObject("mensaje", mensaje);
        return mav;
    }

    @RequestMapping("/reporte/trasladosPDF.htm")
    public ModelAndView indexReporteTrasladosPDF(@RequestParam(required = false, value = "fechaInicial") String fechaInicial,
             @RequestParam(required = false, value = "fechaFinal") String fechaFinal,
            @PathVariable String sede) {
        ModelAndView mav = null;

        List<TrasladosDto> traslados = facturasService.reporteTraslados(sede, Formatos.StringDateToDate(fechaInicial), Formatos.StringDateToDate(fechaFinal));
        if (traslados != null) {
            if (traslados.size() > 0) {
                JRDataSource datos = new JRBeanCollectionDataSource(traslados);
                Map<String, Object> parameterMap = new HashMap<>();
                parameterMap.put("datos", datos);
                parameterMap.put("fechaInicial", fechaInicial);
                parameterMap.put("fechaFinal", fechaFinal);
                SedesDto sedesDto = connectsAuth.findSedeXName(sede);
                parameterMap.put("nombresede", sedesDto.getTitulo());
                parameterMap.put("slogan", sedesDto.getSlogan());
                mav = new ModelAndView("traslados", parameterMap);
                return mav;
            }
        }
        mav = new ModelAndView("redirect:/" + sede + "/traslados/reporte/traslados.htm");
        mav.addObject("mensaje", "Se encontrar&oacute;n 0 registros");

        return mav;
    }
}
