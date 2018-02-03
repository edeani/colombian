/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;


import com.administracion.dto.DetalleAveriaDTO;
import com.administracion.service.AveriasService;
import com.administracion.service.autorizacion.SecurityService;
import com.administracion.util.LectorPropiedades;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jose Efren
 */
@Controller
@RequestMapping("/{sede:[a-zA-Z]+}/averia")
public class AveriasController extends BaseController {

    @Autowired
    AveriasService averiasService;
    @Autowired
    SecurityService securityService;
    
    private LectorPropiedades propiedades;
    private  final String titulo = "Averias";

    @RequestMapping("/home.htm")
    public ModelAndView inicio() {
        ModelAndView mav = new ModelAndView("averias/averias");

        DetalleAveriaDTO detalleAveriaDTO = new DetalleAveriaDTO();

        mav.addObject("titulo", titulo);
        setBasicModel(mav, detalleAveriaDTO);
        return mav;
    }

    @RequestMapping("/guardar.htm")
    public ModelAndView guardarAveria(@Valid DetalleAveriaDTO detalleAveriaDTO,@PathVariable String sede) {

        averiasService.guardarAveria(sede, detalleAveriaDTO.getAveria(), detalleAveriaDTO.getTotalAveria(), securityService.getCurrentUser().getUsername());
        
        return new ModelAndView("redirect:/averia/home.htm");
    }
}
