/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package com.administracion.controller;

import com.adiministracion.mapper.PagosMapper;
import com.administracion.dao.SedesDao;
import com.administracion.dto.BeneficiarioAutocompletarDto;
import com.administracion.dto.DetallePagosProveedorDto;
import com.administracion.dto.DetallePagosTercerosDto;
import com.administracion.dto.PagosCabeceraDto;
import com.administracion.dto.PagosConsolidadoSedeDto;
import com.administracion.dto.PagosProveedorDto;
import com.administracion.dto.PagosTercerosDto;
import com.administracion.dto.ReportePagosDto;
import com.administracion.dto.SedesDto;
import com.administracion.entidad.DetallePagos;
import com.administracion.entidad.Pagos;
import com.administracion.entidad.Sedes;
import com.administracion.service.BeneficiariosService;
import com.administracion.service.PagosService;
import com.administracion.service.ReporteService;
import com.administracion.service.autorizacion.ConnectsAuth;
import com.administracion.service.autorizacion.SecurityService;
import com.administracion.util.Formatos;
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
@RequestMapping("/{sede:[a-zA-Z]+}/pagos")
public class PagosController extends BaseController {

    @Autowired
    private BeneficiariosService beneficiariosService;
    @Autowired
    private PagosService pagosService;
    @Autowired
    private SecurityService security;
    @Autowired
    private ReporteService reportesService;
    @Autowired
    private SedesDao sedesDao;
    @Autowired
    private ConnectsAuth connectsAuth;

    private final String cuentaProveedores = "220505";

    /**
     * Pagos Terceros
     *
     * @return
     */
    @RequestMapping(value = "/terceros/index.htm")
    public ModelAndView inicioPagos() {
        ModelAndView mav = new ModelAndView("contabilidad/pagosterceros/administracionpagos");
        PagosTercerosDto pagosTercerosDto = new PagosTercerosDto();
        setBasicModel(mav, pagosTercerosDto);
        mav.addObject("pagosTercerosDto", pagosTercerosDto);
        return mav;
    }

    @RequestMapping("/ajax/terceros/tabla.htm")
    public ModelAndView tablaPagosTerceros(){
        return new ModelAndView("contabilidad/pagosterceros/tablaPagosTerceros");
    }
    
    @RequestMapping(value = "/terceros/edicion/index.htm")
    public ModelAndView inicioPagosEdicion() {
        ModelAndView mav = new ModelAndView("contabilidad/pagosterceros/edicion/administracionpagos");
        PagosTercerosDto pagosTercerosDto = new PagosTercerosDto();
        setBasicModel(mav, pagosTercerosDto);
        mav.addObject("pagosTercerosDto", pagosTercerosDto);
        return mav;
    }
    
    @RequestMapping(value = "/ajax/terceros/buscar.htm")
    public ModelAndView inicioPagosEdicion(@PathVariable String sede,@RequestParam Long idpagotercero){
        ModelAndView mav = new ModelAndView("contabilidad/pagosterceros/edicion/tablaPagosTerceros");
        List<DetallePagosTercerosDto> detalle = pagosService.buscarDetallePagosTercerosDtos(sede, idpagotercero);
        PagosCabeceraDto pagosProveedor = pagosService.buscarPagosProveedorXId(sede, idpagotercero);
        
        PagosMapper pagosMapper = new PagosMapper();
        PagosTercerosDto pagosTercerosDto = pagosMapper.pagoCabeceraDtoToPagosTercerosDto(pagosProveedor);
        pagosTercerosDto.setDetallePagosTerceros(detalle);
        
        setBasicModel(mav, pagosTercerosDto);
        mav.addObject("pagosTercerosDto", pagosTercerosDto);
        return mav;
    }
    /**
     * Pagos Proveedor
     *
     * @return
     */
    @RequestMapping(value = "/proveedor/index.htm")
    public ModelAndView inicioPagosProveedor() {
        ModelAndView mav = new ModelAndView("contabilidad/pagosproveedor/administracionpagosproveedor");
        PagosProveedorDto pagosProveedorDto = new PagosProveedorDto();
        setBasicModel(mav, pagosProveedorDto);
        mav.addObject("pagosTercerosDto", pagosProveedorDto);
        mav.addObject("cuentaProveedores", cuentaProveedores);
        return mav;
    }

    @RequestMapping(value = "/ajax/comprobante/buscar.htm")
    public ModelAndView buscarPagos(@RequestParam String fechaInicial, @PathVariable String sede) {
        ModelAndView mav = new ModelAndView("reportes/consolidado/imprimir/comprobantes");
        List<PagosCabeceraDto> pagosProveedor = pagosService.buscarPagosProveedorXFecha(sede, fechaInicial);
        mav.addObject("pagos", pagosProveedor);
        return mav;
    }

    /**
     * Pagos porcentages sedes
     *
     * @return
     */
    @RequestMapping(value = "/sede/consolidado/index.htm")
    public ModelAndView inicioPagoSedesConsolidado() {
        ModelAndView mav = new ModelAndView("contabilidad/pagosconconsolidado/pagosConsolidado");
        PagosConsolidadoSedeDto pagosConsolidadoSedeDto = new PagosConsolidadoSedeDto();
        Date fechaDate = new Date();
        pagosConsolidadoSedeDto.setFechaPago(Formatos.dateTostring(fechaDate));
        setBasicModel(mav, pagosConsolidadoSedeDto);
        mav.addObject("pagosConsolidadoSedeDto", pagosConsolidadoSedeDto);
        return mav;
    }

    @RequestMapping(value = "/ajax/consolidado/porcentaje/sede/generar.htm")
    public ModelAndView traerComprobanteConsolidadoSede(@PathVariable String sede) {
        ModelAndView mav = null;
        Date fecha = new Date();
        int mes = Formatos.obtenerMes(fecha);
        PagosConsolidadoSedeDto pagosConsolidadoSedeDtos = reportesService.generarPagoConsolidadoSedePorcentaje(sede, mes - 1);
        mav = new ModelAndView("contabilidad/pagosconconsolidado/datosPagoConsolidado");
        mav.addObject("detallePagosCosolidadoSedeDto", pagosConsolidadoSedeDtos.getDetallePagosCosolidadoSedeDtos());
        mav.addObject("fechaActual", Formatos.dateTostring(fecha));

        return mav;
    }

    @RequestMapping(value = "/ajax/autocompletar/terceros.htm")
    public @ResponseBody
    String autocompletarBeneficiarios(@RequestParam String term, @PathVariable String sede) {
        Gson gson = new Gson();
        String json = "[]";
        List<BeneficiarioAutocompletarDto> beneficiarios = beneficiariosService.buscarBeneficiarioLikeNombre(sede, term);
        JsonArray jsonArray = null;
        if (beneficiarios != null) {
            json = gson.toJson(beneficiarios);
        }

        return json;
    }

    @RequestMapping("/ajax/consolidado/sede/guardar.htm")
    public @ResponseBody
    String guardarPagoConsolidadoSede(@ModelAttribute PagosConsolidadoSedeDto pagosConsolidadoSedeDto,
            @PathVariable String sede) {
        PagosMapper pagosMapper = new PagosMapper();
        Pagos pagosTerceros = pagosMapper.pagoConsolidadoSedeDtoToPagoCabecera(pagosConsolidadoSedeDto);
        List<DetallePagos> detallePagosTerceros = pagosMapper.detallePagosCosolidadoSedeDtosToDetallePagos(pagosConsolidadoSedeDto.getDetallePagosCosolidadoSedeDtos());

        pagosService.guardarPagosTerceros(sede, pagosTerceros, detallePagosTerceros);

        return "ok";
    }

    @RequestMapping(value = "/reporte/sede/todos.htm")
    public ModelAndView reporteConsolidadoSedeInicio() {
        ModelAndView mav = new ModelAndView("reportes/pagos/pagosSede");
        return mav;
    }

    @RequestMapping("/ajax/terceros/guardar.htm")
    public @ResponseBody
    String guardarPagoTerceros(@ModelAttribute PagosTercerosDto pagosTercerosDto,
            @PathVariable String sede) {
        PagosMapper pagosMapper = new PagosMapper();
        Pagos pagosTerceros = pagosMapper.pagoBeneficiarioDtoToPagoCabecera(pagosTercerosDto);
        List<DetallePagos> detallePagosTerceros = pagosMapper.detallePagosTercerosDtoTodetallePagosTerceros(pagosTercerosDto.getDetallePagosTerceros());

        pagosService.guardarPagosTerceros(sede, pagosTerceros, detallePagosTerceros);

        return "ok";
    }

    @RequestMapping("/ajax/terceros/actualizar.htm")
    public @ResponseBody
    String actualizarPagoTerceros(@ModelAttribute PagosTercerosDto pagosTercerosDto,
            @PathVariable String sede) {
        PagosMapper pagosMapper = new PagosMapper();
        Pagos pagosTerceros = pagosMapper.pagoBeneficiarioDtoToPagoCabecera(pagosTercerosDto);
        List<DetallePagos> detallePagosTerceros = pagosMapper.detallePagosTercerosDtoTodetallePagosTerceros(pagosTercerosDto.getDetallePagosTerceros());

        pagosService.actualizarPagosTerceros(sede, pagosTerceros, detallePagosTerceros);

        return "ok";
    }
    
    @RequestMapping("/ajax/proveedor/guardar.htm")
    public @ResponseBody
    String guardarPagoProveedor(@ModelAttribute PagosProveedorDto pagosProveedorDto,
            @PathVariable String sede) {
        PagosMapper pagosMapper = new PagosMapper();
        //to do: Hacer los mappers de las clases
        Pagos pagosProveedor = pagosMapper.pagoProveedorDtoToPagoCabecera(pagosProveedorDto);
        List<DetallePagos> detallePagosProveedor = pagosMapper.detallePagosProveedorDtoTodetallePagosTerceros(pagosProveedorDto.getDetallePagosProveedor());

        pagosService.guardarPagosProveedor(sede, pagosProveedor, detallePagosProveedor);

        return "ok";
    }

    @RequestMapping(value = "/terceros/pdf/comprobante.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView comprobanteTerceroPDF(Long idpagotercero, @PathVariable String sede) {

        List<DetallePagosTercerosDto> detalle = pagosService.buscarDetallePagosTercerosDtos(sede, idpagotercero);
        Pagos cabecera = pagosService.buscarPagoXIdPago(sede, idpagotercero);
        ModelAndView mav = null;
        if (detalle != null) {
            if (detalle.size() > 0) {
                JRDataSource datos = new JRBeanCollectionDataSource(detalle);
                Map<String, Object> parameterMap = new HashMap<>();
                parameterMap.put("usuario", security.getCurrentUser().getUsername());
                parameterMap.put("datos", detalle);
                parameterMap.put("comprobante", cabecera.getIdpagos());
                parameterMap.put("titulo", "Comprobante Terceros Caja Mayor");
                SedesDto sedesDto = connectsAuth.findSedeXName(sede);
                parameterMap.put("msjtitulo", sedesDto.getTitulo());
                parameterMap.put("nombresede",sede);
                parameterMap.put("slogan", sedesDto.getSlogan());
                mav = new ModelAndView("comprobanteBeneficiario", parameterMap);
                return mav;
            }
        }
        return mav;
    }

    @RequestMapping(value = "/pdf/sede/todos.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reportePagosPDF(@RequestParam String fechaInicial,
            @RequestParam String fechaFinal, @RequestParam Long idsede, @PathVariable String sede) {

        List<ReportePagosDto> pagos = pagosService.reportePagos(sede, fechaInicial, fechaFinal, idsede);
        Sedes sedes = sedesDao.buscarSede(idsede);

        ModelAndView mav = null;
        if (pagos != null) {
            if (pagos.size() > 0) {
                JRDataSource datos = new JRBeanCollectionDataSource(pagos);
                Map<String, Object> parameterMap = new HashMap<>();
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
    public ModelAndView comprobanteProveedorPDF(Long idpagoproveedor, @PathVariable String sede) {

        List<DetallePagosProveedorDto> detalle = pagosService.buscarDetallePagosDtos(sede, idpagoproveedor);
        Pagos cabecera = pagosService.buscarPagoXIdPago(sede, idpagoproveedor);
        ModelAndView mav = null;
        if (detalle != null) {
            if (detalle.size() > 0) {
                JRDataSource datos = new JRBeanCollectionDataSource(detalle);
                Map<String, Object> parameterMap = new HashMap<>();
                parameterMap.put("usuario", security.getCurrentUser().getUsername());
                parameterMap.put("datos", detalle);
                parameterMap.put("comprobante", cabecera.getIdpagos());
                parameterMap.put("fecha", cabecera.getFecha());
                SedesDto sedesDto = connectsAuth.findSedeXName(sede);
                parameterMap.put("titulo", sedesDto.getTitulo());
                parameterMap.put("nombresede", sede);
                parameterMap.put("slogan", sedesDto.getSlogan());
                mav = new ModelAndView("comprobanteProveedor", parameterMap);
                return mav;
            }
        }
        return mav;
    }

    @RequestMapping(value = "/ajax/secuencia.htm")
    public @ResponseBody
    String secuenciaPago(@PathVariable String sede) {
        Long secuencia = pagosService.secuenciaPagos(sede);
        if (secuencia != null) {
            return secuencia.toString();
        }

        return "";
    }

    @RequestMapping(value = "/ajax/fecha.htm")
    public @ResponseBody
    String fechaActual() {
        Date fecha = new Date();
        return Formatos.dateTostring(fecha);
    }
}
