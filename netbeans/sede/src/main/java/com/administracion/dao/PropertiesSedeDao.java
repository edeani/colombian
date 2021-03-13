/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.entidad.PropertiesSede;

/**
 *
 * @author edeani
 */
public interface PropertiesSedeDao {
    
    PropertiesSede getPropertie(String name, Long idSedePrincipal);
}
