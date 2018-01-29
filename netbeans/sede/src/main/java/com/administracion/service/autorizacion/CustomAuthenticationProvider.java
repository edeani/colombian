/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.autorizacion;

import com.adiministracion.mapper.UserMapper;
import com.administracion.dao.SubSedesDao;
import com.administracion.dto.SedesDto;
import com.administracion.entidad.Users;
import com.administracion.entidad.Userxsede;
import com.administracion.service.UsuarioService;
import com.administracion.service.UsuarioXSedeService;
import java.util.ArrayList;
import java.util.List;
import static java.util.regex.Pattern.matches;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UsuarioXSedeService usuarioXSedeService;
    
    @Autowired
    private SubSedesDao subSedesDao;
    
    private AccesosSubsedes accesosSubsedes;
    
    @Autowired
    private void init(AccesosSubsedes accesosSubsedes){
        this.accesosSubsedes=accesosSubsedes;
    }
     
    private static final String PREFIJO_ROL = "ROLE_";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        
        String path = accesosSubsedes.getPath();
        Userxsede userxsede = usuarioXSedeService.findUusarioByCorreoSede(username, path);
        if (userxsede == null) {
            throw new BadCredentialsException("1000");
        }
        if(!userxsede.getIdsede().getSede().equals(path)){
            throw new BadCredentialsException("1000");
        }
        
        if (!matches(password, userxsede.getIduser().getPassword())) {
            throw new BadCredentialsException("1000");
        }
        
        Users user = userxsede.getIduser();
        
        /**
         * Sede del usuario
         */
        SedesDto sedeDto = new SedesDto();
        sedeDto.setIdsedes(userxsede.getIdsede().getIdsedes());
        sedeDto.setUsername(userxsede.getIdsede().getUsername());
        sedeDto.setPassword(userxsede.getIdsede().getPassword());
        sedeDto.setUrl(userxsede.getIdsede().getUrl());
        sedeDto.setSede(userxsede.getIdsede().getSede());
        sedeDto.setUsersLogin(user.getUsername());
        accesosSubsedes.getSedes().add(sedeDto);
        /**
         * Subsedes de la sede del usuario
         */
        accesosSubsedes.setSubsedes(subSedesDao.subsedesXIdSede(userxsede.getIdsede().getIdsedes()));
        /**
         * Usuario que se loguea
         */
        accesosSubsedes.getUserLog().add(UserMapper.userToUserItemDto(userxsede));
        
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
