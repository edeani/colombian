/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.autorizacion;

import com.administracion.entidad.Users;


/**
 *
 * @author user
 */
public interface SecurityService {
    public Users getCurrentUser();
}