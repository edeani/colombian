/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.administracion.dao;

import com.administracion.entidad.Barrios;
import javax.sql.DataSource;

/**
 *
 * @author Anlod
 */
public interface BarriosDao {
    
    Barrios findBarrioById(Integer codigoBarrio,DataSource dataSource);
    
}
