/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.security;

import com.mycompany.entidades.Usuario;

/**
 *
 * @author eder
 */
public interface UsuarioService {
    
    public Usuario encontraUsuario(String nombre,String pwd);
    
}
