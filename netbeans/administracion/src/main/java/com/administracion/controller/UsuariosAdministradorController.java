/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.administracion.entidad.Usuario;
import com.administracion.service.UsuarioService;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author edeani
 */
@Controller
@RequestMapping("/usuarios")
public class UsuariosAdministradorController {
    
    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping("/index.htm")
    public ModelAndView indexAdminUsuarios() {
        return new ModelAndView("usuarios/clientes");
    }

    @RequestMapping("/ajax/buscar-x-mail.htm")
    public ModelAndView buscarUsuarioXmail(@RequestParam String email) {
        EmailValidator validatorEmail = new EmailValidator();
        ModelAndView mav = new ModelAndView("usuarios/finded");
        if (validatorEmail.isValid(email, null)) {
            Usuario usuario = usuarioService.findUsuarioByCorreo(email);
            if (usuario != null) {
                mav.addObject("usuario", usuario);

            }else{
                mav.addObject("mensaje", "Usuario no encontrado");
            }
        } else {
            mav.addObject("mensaje", "Email no v&aacute;lido");
        }
        return mav;
    }
}
