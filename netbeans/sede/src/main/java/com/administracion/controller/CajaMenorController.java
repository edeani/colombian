/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;


import com.adiministracion.mapper.CajaMenorMapper;
import com.administracion.dto.DetallePagosProveedorDto;
import com.administracion.dto.DetallePagosTercerosDto;
import com.administracion.dto.MovimientoCajaDto;
import com.administracion.dto.PagosConsolidadoSedeDto;
import com.administracion.dto.PagosProveedorDto;
import com.administracion.dto.PagosTercerosDto;
import com.administracion.entidad.CajaMenor;
import com.administracion.entidad.DetalleCajaMenor;
import com.administracion.service.CajaMenorService;
import com.administracion.service.MysqlService;
import com.administracion.service.ReporteService;
import com.administracion.service.autorizacion.SecurityService;
import com.administracion.util.Formatos;
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
@RequestMapping("/cajamenor")
public class CajaMenorController extends BaseController {

    @Autowired
    private MysqlService mysqlService;
    @Autowired
    private ReporteService reporteService;
    @Autowired
    private SecurityService security;
    @Autowired
    private CajaMenorService cajaMenorService;
    private  final String cuentaProveedores = "220505";
    /**
     * Pagos terceros de la caja menor
     * @return 
     */
    @RequestMapping(value = "/terceros/index.htm")
    public ModelAndView inicioPagosTercerosCajaMenor(){
        ModelAndView mav = new ModelAndView("contabilidad/cajaMenor/terceros/administracionpagos");
        PagosTercerosDto pagosTercerosDto = new PagosTercerosDto();
        setBasicModel(mav, pagosTercerosDto);
        mav.addObject("pagosTercerosDto", pagosTercerosDto);
        return mav;
    }
    
    /**
     * 
     * @param pagosTercerosDto
     * @return 
     */
    @RequestMapping("/ajax/terceros/guardar.htm")
    public @ResponseBody String guardarPagoTerceros(@ModelAttribute PagosTercerosDto pagosTercerosDto){
        CajaMenorMapper cajaMenorMapper = new CajaMenorMapper();
        CajaMenor pagosTerceros = cajaMenorMapper.pagosTercerosDtoToPagoCabecera(pagosTercerosDto);
        List<DetalleCajaMenor> detallePagosTerceros = cajaMenorMapper.detallePagosTercerosDtoToDetalleCajaMenor(pagosTercerosDto.getDetallePagosTerceros());
        
        cajaMenorService.guardarPagosTercerosCajaMenor(getPropiedades().leerPropiedad(), pagosTerceros, detallePagosTerceros);
        
        return "ok";
    }
    
    @RequestMapping(value = "/terceros/pdf/comprobante.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView comprobanteTerceroPDF(@RequestParam Long idpagotercero,@RequestParam(required = false) String titulo){
        if(titulo==null){
            titulo="";
        }
        List<DetallePagosTercerosDto> detalle = cajaMenorService.buscarDetallePagosTercerosCajaMenorDtos(getPropiedades().leerPropiedad(), idpagotercero);
        CajaMenor cabecera = cajaMenorService.buscarPagoXIdPagoCajaMenor(getPropiedades().leerPropiedad(), idpagotercero);
        ModelAndView mav = null;
        if(detalle!=null){
            if(detalle.size()>0){
                JRDataSource datos = new JRBeanCollectionDataSource(detalle);
                Map<String, Object> parameterMap = new HashMap<>();
                parameterMap.put("usuario", security.getCurrentUser().getUsername());
                parameterMap.put("datos", detalle);
                parameterMap.put("comprobante", cabecera.getIdcajamenor());
                if(titulo.isEmpty()){
                   parameterMap.put("titulo", "Comprobante Terceros Bancos");
                }else{
                    parameterMap.put("titulo", titulo);
                }
                mav = new ModelAndView("comprobanteBeneficiario", parameterMap);
                return mav;
            }
        }
        return mav;
    }
    
    /**
     * Pagos Caja Menor Proveedor
     * @return 
     */
    @RequestMapping(value = "/proveedor/index.htm")
    public ModelAndView inicioPagosProveedor(){
        ModelAndView mav = new ModelAndView("contabilidad/cajaMenor/proveedor/administracionpagosproveedor");
        PagosProveedorDto pagosProveedorDto = new PagosProveedorDto();
        setBasicModel(mav, pagosProveedorDto);
        mav.addObject("pagosTercerosDto", pagosProveedorDto);
        mav.addObject("cuentaProveedores", cuentaProveedores);
        return mav;
    }
    
    /**
     * Guardar Pagos Proveedor
     * @param pagosProveedorDto
     * @return 
     */
    @RequestMapping("/ajax/proveedor/guardar.htm")
    public @ResponseBody String guardarPagoProveedor(@ModelAttribute PagosProveedorDto pagosProveedorDto){
        CajaMenorMapper cajaMenorMapper = new CajaMenorMapper();
        //to do: Hacer los mappers de las clases
        CajaMenor cajaMenor = cajaMenorMapper.pagoProveedorDtoToCajaMenorCabecera(pagosProveedorDto);
        List<DetalleCajaMenor> detallePagosCajaMenor = cajaMenorMapper.detallePagosProveedorDtoTodetallePagosCajaMenor(pagosProveedorDto.getDetallePagosProveedor());
        
        cajaMenorService.guardarPagosProveedorCajaMenor(getPropiedades().leerPropiedad(),cajaMenor , detallePagosCajaMenor);
        
        return "ok";
    }
    
    @RequestMapping(value = "/proveedores/pdf/comprobante.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView comprobanteProveedorPDF(Long idpagoproveedor){
        
        List<DetallePagosProveedorDto> detalle = cajaMenorService.buscarDetallePagosDtos(getPropiedades().leerPropiedad(), idpagoproveedor);
        CajaMenor cabecera = cajaMenorService.buscarPagoXIdPagoCajaMenor(getPropiedades().leerPropiedad(), idpagoproveedor);
        ModelAndView mav = null;
        if(detalle!=null){
            if(detalle.size()>0){
                JRDataSource datos = new JRBeanCollectionDataSource(detalle);
                Map<String, Object> parameterMap = new HashMap<>();
                parameterMap.put("usuario", security.getCurrentUser().getUsername());
                parameterMap.put("datos", detalle);
                parameterMap.put("comprobante", cabecera.getIdcajamenor());
                parameterMap.put("fecha", cabecera.getFecha());
                mav = new ModelAndView("comprobanteProveedor", parameterMap);
                return mav;
            }
        }
        return mav;
    }
    
    /**
     * Pagos porcentages sedes Caja Menor
     * @return 
     */
    @RequestMapping(value = "/sede/consolidado/index.htm")
    public ModelAndView inicioPagoSedesConsolidadoCajaMenor(){
        ModelAndView mav = new ModelAndView("contabilidad/cajaMenor/pagosconsolidado/pagosConsolidado");
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
        PagosConsolidadoSedeDto pagosConsolidadoSedeDtos = reporteService.generarPagoConsolidadoSedePorcentaje(getPropiedades().leerPropiedad(), mes-1);
        mav = new ModelAndView("contabilidad/cajaMenor/pagosconsolidado/datosPagoConsolidado");
        mav.addObject("detallePagosCosolidadoSedeDto", pagosConsolidadoSedeDtos.getDetallePagosCosolidadoSedeDtos());
        mav.addObject("fechaActual", Formatos.dateTostring(fecha));
               
        return mav;
    }
    
    
    @RequestMapping("/ajax/consolidado/sede/guardar.htm")
    public @ResponseBody String guardarPagoConsolidadoSede(@ModelAttribute PagosConsolidadoSedeDto pagosConsolidadoSedeDto){
        CajaMenorMapper cajaMenorMapper = new CajaMenorMapper();
        CajaMenor pagosCajaMenor = cajaMenorMapper.pagoConsolidadoSedeDtoToCajaMenorCabecera(pagosConsolidadoSedeDto);
        List<DetalleCajaMenor> detallePagosCajaMenor = cajaMenorMapper.detallePagosCosolidadoSedeDtosToDetalleCajaMenor(pagosConsolidadoSedeDto.getDetallePagosCosolidadoSedeDtos());
        
        cajaMenorService.guardarPagosTercerosCajaMenor(getPropiedades().leerPropiedad(), pagosCajaMenor, detallePagosCajaMenor);
        
        return "ok";
    }
    
    
    @RequestMapping(value = "/reporte/index.htm")
    public ModelAndView indexReporteCajaMenor() {
        ModelAndView mav = new ModelAndView("reportes/consolidado/cajamenor/cajaMenor");
        mav.addObject("fechaInicial", new Date());
        mav.addObject("fechaFinal", new Date());
        mav.addObject("titulo", "Reporte Movimientos Bancos");
        return mav;
    }

    @RequestMapping(value = "/reporte/pdf.htm")
    public ModelAndView generarReportePdfCajaMenor(@RequestParam String fechaInicial, @RequestParam String fechaFinal) {

        List<MovimientoCajaDto> movimientos = reporteService.movimientoCajaMenor(getPropiedades().leerPropiedad(), fechaInicial, fechaFinal);
        ModelAndView mav = null;
        if (movimientos != null) {

            JRDataSource datos = new JRBeanCollectionDataSource(movimientos);
            Map<String, Object> parameterMap = new HashMap<>();

            parameterMap.put("datos", datos);
            parameterMap.put("fechaInicio", Formatos.StringDateToDate(fechaInicial));
            parameterMap.put("fechaFin", Formatos.StringDateToDate(fechaFinal));
            parameterMap.put("usuario", security.getCurrentUser().getUsername());
            parameterMap.put("titulo", "Libro Movimiento Bancos");
            mav = new ModelAndView("movimientoCaja", parameterMap);

        }
        return mav;
    }
    
    @RequestMapping(value = "/ajax/secuencia.htm")
    public @ResponseBody String secuenciaPago(){
        Long secuencia = mysqlService.secuenciaTabla(getPropiedades().leerPropiedad(), "caja_menor");
        if(secuencia!=null)
            return secuencia.toString();
        
        return "";
    }
}
