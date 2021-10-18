/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.controllers;

import com.colombia.cali.colombiancaliycali.util.LectorPropiedades;
import com.colombian.cali.colombiancaliycali.dto.ItemsDTO;
import com.colombian.cali.colombiancaliycali.entidades.Proveedor;
import com.colombian.cali.colombiancaliycali.services.ComprasService;
import com.colombian.cali.colombiancaliycali.services.ProveedoresService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/proveedor")
public class ProveedorController extends BaseController{
    
    @Autowired
    private ComprasService comprasService;
    
    @Autowired
    private ProveedoresService proveedoresService;
    
    private LectorPropiedades propiedades;
    
    @RequestMapping("/home.htm")
    public ModelAndView homeProveedores(){
        ModelAndView mav = new ModelAndView("proveedor/proveedor");
        return mav;
    }
    
    @RequestMapping("/ajax/proveedores.htm")
    public ModelAndView proveedores(){
        propiedades = new LectorPropiedades();
        propiedades.setArchivo(getArchivo());
        propiedades.setPropiedad(getPropiedadPrincipal());
        ModelAndView mav = new ModelAndView("proveedor/proveedores");
        List<Proveedor> proveedores = proveedoresService.proveedores(propiedades.leerPropiedad());
        mav.addObject("proveedores", proveedores);
        return mav;
    }
    
    @RequestMapping("/ajax/listaProveedores.htm")
    public ModelAndView listaProvedores(){
        propiedades = new LectorPropiedades();
        propiedades.setArchivo(getArchivo());
        propiedades.setPropiedad(getPropiedadPrincipal());
        
        List<ItemsDTO> proveedores = comprasService.listaProveedores(propiedades.leerPropiedad());
        ModelAndView mav = new ModelAndView("util/formSelect");
        mav.addObject("datos", proveedores);
        return mav;
    }
    
    @RequestMapping("/ajax/buscar/proveedor.htm")
    public ModelAndView buscarProveedor(@RequestParam(value="idproveedor") Long idproveedor){
        propiedades = new LectorPropiedades();
        propiedades.setArchivo(getArchivo());
        propiedades.setPropiedad(getPropiedadPrincipal());
        
        ModelAndView mav = new ModelAndView("proveedor/formProveedor");
        Proveedor proveedor = proveedoresService.proveedor(propiedades.leerPropiedad(), idproveedor);
        mav.addObject("proveedor", proveedor);
        return mav;
    }
  
    @RequestMapping("/ajax/contenido/nuevo/proveedor.htm")
    public ModelAndView formNuevoProveedor(){
        ModelAndView mav = new ModelAndView("proveedor/formAgregarProveedor");
        return  mav;
    }
    
    @RequestMapping("/ajax/guardar/proveedor.htm")
    public @ResponseBody String guardarProveedor(@RequestParam(value="nombre",required=true) String nombre,
    @RequestParam(value="direccion",required=true) String direccion,
    @RequestParam(value="telefono",required=false) String telefono,
    @RequestParam(value="correo",required=false) String correo,
    @RequestParam(value="nit",required=false) String nit){
        propiedades = new LectorPropiedades();
        propiedades.setArchivo(getArchivo());
        propiedades.setPropiedad(getPropiedadPrincipal());
        
        Proveedor proveedor = new Proveedor();
        
        proveedor.setNombre(nombre);
        proveedor.setDireccion(direccion);
        proveedor.setTelefono(telefono);
        proveedor.setCorreo(correo);
        proveedor.setNit(nit);
        
        try {
            proveedoresService.guardarProveedor(propiedades.leerPropiedad(), proveedor);
        } catch (Exception e) {
            return e.getMessage();
        }
        
        return "ok";
    }
    @RequestMapping("/ajax/actualizar/proveedor.htm")
    public @ResponseBody String actualizarProveedor(@RequestParam(value="idproveedor",required=true) Long idproveedor,
    @RequestParam(value="nombre",required=true) String nombre,
    @RequestParam(value="direccion",required=true) String direccion,
    @RequestParam(value="telefono",required=false) String telefono,
    @RequestParam(value="correo",required=false) String correo,
    @RequestParam(value="nit",required=false) String nit){
        propiedades = new LectorPropiedades();
        propiedades.setArchivo(getArchivo());
        propiedades.setPropiedad(getPropiedadPrincipal());
        
        Proveedor proveedor = new Proveedor();
        proveedor.setIdproveedor(idproveedor);
        proveedor.setNombre(nombre);
        proveedor.setDireccion(direccion);
        proveedor.setTelefono(telefono);
        proveedor.setCorreo(correo);
        proveedor.setNit(nit);
        try {
            proveedoresService.actualizarProveedor(propiedades.leerPropiedad(), proveedor);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "ok";
    }
    @RequestMapping("/ajax/eliminar/proveedor.htm")
    public @ResponseBody String eliminarProveedor(@RequestParam(value="idproveedor") Long idproveedor){
        propiedades = new LectorPropiedades();
        propiedades.setArchivo(getArchivo());
        propiedades.setPropiedad(getPropiedadPrincipal());
        
        try {
            proveedoresService.eliminarProveedor(propiedades.leerPropiedad(), idproveedor);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "ok";
    }
    
    /* @RequestMapping(value = "/reportes/comprasTotalesXProveedorPDF.htm", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView reporteComprasTotalesXProveedorPDF(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(required = true, value = "idproveedor") Long idproveedor,
            @RequestParam(required = true, value = "fechaInicial") String fechaInicial, @RequestParam(required = true, value = "fechaFinal") String fechaFinal
    ) {
        ModelAndView mav = null;
        List<ReporteComprasTotalesXProveedorDTO> reporte = (List<ReporteComprasTotalesXProveedorDTO>) comprasService.comprasTotalesXProveedor(getPropiedades().leerPropiedad(),idproveedor, fechaInicial, fechaFinal);
        if (reporte.size() > 0) {
            JRDataSource datos = new JRBeanCollectionDataSource(reporte);
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("datos", datos);
            parameterMap.put("fechaInicial", fechaInicial);
            parameterMap.put("fechaFinal", fechaFinal);
            mav = new ModelAndView("ctxproveedor", parameterMap);
        } else {
            /**
             * TO:DO MODIFICAR REDIRECT
             
            mav = new ModelAndView("redirect:/inventario/reportes/inventarioTotal.htm");
            mav.addObject("mensaje", "Se encontrar&oacute;n 0 registros");
        }
        return mav;
    }*/
}
