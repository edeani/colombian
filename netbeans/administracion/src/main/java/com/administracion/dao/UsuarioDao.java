/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.entidad.Usuario;
import java.util.List;

/**
 *
 * @author user
 */
public interface UsuarioDao extends GenericDao<Usuario>{
    public List<Usuario> listUsuarios();
}