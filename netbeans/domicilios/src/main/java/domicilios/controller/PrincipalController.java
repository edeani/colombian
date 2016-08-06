/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.controller;

import domicilios.entidad.Usuario;
import domicilios.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author user
 */
@Controller
public class PrincipalController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @RequestMapping("/crear.htm")
    public @ResponseBody String principal(){
        Usuario usuario =  new Usuario();
        usuario.setCedula("998866553322");
        usuario.setCorreo("anloder4@gmail.com");
        usuario.setDireccion("dir1");
        usuario.setEstado("A");
        usuario.setNombreusuario("koko");
        usuario.setPassword("domicilios");
        usuario.setIdrol(usuarioService.roles(1));
        
        usuarioService.crearUsuario(usuario);
        
        
        return "Terminado";
    }
   
    
    @RequestMapping("/index.htm")
    public ModelAndView principalProbando(){
        List<Usuario> usuarios = usuarioService.listUsuarios();
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("usuarios", usuarios);
        return mav;
    }
}
