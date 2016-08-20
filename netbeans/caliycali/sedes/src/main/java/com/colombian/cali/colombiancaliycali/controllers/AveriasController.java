/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.controllers;

import com.colombia.cali.colombiancaliycali.util.LectorPropiedades;
import com.colombian.cali.colombiancaliycali.dto.DetalleAveriaDTO;
import com.colombian.cali.colombiancaliycali.entidades.Sedes;
import com.colombian.cali.colombiancaliycali.services.AveriasService;
import com.colombian.cali.colombiancaliycali.services.SecurityService;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jose Efren
 */
@Controller
@RequestMapping("/averia")
public class AveriasController extends BaseController {

    @Autowired
    AveriasService averiasService;
    @Autowired
    SecurityService securityService;
    
    private LectorPropiedades propiedades;
    private static final String titulo = "Averias";

    @RequestMapping("/home.htm")
    public ModelAndView inicio() {
        ModelAndView mav = new ModelAndView("averias/averias");

        DetalleAveriaDTO detalleAveriaDTO = new DetalleAveriaDTO();

        mav.addObject("titulo", titulo);
        setBasicModel(mav, detalleAveriaDTO);
        return mav;
    }

    @RequestMapping("/guardar.htm")
    public ModelAndView guardarAveria(@Valid DetalleAveriaDTO detalleAveriaDTO) {

        propiedades = new LectorPropiedades();
        propiedades.setArchivo(getArchivo());
        propiedades.setPropiedad(getPropiedadPrincipal());

        averiasService.guardarAveria(propiedades.leerPropiedad(), detalleAveriaDTO.getAveria(), detalleAveriaDTO.getTotalAveria(), securityService.getCurrentUser().getUsername());
        
        return new ModelAndView("redirect:/averia/home.htm");
    }
}
