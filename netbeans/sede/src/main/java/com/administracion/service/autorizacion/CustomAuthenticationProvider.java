/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.autorizacion;

import com.administracion.dao.SubSedesDao;
import com.administracion.dao.UsuarioDao;
import com.administracion.entidad.Sedes;
import com.administracion.entidad.Users;
import com.administracion.service.UsuarioService;
import com.administracion.util.LeerXml;
import java.util.ArrayList;
import java.util.List;
import static java.util.regex.Pattern.matches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author edeani
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider, SecurityService {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private LeerXml leerXml;
    
    @Autowired
    private SubSedesDao subSedesDao;
    
    private AccesosSubsedes accesosSubsedes_;
    
    @Autowired
    private void init(AccesosSubsedes accesosSubsedes_){
        this.accesosSubsedes_=accesosSubsedes_;
    }
    
    @Value("${usuariobd}")
    private String usernamebd;
    @Value("${paswordbd}")
    private String passwordbd;
    @Value("${serverbd}")
    private String urlbd;
    @Value("${basededatos}")
    private String bd;
   
    private static final String PREFIJO_ROL = "ROLE_";
    private static final String USUARIO_ACTIVO = "A";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        
        Users user = usuarioService.findUsuarioByCorreo(username);
        if (user == null) {
            throw new BadCredentialsException("1000");
        }
        String path = accesosSubsedes_.getPath();
        
        if(!user.getIdsedes().getSede().equals(path)){
            throw new BadCredentialsException("1000");
        }
        
        if (!matches(password, user.getPassword())) {
            throw new BadCredentialsException("1000");
        }
        
        
        /**
         * Sede del usuario
         */
        accesosSubsedes_.setSedes(new ArrayList<>());
        accesosSubsedes_.getSedes().add(user.getIdsedes());
        /**
         * Subsedes de la sede del usuario
         */
        accesosSubsedes_.setSubsedes(subSedesDao.subsedesXIdSede(user.getIdsedes().getIdsedes()));
        /**
         * agregar bd principal
         */       
        Sedes sedes = new Sedes();
        sedes.setPassword(this.passwordbd);
        sedes.setUsername(this.usernamebd);
        sedes.setSede(this.bd);
        sedes.setUrl("jdbc:mysql://"+this.urlbd+"/"+this.bd);
        accesosSubsedes_.setSedePrincipal(sedes);
        accesosSubsedes_.setPrincialDataSource(sedes);
        /**
         * Fin  agregar bd principal
         */
        
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority(PREFIJO_ROL + user.getIdrol().getNombre()));
        //El object user que mando aqui puede ser cualquier objeto desde un string a uno con atributos
        return new UsernamePasswordAuthenticationToken(user, password, grantedAuths);
    }

    @Override
    public boolean supports(Class<?> type) {
        return true;
    }

    @Override
    public Users getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof String) {
                return null;
            }
            return (Users) principal;
        } else {
            return null;
        }

    }

  

}
