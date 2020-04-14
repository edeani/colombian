/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import domicilios.entidad.Usuario;
import domicilios.service.UsuarioService;

/**
 * @author user
 */
@Controller
public class PrincipalController {
    
    @Autowired
    private UsuarioService usuarioService;
   
    
    @GetMapping("/index.htm")
    public ModelAndView principalProbando(){
        List<Usuario> usuarios = usuarioService.listUsuarios();
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("usuarios", usuarios);
        return mav;
    }
}
