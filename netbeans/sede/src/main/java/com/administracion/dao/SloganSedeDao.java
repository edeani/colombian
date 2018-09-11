/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.entidad.Sloganxsede;
import com.administracion.entidad.TextosSloganSedeDto;

/**
 *
 * @author EderArmando
 */
public interface SloganSedeDao extends GenericDao<Sloganxsede>{
    TextosSloganSedeDto finTextosSloganXIdSede(Integer idsede);
}
