/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombian.cali.colombiancaliycali.controllers;

import com.colombia.cali.colombiancaliycali.dao.SedesDao;
import com.colombia.cali.colombiancaliycali.util.Formatos;
import com.colombian.cali.colombiancaliycali.dto.BeneficiarioAutocompletarDto;
import com.colombian.cali.colombiancaliycali.dto.DetallePagosProveedorDto;
import com.colombian.cali.colombiancaliycali.dto.DetallePagosTercerosDto;
import com.colombian.cali.colombiancaliycali.dto.PagosConsolidadoSedeDto;
import com.colombian.cali.colombiancaliycali.dto.PagosCabeceraDto;
import com.colombian.cali.colombiancaliycali.dto.PagosProveedorDto;
import com.colombian.cali.colombiancaliycali.dto.PagosTercerosDto;
import com.colombian.cali.colombiancaliycali.dto.ReportePagosDto;
import com.colombian.cali.colombiancaliycali.entidades.DetallePagos;
import com.colombian.cali.colombiancaliycali.entidades.Pagos;
import com.colombian.cali.colombiancaliycali.entidades.Sedes;
import com.colombian.cali.colombiancaliycali.mapper.PagosMapper;
import com.colombian.cali.colombiancaliycali.services.BeneficiariosService;
import com.colombian.cali.colombiancaliycali.services.PagosService;
import com.colombian.cali.colombiancaliycali.services.ReportesService;
import com.colombian.cali.colombiancaliycali.services.SecurityServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@RequestMapping("/pagos")
public class PagosController extends BaseController{
    
    @Autowired
    private BeneficiariosService beneficiariosService;
    @Autowired
    private PagosService pagosService;
    @Autowired
    private SecurityServiceImpl security;
    @Autowired
    private ReportesService reportesService;
    @Autowired
    private SedesDao sedesDao;
    
    private static final String cuentaProveedores = "220505";
    /**
     * Pagos Terceros
     * @return 
     */
    @RequestMapping(value = "/terceros/index.htm")
    public ModelAndView inicioPagos(){
        ModelAndView mav = new ModelAndView("contabilidad/pagosterceros/administracionpagos");
        PagosTercerosDto pagosTercerosDto = new PagosTercerosDto();
        setBasicModel(mav, pagosTercerosDto);
        mav.addObject("pagosTercerosDto", pagosTercerosDto);
        return mav;
    }
    /**
     * Pagos Proveedor
     * @return 
     */
    @RequestMapping(value = "/proveedor/index.htm")
    public ModelAndView inicioPagosProveedor(){
        ModelAndView mav = new ModelAndView("contabilidad/pagosproveedor/administracionpagosproveedor");
        PagosProveedorDto pagosProveedorDto = new PagosProveedorDto();
        setBasicModel(mav, pagosProveedorDto);
        mav.addObject("pagosTercerosDto", pagosProveedorDto);
        mav.addObject("cuentaProveedores", cuentaProveedores);
        return mav;
    }
    
    @RequestMapping(value = "/ajax/comprobante/buscar.htm")
    public ModelAndView buscarPagos(@RequestParam String fechaInicial){
        ModelAndView mav = new ModelAndView("reportes/consolidado/imprimir/comprobantes");
        List<PagosCabeceraDto> pagosProveedor = pagosService.buscarPagosProveedorXFecha(getPropiedades().leerPropiedad(), fechaInicial);
        mav.addObject("pagos", pagosProveedor);
        return mav;
    }
    /**
     * Pagos porcentages sedes
     * @return 
     */
    @RequestMapping(value = "/sede/consolidado/index.htm")
    public ModelAndView inicioPagoSedesConsolidado(){
        ModelAndView mav = new ModelAndView("contabilidad/pagosconconsolidado/pagosConsolidado");
        PagosConsolidadoSedeDto pagosConsolidadoSedeDto = new PagosConsolidadoSedeDto();
        Date fechaDate = new Date();
        pagosConsolidadoSedeDto.setFechaPago(Formatos.dateTostring(fechaDate));
        setBasicModel(mav, pagosConsolidadoSedeDto);
        mav.addObject("pagosConsolidadoSedeDto", pagosConsolidadoSedeDto);
        return mav;
    }
   @RequestMapping(value = "/ajax/consolidado/porcentaje/sede/generar.htm")
    public ModelAndView traerComprobanteConsolidadoSede() {
        ModelAndView mav = null;
        Date fecha = new Date();
        int mes = Formatos.obtenerMes(fecha);
        PagosConsolidadoSedeDto pagosConsolidadoSedeDtos = reportesService.generarPagoConsolidadoSedePorcentaje(getPropiedades().leerPropiedad(), mes-1);
        mav = new ModelAndView("contabilidad/pagosconconsolidado/datosPagoConsolidado");
        mav.addObject("detallePagosCosolidadoSedeDto", pagosConsolidadoSedeDtos.getDetallePagosCosolidadoSedeDtos());
        mav.addObject("fechaActual", Formatos.dateTostring(fecha));
               
        return mav;
    }
    
    @RequestMapping(value = "/ajax/autocompletar/terceros.htm")
    public @ResponseBody String  autocompletarBeneficiarios(@RequestParam String term){
        Gson gson = new Gson();
        String json = "[]";
        List<BeneficiarioAutocompletarDto> beneficiarios = beneficiariosService.buscarBeneficiarioLikeNombre(getPropiedades().leerPropiedad(), term);
        JsonArray jsonArray = null;
        if (beneficiarios != null) {
            json = gson.toJson(beneficiarios);
        }

        return json;
    }
    
    @RequestMapping("/ajax/consolidado/sede/guardar.htm")
    public @ResponseBody String guardarPagoConsolidadoSede(@ModelAttribute PagosConsolidadoSedeDto pagosConsolidadoSedeDto){
        PagosMapper pagosMapper = new PagosMapper();
        Pagos pagosTerceros = pagosMapper.pagoConsolidadoSedeDtoToPagoCabecera(pagosConsolidadoSedeDto);
        List<DetallePagos> detallePagosTerceros = pagosMapper.detallePagosCosolidadoSedeDtosToDetallePagos(pagosConsolidadoSedeDto.getDetallePagosCosolidadoSedeDtos());
        
        pagosService.guardarPagosTerceros(getPropiedades().leerPropiedad(), pagosTerceros, detallePagosTerceros);
        
        return "ok";
    }
    @RequestMapping(value = "/reporte/sede/todos.htm")
    public ModelAndView reporteConsolidadoSedeInicio() {
        ModelAndView mav = new ModelAndView("reportes/pagos/pagosSede");
        return mav;
    }
    @RequestMapping("/ajax/terceros/guardar.htm")
    public @ResponseBody String guardarPagoTerceros(@ModelAttribute PagosTercerosDto pagosTercerosDto){
        PagosMapper pagosMapper = new PagosMapper();
        Pagos pagosTerceros = pagosMapper.pagoBeneficiarioDtoToPagoCabecera(pagosTercerosDto);
        List<DetallePagos> detallePagosTerceros = pagosMapper.detallePagosTercerosDtoTodetallePagosTerceros(pagosTercerosDto.getDetallePagosTerceros());
        
        pagosService.guardarPagosTerceros(getPropiedades().leerPropiedad(), pagosTerceros, detallePagosTerceros);
        
        return "ok";
    }
    @RequestMapping("/ajax/proveedor/guardar.htm")
    public @ResponseBody String guardarPagoProveedor(@ModelAttribute PagosProveedorDto pagosProveedorDto){
        PagosMapper pagosMapper = new PagosMapper();
        //to do: Hacer los mappers de las clases
        Pagos pagosProveedor = pagosMapper.pagoProveedorDtoToPagoCabecera(pagosProveedorDto);
        List<DetallePagos> detallePagosProveedor = pagosMapper.detallePagosProveedorDtoTodetallePagosTerceros(pagosProveedorDto.getDetallePagosProveedor());
        
        pagosService.guardarPagosProveedor(getPropiedades().leerPropiedad(), pagosProveedor, detallePagosProveedor);
        
        
        return "ok";
    }
    @RequestMapping(value = "/terceros/pdf/comprobante.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView comprobanteTerceroPDF(Long idpagotercero){
        
        List<DetallePagosTercerosDto> detalle = pagosService.buscarDetallePagosTercerosDtos(getPropiedades().leerPropiedad(), idpagotercero);
        Pagos cabecera = pagosService.buscarPagoXIdPago(getPropiedades().leerPropiedad(), idpagotercero);
        ModelAndView mav = null;
        if(detalle!=null){
            if(detalle.size()>0){
                JRDataSource datos = new JRBeanCollectionDataSource(detalle);
                Map<String, Object> parameterMap = new HashMap<String, Object>();
                parameterMap.put("usuario", security.getCurrentUser().getUsername());
                parameterMap.put("datos", detalle);
                parameterMap.put("comprobante", cabecera.getIdpagos());
                parameterMap.put("titulo", "Comprobante Terceros Caja Mayor");
                mav = new ModelAndView("comprobanteBeneficiario", parameterMap);
                return mav;
            }
        }
        return mav;
    }
    
    @RequestMapping(value = "/pdf/sede/todos.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reportePagosPDF(@RequestParam String fechaInicial, @RequestParam String fechaFinal,@RequestParam Long idsede){
        

        List<ReportePagosDto> pagos = pagosService.reportePagos(getPropiedades().leerPropiedad(), fechaInicial,fechaFinal, idsede);
        Sedes sedes = sedesDao.findSede(idsede);
        
        ModelAndView mav = null;
        if(pagos!=null){
            if(pagos.size()>0){
                JRDataSource datos = new JRBeanCollectionDataSource(pagos);
                Map<String, Object> parameterMap = new HashMap<String, Object>();
                parameterMap.put("usuario", security.getCurrentUser().getUsername());
                parameterMap.put("datos", pagos);
                parameterMap.put("sede", sedes.getSede());
                parameterMap.put("fechaInicial", Formatos.StringDateToDate(fechaInicial));
                parameterMap.put("fechaFinal", Formatos.StringDateToDate(fechaFinal));
                mav = new ModelAndView("reportePagos", parameterMap);
                return mav;
            }
        }
        return mav;
    }
    
     @RequestMapping(value = "/proveedores/pdf/comprobante.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView comprobanteProveedorPDF(Long idpagoproveedor){
        
        List<DetallePagosProveedorDto> detalle = pagosService.buscarDetallePagosDtos(getPropiedades().leerPropiedad(), idpagoproveedor);
        Pagos cabecera = pagosService.buscarPagoXIdPago(getPropiedades().leerPropiedad(), idpagoproveedor);
        ModelAndView mav = null;
        if(detalle!=null){
            if(detalle.size()>0){
                JRDataSource datos = new JRBeanCollectionDataSource(detalle);
                Map<String, Object> parameterMap = new HashMap<String, Object>();
                parameterMap.put("usuario", security.getCurrentUser().getUsername());
                parameterMap.put("datos", detalle);
                parameterMap.put("comprobante", cabecera.getIdpagos());
                parameterMap.put("fecha", cabecera.getFecha());
                mav = new ModelAndView("comprobanteProveedor", parameterMap);
                return mav;
            }
        }
        return mav;
    }
    @RequestMapping(value = "/ajax/secuencia.htm")
    public @ResponseBody String secuenciaPago(){
        Long secuencia = pagosService.secuenciaPagos(getPropiedades().leerPropiedad());
        if(secuencia!=null)
            return secuencia.toString();
        
        return "";
    }
    
    @RequestMapping(value = "/ajax/fecha.htm")
    public @ResponseBody String fechaActual(){
        Date fecha = new Date();
        return Formatos.dateTostring(fecha);
    }
}
