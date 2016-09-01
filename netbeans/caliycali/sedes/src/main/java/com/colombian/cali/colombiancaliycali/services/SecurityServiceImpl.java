/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services;
import com.colombia.cali.colombiancaliycali.dao.generic.GenericDao;
import com.colombia.cali.colombiancaliycali.util.GeneratorMD5;
import com.colombian.cali.colombiancaliycali.entidades.Users;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Eslayfer
 */
@Service
public class SecurityServiceImpl implements AuthenticationProvider, SecurityService, UserDetailsService {

    private static ThreadLocal<Boolean> BYPASS = new ThreadLocal<Boolean>();
 
    private com.colombia.cali.colombiancaliycali.dao.generic.GenericDao dao;
    
    @Override
    public Users getCurrentUser() {
    	
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        if (principal instanceof String) {
        	Users permisosUsuario = new Users();
        	permisosUsuario.setUsername("Anonimo");
            return permisosUsuario;
        }
        return (Users) principal;

    }

    @Override
    public Users getConnectedCurrentUser() {
        System.out.println("llege a curre y es " +this.getDao().findById(Users.class,this.getCurrentUser().getCedula()));
        return (Users) this.getDao().findById(Users.class,this.getCurrentUser().getCedula());

    }

    private List<GrantedAuthority> getGrantedAuthorities(Users permisosUsuario) {
        List<GrantedAuthority> authorities = new ArrayList <GrantedAuthority>();
        if (permisosUsuario.getRol()!=null) {
            authorities.add(new GrantedAuthorityImpl(permisosUsuario.getRol().getNombre()));
        }
        return authorities;
    }

    @Override
    public void connect(Users permisosUsuario) {
        List<GrantedAuthority> authorities = getGrantedAuthorities(permisosUsuario);
        Authentication auth = new UsernamePasswordAuthenticationToken(permisosUsuario,
        		permisosUsuario.getUsername(), authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);

    }

    @Override
    public boolean claveValida(String usr, String actual) {
    	Users permisosUsuario = (Users)dao.findUniqueByFields(Users.class, new String[] { "username" }, new Object[] { usr });        
        
        if (permisosUsuario == null) {
            return false;
        }
        return permisosUsuario.getPassword().equals(actual);

    }

    @Override
    public void byPassSecurity(Boolean bypass) {
        BYPASS.set(bypass);

    }

    @Override
    public boolean byPassSecurity() {
        return BYPASS.get() != null && BYPASS.get();
    }

    private boolean validateHash(Users permisosUsuario, String hash) {
        String token = "colombiacaliycali";
        String cadena = permisosUsuario.getCedula() + permisosUsuario.getPassword() + token;
        String hashOwn = "";

        try {
            hashOwn = GeneratorMD5.toMD5(cadena);
        } catch (NoSuchAlgorithmException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return hashOwn.equals(hash);
    }

    @Override
    public boolean validacionCorrecta(Long idCedula, String hash) {
    	Users permisosUsuario = (Users)dao.findById(Users.class, idCedula);
        
        if(validateHash(permisosUsuario, hash)){
            this.connect(permisosUsuario);
            return true;
        } else{
            return false;
        }
        
    }


    public GenericDao getDao() {
        return dao;
    }

    public void setDao(GenericDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional(readOnly=true)
    public Authentication authenticate(Authentication aut) {
       // JOptionPane.showConfirmDialog(null, "listo"+aut.getName()+"y el pass es "+aut.getCredentials());
        System.out.println( "listo el "+aut.getName()+"y el pass es "+aut.getCredentials());
    	Users permisosUsuario;
        permisosUsuario = new Users();
        try {
            Users obj = (Users) getDao().findUniqueByFields(Users.class, new String[] { "username" }, new Object[] { aut.getName() });
            
            if(obj!=null){
                System.out.println( "entre "+obj.getClass()+","+obj.toString()+","+obj.hashCode());
                try{
            	permisosUsuario = obj;
                System.out.println( "permisos usuarios es:"+ permisosUsuario.getUsername());
                }catch(Exception e){
                    System.out.println( "error "+ e.toString());
                }
            }

        } catch (Exception e) {
            throw new BadCredentialsException("Bad Credentials");
        }
        if (permisosUsuario == null) {
            throw new BadCredentialsException("Bad Credentials");
        }

        String passMD5;
		try {
			passMD5 = ""+aut.getCredentials();
			if (!permisosUsuario.getPassword().equals(passMD5)) {
	            throw new BadCredentialsException("Bad Credentials");
	        }
                }
		catch (Exception e) {
			throw new BadCredentialsException("Bad Credentials");
		}       
		
		
        List<GrantedAuthority> authorities = getGrantedAuthorities(permisosUsuario);
        
        Authentication authentication = new UsernamePasswordAuthenticationToken(permisosUsuario,
                aut.getCredentials(), authorities);
        
        return authentication;
    }

    @Override
    public boolean supports(Class<? extends Object> arg0) {
       
        return true;
        
    }
   

	@Override
	public UserDetails loadUserByUsername(String username){
		Users permisosUsuario = null;
	    try {
	        Object obj = getDao().findUniqueByFields(Users.class, new String[] { "username" }, new Object[] { username });
	        if(obj!=null){
	        	permisosUsuario = (Users)obj;
	        }
	    } catch (Exception e) {
	        throw new BadCredentialsException("Bad Credentials");
	    }
	    if (permisosUsuario == null) {
	        throw new BadCredentialsException("Bad Credentials");
	    }
	   
	
	    return permisosUsuario;
	}
}
    