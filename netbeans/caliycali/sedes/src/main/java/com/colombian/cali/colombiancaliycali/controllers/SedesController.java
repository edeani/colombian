/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.controllers;

import com.colombia.cali.colombiancaliycali.util.LectorPropiedades;
import com.colombian.cali.colombiancaliycali.dto.ItemsDTO;
import com.colombian.cali.colombiancaliycali.entidades.Sedes;
import com.colombian.cali.colombiancaliycali.services.SecurityService;
import com.colombian.cali.colombiancaliycali.services.SedesService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@RequestMapping("/sedes")
public class SedesController extends BaseController {

    @Autowired
    private SedesService sedeService;
    @Autowired
    private SecurityService securityService;


    @ModelAttribute("sedes")
    public String traerSedes(HttpSession session, HttpServletRequest request) {
        String idsede = (String) session.getAttribute("sede");
        System.out.println("Sede " + idsede);
        if (idsede == null) {
            request.getSession().setAttribute("sede", "1");
            System.out.println("Entro Sede " + idsede);
            idsede = "1";
        }

        return idsede;
    }

    //Carga las sedes en el select
    @RequestMapping("/ajax/listaSedeSelect.htm")
    public ModelAndView cargarSedes(HttpSession session) {
        ModelAndView mav = new ModelAndView("util/formSelect");
        getPropiedades().setArchivo(getArchivo());
        getPropiedades().setPropiedad(getPropiedadPrincipal());

        List<ItemsDTO> datosSedes = sedeService.listaSedesOptions(getPropiedades().leerPropiedad());
        mav.addObject("datos", datosSedes);

        return mav;
    }

    //Seteo la sede en Sesión
    @RequestMapping(value = "/ajax/seleccionarSede.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView seleccionarSede(HttpSession session, HttpServletRequest request,
            @RequestParam(value = "idSede", required = false) Long idSede, @RequestParam(value = "nombreSede", required = false) String nombreSede) {
        ModelAndView mav = new ModelAndView("util/formSelectSedes");
        getPropiedades().setArchivo(getArchivo());
        getPropiedades().setPropiedad(getPropiedadPrincipal());

        List<ItemsDTO> datosSedes = sedeService.listaSedesOptions(getPropiedades().leerPropiedad());
        mav.addObject("datos", datosSedes);
        mav.addObject("sede", idSede);
        return mav;
    }

    @RequestMapping(value = "/ajax/setSedeSession.htm")
    public @ResponseBody   String setSedeReportesColombian(@RequestParam Long idSede) {
        try {
            Sedes sede = sedeService.buscarSede(getPropiedades().leerPropiedad(), idSede);
            securityService.getCurrentUser().setSede(sede); 
        } catch (Exception e) {
            System.out.println("setSedeReportesColombian::"+e.getMessage());
            return "error";
        }
        return "ok";
    }

}
