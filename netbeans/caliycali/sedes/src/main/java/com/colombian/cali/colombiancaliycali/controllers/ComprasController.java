/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.controllers;

import com.colombia.cali.colombiancaliycali.util.Formatos;
import com.colombia.cali.colombiancaliycali.util.LectorPropiedades;
import com.colombian.cali.colombiancaliycali.dto.ComprasDto;
import com.colombian.cali.colombiancaliycali.dto.ComprasProveedorFechaDto;
import com.colombian.cali.colombiancaliycali.dto.ComprasTotalesDTO;
import com.colombian.cali.colombiancaliycali.dto.CuentasPagarProveedoresDto;
import com.colombian.cali.colombiancaliycali.dto.DetalleCompraDTO;
import com.colombian.cali.colombiancaliycali.dto.ItemsDTO;
import com.colombian.cali.colombiancaliycali.dto.ReporteComprasTotalesProvDTO;
import com.colombian.cali.colombiancaliycali.dto.ReporteComprasTotalesXProveedorDTO;
import com.colombian.cali.colombiancaliycali.entidades.Compras;
import com.colombian.cali.colombiancaliycali.entidades.Proveedor;
import com.colombian.cali.colombiancaliycali.mapper.ComprasMapper;
import com.colombian.cali.colombiancaliycali.services.ComprasService;
import com.colombian.cali.colombiancaliycali.services.InventarioService;
import com.colombian.cali.colombiancaliycali.services.JasperService;
import com.colombian.cali.colombiancaliycali.services.SecurityService;
import com.colombian.cali.colombiancaliycali.services.colombianjsf.ComprasColombianService;
import com.mycompany.dto.ReporteComprasSedeDto;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/compras")
public class ComprasController extends BaseController {

    @Autowired
    private ComprasService comprasService;
    @Autowired
    private ComprasColombianService comprasColombianService;
    @Autowired
    private InventarioService inventarioService;
    @Autowired
    private JasperService jasperService;
    @Autowired
    private SecurityService securityService;
    private LectorPropiedades propiedades;
    private static final String titulo = "Entradas Compras";

    @RequestMapping("/home.htm")
    public ModelAndView cargarCompras(HttpSession session) {
        ModelAndView mav = new ModelAndView("compras/compra");
        //bd principal
        propiedades = new LectorPropiedades();
        propiedades.setArchivo(getArchivo());
        propiedades.setPropiedad(getPropiedadPrincipal());

        List<ItemsDTO> proveedores = comprasService.listaProveedores(propiedades.leerPropiedad());
        DetalleCompraDTO detalleCompraDTO = new DetalleCompraDTO();

        mav.addObject("titulo", titulo);
        setBasicModel(mav, detalleCompraDTO);
        return mav;
    }

    @RequestMapping("/edicion.htm")
    public ModelAndView editarCompras() {
        ModelAndView mav = new ModelAndView("compras/edicion/compra");
        //bd principal
        propiedades = new LectorPropiedades();
        propiedades.setArchivo(getArchivo());
        propiedades.setPropiedad(getPropiedadPrincipal());

        DetalleCompraDTO detalleCompraDTO = new DetalleCompraDTO();

        mav.addObject("titulo", titulo);
        setBasicModel(mav, detalleCompraDTO);
        return mav;
    }
    
    /**
     * Este metodo verifica que una compa exista o n
     * @param idcompra
     * @return Devuelve el String true si existe o false en caso contrario
     */
    @RequestMapping("/ajax/verificar/compra.htm")
    public @ResponseBody String verificarCompra(@RequestParam(value = "idcompra") Long idcompra){
        ModelAndView mav = buscarCompras(idcompra);
        DetalleCompraDTO detalleCompraDTO = (DetalleCompraDTO) mav.getModelMap().get("detalleCompraDTO");
        if( detalleCompraDTO.getNumeroFactura() != null){
            return "true";
        }else{
            return "false";
        }
    }
    
    @RequestMapping("/ajax/buscar/compra.htm")
    public ModelAndView buscarCompras(@RequestParam(value = "idcompra") Long idcompra) {
        ModelAndView mav = new ModelAndView("compras/edicion/detalleCompra");
        //bd principal
        propiedades = new LectorPropiedades();
        propiedades.setArchivo(getArchivo());
        propiedades.setPropiedad(getPropiedadPrincipal());
        String bd = propiedades.leerPropiedad();
        DetalleCompraDTO detalleCompraDTO = comprasService.getCompraDTO(bd, idcompra);
        List<ComprasTotalesDTO> detalleCompras = comprasService.getDetalleCompraDTO(bd, idcompra);
        int numeroCompras = 0;
        if (detalleCompras != null) {
            numeroCompras = detalleCompras.size();
        }
        if (numeroCompras > 0) {
            Proveedor proveedor = comprasService.getProveedor(propiedades.leerPropiedad(), Long.parseLong(detalleCompraDTO.getCodigoProveedor()));

            mav.addObject("detalleCompraDTO", detalleCompraDTO);
            mav.addObject("detalleCompras", detalleCompras);
            mav.addObject("sproveedor", proveedor.getNombre());
            mav.addObject("titulo", titulo);
            setBasicModel(mav, detalleCompraDTO);
        } else {
            mav.addObject("detalleCompraDTO", detalleCompraDTO);
            mav.addObject("detalleCompras", detalleCompras);
            mav.addObject("sproveedor", "");
            setBasicModel(mav, detalleCompraDTO);
            //mav = new ModelAndView("redirect:/compras/edicion.htm");
        }

        return mav;
    }

    @RequestMapping("/guardar.htm")
    public ModelAndView guardarCompras(@Valid DetalleCompraDTO detalleCompraDTO) {
        //bd principal
        propiedades = new LectorPropiedades();
        propiedades.setArchivo(getArchivo());
        propiedades.setPropiedad(getPropiedadPrincipal());

        comprasService.guardarCompra(propiedades.leerPropiedad(), detalleCompraDTO);


        return new ModelAndView("redirect:/compras/home.htm");
    }

    @RequestMapping("/actualizar.htm")
    public ModelAndView actualizarCompras(@Valid DetalleCompraDTO detalleCompraDTO) {
        //bd principal
        propiedades = new LectorPropiedades();
        propiedades.setArchivo(getArchivo());
        propiedades.setPropiedad(getPropiedadPrincipal());
        String bd = propiedades.leerPropiedad();
        comprasService.actualizarCompra(bd, detalleCompraDTO);
        return new ModelAndView("redirect:/compras/edicion.htm");
    }

    @RequestMapping(value = "/ajax/avencer.htm")
    public ModelAndView buscarComprasAVencer(Long idProveedor){
    
        ModelAndView mav = new ModelAndView("contabilidad/pagosproveedor/comprasAVencer");
        List<Compras> compras = comprasService.comprasAVencer(getPropiedades().leerPropiedad(), 0,idProveedor);
        ComprasMapper comprasMapper = new ComprasMapper();
        List<ComprasDto> comprasPendientes = comprasMapper.comprasListaTOComprasDto(compras);
        mav.addObject("comprasPendientes", comprasPendientes);
        
        return mav;
    }
    @RequestMapping(value = "/reportes/comprasTotales.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteComprasTotales(@RequestParam(required = false, value = "mensaje") String mensaje) {

        ModelAndView mav = new ModelAndView("reportes/compras/comprasTotales");

        mav.addObject("fechaInicial", new Date());
        mav.addObject("fechaFinal", new Date());
        mav.addObject("titulo", "Reporte compras totales");
        mav.addObject("mensaje", mensaje);
        return mav;
    }

    @RequestMapping(value = "/reportes/comprasTotalesProducto.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteComprasTotalesProducto(@RequestParam(required = false, value = "mensaje") String mensaje) {

        ModelAndView mav = new ModelAndView("reportes/compras/compraTotalProducto");

        mav.addObject("fechaInicial", new Date());
        mav.addObject("fechaFinal", new Date());
        mav.addObject("titulo", "Reporte compras totales producto");
        mav.addObject("mensaje", mensaje);
        return mav;
    }

    @RequestMapping(value = "/reportes/comprasTotalesProveedores.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteComprasTotalesProveedores(@RequestParam(required = false, value = "mensaje") String mensaje) {

        ModelAndView mav = new ModelAndView("reportes/compras/comprasTotalProveedores");

        mav.addObject("fechaInicial", new Date());
        mav.addObject("fechaFinal", new Date());
        mav.addObject("titulo", "Reporte compras totales");
        mav.addObject("mensaje", mensaje);
        return mav;
    }

    @RequestMapping(value = "/reportes/cuentasPagarProveedor.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reportePagarProveedores(@RequestParam(required = false, value = "mensaje") String mensaje) {

        ModelAndView mav = new ModelAndView("reportes/compras/cuentasPagarProveedor");

        mav.addObject("fechaInicial", new Date());
        mav.addObject("fechaFinal", new Date());
        mav.addObject("titulo", "Reporte compras totales");
        mav.addObject("mensaje", mensaje);
        return mav;
    }
    
    @RequestMapping(value = "/reportes/comprasTotalesProveedor.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteComprasTotalesProveedor(@RequestParam(required = false, value = "mensaje") String mensaje) {

        ModelAndView mav = new ModelAndView("reportes/compras/comprasTotalesProveedor");
        //bd principal
        propiedades = new LectorPropiedades();
        propiedades.setArchivo(getArchivo());
        propiedades.setPropiedad(getPropiedadPrincipal());

        List<ItemsDTO> proveedores = comprasService.listaProveedores(propiedades.leerPropiedad());

        mav.addObject("fechaInicial", new Date());
        mav.addObject("fechaFinal", new Date());
        mav.addObject("proveedores", proveedores);
        mav.addObject("titulo", "Reporte compras totales por proveedor");
        mav.addObject("mensaje", mensaje);
        //setBasicModel(mav, comprasTotalesProveedorDTO);
        return mav;
    }

    @RequestMapping(value = "/reportes/comprasProveedorFecha.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteComprasProveedorFecha(@RequestParam(required = false, value = "mensaje") String mensaje) {

        ModelAndView mav = new ModelAndView("reportes/compras/comprasProveedoresFecha");

        mav.addObject("fechaInicial", new Date());
        mav.addObject("fechaFinal", new Date());
        mav.addObject("titulo", "Reporte compras proveedor fecha");
        mav.addObject("mensaje", mensaje);
        //setBasicModel(mav, comprasTotalesProveedorDTO);
        return mav;
    }
  
    @RequestMapping(value = "/reportes/comprasProveedorFechaPDF.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteComprasProveedorFechaPDF(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false, value = "fechaInicial") String fechaInicial, @RequestParam(required = false, value = "fechaFinal") String fechaFinal) {
        ModelAndView mav = null;

        List<ComprasProveedorFechaDto> reporte = comprasService.reporteComprasProveedorFechaDto(getPropiedades().leerPropiedad(), fechaInicial, fechaFinal);
        if (reporte != null) {
            if (reporte.size() > 0) {
                JRDataSource datos = new JRBeanCollectionDataSource(reporte);
                Map<String, Object> parameterMap = new HashMap<String, Object>();
                parameterMap.put("datos", datos);
                parameterMap.put("fechaInicial", fechaInicial);
                parameterMap.put("fechaFinal", fechaFinal);
                mav = new ModelAndView("comprasProveedorFecha", parameterMap);
                return mav;
            }
        }
        mav = new ModelAndView("redirect:/compras/reportes/comprasProveedorFecha.htm");
        mav.addObject("mensaje", "Se encontrar&oacute;n 0 registros");

        return mav;
    }
    
    @RequestMapping(value = "/reportes/comprasTotalesPDF.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteComprasTotalesPDF(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false, value = "fechaInicial") String fechaInicial, @RequestParam(required = false, value = "fechaFinal") String fechaFinal) {
        ModelAndView mav = null;

        List<ComprasTotalesDTO> comprasTotales = comprasService.comprasTotales(getPropiedades().leerPropiedad(), fechaInicial, fechaFinal, "A");
        if (comprasTotales != null) {
            if (comprasTotales.size() > 0) {
                JRDataSource datos = new JRBeanCollectionDataSource(comprasTotales);
                Map<String, Object> parameterMap = new HashMap<String, Object>();
                parameterMap.put("datos", datos);
                parameterMap.put("fechaInicial", fechaInicial);
                parameterMap.put("fechaFinal", fechaFinal);
                mav = new ModelAndView("comprasTotales", parameterMap);
                return mav;
            }
        }
        mav = new ModelAndView("redirect:/compras/reportes/comprasTotales.htm");
        mav.addObject("mensaje", "Se encontrar&oacute;n 0 registros");

        return mav;
    }

    @RequestMapping(value = "/reportes/comprasTotalesProductoPDF.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteComprasTotalesProductoPDF(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false, value = "fechaInicial") String fechaInicial, @RequestParam(required = false, value = "fechaFinal") String fechaFinal) {
        ModelAndView mav = null;
        List<ReporteComprasTotalesProvDTO> compras = comprasService.comprasTotalesProveedores(getPropiedades().leerPropiedad(), fechaInicial, fechaFinal);
        if (compras != null) {
            if (compras.size() > 0) {
                JRDataSource datos = new JRBeanCollectionDataSource(compras);
                Map<String, Object> parameterMap = new HashMap<String, Object>();
                parameterMap.put("datos", datos);
                parameterMap.put("fechaInicial", fechaInicial);
                parameterMap.put("fechaFinal", fechaFinal);
                mav = new ModelAndView("comprasTotalProducto", parameterMap);
                return mav;
            }
        }
        mav = new ModelAndView("redirect:/compras/reportes/comprasTotalesProducto.htm");
        mav.addObject("mensaje", "Se encontrar&oacute;n 0 registros");

        return mav;
    }

    @RequestMapping(value = "/reportes/comprasTotalesProveedorPDF.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteComprasTotalesProveedorPDF(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false, value = "fechaInicial") String fechaInicial, @RequestParam(required = false, value = "fechaFinal") String fechaFinal,
            @RequestParam(required = false, value = "nombreProveedor") String nombreProveedor, @RequestParam(required = false, value = "codigoProveedor") Long codigoProveedor) {
        ModelAndView mav = null;
        List<ComprasTotalesDTO> comprasTotales = comprasService.comprasTotalesProveedor(getPropiedades().leerPropiedad(), fechaInicial, fechaFinal, "A", codigoProveedor.longValue());
        if (comprasTotales != null) {
            if (comprasTotales.size() > 0) {
                JRDataSource datos = new JRBeanCollectionDataSource(comprasTotales);
                Map<String, Object> parameterMap = new HashMap<String, Object>();
                parameterMap.put("datos", datos);
                parameterMap.put("nombreProveedor", nombreProveedor);
                parameterMap.put("fechaInicial", fechaInicial);
                parameterMap.put("fechaFinal", fechaFinal);
                mav = new ModelAndView("comprasTotalesProveedor", parameterMap);
                return mav;
            }
        }
        mav = new ModelAndView("redirect:/compras/reportes/comprasTotalesProveedor.htm");
        mav.addObject("mensaje", "Se encontrar&oacute;n 0 registros");

        return mav;
    }

    @RequestMapping(value = "/reportes/comprasTotalXProveedorPDF.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteComprasTotalXProveedorPDF(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false, value = "fechaInicial") String fechaInicial, @RequestParam(required = false, value = "fechaFinal") String fechaFinal,
            @RequestParam(required = false, value = "nombreProveedor") String nombreProveedor, @RequestParam(required = false, value = "codigoProveedor") Long codigoProveedor) {
        ModelAndView mav = null;
        List<ReporteComprasTotalesXProveedorDTO> reporte = comprasService.comprasTotalesXProveedor(getPropiedades().leerPropiedad(), codigoProveedor, fechaInicial, fechaFinal);
        if (reporte != null) {
            if (reporte.size() > 0) {
                JRDataSource datos = new JRBeanCollectionDataSource(reporte);
                Map<String, Object> parameterMap = new HashMap<String, Object>();
                parameterMap.put("datos", datos);
                parameterMap.put("nombreProveedor", nombreProveedor);
                parameterMap.put("fechaInicial", fechaInicial);
                parameterMap.put("fechaFinal", fechaFinal);
                mav = new ModelAndView("comprasTotalXProveedor", parameterMap);
                return mav;
            }
        }
        mav = new ModelAndView("redirect:/compras/reportes/comprasTotalesProveedores.htm");
        mav.addObject("mensaje", "Se encontrar&oacute;n 0 registros");

        return mav;
    }
    
    @RequestMapping(value = "/reportes/cuentasPagarProveedorPDF.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteCuentasPagarProveedorPDF(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false, value = "fechaInicial") String fechaInicial, @RequestParam(required = false, value = "fechaFinal") String fechaFinal,
            @RequestParam(required = false, value = "nombreProveedor") String nombreProveedor, @RequestParam(required = false, value = "codigoProveedor") Long codigoProveedor) {
        ModelAndView mav = null;
        List<CuentasPagarProveedoresDto> reporte = comprasService.reporteCuentasPagarProveedoresDto(getPropiedades().leerPropiedad(), fechaInicial, fechaFinal, codigoProveedor);
        if (reporte != null) {
            if (reporte.size() > 0) {
                JRDataSource datos = new JRBeanCollectionDataSource(reporte);
                Map<String, Object> parameterMap = new HashMap<String, Object>();
                parameterMap.put("datos", datos);
                parameterMap.put("nombreProveedor", nombreProveedor);
                parameterMap.put("fechaInicial", fechaInicial);
                parameterMap.put("fechaFinal", fechaFinal);
                mav = new ModelAndView("comprasCuentasPagarProveedor", parameterMap);
                return mav;
            }
        }
        mav = new ModelAndView("redirect:/compras/reportes/cuentasPagarProveedor.htm");
        mav.addObject("mensaje", "Se encontrar&oacute;n 0 registros");

        return mav;
    }
    
    /**
     * Pagina Reporte de compras de Colombian
     * @return 
     */
    @RequestMapping("/colombian/reportes/compras.htm")
    public ModelAndView reporteComprasColombian(){
        ModelAndView mav = new ModelAndView("reportes/compras/colombian/comprasSede");
        return mav;
    }
    @RequestMapping("/colombian/reportes/compraspdf.htm")
    public ModelAndView generarComprasColombian(@RequestParam(required = false, value = "fechaInicial") String fechaInicial, @RequestParam(required = false, value = "fechaFinal") String fechaFinal){
        ModelAndView mav = null;
        
        List<ReporteComprasSedeDto> reporte = comprasColombianService.listadoCompras(Formatos.StringDateToDate(fechaInicial), Formatos.StringDateToDate(fechaFinal));
        if (reporte != null) {
            if (reporte.size() > 0) {
                JRDataSource datos = new JRBeanCollectionDataSource(reporte);
                Map<String, Object> parameterMap = new HashMap<String, Object>();
                parameterMap.put("datos", datos);
                parameterMap.put("sede", securityService.getCurrentUser().getSede().getSede());
                parameterMap.put("fechaInicial", fechaInicial);
                parameterMap.put("fechaFinal", fechaFinal);
                mav = new ModelAndView("comprasSedesColombian", parameterMap);
                return mav;
            }
        }
        return mav;
    }
}
