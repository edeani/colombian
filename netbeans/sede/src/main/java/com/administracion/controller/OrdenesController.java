/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administracion.controller;

import com.administracion.dto.OrdenesClienteProdDto;
import com.administracion.dto.SedesDto;
import com.administracion.dto.SubSedesDto;
import com.administracion.entidad.Barrios;
import com.administracion.entidad.Clientes;
import com.administracion.service.BarriosService;
import com.administracion.service.ClientesService;
import com.administracion.service.OrdenesService;
import com.administracion.service.autorizacion.ConnectsAuth;
import com.administracion.service.autorizacion.SecurityService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Esta clase manejará todas las consultas hacia la tabla de ordenes
 * @author Anlod
 */
@Controller
@RequestMapping("/{sede:[a-zA-Z]+}/ordenes")
public class OrdenesController extends BaseController{
    
    @Autowired
    private OrdenesService ordenesService;
    @Autowired
    private SecurityService security;
    @Autowired
    private BarriosService barriosService;
    @Autowired
    private ClientesService clientesService;
    @Autowired
    private ConnectsAuth connectsAuth;
    
    @GetMapping("/index.htm")
    public ModelAndView indexOrdenes(){
        ModelAndView mav =new ModelAndView("reportes/ordenes/ordenesIndexSede");
        mav.addObject("fecha",new Date());
        return mav; 
        
    }
    
    @RequestMapping("/usuariosPdf.htm")
     public ModelAndView reporteOrdenesUsuario(@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam String fechaInicial
            ,@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam String fechaFinal
            , @RequestParam(value="sede")Integer subsede,@RequestParam String tel
            ,@PathVariable(value = "sede") String sedePrincipal) {
        
        SubSedesDto subSedesDto = connectsAuth.findSubsedeXId(subsede);
        if (Objects.isNull(subSedesDto)) {
            indexOrdenes();
        }

        List<OrdenesClienteProdDto> ordenesIndex = ordenesService.ordenesReporteClientesSubSede(subSedesDto.getSede(),
                 fechaInicial, fechaFinal, tel);
        Clientes cliente = clientesService.findClientesByTel(tel,subSedesDto.getSede() );
        
        
        ModelAndView mav = null;
        if (ordenesIndex != null && Objects.nonNull(cliente)) {
            Barrios barrio = barriosService.listClientes(cliente.getCodigoBarrio(), subSedesDto.getSede());
            SedesDto sedesDto = connectsAuth.findSedeXName(sedePrincipal);
            if (Boolean.FALSE.equals(ordenesIndex.isEmpty())) {
                Map<String, Object> parameterMap = new HashMap<>();
                parameterMap.put("usuario", security.getCurrentUser().getUsername());
                parameterMap.put("datos", ordenesIndex);
                
                parameterMap.put("titulo", sedesDto.getTitulo());
                parameterMap.put("sede", subSedesDto.getSede());
                parameterMap.put("nombresede", sedePrincipal);
                parameterMap.put("fechaInicial", fechaInicial);
                parameterMap.put("fechaFinal", fechaFinal);
                parameterMap.put("slogan", sedesDto.getSlogan());
                parameterMap.put("cliente", cliente.getDescripcionCliente());
                parameterMap.put("telefono", tel);
                parameterMap.put("barrio", barrio.getDescripcionBarrio());
                mav = new ModelAndView("reporteOrdenesUsuarios", parameterMap);

            }
        }
        return mav;
    }
}
