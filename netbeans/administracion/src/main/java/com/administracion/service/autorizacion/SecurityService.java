/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.autorizacion;

import com.administracion.entidad.Usuario;

/**
 *
 * @author user
 */
public interface SecurityService {
    public Usuario getCurrentUser();
    void autenticarUsuarioRegistrado(String username,String token);
}
