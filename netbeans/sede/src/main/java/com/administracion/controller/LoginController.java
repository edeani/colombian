/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.administracion.dto.SedesDto;
import com.administracion.entidad.Users;
import com.administracion.service.SedesService;
import com.administracion.service.UsuarioService;
import com.administracion.service.autorizacion.AccesosSubsedes;
import static java.util.regex.Pattern.matches;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author user
 */
@Controller
public class LoginController {

    @Value("${basededatos}")
    private String base_datos_principal;
    
    @Value("${url.login}")
    private String login;
    
    @Value("${url.login.generic}")
    private String login_generic;
    
    @Autowired
    private SedesService sedesService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AccesosSubsedes accesosSubsedes;

    /**
     * Carga base de datos principal si no se le especifica sede
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/signin.htm")
    public ModelAndView paginaLogin(HttpSession session) {
        session.setAttribute("path", base_datos_principal);
        return new ModelAndView("index");
    }

    /**
     * Cargar base de datos y sede seleccionada en el PATH
     *
     * @param request
     * @param sede
     * @return
     */
    @RequestMapping(value = "/{sede:[a-zA-Z]+}/signin.htm")
    public ModelAndView paginaLoginSede(HttpServletRequest request, @PathVariable String sede) {
        HttpSession session = request.getSession();
        SedesDto s = sedesService.findSedeXNameDto(sede);
        String rutaLogin = "";
        ModelAndView mav;
        if (s == null) {
            return new ModelAndView("redirect:/404.htm");
        } else {
            if (accesosSubsedes.getSedes().size() > 0) {
                rutaLogin=login_generic;
                mav = new ModelAndView("indexGeneric");
            } else {
                rutaLogin=login;
                mav = new ModelAndView("index");
            }
            request.getSession().setAttribute("path", sede);
            accesosSubsedes.setPath(sede);
        }
        
        mav.addObject("urlLogin", rutaLogin);
        mav.addObject("sedePath", sede);
        mav.addObject("rt","/"+ sede + "/home.htm");
        return mav;
    }

    @RequestMapping(value = "/generic_sec.htm", method = RequestMethod.POST)
    public ModelAndView loginGenericSede(@RequestParam String loginname, @RequestParam String passwordsede,
            @RequestParam(value = "sede") String sedePath,@RequestParam String rt,HttpServletRequest request) {
        SedesDto puntoSede = sedesService.findSedeXNameDto(sedePath);
        if (puntoSede != null) {
            Users users = usuarioService.findUsuarioByCorreo(loginname);
            if (users == null) {
                throw new BadCredentialsException("1000");
            }
            String path = sedePath;
            if (!users.getIdsedes().getSede().equals(path)) {
                throw new BadCredentialsException("1000");
            }
            
            if (!matches(passwordsede, users.getPassword())) {
                throw new BadCredentialsException("1000");
            }
            if(rt.isEmpty()){
                rt=request.getContextPath()+"/403.htm";
            }else{
                puntoSede.setUsersLogin(users.getUsername());
                accesosSubsedes.getSedes().add(puntoSede); 
                accesosSubsedes.setMultiple(Boolean.TRUE);
                
            }
            
            ModelAndView mav = new ModelAndView("redirect:"+rt);
            
            return mav;
            
        } else {
            return new ModelAndView("redirect:/404.htm");
        }
    }

    @RequestMapping("/403.htm")
    public ModelAndView sedes403() {
        ModelAndView mav = new ModelAndView("403");
        return mav;
    }

    @RequestMapping("/404.htm")
    public ModelAndView notfound() {
        ModelAndView mav = new ModelAndView("404");
        return mav;
    }
}
