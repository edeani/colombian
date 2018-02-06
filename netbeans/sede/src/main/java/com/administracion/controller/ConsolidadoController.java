/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.administracion.dao.SedesDao;
import com.administracion.dao.SubSedesDao;
import com.administracion.dto.BalanceDto;
import com.administracion.dto.CierreSedesDto;
import com.administracion.dto.ComprobanteCierreSedesDto;
import com.administracion.dto.ComprobanteConsolidadoSedeDto;
import com.administracion.dto.EstadoPerdidaGananciaProvisionalDto;
import com.administracion.dto.ItemsDTO;
import com.administracion.dto.MovimientoCajaDto;
import com.administracion.dto.ReporteComprobanteCierreDto;
import com.administracion.dto.ReporteConsolidadoDto;
import com.administracion.dto.ReporteTotalCuentasXNivelDto;
import com.administracion.dto.SubSedesDto;
import com.administracion.entidad.Sedes;
import com.administracion.entidad.SubSedes;
import com.administracion.service.CierreSedesService;
import com.administracion.service.CuentasService;
import com.administracion.service.ReporteService;
import com.administracion.service.autorizacion.AccesosSubsedes;
import com.administracion.service.autorizacion.ConnectsAuth;
import com.administracion.service.autorizacion.SecurityService;
import com.administracion.util.Formatos;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
 * @author EderArmando
 */
@Controller
@RequestMapping("/{sede:[a-zA-Z]+}/consolidado")
public class ConsolidadoController extends BaseController {
    
    @Autowired
    private ReporteService reporteService;
    @Autowired
    private CierreSedesService cierreSedesService;
    @Autowired
    private SecurityService security;
    @Autowired
    private SedesDao sedesDao;
    @Autowired
    private SubSedesDao subSedesDao;
    @Autowired
    private CuentasService cuentasService;
    @Autowired
    private AccesosSubsedes accesosSubsedes;
    @Autowired
    private ConnectsAuth connectsAuth;

    @RequestMapping(value = "/sede.htm")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("reportes/consolidado/consolidado");

        mav.addObject("fechaInicial", new Date());
        mav.addObject("fechaFinal", new Date());
        mav.addObject("titulo", " Reporte Consolidado ");
        return mav;
    }

    @RequestMapping(value = "/consolidadoPDF.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteConsolidadoPDF(HttpServletRequest request, HttpServletResponse response,HttpSession session,
            @RequestParam(required = false, value = "fechaInicial") String fechaInicial,
            @PathVariable String sede,@RequestParam(required = false, value = "fechaFinal") String fechaFinal) {
        SubSedesDto ss = connectsAuth.findSubsedeXName((String)session.getAttribute("path"));
        List<ReporteConsolidadoDto> reporte = reporteService.reporteConsolidado(ss.getId(), fechaInicial, fechaFinal);
        ModelAndView mav = null;
        if (reporte.size() > 0) {
            JRDataSource datos = new JRBeanCollectionDataSource(reporte);
            Map<String, Object> parameterMap = new HashMap<>();
            parameterMap.put("datos", datos);
            parameterMap.put("fechaInicial", fechaInicial);
            parameterMap.put("fechaFinal", fechaFinal);
            mav = new ModelAndView("consolidado", parameterMap);
        } else {
            mav = new ModelAndView("redirect:"+sede+"/consolidado/sede.htm");
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
    
    @RequestMapping(value = "/ajax/comprobante/sede/generar.htm")
    public ModelAndView traerComprobanteConsolidadoSede(@RequestParam Integer idSede, @RequestParam(value = "fecha") String sfecha,@PathVariable(value = "sede") String sedePath) {
        ModelAndView mav;
        Date fecha = Formatos.StringDateToDate(sfecha);
        List<ComprobanteConsolidadoSedeDto> comprobanteConsolidadoSedeDto = reporteService.comprobanteConsolidado(sedePath,idSede, fecha);
        if (comprobanteConsolidadoSedeDto == null || comprobanteConsolidadoSedeDto.isEmpty()) {
            SubSedes sede = subSedesDao.findById(idSede);
            mav = new ModelAndView("contabilidad/cierres/datosCierreSedeAgregar");
            mav.addObject("idSede", idSede);
            mav.addObject("sede", sede.getSede());
            mav.addObject("fecha", sfecha);
        } else {
            mav = new ModelAndView("contabilidad/cierres/datosCierreSede");
            mav.addObject("comprobanteConsolidadoSedeDto", comprobanteConsolidadoSedeDto);
        }

        return mav;
    }

    /**
     * Guardar lo de consolidado porcentaje sedes
     *
     * @param comprobanteCierreSedesDto
     * @param sedeEntry
     * @return
     */
    @RequestMapping(value = "/ajax/comprobante/sede/guardar.htm")
    public @ResponseBody
    String guardarComprobanteConsolidadoSede(@ModelAttribute ComprobanteCierreSedesDto comprobanteCierreSedesDto,@PathVariable(value = "sede") String sedeEntry) {
        cierreSedesService.guardarComprobanteCierreService(sedeEntry, comprobanteCierreSedesDto);
        return comprobanteCierreSedesDto.getConsecutivo().toString();
    }

    @RequestMapping(value = "/comprobante/sede/pdf.htm")
    public ModelAndView generarComprobanteCierreSedePdf(@RequestParam Long idComprobanteCierre,@PathVariable String sede) {
        List<ReporteComprobanteCierreDto> reporte = cierreSedesService.buscarDetalleComprobanteCierreSedesView(sede, idComprobanteCierre);
        CierreSedesDto cierreSedesDto = cierreSedesService.buscarComprobanteCierreDto(sede, idComprobanteCierre);
        ModelAndView mav = null;
        if (reporte != null) {
            JRDataSource datos = new JRBeanCollectionDataSource(reporte);
            Map<String, Object> parameterMap = new HashMap<>();

            parameterMap.put("datos", datos);
            parameterMap.put("sede", cierreSedesDto.getSede());
            parameterMap.put("comprobante", cierreSedesDto.getConsecutivo());
            parameterMap.put("fecha", cierreSedesDto.getFecha());
            parameterMap.put("usuario", security.getCurrentUser().getUsername());
            mav = new ModelAndView("comprobanteCierreSede", parameterMap);
        } else {
            mav = new ModelAndView("redirect:"+sede+"/consolidado/comprobante/sede.htm");
        }

        return mav;
    }

    @RequestMapping(value = "/comprobante/reporte/sede/pdf.htm")
    public ModelAndView generarReporteCierreSedePdf(@RequestParam Long idsede,@RequestParam String fechaInicial,@RequestParam String fechaFinal,
            @PathVariable String sede) {
        List<CierreSedesDto> reporte = cierreSedesService.reporteComprobanteCierreSedesView(sede, fechaInicial,
                fechaFinal,idsede);
        Sedes sedeObj = sedesDao.buscarSede(idsede);
        
        ModelAndView mav = null;
        if (reporte != null) {
            JRDataSource datos = new JRBeanCollectionDataSource(reporte);
            Map<String, Object> parameterMap = new HashMap<>();

            parameterMap.put("datos", datos);
            parameterMap.put("sede", sedeObj.getSede());
            parameterMap.put("fechaInicial", Formatos.StringDateToDate(fechaInicial));
            parameterMap.put("fechaFinal", Formatos.StringDateToDate(fechaFinal));
            parameterMap.put("usuario", security.getCurrentUser().getUsername());
            mav = new ModelAndView("reporteCierreSedes", parameterMap);
        } else {
            mav = new ModelAndView("redirect:"+sede+"/"+sede+"/consolidado/comprobante/sede.htm");
        }

        return mav;
    }
    @RequestMapping(value = "/comprobante/cajamayor.htm")
    public ModelAndView comprobanteCajaMayor() {
        ModelAndView mav = new ModelAndView("reportes/consolidado/cajaMayor");
        mav.addObject("fechaInicial", new Date());
        mav.addObject("fechaFinal", new Date());
        mav.addObject("titulo", "Movimientos Caja Mayor");
        return mav;
    }

    @RequestMapping(value = "/comprobante/reporte/movimiento/cajamayor/pdf.htm")
    public ModelAndView reporteCajaMayor(@RequestParam String fechaInicial, @RequestParam String fechaFinal,
            @PathVariable String sede) {
        Date objFechaInicio = Formatos.StringDateToDate(fechaInicial);
        Date objFechaFin = Formatos.StringDateToDate(fechaFinal);
        List<MovimientoCajaDto> movimientos = reporteService.movimientoCajaMayor(sede, objFechaInicio, objFechaFin);
        ModelAndView mav = null;
        if (movimientos != null) {
            JRDataSource datos = new JRBeanCollectionDataSource(movimientos);
            Map<String, Object> parameterMap = new HashMap<>();

            parameterMap.put("datos", datos);
            parameterMap.put("fechaInicio", objFechaInicio);
            parameterMap.put("fechaFin", objFechaFin);
            parameterMap.put("usuario", security.getCurrentUser().getUsername());
            parameterMap.put("titulo", "Libro Auxiliar de Caja Mayor");
            mav = new ModelAndView("movimientoCaja", parameterMap);
        }

        return mav;
    }

    @RequestMapping(value = "/reporte/general/perdidaganancias.htm")
    public ModelAndView reportePerdidaGananciasGeneral() {
        ModelAndView mav = new ModelAndView("reportes/consolidado/perdidaGananciasGeneral");
        mav.addObject("titulo", "Reporte Estado P&eacute;rdidas y Ganancias General");
        Date fecha = new Date();
        mav.addObject("fechaInicial", fecha);
        mav.addObject("fechaFinal", fecha);
        return mav;
    }
    
    @RequestMapping(value = "/reporte/perdidaganancias.htm")
    public ModelAndView reportePerdidaGanancias() {
        ModelAndView mav = new ModelAndView("reportes/consolidado/perdidaGanancias");
        mav.addObject("titulo", "Reporte Estado P&eacute;rdidas y Ganancias Detalle");
        Date fecha = new Date();
        mav.addObject("fechaInicial", fecha);
        mav.addObject("fechaFinal", fecha);
        return mav;
    }

    @RequestMapping(value = "/reporte/perdidaganancias_old/pdf.htm")
    public ModelAndView reportePerdidaGanancias_old(@RequestParam Long tipoReporte, @RequestParam String fechaInicial,
            @RequestParam String fechaFinal,@PathVariable String sede) {

        //Ingresos
        List<ReporteTotalCuentasXNivelDto> reporte = new ArrayList<ReporteTotalCuentasXNivelDto>();
        //List<ReporteTotalCuentasXNivelDto> subreporte = new ArrayList<ReporteTotalCuentasXNivelDto>();
        reporte = reporteService.reportePerdidaIngresoTotalXNivelSede(sede, fechaInicial, fechaFinal);

        ModelAndView mav = null;
        if (!reporte.isEmpty()) {
            JRDataSource datos = new JRBeanCollectionDataSource(reporte);
            Map<String, Object> parameterMap = new HashMap<>();
            parameterMap.put("datos", datos);
            //parameterMap.put("reporteIngresosDataSource", subDatasourceIngresos);
            parameterMap.put("fechaInicio", Formatos.StringDateToDate(fechaInicial));
            parameterMap.put("fechaFin", Formatos.StringDateToDate(fechaFinal));
            parameterMap.put("usuario", security.getCurrentUser().getUsername());
            //mav =  new ModelAndView("estadoPerdidaGanancias");
            mav = new ModelAndView("estadoPerdidaGananciasSede", parameterMap);
        }

        return mav;
    }

    @RequestMapping(value = "/reporte/general/perdidaganancias/pdf.htm")
    public ModelAndView reportePerdidaGananciasProvisional(@RequestParam Long tipoReporte, @RequestParam String fechaInicial,
            @RequestParam String fechaFinal, @RequestParam(required = false) Long sedeNumber
            , @RequestParam(required = false) String nombreSede,@PathVariable String sede) {

        //Ingresos
        List<EstadoPerdidaGananciaProvisionalDto> reporte = new ArrayList<EstadoPerdidaGananciaProvisionalDto>();
        //List<ReporteTotalCuentasXNivelDto> subreporte = new ArrayList<ReporteTotalCuentasXNivelDto>();
        if (tipoReporte == 1L) {
            reporte = reporteService.reporteEstadoPerdidaGananciaProvisional(sede, fechaInicial, fechaFinal);
        } else {
            reporte = reporteService.reporteEstadoPerdidaGananciaProvisionalXSede(sede, fechaInicial, fechaFinal, sedeNumber);
        }
        if (nombreSede == null) {
            nombreSede = "";
        }
        ModelAndView mav = null;
        if (reporte!=null && !reporte.isEmpty()) {
            JRDataSource datos = new JRBeanCollectionDataSource(reporte);
            Map<String, Object> parameterMap = new HashMap<>();
            parameterMap.put("datos", datos);
            //parameterMap.put("reporteIngresosDataSource", subDatasourceIngresos);
            parameterMap.put("fechaInicio", Formatos.StringDateToDate(fechaInicial));
            parameterMap.put("fechaFin", Formatos.StringDateToDate(fechaFinal));
            parameterMap.put("usuario", security.getCurrentUser().getUsername());
            parameterMap.put("nombreSede", nombreSede);
            //mav =  new ModelAndView("estadoPerdidaGanancias");
            mav = new ModelAndView("estadoPerdidaGananciasProvisional", parameterMap);
        }

        return mav;
    }

    @RequestMapping(value = "/reporte/perdidaganancias/pdf.htm")
    public  ModelAndView reportePerdidaGanancias(@RequestParam String fechaInicial,
            @RequestParam String fechaFinal,@RequestParam(required = false) Long sedeNumber
            ,@RequestParam (required = false) String nombreSede,@PathVariable String sede) {
        ModelAndView mav = null;
        try {
            List<BalanceDto> reporte = reporteService.reporteBalanceService(sede, fechaInicial, fechaFinal,sedeNumber);
            List<ItemsDTO> items =  cuentasService.cuentasBase(sede);
            
            /***********************************************/
            if (!reporte.isEmpty()) {
            JRDataSource datos = new JRBeanCollectionDataSource(reporte);
            Map<String, Object> parameterMap = new HashMap<>();
            parameterMap.put("datos", items);
            parameterMap.put("usuario", security.getCurrentUser().getUsername());
            parameterMap.put("fechaInicial", Formatos.StringDateToDate(fechaInicial));
            parameterMap.put("fechaFinal", Formatos.StringDateToDate(fechaFinal));
            parameterMap.put("nombreSede", nombreSede);
            parameterMap.put("JasperCustomSubReportDatasource", datos);
            mav = new ModelAndView("reporteUtilidades", parameterMap);
        }
        } catch (Exception e) {
            System.out.println("ERROR::"+e.getMessage());
        }
        
        
    return mav;
    
    }
}
