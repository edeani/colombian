/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.controllers;

import com.colombia.cali.colombiancaliycali.util.LectorPropiedades;
import com.colombian.cali.colombiancaliycali.dto.FormReporteInventarioDto;
import com.colombian.cali.colombiancaliycali.dto.InventarioDTO;
import com.colombian.cali.colombiancaliycali.dto.InventarioFinalDTO;
import com.colombian.cali.colombiancaliycali.dto.ItemsDTO;
import com.colombian.cali.colombiancaliycali.dto.ReporteInventarioDTO;
import com.colombian.cali.colombiancaliycali.mapper.InventarioMapper;
import com.colombian.cali.colombiancaliycali.services.InventarioService;
import com.colombian.cali.colombiancaliycali.services.SedesService;
import com.google.gson.Gson;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author edeani
 */
@Controller
@RequestMapping("/inventario")
public class InventarioController extends BaseController {

    private static final String titulo = "Formulario de Inventario";
    private static final String tituloReporte = "Reporte de Inventario";
    @Autowired
    SedesService sedesService;
    @Autowired
    InventarioService inventarioService;
    private LectorPropiedades propiedades;

    @RequestMapping("/index.htm")
    public ModelAndView inicio() {
        ModelAndView mav = new ModelAndView("inventario/inventario");
        mav.addObject("titulo", titulo);
        return mav;
    }

    @RequestMapping("/ajax/formularioInventario.htm")
    public ModelAndView formularioInventario() {
        ModelAndView mav = new ModelAndView("inventario/formInventario");

        return mav;
    }

    @RequestMapping("/reportes/inventario.htm")
    public ModelAndView reporteInvetario() {
        FormReporteInventarioDto formReporteInventarioDto = new FormReporteInventarioDto();
        ModelAndView mav = new ModelAndView("reportes/inventario/inventario");
        propiedades = new LectorPropiedades();
        propiedades.setArchivo(getArchivo());
        propiedades.setPropiedad(getPropiedadPrincipal());
        List<ItemsDTO> productos = inventarioService.listaProductosLabel(propiedades.leerPropiedad());
        mav.addObject("productos", productos);
        mav.addObject("fecha", new Date());
        mav.addObject("titulo", titulo);
        mav.addObject("sedes", sedesService.traerSedes(propiedades.leerPropiedad()));
        return mav;
    }

    @RequestMapping(value = "/ajax/inventarioSede.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteInventarioSede(@RequestParam("fecha") String fecha, @RequestParam("sede") String sede) {
        ModelAndView mav = new ModelAndView("reportes/inventario/inventarioSede");
        propiedades = new LectorPropiedades();
        propiedades.setArchivo(getArchivo());
        propiedades.setPropiedad(getPropiedadPrincipal());
        mav.addObject("fecha", fecha);
        mav.addObject("sede", sede);
        return mav;
    }

    @RequestMapping(value = "/ajax/sede.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    String reporteInventarioSedeDatos(@RequestParam("fecha") String fecha,
            @RequestParam("sede") String sede) {
        ModelAndView mav = new ModelAndView("reportes/inventario/sede");

        String s[] = sede.split(",");
        List<ReporteInventarioDTO> reporteInventarioDTOs = inventarioService.reporteInventario(s[0], fecha);
        String json = "[]";
        if (reporteInventarioDTOs != null) {
            Gson gson = new Gson();
            json = gson.toJson(reporteInventarioDTOs);
        }
        System.out.println("JSON::" + json);
        return json;
    }

    @RequestMapping(value = "/ajax/listInventario.htm")
    public ModelAndView listarInventario() {
        ModelAndView mav = new ModelAndView("inventario/listInventario");
        propiedades = new LectorPropiedades();
        propiedades.setArchivo(getArchivo());
        propiedades.setPropiedad(getPropiedadPrincipal());

        mav.addObject("inventarios", inventarioService.reporteInventario(propiedades.leerPropiedad()));

        return mav;
    }

    @RequestMapping(value = "/ajax/eliminarProducto.htm")
    public @ResponseBody
    String eliminarProducto(@RequestParam("idProducto") Long idProducto) {

        propiedades = new LectorPropiedades();
        propiedades.setArchivo(getArchivo());
        propiedades.setPropiedad(getPropiedadPrincipal());

        inventarioService.eliminarProducto(propiedades.leerPropiedad(), idProducto);

        return "";
    }

    @RequestMapping(value = "/ajax/insertarProducto.htm")
    public @ResponseBody
    String insertarProducto(String codigoProductoInventario,
            String fechaInicial,
            String fechaFinal,
            String stockMinimo,
            String stockHoy,
            String stockReal,
            String descripcionProducto,
            String promedio) {

        propiedades = new LectorPropiedades();
        propiedades.setArchivo(getArchivo());
        propiedades.setPropiedad(getPropiedadPrincipal());

        //consulto para ver si existe el producto
        InventarioDTO inventarioDTO = inventarioService.traerProducto(propiedades.leerPropiedad(), Long.parseLong(codigoProductoInventario));

        if (inventarioDTO == null) {
            inventarioDTO = new InventarioDTO();

            inventarioDTO.setCodigoProductoInventario(codigoProductoInventario);
            inventarioDTO.setDescripcionProducto(descripcionProducto);
            inventarioDTO.setFechaFinal(fechaFinal);
            inventarioDTO.setFechaInicial(fechaInicial);
            inventarioDTO.setPromedio(promedio);
            inventarioDTO.setStockHoy(stockHoy);
            inventarioDTO.setStockMinimo(stockMinimo);
            inventarioDTO.setStockReal(stockReal);

            inventarioService.insertarProducto(propiedades.leerPropiedad(), inventarioDTO);

            return "";
        } else {
            return "El c&oacute;digo del producto ya existe";
        }
    }

    @RequestMapping(value = "/ajax/actualizarProducto.htm")
    public @ResponseBody
    String actualizarProducto(@RequestParam("producto") String tramaProducto) {

        propiedades = new LectorPropiedades();
        propiedades.setArchivo(getArchivo());
        propiedades.setPropiedad(getPropiedadPrincipal());

        InventarioDTO inventarioDTO = new InventarioDTO();
        InventarioMapper inventarioMapper = new InventarioMapper();
        inventarioDTO = inventarioMapper.tramaProductoToInventarioDTO(tramaProducto);
        //consulto para ver si existe el producto
        InventarioDTO invDTO = inventarioService.traerProducto(propiedades.leerPropiedad(), Long.parseLong(inventarioDTO.getCodigoProductoInventario()));

        if (invDTO != null) {

            inventarioService.actualizarProducto(propiedades.leerPropiedad(), inventarioDTO);

            return "";
        } else {
            return "El c&oacute;digo del producto no existe";
        }
    }

    @RequestMapping(value = "/reportes/inventarioTotal.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteInventarioTotal(@RequestParam(required = false, value = "mensaje") String mensaje) {

        ModelAndView mav = new ModelAndView("reportes/inventario/inventarioTotal");

        mav.addObject("fechaInicial", new Date());
        mav.addObject("fechaFinal", new Date());
        mav.addObject("titulo", "Inventario Total");
        mav.addObject("mensaje", mensaje);
        return mav;
    }

    @RequestMapping(value = "/reportes/inventarioTotalPDF.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteComprasTotalesProveedorPDF(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false, value = "fechaInicial") String fechaInicial, @RequestParam(required = false, value = "fechaFinal") String fechaFinal
    ) {
        ModelAndView mav = null;
        List<InventarioFinalDTO> inventarioTotal = inventarioService.reporteInventarioFinal(getPropiedades().leerPropiedad(), fechaInicial, fechaFinal);
        if (inventarioTotal.size() > 0) {
            JRDataSource datos = new JRBeanCollectionDataSource(inventarioTotal);
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("datos", datos);
            parameterMap.put("fechaInicial", fechaInicial);
            parameterMap.put("fechaFinal", fechaFinal);
            mav = new ModelAndView("inventarioTotal", parameterMap);
        } else {
            mav = new ModelAndView("redirect:/inventario/reportes/inventarioTotal.htm");
            mav.addObject("mensaje", "Se encontrar&oacute;n 0 registros");
        }
        return mav;
    }

    @RequestMapping("/ajax/selectProducto.htm")
    public ModelAndView selectProducto(@RequestParam(value = "seleccion", required = false) Long seleccion){
        propiedades = new LectorPropiedades();
        propiedades.setArchivo(getArchivo());
        propiedades.setPropiedad(getPropiedadPrincipal());

        ModelAndView mav = new ModelAndView("util/formSelect");

        if (seleccion == null) {
            seleccion = 0L;
        }

        mav.addObject("datos", inventarioService.listaProductoOptions(propiedades.leerPropiedad()));
        mav.addObject("seleccion", seleccion);
        return mav;
    }
    
    
}
