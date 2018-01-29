/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.entidad.Userxsede;

/**
 *
 * @author edeani
 */
public interface UsuarioXSedeService {
    Userxsede findUusarioByCorreoSede(String correo,String sede);
}
