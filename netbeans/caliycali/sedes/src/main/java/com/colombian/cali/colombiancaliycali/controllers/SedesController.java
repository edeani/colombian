/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.controllers;

import com.colombia.cali.colombiancaliycali.util.LectorPropiedades;
import com.colombian.cali.colombiancaliycali.dto.ItemsDTO;
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
    
    private LectorPropiedades propiedades;
    
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
        propiedades = new LectorPropiedades();
        propiedades.setArchivo(getArchivo());
        propiedades.setPropiedad(getPropiedadPrincipal());
        
        List<ItemsDTO> datosSedes = sedeService.listaSedesOptions(propiedades.leerPropiedad());
        mav.addObject("datos", datosSedes);

        return mav;
    }
    
    //Seteo la sede en Sesión
    @RequestMapping(value="/ajax/seleccionarSede.htm",method = {RequestMethod.GET, RequestMethod.POST})
    public  ModelAndView seleccionarSede(HttpSession session,HttpServletRequest request,
           @RequestParam(value="idSede",required = false) Long idSede,@RequestParam(value="nombreSede",required = false) String nombreSede){
        ModelAndView mav = new ModelAndView("util/formSelectSedes");
        propiedades = new LectorPropiedades();
        propiedades.setArchivo(getArchivo());
        propiedades.setPropiedad(getPropiedadPrincipal());

        List<ItemsDTO> datosSedes = sedeService.listaSedesOptions(propiedades.leerPropiedad());
        mav.addObject("datos", datosSedes);
        mav.addObject("sede", idSede);
        //mav.addObject("sede", sede.getIdsedes());
        
        //session.setAttribute("objetoSede", sede);
        
        return mav;
    }
    
    

}
