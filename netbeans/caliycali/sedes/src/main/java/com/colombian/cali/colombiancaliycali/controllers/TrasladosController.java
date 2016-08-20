/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.controllers;

import com.colombia.cali.colombiancaliycali.dao.TrasladoDao;
import com.colombia.cali.colombiancaliycali.util.Formatos;
import com.colombian.cali.colombiancaliycali.dto.ComprasTotalesDTO;
import com.colombian.cali.colombiancaliycali.dto.DetalleFacturaDTO;
import com.colombian.cali.colombiancaliycali.dto.DetalleFacturaTrasladoDTO;
import com.colombian.cali.colombiancaliycali.dto.TrasladosDto;
import com.colombian.cali.colombiancaliycali.services.FacturasService;
import com.colombian.cali.colombiancaliycali.services.SedesService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/traslados")
public class TrasladosController extends BaseController{

    @Autowired
    SedesService sedesService;
    @Autowired
    FacturasService facturasService;
    
    private static final String estadoFactura = "A";
    @RequestMapping("/home.htm")
    public ModelAndView home(){
   
        ModelAndView mav = new ModelAndView("facturacion/traslados/factura");
        DetalleFacturaTrasladoDTO detalleFacturaTrasladoDTO = new DetalleFacturaTrasladoDTO();
        mav.addObject("detalleFacturaTrasladoDTO", detalleFacturaTrasladoDTO);
        mav.addObject("titulo", "Traslados");
        setBasicModel(mav, detalleFacturaTrasladoDTO);
        return mav;
    }
    
    @RequestMapping(value="/ajax/guardarTraslado.htm",method={RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody String guardarTraslado(@Valid DetalleFacturaTrasladoDTO detalleFacturaTrasladoDTO){
        
        ModelAndView mav = new ModelAndView("facturacion/traslados/formFactura");
        //Hago el traslado
        List<DetalleFacturaDTO> facturasTrasladadas;
        facturasTrasladadas = facturasService.trasladarFactura(getPropiedades().leerPropiedad(), detalleFacturaTrasladoDTO);
        String facturas = facturasTrasladadas.get(0).getNumeroFactura()+"@"+facturasTrasladadas.get(1).getNumeroFactura();
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
    public ModelAndView indexReporteTraslados(@RequestParam(required = false, value = "mensaje") String mensaje){
        ModelAndView mav = new ModelAndView("reportes/traslados/reporte_traslados");
        mav.addObject("fechaInicial", new Date());
        mav.addObject("fechaFinal", new Date());
        mav.addObject("titulo", "Reporte Traslados");
        mav.addObject("mensaje", mensaje);
        return mav;
    }
    
    @RequestMapping("/reporte/trasladosPDF.htm")
    public ModelAndView indexReporteTrasladosPDF(@RequestParam(required = false, value = "fechaInicial") String fechaInicial
            , @RequestParam(required = false, value = "fechaFinal") String fechaFinal){
        ModelAndView mav = null;
       
        List<TrasladosDto> traslados = facturasService.reporteTraslados(getPropiedades().leerPropiedad(), Formatos.StringDateToDate(fechaInicial),Formatos.StringDateToDate(fechaFinal));
        if (traslados != null) {
            if (traslados.size() > 0) {
                JRDataSource datos = new JRBeanCollectionDataSource(traslados);
                Map<String, Object> parameterMap = new HashMap<String, Object>();
                parameterMap.put("datos", datos);
                parameterMap.put("fechaInicial", fechaInicial);
                parameterMap.put("fechaFinal", fechaFinal);
                mav = new ModelAndView("traslados", parameterMap);
                return mav;
            }
        }
        mav = new ModelAndView("redirect:/traslados/reporte/traslados.htm");
        mav.addObject("mensaje", "Se encontrar&oacute;n 0 registros");

        return mav;
    }
}
