/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.administracion.dto.CuentasAutoCompletarDto;
import com.administracion.dto.SedesDto;
import com.administracion.dto.SubSedesDto;
import com.administracion.dto.reports.general.ReporteConsolidadoDto;
import com.administracion.dto.reports.general.ReporteCuentasDetalleDTO;
import com.administracion.entidad.CuentasPuc;
import com.administracion.enumeration.DescargasEnum;
import com.administracion.service.CuentasService;
import com.administracion.service.ReporteService;
import com.administracion.service.SubSedesService;
import com.administracion.service.autorizacion.AccesosSubsedes;
import com.administracion.service.autorizacion.ConnectsAuth;
import com.administracion.util.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jose Efren
 */
@Controller
@RequestMapping("/{sede:[a-zA-Z]+}/cuentas")
public class CuentasController extends BaseController {

    @Autowired
    private CuentasService cuentasService;
    
    @Autowired
    private ReporteService reporteService;


    @Autowired
    private SubSedesService subSedesService;

    @Autowired
    private AccesosSubsedes accesosSubsedesCuentas;

    @Autowired
    private ConnectsAuth connectsAuth;

    @RequestMapping(value = "/index.htm")
    public ModelAndView inicio() {
        ModelAndView mav = new ModelAndView("contabilidad/cuentas");
        CuentasPuc cuentasPuc = new CuentasPuc();
        mav.addObject("cuentasPuc", cuentasPuc);
        setBasicModel(mav, cuentasPuc);
        return mav;
    }

    @RequestMapping(value = "/ajax/actualizar.htm")
    public @ResponseBody
    String actualizarCuenta(@Valid CuentasPuc cuentasPuc, @PathVariable String sede) {
        ModelAndView mav = null;

        try {
            cuentasService.actualizarCuenta(cuentasPuc, sede);
        } catch (Exception e) {
            System.out.println("Controller:actualizarCuenta:" + e.getMessage());
            return "";
        }

        return "ok";

    }

    @RequestMapping(value = "/ajax/detalleGuardar.htm")
    public ModelAndView cargarDetalleGuardar() {

        ModelAndView mav = new ModelAndView("contabilidad/detalleCrearCuenta");
        return mav;

    }

    @RequestMapping(value = "/ajax/guardar.htm")
    public @ResponseBody
    String guardarCuenta(@Valid CuentasPuc cuentasPuc, @PathVariable String sede) {
        ModelAndView mav = null;

        try {
            cuentasService.guardarCuenta(cuentasPuc, sede);
        } catch (Exception e) {
            System.out.println("Controller:guardarCuenta:" + e.getMessage());
            return "";
        }
        return "ok";
    }

    @RequestMapping(value = "/ajax/buscar.htm")
    public ModelAndView buscarCuenta(@RequestParam(value = "idCuenta") Long idCuenta,
            @PathVariable String sede) {

        ModelAndView mav = null;
        boolean haycuenta = false;
        CuentasPuc cuentasPuc = cuentasService.buscarCuenta("" + idCuenta, sede);
        if (cuentasPuc != null) {
            haycuenta = true;
            mav = new ModelAndView("contabilidad/detalleCuenta");
        } else {
            mav = new ModelAndView("contabilidad/cuentas");
        }
        mav.addObject("cuentasPuc", cuentasPuc);
        mav.addObject("haycuenta", haycuenta);
        return mav;
    }

    @RequestMapping(value = "/ajax/autocompletar.htm")
    public @ResponseBody
    String autocompletarCuenta(@RequestParam String term, @PathVariable String sede) {

        Gson gson = new Gson();
        String json = "[]";
        List<CuentasAutoCompletarDto> cuentasAutoCompletarDtos = cuentasService.autocompletarIdCuenta(term, sede);
        JsonArray jsonArray = null;
        if (cuentasAutoCompletarDtos != null) {
            json = gson.toJson(cuentasAutoCompletarDtos);
        }

        return json;
    }

    @RequestMapping("/reportes/detalle.htm")
    public ModelAndView reporteDetalleCuentas(@PathVariable String sede) {
        ModelAndView mav = new ModelAndView("reportes/cuentas/detalleCuentas");
        SedesDto sedesDto = connectsAuth.findSedeXName(sede);
        SubSedesDto subSedePrincipal = subSedesService.getSubSedePrincipal(sedesDto.getIdsedes());
        mav.addObject("titulo", "Detalle Cuentas");
        mav.addObject("sedeSeleccionada", subSedePrincipal.getId());
        Date currentDate = new Date();
        mav.addObject("fechaInicial", currentDate);
        mav.addObject("fechaFinal", currentDate);
        return mav;
    }

    @RequestMapping("/reportes/detalleCuentasFile.htm")
    public ModelAndView reporteDetalleCuentasFile(HttpServletRequest request, HttpServletResponse response, HttpSession session,
            @RequestParam(required = false, value = "fechaInicial") String fechaInicial,
            @RequestParam(required = false, value = "fechaFinal") String fechaFinal,
            @RequestParam String tipo,@RequestParam String idCuenta,
            @RequestParam String concepto, @PathVariable String sede) {
        SedesDto ss = connectsAuth.findSedeXName(sede);
        List<ReporteCuentasDetalleDTO> reporte = reporteService.buscarDetallesCuentas(sede,idCuenta, fechaInicial, fechaFinal);
        ModelAndView mav = null;
        if (reporte.size() > 0) {
            JRDataSource datos = new JRBeanCollectionDataSource(reporte);
            Map<String, Object> parameterMap = new HashMap<>();
            parameterMap.put("datos", datos);
            parameterMap.put("fechaInicial", fechaInicial);
            parameterMap.put("fechaFinal", fechaFinal);
            SedesDto sedesDto = connectsAuth.findSedeXName(sede);
            parameterMap.put("titulo", sedesDto.getTitulo());
            parameterMap.put("nombresede", sede);
            parameterMap.put("slogan", sedesDto.getSlogan());
            
            parameterMap.put("idCuenta", idCuenta);
            parameterMap.put("concepto", concepto);

            mav = new ModelAndView("cuentasDetalle", parameterMap);

            if (tipo.toLowerCase().equals(DescargasEnum.EXCEL.getDescarga())) {
                tipo = DescargasEnum.EXCEL.getTipo();
            } else {
                tipo = DescargasEnum.PDF.getTipo();
            }
            mav.addObject(Constants.Attributos.JASPER_FORMAT, tipo);

        } else {
            mav = new ModelAndView("redirect:/" + sede + "/cuentas/reportes/detalle.htm");
            mav.addObject("mensaje", "Se encontrar&oacute;n 0 registros");
        }
        return mav;
    }
    
    @RequestMapping("/reportes/ajax/detalle/cuenta.htm")
    
    public @ResponseBody String checkDetalleCuentas(HttpServletRequest request, HttpServletResponse response, HttpSession session,
            @RequestParam(required = false, value = "fechaInicial") String fechaInicial,
            @RequestParam(required = false, value = "fechaFinal") String fechaFinal,
            @RequestParam String idCuenta,
            @RequestParam String concepto, @PathVariable String sede){
        
        List<ReporteCuentasDetalleDTO> reporte = reporteService.buscarDetallesCuentas(sede,idCuenta, fechaInicial, fechaFinal);
        
        if(reporte.isEmpty()){
            return "N";
        }else{
            return "S";
        }
    }
}
