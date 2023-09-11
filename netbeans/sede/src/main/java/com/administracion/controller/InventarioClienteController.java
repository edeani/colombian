/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administracion.controller;

import com.administracion.dto.InventarioClienteDto;
import com.administracion.dto.InventarioConsolidadoClienteDto;
import com.administracion.dto.SubSedesDto;
import com.administracion.service.InventarioService;
import com.administracion.service.autorizacion.ConnectsAuth;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Anlod
 */
@Controller
@RequestMapping("/{sede:[a-zA-Z]+}/inventario/reportes/cliente")
public class InventarioClienteController extends BaseController{
    
    private static final String TITULO = "Inventario Clientes";
    private static final String TITULO_CONSOLIDADO = "Inventario Consolidado";
    
    @Autowired
    private InventarioService inventarioService;
    @Autowired
    private ConnectsAuth connectsAuth;
    
    @GetMapping("/index.htm")
    public ModelAndView indexInventarioClientes(){
        ModelAndView mav = new ModelAndView("reportes/inventario/cliente/inventarioCliente");
        mav.addObject("titulo", TITULO);
        mav.addObject("fecha", new Date());
        return mav;
    }
    
    @PostMapping(value = "/ajax/consultar.htm")
    public ModelAndView consultarInventarioColombian(@RequestParam String fechaInicial, @RequestParam String fechaFinal,
            @RequestParam(required = false, value = "sede") Integer subsede,@RequestParam String tel) {
        ModelAndView mav = new ModelAndView("reportes/inventario/cliente/datosInventarioCliente");
        SubSedesDto subSedesDto = connectsAuth.findSubsedeXId(subsede);
        List<InventarioClienteDto> inventarioCliente = inventarioService.traerProductoClienteInventario(subSedesDto.getSede(),tel, fechaInicial,
                fechaFinal);
        mav.addObject("inventarioCliente", inventarioCliente);
        mav.addObject("size", inventarioCliente.size());
        return mav;
    }
    
    @GetMapping("/consolidado/index.htm")
    public ModelAndView indexConsolidadoInventarioClientes(){
        ModelAndView mav = new ModelAndView("reportes/inventario/cliente/consolidado/inventarioConsolidado");
        mav.addObject("titulo", TITULO_CONSOLIDADO);
        mav.addObject("fecha", new Date());
        return mav;
    }
    
    @PostMapping(value = "/ajax/consolidado/consultar.htm")
    public ModelAndView consultarInventarioConsolidadoColombian(@PathVariable String sede, @RequestParam String fechaInicial
            , @RequestParam String fechaFinal, @RequestParam String tel) {
        ModelAndView mav = new ModelAndView("reportes/inventario/cliente/consolidado/datosInventarioConsolidado");
        List<InventarioConsolidadoClienteDto> inventarioConsolidado =inventarioService.traerProductoConsolidadoInventario(sede,tel, fechaInicial, fechaFinal);
        mav.addObject("inventarioConsolidado", inventarioConsolidado);
        mav.addObject("size", inventarioConsolidado.size());
        return mav;
    }
}
