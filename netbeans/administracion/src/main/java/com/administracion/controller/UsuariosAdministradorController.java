/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.administracion.dto.UsuarioDto;
import com.administracion.service.UsuarioService;
import com.administracion.util.LectorPropiedades;
import com.administracion.util.ManageCookies;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author edeani
 */
@Controller
@RequestMapping("/usuarios")
public class UsuariosAdministradorController extends BaseController {

    @Autowired
    private UsuarioService usuarioService;

    private List<String> coordUrbana;

    private static final String PROPIEDADES_COLOMBIAN = "colombian.properties";

    @Autowired
    private void listaEstructuraUrbana() {
        coordUrbana = new ArrayList<>();
        LectorPropiedades lp = new LectorPropiedades();
        final String listaBuff = lp.leerPropiedad(PROPIEDADES_COLOMBIAN, "coord.urbana");
        String listado[] = listaBuff.split(",");
        coordUrbana.addAll(Arrays.asList(listado));
    }

    @RequestMapping("/index.htm")
    public ModelAndView indexAdminUsuarios() {
        return new ModelAndView("usuarios/clientes");
    }

    @RequestMapping("/roles.htm")
    public ModelAndView modificarRol(Integer idrol) {
        ModelAndView mav = new ModelAndView("usuarios/clientes-rol");
        return mav;
    }

    @RequestMapping("/ajax/buscar-x-mail-rol.htm")
    public ModelAndView buscarUsuarioXmailRol(@RequestParam String email) {
        EmailValidator validatorEmail = new EmailValidator();
        ModelAndView mav = new ModelAndView("usuarios/finded-rol");
        if (validatorEmail.isValid(email, null)) {
            UsuarioDto usuarioDto = usuarioService.findUsuarioByCorreoDto(email);
            if (usuarioDto != null) {
                mav.addObject("usuario", usuarioDto);
            } else {
                mav.addObject("mensaje", "Usuario no encontrado");
            }
        } else {
            mav.addObject("mensaje", "Email no v&aacute;lido");
        }
        return mav;
    }

    @RequestMapping("/ajax/buscar-x-mail.htm")
    public ModelAndView buscarUsuarioXmail(@RequestParam String email) {
        EmailValidator validatorEmail = new EmailValidator();
        ModelAndView mav = new ModelAndView("usuarios/finded");
        if (validatorEmail.isValid(email, null)) {
            UsuarioDto usuarioDto = usuarioService.findUsuarioByCorreoDto(email);
            if (usuarioDto != null) {
                setBasicModel(mav, usuarioDto);
                String direccion = usuarioDto.getDireccion();
                String[] direccionPartes = direccion.split("#");
                String placa = direccionPartes[1];

                mav.addObject("datoComponente1", placa.split("-")[0]);
                mav.addObject("datoComponente2", placa.split("-")[1]);

                String nombreNum = direccionPartes[0];
                String[] nombreNumPartes = nombreNum.split(" ");

                if (nombreNumPartes.length == 2) {
                    mav.addObject("componente", nombreNumPartes[0]);
                    mav.addObject("datoComponente", nombreNumPartes[1]);
                } else {
                    mav.addObject("componente", nombreNumPartes[0] + " " + nombreNumPartes[1]);
                    mav.addObject("datoComponente", nombreNumPartes[2]);
                }
                mav.addObject("usuario", usuarioDto);
            } else {
                mav.addObject("mensaje", "Usuario no encontrado");
            }
        } else {
            mav.addObject("mensaje", "Email no v&aacute;lido");
        }
        return mav;
    }

    @RequestMapping("/ajax/actualizar-usuario.htm")
    public ModelAndView actualizarUsuario(@ModelAttribute @Valid UsuarioDto usuarioDto, BindingResult binding,
            HttpServletResponse response) {
        if (binding.hasErrors()) {
            ManageCookies.setCookie(response, "updateUser", "N", 1, "/");
            ModelAndView mav = new ModelAndView("usuarios/finded");
            setBasicModel(mav, usuarioDto);
            mav.addObject("usuario", usuarioDto);
            return mav;
        } else {
            ModelAndView mav = new ModelAndView("usuarios/finded");
            usuarioService.actualizarUsuarioAdministracion(usuarioDto);
            ManageCookies.setCookie(response, "updateUser", "S", 1, "/");
            mav.addObject("mensaje", "OK");
            return mav;
        }
    }

    @ModelAttribute("coord")
    public List<String> listaCoordUrbanas() {
        return coordUrbana;
    }
}
