/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.entidad.Sedes;

/**
 *
 * @author EderArmando
 */
public interface SedesDao extends GenericDao<Sedes>{
    Sedes findXName(String sede);
}
