/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services;

import com.colombian.cali.colombiancaliycali.entidades.Users;

/**
 *
 * @author Eslayfer
 */
public interface SecurityService {

    /**
     * Retorna el usuario logueado en el sistema.
     * 
     * @return
     */
	public Users getCurrentUser();

    /**
     * Conecta al usuario con la unidad de trabajo persistene para poder
     * realizar operaciones que involucren transacciones.
     * 
     */
    public Users getConnectedCurrentUser();

    /**
     * Simula un ingreso del usuario.
     * 
     * @param usuario
     */
    public void connect(Users permisosUsuario);

    /**
     * Valida que la contrasena ingresada sea la misma que la del usuario.
     * 
     * @param actual
     * @return
     */
    public boolean claveValida(String usuario,String actual);

    /**
     * Configura la seguridad para que no se valide los permisos sobre una
     * entidad.
     * 
     * @param bypass
     */
    void byPassSecurity(Boolean bypass);

    /**
     * Determina si la seguridad hay que saltearla.
     * 
     * @return
     */
    boolean byPassSecurity();
    
    
    /**
     * Valida que el hash sea correcto
     * 
     */
    public boolean validacionCorrecta(Long idVisitor, String hash); 
    
}
