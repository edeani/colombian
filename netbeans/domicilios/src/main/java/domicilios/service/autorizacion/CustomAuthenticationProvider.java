/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.service.autorizacion;

import domicilios.dao.UsuarioDao;
import domicilios.dao.ValidacionUsuarioDao;
import domicilios.entidad.Usuario;
import domicilios.entidad.ValidacionUsuarios;
import domicilios.service.UsuarioService;
import domicilios.util.LeerXml;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import static java.util.regex.Pattern.matches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author edeani
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider, SecurityService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ValidacionUsuarioDao validacionUsuarioDao;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private LeerXml leerXml;

    private static final String PREFIJO_ROL = "ROLE_";
    private static final String USUARIO_ACTIVO = "A";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        Usuario user = usuarioService.findUsuarioByCorreo(username);
        if (user == null) {
            throw new BadCredentialsException("1000");
        }
        String estado = user.getEstado();
        if (estado.equals("I")) {

            throw new DisabledException("1001");
        }
        if (!matches(password, user.getPassword())) {
            throw new BadCredentialsException("1000");
        }

        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority(PREFIJO_ROL + user.getIdrol().getNombrerol()));
        //El object user que mando aqui puede ser cualquier objeto desde un string a uno con atributos
        return new UsernamePasswordAuthenticationToken(user, password, grantedAuths);
    }

    @Override
    public boolean supports(Class<?> type) {
        return true;
    }

    @Override
    public Usuario getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof String) {
                return null;
            }
            return (Usuario) principal;
        } else {
            return null;
        }

    }

    @Transactional
    @Override
    public void autenticarUsuarioRegistrado(String username, String token) {
        Usuario usuario = usuarioService.findUsuarioByCorreo(username);
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("idusuario", usuario.getIdusuario());
        ValidacionUsuarios vu = validacionUsuarioDao.queryOpjectJpa(leerXml.getQuery("ValidacionUsuarioJpa.findXidusuario"), parametros);

        if (vu != null) {
            if (!token.equals(vu.getToken())) {
                throw new SecurityException("1002");
            }
            usuario.setEstado(USUARIO_ACTIVO);
            usuarioDao.Update(usuario);

            vu.setEstado(USUARIO_ACTIVO);
            vu.setFechaactivacion(new Date());
            validacionUsuarioDao.Update(vu);
            
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority(PREFIJO_ROL + usuario.getIdrol().getNombrerol()));
            
           UsernamePasswordAuthenticationToken usuarioLogueado = new UsernamePasswordAuthenticationToken(usuario.getCorreo(), usuario.getPassword(), grantedAuths);
           Authentication authentication = authenticationManager.authenticate(usuarioLogueado);
           
           
           if(usuarioLogueado.isAuthenticated()){
             SecurityContextHolder.getContext().setAuthentication(authentication);
           }
        }
    }

}
