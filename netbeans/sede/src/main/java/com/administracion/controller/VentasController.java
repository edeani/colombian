/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.administracion.service.autorizacion.ConnectsAuth;
import com.administracion.service.jsf.VentasColombianService;
import com.administracion.util.ExcelUtil;
import com.mycompany.mapper.VentasMapper;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author EderArmando
 */
@Controller
@RequestMapping("/{sede:[a-zA-Z]+}/ventas")
public class VentasController {

    @Autowired
    private VentasColombianService ventasColombianService;
    @Autowired
    private ConnectsAuth connectsAuth;

    @RequestMapping("/index-ventas.htm")
    public ModelAndView indexVentas(@PathVariable String sede) {
        ModelAndView mav = new ModelAndView("ventas/reporte_ventas");
        mav.addObject("fecha", new Date());
        mav.addObject("sede", sede);
        return mav;
    }

    @RequestMapping("/ajax/ventasmesa.htm")
    public ModelAndView getVentasMesa(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fi,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date ff, @RequestParam Integer idSubsede, @PathVariable String sede) {
        ModelAndView mav = new ModelAndView("ventas/tabla_ventas");
        mav.addObject("titulo", "Reporte Ventas Mesa");
        mav.addObject("clase", "Mesa");
        mav.addObject("datosVenta", ventasColombianService.ventasMesa(
                connectsAuth.findSubsedeXId(idSubsede).getSede(), fi, ff));
        return mav;
    }

    @RequestMapping("/ajax/ventasdomicilio.htm")
    public ModelAndView getVentasDomicilios(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fi,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date ff, @RequestParam Integer idSubsede, @PathVariable String sede) {
        ModelAndView mav = new ModelAndView("ventas/tabla_ventas");
        mav.addObject("titulo", "Reporte Ventas Domicilio");
        mav.addObject("clase", "Domicilio");
        mav.addObject("datosVenta", ventasColombianService.ventasDomicilio(connectsAuth.findSubsedeXId(idSubsede).getSede(),
                fi, ff));
        return mav;
    }

    @RequestMapping("/ajax/ventasmostrador.htm")
    public ModelAndView getVentasMostrador(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fi,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date ff, @RequestParam Integer idSubsede, @PathVariable String sede) {
        ModelAndView mav = new ModelAndView("ventas/tabla_ventas");
        mav.addObject("titulo", "Reporte Ventas Llevar");
        mav.addObject("clase", "Llevar");
        mav.addObject("datosVenta", ventasColombianService.ventasMostrador(connectsAuth.findSubsedeXId(idSubsede).getSede(),
                fi, ff));
        return mav;
    }

    @RequestMapping("/ajax/ventastotal.htm")
    public ModelAndView getVentasTotal(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fi,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date ff, @RequestParam Integer idSubsede, @PathVariable String sede) {
        ModelAndView mav = new ModelAndView("ventas/tabla_ventas");
        mav.addObject("titulo", "Reporte Venta Total");
        mav.addObject("clase", "Total");
        mav.addObject("reporte", "total");
        mav.addObject("datosVenta", ventasColombianService.totalVentas(connectsAuth.findSubsedeXId(idSubsede).getSede(),
                fi, ff));
        return mav;
    }

    
    @PostMapping("/ajax/reporte/ventastotal.htm")
    public @ResponseBody
    void reporteExcelVentasTotales(HttpServletResponse response, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fi,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date ff, @RequestParam Integer idSubsede, @PathVariable String sede) throws Exception {
        ExcelUtil excelUtil = new ExcelUtil();
        List<VentasMapper> ventasTotales = ventasColombianService.totalVentas(connectsAuth.findSubsedeXId(idSubsede).getSede(),
                fi, ff);
        if (ventasTotales != null && !ventasTotales.isEmpty()) {
            byte[] excelBytes = excelUtil.buildExcelDocument(ventasTotales, "reporte_total", VentasMapper.fieldsOrder,VentasMapper.fieldsType);
            response.setHeader("Content-Disposition", "attachment; filename=ventastotal.xlsx");
            response.getOutputStream().write(excelBytes);
            response.flushBuffer();
        }

    }
    
    @PostMapping("/ajax/reporte/llevartotalxls.htm")
    public @ResponseBody
    void reporteExcelLlevarTotales(HttpServletResponse response, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fi,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date ff, @RequestParam Integer idSubsede, @PathVariable String sede) throws Exception {
        ExcelUtil excelUtil = new ExcelUtil();
        List<VentasMapper> llevarTotales = ventasColombianService.ventasMostradorTotalCalc(connectsAuth.findSubsedeXId(idSubsede).getSede(),
                fi, ff);
        if (llevarTotales != null && !llevarTotales.isEmpty()) {
            byte[] excelBytes = excelUtil.buildExcelDocument(llevarTotales, "reporte_total", VentasMapper.fieldsOrder,VentasMapper.fieldsType);
            response.setHeader("Content-Disposition", "attachment; filename=llevartotal.xlsx");
            response.getOutputStream().write(excelBytes);
            response.flushBuffer();
        }

    }

    @PostMapping("/ajax/reporte/domiciliototalxls.htm")
    public @ResponseBody
    void reporteExcelDomicilioTotales(HttpServletResponse response, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fi,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date ff, @RequestParam Integer idSubsede, @PathVariable String sede) throws Exception {
        ExcelUtil excelUtil = new ExcelUtil();
        List<VentasMapper> domiciliosTotales = ventasColombianService.ventasDomicilioTotalCalc(connectsAuth.findSubsedeXId(idSubsede).getSede(),
                fi, ff);
        if (domiciliosTotales != null && !domiciliosTotales.isEmpty()) {
            byte[] excelBytes = excelUtil.buildExcelDocument(domiciliosTotales, "reporte_total", VentasMapper.fieldsOrder,VentasMapper.fieldsType);
            response.setHeader("Content-Disposition", "attachment; filename=domiciliototal.xlsx");
            response.getOutputStream().write(excelBytes);
            response.flushBuffer();
        }

    }
    
    @PostMapping("/ajax/reporte/mesatotalxls.htm")
    public @ResponseBody
    void reporteExcelMesaTotales(HttpServletResponse response, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fi,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date ff, @RequestParam Integer idSubsede, @PathVariable String sede) throws Exception {
        ExcelUtil excelUtil = new ExcelUtil();
        List<VentasMapper> mesasTotales = ventasColombianService.ventasMesaTotalCalc(connectsAuth.findSubsedeXId(idSubsede).getSede(),
                fi, ff);
        if (mesasTotales != null && !mesasTotales.isEmpty()) {
            byte[] excelBytes = excelUtil.buildExcelDocument(mesasTotales, "reporte_total", VentasMapper.fieldsOrder,VentasMapper.fieldsType);
            response.setHeader("Content-Disposition", "attachment; filename=mesatotal.xlsx");
            response.getOutputStream().write(excelBytes);
            response.flushBuffer();
        }

    }

}
