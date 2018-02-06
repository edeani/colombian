/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;


import com.adiministracion.mapper.InventarioMapper;
import com.administracion.dto.DetalleFacturaDTO;
import com.administracion.dto.FacturaAutocompletarDto;
import com.administracion.dto.FacturaReporteSedeDto;
import com.administracion.dto.FacturaTotalReporteDto;
import com.administracion.dto.FacturaVentaDTO;
import com.administracion.dto.VentasTotalesDTO;
import com.administracion.entidad.Factura;
import com.administracion.entidad.Inventario;
import com.administracion.entidad.Sedes;
import com.administracion.service.FacturasService;
import com.administracion.service.SedesService;
import com.administracion.util.Formatos;
import com.administracion.util.LectorPropiedades;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
 * @author edeani
 */
@Controller
@RequestMapping("/{sede:[a-zA-Z]+}/factura")
public class FacturasController extends BaseController {

    @Autowired
    private FacturasService facturaService;

    @Autowired
    private SedesService sedesService;
    private LectorPropiedades propiedades;
    private  final String titulo = "Despachos Sedes";

    @RequestMapping("/home.htm")
    public ModelAndView iniciarFactura() {
        ModelAndView mav = new ModelAndView("facturacion/factura");
        DetalleFacturaDTO detalleFacturaDTO = new DetalleFacturaDTO();
        mav.addObject("detalleFacturaDTO", detalleFacturaDTO);
        mav.addObject("titulo", titulo);
        setBasicModel(mav, detalleFacturaDTO);
        return mav;
    }

    @RequestMapping("/edicion.htm")
    public ModelAndView iniciarEdicionFactura() {

        ModelAndView mav = new ModelAndView("facturacion/edicion/factura");
        DetalleFacturaDTO detalleFacturaDTO = new DetalleFacturaDTO();
        mav.addObject("detalleFacturaDTO", detalleFacturaDTO);
        mav.addObject("titulo", titulo);
        setBasicModel(mav, detalleFacturaDTO);
        return mav;
    }

    @RequestMapping("/ajax/formFactura.htm")
    public ModelAndView iniciarFormFactura() {
        ModelAndView mav = new ModelAndView("facturacion/formFactura");
        DetalleFacturaDTO detalleFacturaDTO = new DetalleFacturaDTO();
        mav.addObject("detalleFacturaDTO", detalleFacturaDTO);
        mav.addObject("titulo", titulo);
        setBasicModel(mav, detalleFacturaDTO);
        return mav;
    }

    @RequestMapping("/ajax/producto.htm")
    public @ResponseBody
    String traerProducto(@RequestParam("idProducto") Long idProducto,
            @PathVariable String sede) {
        Inventario inventario = facturaService.traerProducto(sede, idProducto);
        if (inventario != null) {
            InventarioMapper productoMapper = new InventarioMapper();
            return productoMapper.FacturasToString(inventario);
        } else {
            return "El producto no existe";
        }
    }

    @ModelAttribute("fechaActual")
    public String getFechaActual() {
        String fecha = Formatos.dateTostring(new Date());


        return fecha;

    }

    @RequestMapping(value = "/guardar.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView guardarFactura(@Valid DetalleFacturaDTO detalleFacturaDTO,@PathVariable String sede) {
        Sedes sedeObj = sedesService.buscarSede(detalleFacturaDTO.getSede());
        detalleFacturaDTO.setFechaFactura(Formatos.dateTostring(new Date()));
        facturaService.guardarFactura(sede, sedeObj.getSede(), detalleFacturaDTO);
        ModelAndView mav = facturaVenta(detalleFacturaDTO.getNumeroFactura(),Long.parseLong(detalleFacturaDTO.getNumeroFactura()),detalleFacturaDTO.getSede(),
                sede);
        return mav;
    }

    @RequestMapping(value = "/ajax/actualizar.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView actualizarFactura(@Valid DetalleFacturaDTO detalleFacturaDTO, @RequestParam(value = "nombreSede", required = false) String nombreSede,
            @RequestParam(value = "numeroSede", required = false) Long idSede
            , @RequestParam(value = "estadoFactura", required = false) String estadoFactura,
            @PathVariable String sede) {
        String sedeConexion = sede;
       // facturaService.actualizarFactura(sedeConexion, nombreSede,estadoFactura, detalleFacturaDTO);
        Long numfac = Long.parseLong(detalleFacturaDTO.getNumeroFactura());
        Factura factura = facturaService.findFacturaSede(sedeConexion, numfac);
        detalleFacturaDTO.setFechaFactura(Formatos.dateTostring(factura.getFechaFactura()));
        //principal
        facturaService.borrarFacturaSede(sedeConexion, numfac);
        //Sede
        facturaService.borrarFacturaSubSede(nombreSede, numfac);
        
        facturaService.actualizarFactura(sede, nombreSede, estadoFactura, detalleFacturaDTO);
        detalleFacturaDTO = null;
        ModelAndView mav = new ModelAndView("facturacion/edicion/formFactura");
        DetalleFacturaDTO detalleFacturaDTO_ = new DetalleFacturaDTO();
        detalleFacturaDTO_.setNumeroFactura("");
        mav.addObject("detalleFacturaDTO", detalleFacturaDTO_);
        mav.addObject("titulo", titulo);
        setBasicModel(mav, detalleFacturaDTO_);

        return mav;
    }

    @RequestMapping(value = "/facturaVentaPDF.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView facturaVenta(@RequestParam(required = false, value = "factura") String factura,
            @RequestParam(required = false, value = "numeroFactura") Long numeroFactura,
            @RequestParam(required = false, value = "sede") Long sedeNumber,@PathVariable String sede) {
       
        //Servicio
        Factura factura1 = facturaService.findFacturaSede(sede, numeroFactura);

        List<FacturaVentaDTO> detalleFactura = facturaService.detalleFacturaVenta(sede, numeroFactura);

        JRDataSource datos = new JRBeanCollectionDataSource(detalleFactura);
        Map<String, Object> parameterMap = new HashMap<>();
        Sedes sedes = sedesService.buscarSede(sedeNumber);
        parameterMap.put("datos", datos);
        parameterMap.put("numeroFactura", numeroFactura);
        parameterMap.put("fechaFactura", Formatos.dateTostring(factura1.getFechaFactura()));
        parameterMap.put("nombreSede", sedes.getSede());
        ModelAndView mav = new ModelAndView("facturaVenta", parameterMap);

        return mav;
    }
    
    @RequestMapping(value = "/facturaVentaActualizadaPDF.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView facturaVentaActualizada(HttpServletRequest request, @RequestParam(required = false, value = "factura") String factura,
            @RequestParam(required = false, value = "numeroFactura") Long numeroFactura
            , @RequestParam(required = false, value = "sede") Long sedeNumber,@PathVariable String sede) {

        //Servicio
        Factura factura1 = facturaService.findFacturaSede(sede, numeroFactura);
        System.out.println("OBJETO FACTURA::"+factura1);
        System.out.println("FECHA FACTURA::"+factura1.getFechaFactura());

        List<FacturaVentaDTO> detalleFactura = facturaService.detalleFacturaVenta(sede, numeroFactura);
        System.out.println("FECHA FACTURA DESPUES::"+factura1.getFechaFactura());
        JRDataSource datos = new JRBeanCollectionDataSource(detalleFactura);
        Map<String, Object> parameterMap = new HashMap<>();
        Sedes sedes = sedesService.buscarSede(sedeNumber);
        parameterMap.put("datos", datos);
        parameterMap.put("numeroFactura", numeroFactura);
        parameterMap.put("fechaFactura", Formatos.dateTostring(factura1.getFechaFactura()));
        parameterMap.put("nombreSede", sedes.getSede());
        ModelAndView mav = new ModelAndView("facturaVenta", parameterMap);

        return mav;
    }

    @RequestMapping(value = "/reportes/ventasTotalesSede.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteVentasTotalesSede(@RequestParam(required = false, value = "mensaje") String mensaje) {

        ModelAndView mav = new ModelAndView("reportes/facturas/ventasTotalesSede");

        mav.addObject("fechaInicial", new Date());
        mav.addObject("fechaFinal", new Date());
        mav.addObject("titulo", "Reporte ventas totales Sede");
        mav.addObject("mensaje", mensaje);
        return mav;
    }

    @RequestMapping(value = "/reportes/totalFacturasSede.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteTotalFacturasSede(@RequestParam(required = false, value = "mensaje") String mensaje) {

        ModelAndView mav = new ModelAndView("reportes/facturas/totalFacturasSede");
       
        mav.addObject("fechaInicial", new Date());
        mav.addObject("fechaFinal", new Date());
        mav.addObject("titulo", "Reporte Facturas totales Sede");
        mav.addObject("mensaje", mensaje);
        return mav;
    }
    
    @RequestMapping(value = "/reportes/ventasTotales.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteVentasTotales() {

        ModelAndView mav = new ModelAndView("reportes/facturas/ventasTotales");

        mav.addObject("fechaInicial", new Date());
        mav.addObject("fechaFinal", new Date());
        mav.addObject("titulo", "Reporte ventas totales");
        return mav;
    }
    
    @RequestMapping(value = "/reportes/totalFacturas.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteFacturaTotal() {

        ModelAndView mav = new ModelAndView("reportes/facturas/totalFacturas");

        mav.addObject("fechaInicial", new Date());
        mav.addObject("fechaFinal", new Date());
        mav.addObject("titulo", "Reporte Total Facturas");
        return mav;
    }
    
    @RequestMapping(value = "/reportes/ventasTotalesPDF.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteVentasTotalesPDF(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false, value = "fechaInicial") String fechaInicial
            , @RequestParam(required = false, value = "fechaFinal") String fechaFinal,@PathVariable String sede) {

        List<VentasTotalesDTO> ventasTotales = facturaService.ventasTotales(sede, fechaInicial, fechaFinal, "A");
        ModelAndView mav = null;
        if (ventasTotales.size() > 0) {
            JRDataSource datos = new JRBeanCollectionDataSource(ventasTotales);
            Map<String, Object> parameterMap = new HashMap<>();
            parameterMap.put("datos", datos);
            parameterMap.put("fechaInicial", fechaInicial);
            parameterMap.put("fechaFinal", fechaFinal);
            mav = new ModelAndView("ventasTotales", parameterMap);
        } else {
            mav = new ModelAndView("redirect:"+sede+"/factura/reportes/ventasTotales.htm");
            mav.addObject("mensaje", "Se encontrar&oacute;n 0 registros");
        }
        return mav;
    }
    
    @RequestMapping(value = "/reportes/totalFacturasPDF.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteFacturasTotalesPDF(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false, value = "fechaInicial") String fechaInicial
            , @RequestParam(required = false, value = "fechaFinal") String fechaFinal,@PathVariable String sede) {

        List<FacturaTotalReporteDto> reporte = facturaService.reporteFacturaCompra(sede, fechaInicial, fechaFinal);
        ModelAndView mav = null;
        if (reporte.size() > 0) {
            JRDataSource datos = new JRBeanCollectionDataSource(reporte);
            Map<String, Object> parameterMap = new HashMap<>();
            parameterMap.put("datos", datos);
            parameterMap.put("fechaInicial", fechaInicial);
            parameterMap.put("fechaFinal", fechaFinal);
            mav = new ModelAndView("facturasTotal", parameterMap);
        } else {
            mav = new ModelAndView("redirect:"+sede+"/factura/reportes/totalFacturas.htm");
            mav.addObject("mensaje", "Se encontrar&oacute;n 0 registros");
        }
        return mav;
    }

    /*REPORTE A COPIAR*/
    @RequestMapping(value = "/reportes/ventasTotalesSedePDF.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteVentasTotalesSedePDF(HttpServletRequest request,
            @RequestParam(required = false, value = "fechaInicial") String fechaInicial, @RequestParam(required = false, value = "fechaFinal") String fechaFinal,
            @RequestParam(required = false, value = "sede") Long idSede,@PathVariable String sede) {
        ModelAndView mav = null;
        List<VentasTotalesDTO> ventasTotales = facturaService.ventasTotalesSede(sede, fechaInicial, fechaFinal, "A", idSede);
        Sedes sedeObj = sedesService.buscarSede(idSede);
        if (ventasTotales.size() > 0) {
            JRDataSource datos = new JRBeanCollectionDataSource(ventasTotales);
            Map<String, Object> parameterMap = new HashMap<>();
            parameterMap.put("datos", datos);
            parameterMap.put("fechaInicial", fechaInicial);
            parameterMap.put("fechaFinal", fechaFinal);
            parameterMap.put("sede", sedeObj.getSede());
            mav = new ModelAndView("ventasTotalesSede", parameterMap);
        } else {
            mav = new ModelAndView("redirect:"+sede+"/factura/reportes/ventasTotalesSede.htm");
            mav.addObject("mensaje", "Se encontrar&oacute;n 0 registros");
        }
        return mav;
    }
    
    @RequestMapping(value = "/reportes/totalFacturasSedePDF.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteTotalFacturasSedePDF(HttpServletRequest request,
            @RequestParam(required = false, value = "fechaInicial") String fechaInicial, @RequestParam(required = false, value = "fechaFinal") String fechaFinal,
            @RequestParam(required = false, value = "sede") Long idSede,@PathVariable String sede) {
        ModelAndView mav = null;
        List<FacturaReporteSedeDto> reporte = facturaService.reporteFacturaCompraProveedor(sede, idSede, fechaInicial, fechaFinal);
        Sedes sedeObj = sedesService.buscarSede(idSede);
        if (reporte.size() > 0) {
            JRDataSource datos = new JRBeanCollectionDataSource(reporte);
            Map<String, Object> parameterMap = new HashMap<>();
            parameterMap.put("datos", datos);
            parameterMap.put("fechaInicial", fechaInicial);
            parameterMap.put("fechaFinal", fechaFinal);
            parameterMap.put("nombreSede", sedeObj.getSede());
            mav = new ModelAndView("facturasTotalXSede", parameterMap);
        } else {
            mav = new ModelAndView("redirect:"+sede+"/factura/reportes/totalFacturasSede.htm");
            mav.addObject("mensaje", "Se encontrar&oacute;n 0 registros");
        }
        return mav;
    }

    @RequestMapping(value = "/ajax/listaProductos.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView listaProductos(@RequestParam(required = false, value = "numeroFactura") Long numeroFactura,
    @RequestParam(required = false, value = "cambiosede") String cambiosede,
    @PathVariable String sede) {
        ModelAndView mav = null;
        if(cambiosede==null){
            mav = new ModelAndView("facturacion/detalleFactura");
        }else{
            mav = new ModelAndView("facturacion/sede/detalleFactura");
        }
        List<FacturaVentaDTO> detalle = facturaService.traerProductosFactura(sede, numeroFactura);
        Sedes sedeObj = null;
        if (detalle!=null && !detalle.isEmpty()) {
            Long idSede = detalle.get(0).getIdsede();
            String estadoFactura = detalle.get(0).getEstado();
            sedeObj = sedesService.buscarSede(idSede);
            mav.addObject("sede", sedeObj);
            mav.addObject("productos", detalle);
            mav.addObject("totalFactura", calcularTotalFactura(detalle));
            mav.addObject("estadoFactura", estadoFactura);
        }
        return mav;
    }
    
    @RequestMapping("/cambiarSede.htm")
    public ModelAndView homeCambiarSede() {
        ModelAndView mav = new ModelAndView("facturacion/sede/factura");
        DetalleFacturaDTO detalleFacturaDTO = new DetalleFacturaDTO();
        mav.addObject("detalleFacturaDTO", detalleFacturaDTO);
        mav.addObject("titulo", "Cambiar sede Factura");
        setBasicModel(mav, detalleFacturaDTO);
        return mav;
    }
    
    @RequestMapping("/traslados.htm")
    public ModelAndView homeTraslados() {
        ModelAndView mav = new ModelAndView("facturacion/traslados/factura");
        DetalleFacturaDTO detalleFacturaDTO = new DetalleFacturaDTO();
        mav.addObject("detalleFacturaDTO", detalleFacturaDTO);
        mav.addObject("titulo", "Traslados");
        setBasicModel(mav, detalleFacturaDTO);
        return mav;
    }
    
    @RequestMapping(value="/guardarCambioSede.htm",method={RequestMethod.POST, RequestMethod.GET})
    public ModelAndView guardarCambioSede(@Valid DetalleFacturaDTO detalleFacturaDTO,@RequestParam(value="numeroSede", required=false) Long idSedeOrigen 
                                    ,@RequestParam(value="estadoFactura", required=false)String estadoFactura
    ,@PathVariable String sede){
        //Traigo las sedes
        Sedes sedeOrigen = sedesService.buscarSede(idSedeOrigen);
        Sedes sedeDestino = sedesService.buscarSede(detalleFacturaDTO.getSede());
        
        //Hago el traslado
        facturaService.cambiarSedeFactura(sede, detalleFacturaDTO, estadoFactura, sedeOrigen, sedeDestino);
        ModelAndView mav = facturaVenta(detalleFacturaDTO.getNumeroFactura(),Long.parseLong(detalleFacturaDTO.getNumeroFactura()),detalleFacturaDTO.getSede(),
                sede);
        return mav;
    }
    
    @RequestMapping("/ajax/sede/formFactura.htm")
    public ModelAndView iniciarFormFacturaTraslado() {
        ModelAndView mav = new ModelAndView("facturacion/sede/formFactura");
        DetalleFacturaDTO detalleFacturaDTO = new DetalleFacturaDTO();
        mav.addObject("detalleFacturaDTO", detalleFacturaDTO);
        mav.addObject("titulo", titulo);
        setBasicModel(mav, detalleFacturaDTO);
        return mav;
    }
    @RequestMapping("/ajax/proveedor/autocompletar.htm")
    public @ResponseBody String autocompletarFacturaProveedor(@RequestParam String term,@RequestParam Long idproveedor,
            @PathVariable String sede){
        List<FacturaAutocompletarDto> busqueda = facturaService.buscarFacturaAutocompletar(sede,term , idproveedor);
        JsonArray jsonArray = null;
        Gson gson = new Gson();
        String json = "[]";
        if (busqueda != null) {
            json = gson.toJson(busqueda);
        }

        return json;
    }
    /**
     *
     * @param detalle tiene el detalle de la factrua
     * @return total de los productos que posee el detalle
     */
    public Long calcularTotalFactura(List<FacturaVentaDTO> detalle) {

        Long total = 0L;
        total = detalle.stream().map((facturaVentaDTO) -> facturaVentaDTO.getTotalProducto()).reduce(total, (accumulator, _item) -> accumulator + _item);

        return total;
    }
}
