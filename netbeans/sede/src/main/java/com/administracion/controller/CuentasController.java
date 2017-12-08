/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;


import com.administracion.dto.CuentasAutoCompletarDto;
import com.administracion.entidad.CuentasPuc;
import com.administracion.service.CuentasService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    String actualizarCuenta(@Valid CuentasPuc cuentasPuc) {
        ModelAndView mav = null;

        try {
            cuentasService.actualizarCuenta(cuentasPuc, getPropiedades().leerPropiedad());
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
    String guardarCuenta(@Valid CuentasPuc cuentasPuc) {
        ModelAndView mav = null;

        try {
            cuentasService.guardarCuenta(cuentasPuc, getPropiedades().leerPropiedad());
        } catch (Exception e) {
            System.out.println("Controller:guardarCuenta:" + e.getMessage());
            return "";
        }
        return "ok";
    }

    @RequestMapping(value = "/ajax/buscar.htm")
    public ModelAndView buscarCuenta(@RequestParam(value = "idCuenta") Long idCuenta) {

        ModelAndView mav = null;
        boolean haycuenta = false;
        CuentasPuc cuentasPuc = cuentasService.buscarCuenta("" + idCuenta, getPropiedades().leerPropiedad());
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
    String autocompletarCuenta(@RequestParam String term) {

        Gson gson = new Gson();
        String json = "[]";
        List<CuentasAutoCompletarDto> cuentasAutoCompletarDtos = cuentasService.autocompletarIdCuenta(term, getPropiedades().leerPropiedad());
        JsonArray jsonArray = null;
        if (cuentasAutoCompletarDtos != null) {
            json = gson.toJson(cuentasAutoCompletarDtos);
        }

        return json;
    }
}
