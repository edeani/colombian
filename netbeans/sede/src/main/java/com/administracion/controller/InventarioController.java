/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;


import com.adiministracion.mapper.InventarioMapper;
import com.administracion.dto.FormReporteInventarioDto;
import com.administracion.dto.InventarioDTO;
import com.administracion.dto.InventarioFinalDTO;
import com.administracion.dto.ItemsDTO;
import com.administracion.dto.ReporteInventarioDTO;
import com.administracion.dto.SedesDto;
import com.administracion.service.InventarioService;
import com.administracion.service.SedesService;
import com.administracion.service.autorizacion.ConnectsAuth;
import com.administracion.service.jsf.InventarioColombianService;
import com.administracion.util.Formatos;
import com.administracion.util.LectorPropiedades;
import com.google.gson.Gson;
import com.mycompany.mapper.Inventario;
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
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/{sede:[a-zA-Z]+}/inventario")
public class InventarioController extends BaseController {

    private  final String titulo = "Formulario de Inventario";
    private  final String tituloReporte = "Reporte de Inventario";
    @Autowired
    SedesService sedesService;
    @Autowired
    InventarioService inventarioService;
    @Autowired
    private ConnectsAuth connectsAuth;
    @Autowired
    private InventarioColombianService inventarioColombianService;

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
    public ModelAndView reporteInvetario(@PathVariable String sede) {
        FormReporteInventarioDto formReporteInventarioDto = new FormReporteInventarioDto();
        ModelAndView mav = new ModelAndView("reportes/inventario/inventario");
        List<ItemsDTO> productos = inventarioService.listaProductosLabel(sede);
        mav.addObject("productos", productos);
        mav.addObject("fecha", new Date());
        mav.addObject("titulo", titulo);
        mav.addObject("sedes", sedesService.traerSedes(sede));
        return mav;
    }

    @RequestMapping(value = "/ajax/inventarioSede.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteInventarioSede(@RequestParam("fecha") String fecha, @RequestParam("sede") String sede) {
        ModelAndView mav = new ModelAndView("reportes/inventario/inventarioSede");
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
    public ModelAndView listarInventario(@PathVariable String sede) {
        ModelAndView mav = new ModelAndView("inventario/listInventario");

        mav.addObject("inventarios", inventarioService.reporteInventario(sede));

        return mav;
    }

    @RequestMapping(value = "/ajax/eliminarProducto.htm")
    public @ResponseBody
    String eliminarProducto(@RequestParam("idProducto") Long idProducto,
            @PathVariable String sede) {

        inventarioService.eliminarProducto(sede, idProducto);

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
            String promedio,@PathVariable String sede) {


        //consulto para ver si existe el producto
        InventarioDTO inventarioDTO = inventarioService.traerProducto(sede, Long.parseLong(codigoProductoInventario));

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

            inventarioService.insertarProducto(sede, inventarioDTO);

            return "OK";
        } else {
            return "El c&oacute;digo del producto ya existe";
        }
    }

    @RequestMapping(value = "/ajax/actualizarProducto.htm")
    public @ResponseBody
    String actualizarProducto(@RequestParam("producto") String tramaProducto,@PathVariable String sede) {


        InventarioDTO inventarioDTO = new InventarioDTO();
        InventarioMapper inventarioMapper = new InventarioMapper();
        inventarioDTO = inventarioMapper.tramaProductoToInventarioDTO(tramaProducto);
        //consulto para ver si existe el producto
        InventarioDTO invDTO = inventarioService.traerProducto(sede, Long.parseLong(inventarioDTO.getCodigoProductoInventario()));

        if (invDTO != null) {

            inventarioService.actualizarProducto(sede, inventarioDTO);

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
            @RequestParam(required = false, value = "fechaInicial") String fechaInicial,
            @PathVariable String sede,@RequestParam(required = false, value = "fechaFinal") String fechaFinal
    ) {
        ModelAndView mav = null;
        List<InventarioFinalDTO> inventarioTotal = inventarioService.reporteInventarioFinal(sede, fechaInicial, fechaFinal);
        if (inventarioTotal.size() > 0) {
            JRDataSource datos = new JRBeanCollectionDataSource(inventarioTotal);
            Map<String, Object> parameterMap = new HashMap<>();
            parameterMap.put("datos", datos);
            parameterMap.put("fechaInicial", fechaInicial);
            parameterMap.put("fechaFinal", fechaFinal);
            SedesDto sedesDto = connectsAuth.findSedeXName(sede);
            parameterMap.put("nombresede", sedesDto.getTitulo());
            parameterMap.put("slogan", sedesDto.getSlogan());
            mav = new ModelAndView("inventarioTotal", parameterMap);
        } else {
            mav = new ModelAndView("redirect:/"+sede+"/inventario/reportes/inventarioTotal.htm");
            mav.addObject("mensaje", "Se encontrar&oacute;n 0 registros");
        }
        return mav;
    }

    @RequestMapping("/ajax/selectProducto.htm")
    public ModelAndView selectProducto(@RequestParam(value = "seleccion", required = false) Long seleccion,
            @PathVariable String sede){
        ModelAndView mav = new ModelAndView("util/formSelect");

        if (seleccion == null) {
            seleccion = 0L;
        }

        mav.addObject("datos", inventarioService.listaProductoOptions(sede));
        mav.addObject("seleccion", seleccion);
        return mav;
    }
    
    @RequestMapping(value = "/colombian/reporte/inventarios.htm")
    public ModelAndView indexInventarioColombian(){
        return new ModelAndView("reportes/colombian/inventario/formInventario");
    }
    
    @RequestMapping(value = "/colombian/ajax/consultar.htm")
    public ModelAndView consultarInventarioColombian(@RequestParam String fechaInicial,@RequestParam String fechaFinal,
    @RequestParam(required = false,value="sede" )String subsede){
        ModelAndView mav = new ModelAndView("reportes/colombian/inventario/datosInventario");
        List<Inventario> inventario = inventarioColombianService.traerInventario(Formatos.StringDateToDate(fechaFinal), Formatos.StringDateToDate(fechaInicial),subsede);
        mav.addObject("inventario", inventario); 
        return mav;
    }
}
