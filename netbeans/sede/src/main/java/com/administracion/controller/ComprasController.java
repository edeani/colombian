/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.adiministracion.mapper.ComprasMapper;
import com.adiministracion.mapper.FacturaMapper;
import com.administracion.dto.ComprasDto;
import com.administracion.dto.ComprasProveedorFechaDto;
import com.administracion.dto.ComprasTotalesDTO;
import com.administracion.dto.CuentasPagarProveedoresDto;
import com.administracion.dto.DetalleCompraDTO;
import com.administracion.dto.ItemFacturaDto;
import com.administracion.dto.ItemsDTO;
import com.administracion.dto.ReporteComprasTotalesProvDTO;
import com.administracion.dto.ReporteComprasTotalesXProveedorDTO;
import com.administracion.dto.SedesDto;
import com.administracion.dto.SubSedesDto;
import com.administracion.entidad.Compras;
import com.administracion.entidad.Proveedor;
import com.administracion.service.ComprasService;
import com.administracion.service.ProveedoresService;
import com.administracion.service.autorizacion.ConnectsAuth;
import com.administracion.service.autorizacion.SecurityService;
import com.administracion.service.jsf.ComprasColombianService;
import com.administracion.util.Formatos;
import com.administracion.util.PrintUtil;
import com.mycompany.dto.ReporteComprasSedeDto;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.PrinterName;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
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
 * @author user
 */
@Controller
@RequestMapping("/{sede:[a-zA-Z]+}/compras")
public class ComprasController extends BaseController {

    @Autowired
    private ComprasService comprasService;
    @Autowired
    private ComprasColombianService comprasColombianService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private ProveedoresService proveedoresService;
    @Autowired
    private ConnectsAuth connectsAuth;

    private final String titulo = "Entradas Compras";

    @RequestMapping("/home.htm")
    public ModelAndView cargarCompras(HttpSession session, @PathVariable String sede) {
        ModelAndView mav = new ModelAndView("compras/compra");
        DetalleCompraDTO detalleCompraDTO = new DetalleCompraDTO();
        SedesDto sedePrincipal = connectsAuth.findSedeXName(sede);
        SubSedesDto subSedePrincipal = connectsAuth.findSubSedeXIdSede(sedePrincipal.getIdsedes());
        mav.addObject("titulo", titulo);
        mav.addObject("tipo_sede", sedePrincipal.getTipo_sede());
        mav.addObject("subSedePrincipal", subSedePrincipal);
        setBasicModel(mav, detalleCompraDTO);
        return mav;
    }

    @RequestMapping("/edicion.htm")
    public ModelAndView editarCompras(@PathVariable String sede) {
        ModelAndView mav = new ModelAndView("compras/edicion/compra");

        DetalleCompraDTO detalleCompraDTO = new DetalleCompraDTO();

        mav.addObject("titulo", "Editar Compras");
        setBasicModel(mav, detalleCompraDTO);
        return mav;
    }

    /**
     * Este metodo verifica que una compa exista o n
     *
     * @param idcompra
     * @param codigoProveedor
     * @param sede
     * @return Devuelve el String true si existe o false en caso contrario
     */
    @RequestMapping("/ajax/verificar/compra.htm")
    public @ResponseBody
    String verificarCompra(@RequestParam(value = "idcompra") Long idcompra,
            @RequestParam Integer codigoProveedor,
            @PathVariable String sede) {
        Compras compras = comprasService.getCompraXIDproveedor(sede, idcompra, codigoProveedor);
        if (compras != null) {
            return "true";
        } else {
            return "false";
        }
    }

    @RequestMapping("/ajax/limpiar/compra.htm")
    public ModelAndView limpiarCompras(@PathVariable String sede) {
        ModelAndView mav = new ModelAndView("compras/edicion/detalleCompraInicial");
        DetalleCompraDTO detalleCompraDTO = new DetalleCompraDTO();

        mav.addObject("titulo", "Editar Compras");
        setBasicModel(mav, detalleCompraDTO);
        return mav;
    }

    @RequestMapping("/ajax/buscar/compra.htm")
    public ModelAndView buscarCompras(@RequestParam(value = "idcompra") Long idcompra,
            @RequestParam Integer codigoProveedor,
            @PathVariable String sede) {
        ModelAndView mav = new ModelAndView("compras/edicion/detalleCompra");
        //bd principal
        DetalleCompraDTO detalleCompraDTO = comprasService.getCompraDTO(sede, idcompra, codigoProveedor);
        List<ComprasTotalesDTO> detalleCompras = comprasService.getDetalleCompraDTO(sede, idcompra, codigoProveedor);
        int numeroCompras = 0;
        if (detalleCompras != null) {
            numeroCompras = detalleCompras.size();
        }

        SedesDto sedePrincipal = connectsAuth.findSedeXName(sede);
        SubSedesDto subSedePrincipal = connectsAuth.findSubSedeXIdSede(sedePrincipal.getIdsedes());
        if (numeroCompras > 0) {
            Proveedor proveedor = comprasService.getProveedor(sede, Long.parseLong(detalleCompraDTO.getCodigoProveedor()));
            mav.addObject("tipo_sede", sedePrincipal.getTipo_sede());
            mav.addObject("subSedePrincipal", subSedePrincipal);
            mav.addObject("detalleCompraDTO", detalleCompraDTO);
            mav.addObject("detalleCompras", detalleCompras);
            mav.addObject("sproveedor", proveedor.getNombre());
            mav.addObject("titulo", titulo);
            setBasicModel(mav, detalleCompraDTO);
        } else {
            mav = new ModelAndView("compras/edicion/detalleCompraInicial");
            mav.addObject("tipo_sede", sedePrincipal.getTipo_sede());
            mav.addObject("subSedePrincipal", subSedePrincipal);
            mav.addObject("detalleCompraDTO", detalleCompraDTO);
            mav.addObject("detalleCompras", detalleCompras);
            mav.addObject("sproveedor", "");
            mav.addObject("titulo", "Editar Compras");
            setBasicModel(mav, detalleCompraDTO);
            //mav = new ModelAndView("redirect:/"+sede+"/compras/edicion.htm");
        }

        return mav;
    }

    @RequestMapping("/ajax/guardar.htm")
    public ModelAndView guardarCompras(@Valid DetalleCompraDTO detalleCompraDTO,
            @PathVariable String sede) {
        try {
            comprasService.guardarCompra(sede, detalleCompraDTO);
        } catch (Exception e) {
            System.out.println("Error guardarCompras::" + e.getMessage());
        }

        //imprimirFactura(detalleCompraDTO);
        ModelAndView mav = new ModelAndView("compras/detalleCompra");
        SedesDto sedePrincipal = connectsAuth.findSedeXName(sede);
        SubSedesDto subSedePrincipal = connectsAuth.findSubSedeXIdSede(sedePrincipal.getIdsedes());
        mav.addObject("tipo_sede", sedePrincipal.getTipo_sede());
        mav.addObject("subSedePrincipal", subSedePrincipal);
        detalleCompraDTO = new DetalleCompraDTO();

        mav.addObject("titulo", titulo);
        setBasicModel(mav, detalleCompraDTO);
        return mav;
    }

    @RequestMapping("/ajax/actualizar.htm")
    public ModelAndView actualizarCompras(@Valid DetalleCompraDTO detalleCompraDTO,
            @PathVariable String sede) {
        comprasService.actualizarCompra(sede, detalleCompraDTO);
        //imprimirFactura(detalleCompraDTO);

        detalleCompraDTO = new DetalleCompraDTO();
        ModelAndView mav = new ModelAndView("compras/edicion/detalleCompraInicial");
        mav.addObject("titulo", titulo);
        setBasicModel(mav, detalleCompraDTO);
        return mav;
    }

    @RequestMapping(value = "/ajax/avencer.htm")
    public ModelAndView buscarComprasAVencer(Long idProveedor, @PathVariable String sede) {

        ModelAndView mav = new ModelAndView("contabilidad/pagosproveedor/comprasAVencer");
        List<Compras> compras = comprasService.comprasAVencer(sede, 0, idProveedor);
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
    public ModelAndView reporteComprasTotalesProveedor(@RequestParam(required = false, value = "mensaje") String mensaje,
            @PathVariable String sede) {

        ModelAndView mav = new ModelAndView("reportes/compras/comprasTotalesProveedor");

        List<ItemsDTO> proveedores = comprasService.listaProveedores(sede);

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
    public ModelAndView reporteComprasProveedorFechaPDF( 
            @RequestParam(required = false, value = "fechaInicial") String fechaInicial, @RequestParam(required = false, value = "fechaFinal") String fechaFinal,
            @PathVariable String sede) {
        ModelAndView mav;

        List<ComprasProveedorFechaDto> reporte = comprasService.reporteComprasProveedorFechaDto(sede, fechaInicial, fechaFinal);
        if (reporte != null) {
            if (Boolean.FALSE.equals(reporte.isEmpty())) {
                JRDataSource datos = new JRBeanCollectionDataSource(reporte);
                Map<String, Object> parameterMap = new HashMap<>();
                parameterMap.put("datos", datos);
                parameterMap.put("fechaInicial", fechaInicial);
                parameterMap.put("fechaFinal", fechaFinal);
                SedesDto sedesDto = connectsAuth.findSedeXName(sede);
                parameterMap.put("titulo", sedesDto.getTitulo());
                parameterMap.put("nombresede", sede);
                parameterMap.put("slogan", sedesDto.getSlogan());
                mav = new ModelAndView("comprasProveedorFecha", parameterMap);
                return mav;
            }
        }
        mav = new ModelAndView("redirect:/" + sede + "/compras/reportes/comprasProveedorFecha.htm");
        mav.addObject("mensaje", "Se encontrar&oacute;n 0 registros");

        return mav;
    }

    @RequestMapping(value = "/reportes/comprasTotalesPDF.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteComprasTotalesPDF(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false, value = "fechaInicial") String fechaInicial,
            @RequestParam(required = false, value = "fechaFinal") String fechaFinal, @PathVariable String sede) {
        ModelAndView mav;

        List<ComprasTotalesDTO> comprasTotales = comprasService.comprasTotales(sede, fechaInicial, fechaFinal, "A");
        if (comprasTotales != null) {
            if (!comprasTotales.isEmpty()) {
                JRDataSource datos = new JRBeanCollectionDataSource(comprasTotales);
                Map<String, Object> parameterMap = new HashMap<>();
                parameterMap.put("datos", datos);
                parameterMap.put("fechaInicial", fechaInicial);
                parameterMap.put("fechaFinal", fechaFinal);
                SedesDto sedesDto = connectsAuth.findSedeXName(sede);
                parameterMap.put("nombresede", sede);
                parameterMap.put("titulo", sedesDto.getTitulo());
                parameterMap.put("slogan", sedesDto.getSlogan());
                mav = new ModelAndView("comprasTotales", parameterMap);
                return mav;
            }
        }
        mav = new ModelAndView("redirect:/" + sede + "/compras/reportes/comprasTotales.htm");
        mav.addObject("mensaje", "Se encontrar&oacute;n 0 registros");

        return mav;
    }

    @RequestMapping(value = "/reportes/comprasTotalesProductoPDF.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteComprasTotalesProductoPDF(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false, value = "fechaInicial") String fechaInicial,
            @RequestParam(required = false, value = "fechaFinal") String fechaFinal, @PathVariable String sede) {
        ModelAndView mav;
        List<ReporteComprasTotalesProvDTO> compras = comprasService.comprasTotalesProveedores(sede, fechaInicial, fechaFinal);
        if (compras != null) {
            if (!compras.isEmpty()) {
                JRDataSource datos = new JRBeanCollectionDataSource(compras);
                Map<String, Object> parameterMap = new HashMap<>();
                parameterMap.put("datos", datos);
                parameterMap.put("fechaInicial", fechaInicial);
                parameterMap.put("fechaFinal", fechaFinal);
                SedesDto sedesDto = connectsAuth.findSedeXName(sede);
                parameterMap.put("titulo", sedesDto.getTitulo());
                parameterMap.put("nombresede", sede);
                parameterMap.put("slogan", sedesDto.getSlogan());
                mav = new ModelAndView("comprasTotalProducto", parameterMap);
                return mav;
            }
        }
        mav = new ModelAndView("redirect:/" + sede + "/compras/reportes/comprasTotalesProducto.htm");
        mav.addObject("mensaje", "Se encontrar&oacute;n 0 registros");

        return mav;
    }

    @RequestMapping(value = "/reportes/comprasTotalesProveedorPDF.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteComprasTotalesProveedorPDF(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false, value = "fechaInicial") String fechaInicial, @RequestParam(required = false, value = "fechaFinal") String fechaFinal,
            @RequestParam(required = false, value = "nombreProveedor") String nombreProveedor,
            @RequestParam(required = false, value = "codigoProveedor") Long codigoProveedor, @PathVariable String sede) {
        ModelAndView mav;
        List<ComprasTotalesDTO> comprasTotales = comprasService.comprasTotalesProveedor(sede, fechaInicial, fechaFinal, "A", codigoProveedor);
        if (comprasTotales != null) {
            if (!comprasTotales.isEmpty()) {
                JRDataSource datos = new JRBeanCollectionDataSource(comprasTotales);
                Map<String, Object> parameterMap = new HashMap<>();
                parameterMap.put("datos", datos);
                parameterMap.put("nombreProveedor", nombreProveedor);
                parameterMap.put("fechaInicial", fechaInicial);
                parameterMap.put("fechaFinal", fechaFinal);
                SedesDto sedesDto = connectsAuth.findSedeXName(sede);
                parameterMap.put("titulo", sedesDto.getTitulo());
                parameterMap.put("nombresede", sede);
                parameterMap.put("slogan", sedesDto.getSlogan());
                mav = new ModelAndView("comprasTotalesProveedor", parameterMap);
                return mav;
            }
        }
        mav = new ModelAndView("redirect:/" + sede + "/compras/reportes/comprasTotalesProveedor.htm");
        mav.addObject("mensaje", "Se encontrar&oacute;n 0 registros");

        return mav;
    }

    @RequestMapping(value = "/reportes/comprasTotalXProveedorPDF.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteComprasTotalXProveedorPDF(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false, value = "fechaInicial") String fechaInicial, @RequestParam(required = false, value = "fechaFinal") String fechaFinal,
            @RequestParam(required = false, value = "nombreProveedor") String nombreProveedor,
            @RequestParam(required = false, value = "codigoProveedor") Long codigoProveedor, @PathVariable String sede) {
        ModelAndView mav;
        List<ReporteComprasTotalesXProveedorDTO> reporte = comprasService.comprasTotalesXProveedor(sede, codigoProveedor, fechaInicial, fechaFinal);
        if (reporte != null) {
            if (!reporte.isEmpty()) {
                JRDataSource datos = new JRBeanCollectionDataSource(reporte);
                Map<String, Object> parameterMap = new HashMap<>();
                parameterMap.put("datos", datos);
                parameterMap.put("nombreProveedor", nombreProveedor);
                parameterMap.put("fechaInicial", fechaInicial);
                parameterMap.put("fechaFinal", fechaFinal);
                SedesDto sedesDto = connectsAuth.findSedeXName(sede);
                parameterMap.put("titulo", sedesDto.getTitulo());
                parameterMap.put("nombresede", sede);
                parameterMap.put("slogan", sedesDto.getSlogan());
                mav = new ModelAndView("comprasTotalXProveedor", parameterMap);
                return mav;
            }
        }
        mav = new ModelAndView("redirect:/" + sede + "/compras/reportes/comprasTotalesProveedores.htm");
        mav.addObject("mensaje", "Se encontrar&oacute;n 0 registros");

        return mav;
    }

    @RequestMapping(value = "/reportes/cuentasPagarProveedorPDF.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteCuentasPagarProveedorPDF(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = false, value = "fechaInicial") String fechaInicial, @RequestParam(required = false, value = "fechaFinal") String fechaFinal,
            @RequestParam(required = false, value = "nombreProveedor") String nombreProveedor,
            @RequestParam(required = false, value = "codigoProveedor") Long codigoProveedor, @PathVariable String sede) {
        ModelAndView mav;
        List<CuentasPagarProveedoresDto> reporte = comprasService.reporteCuentasPagarProveedoresDto(sede, fechaInicial, fechaFinal, codigoProveedor);
        if (reporte != null) {
            if (!reporte.isEmpty()) {
                JRDataSource datos = new JRBeanCollectionDataSource(reporte);
                Map<String, Object> parameterMap = new HashMap<>();
                parameterMap.put("datos", datos);
                parameterMap.put("nombreProveedor", nombreProveedor);
                parameterMap.put("fechaInicial", fechaInicial);
                parameterMap.put("fechaFinal", fechaFinal);
                SedesDto sedesDto = connectsAuth.findSedeXName(sede);
                parameterMap.put("titulo", sedesDto.getTitulo());
                parameterMap.put("nombresede", sede);
                parameterMap.put("slogan", sedesDto.getSlogan());
                mav = new ModelAndView("comprasCuentasPagarProveedor", parameterMap);
                return mav;
            }
        }
        mav = new ModelAndView("redirect:/" + sede + "/compras/reportes/cuentasPagarProveedor.htm");
        mav.addObject("mensaje", "Se encontrar&oacute;n 0 registros");

        return mav;
    }

    /**
     * Pagina Reporte de compras de Colombian
     *
     * @return
     */
    @RequestMapping("/colombian/reportes/compras.htm")
    public ModelAndView reporteComprasColombian() {
        ModelAndView mav = new ModelAndView("reportes/compras/colombian/comprasSede");
        return mav;
    }

    @RequestMapping("/colombian/reportes/compraspdf.htm")
    public ModelAndView generarComprasColombian(@RequestParam(required = false, value = "fechaInicial") String fechaInicial, @RequestParam(required = false, value = "fechaFinal") String fechaFinal,
            @RequestParam(required = false, value = "sede") String sede, @PathVariable(value = "sede") String nombreSede) {
        ModelAndView mav = null;
        SubSedesDto subSedesDto = connectsAuth.findSubsedeXId(Integer.valueOf(sede));
        List<ReporteComprasSedeDto> reporte = comprasColombianService.listadoCompras(Formatos.StringDateToDate(fechaInicial), Formatos.StringDateToDate(fechaFinal), subSedesDto.getSede());
        if (reporte != null) {
            if (Boolean.FALSE.equals(reporte.isEmpty())) {
                JRDataSource datos = new JRBeanCollectionDataSource(reporte);
                Map<String, Object> parameterMap = new HashMap<>();
                parameterMap.put("datos", datos);
                parameterMap.put("sede", subSedesDto.getSede());

                SedesDto sedesDto = connectsAuth.findSedeXId(subSedesDto.getIdsede());
                parameterMap.put("nombresede", sedesDto.getTitulo());
                parameterMap.put("slogan", sedesDto.getSlogan());
                parameterMap.put("fechaInicial", fechaInicial);
                parameterMap.put("fechaFinal", fechaFinal);
                mav = new ModelAndView("comprasSedesColombian", parameterMap);
            }
        }
        return mav;
    }

    @RequestMapping("/compraPdf.htm")
    public ModelAndView compraFindedPdf(@Valid DetalleCompraDTO detalleCompraDTO, @PathVariable String sede) {
        ModelAndView mav = null;
        
        Long idcompra = Long.valueOf(detalleCompraDTO.getNumeroFactura());
        Integer codigoProveedor = Integer.valueOf(detalleCompraDTO.getCodigoProveedor());
        detalleCompraDTO = comprasService.getCompraDTO(sede, idcompra, codigoProveedor);
        
        List<ComprasTotalesDTO> detalleCompras = comprasService.getDetalleCompraDTO(sede, idcompra, codigoProveedor);
        
        int numeroCompras = 0;
        if (detalleCompras != null) {
            numeroCompras = detalleCompras.size();
        }
        
        List<ItemFacturaDto> detalleFactura = FacturaMapper.detalleCompraDtoToIteFacturaDto(detalleCompras);
        Proveedor proveedor = proveedoresService.proveedor(sede, codigoProveedor.longValue());
        if (detalleFactura != null) {
            JRDataSource datos = new JRBeanCollectionDataSource(detalleFactura);
            Map<String, Object> parameterMap = new HashMap<>();
            parameterMap.put("datos", datos);
            SedesDto sedesDto = connectsAuth.findSedeXName(sede);
            parameterMap.put("usuario", sedesDto.getUsersLogin());
            parameterMap.put("nombresede", sedesDto.getTitulo());
            parameterMap.put("slogan", sedesDto.getSlogan());
            parameterMap.put("proveedor", proveedor.getNombre());
            parameterMap.put("numeroFactura", detalleCompraDTO.getNumeroFactura());
            mav = new ModelAndView("comprasModificadas", parameterMap);
            return mav;
        }

        return mav;
    }

    @RequestMapping("/ajax/impresoras.htm")
    public ModelAndView jspImpresoras() {
        return new ModelAndView("impresoras/printers");
    }

    private void imprimirFactura(DetalleCompraDTO detalleCompraDTO) {
        List<ItemFacturaDto> detalleFactura = FacturaMapper.stringFacturaToIteFacturaDto(detalleCompraDTO.getFactura());
        JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(detalleFactura);
        InputStream input = this.getClass().getResourceAsStream("/reportes/factura.jrxml");
        try {
            JasperDesign design = JRXmlLoader.load(input);
            JasperReport jasperReport = JasperCompileManager.compileReport(design);
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("usuario", securityService.getCurrentUser().getUsername());
            parametros.put("proveedor", detalleCompraDTO.getNombreProveedor());
            parametros.put("numeroFactura", detalleCompraDTO.getNumeroFactura());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                    parametros, beanCollectionDataSource);

            PrintService selectedService = PrintUtil.findPrintService(detalleCompraDTO.getImpresora());

            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            // printRequestAttributeSet.add(MediaSizeName.ISO_A4); //setting page size
            printRequestAttributeSet.add(new Copies(1));

            PrinterName printerName = new PrinterName(selectedService.getName(), null); //gets printer 

            PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
            printServiceAttributeSet.add(printerName);

            JRPrintServiceExporter exporter = new JRPrintServiceExporter();

            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printServiceAttributeSet);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
            exporter.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(ComprasController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @ModelAttribute("impresoras")
    public List<String> listPrinter() {
        return PrintUtil.getPrinterServiceNameList();
    }

    @ModelAttribute("defaultPrinter")
    public String defaultPrinter() {
        String pDefault = "";
       /* try {
            pDefault = PrintUtil.getDefaultPrinter().getName();
            int indexName = pDefault.lastIndexOf("\\");
            if (indexName != -1) {
                pDefault = pDefault.substring(indexName + 1);
            }
        } catch (Exception e) {
            System.out.println("Error defaultPrinter::" + e.getMessage());
        }*/

        return pDefault;
    }
}
