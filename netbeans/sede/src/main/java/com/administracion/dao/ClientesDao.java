/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.administracion.dao;

import com.administracion.entidad.Clientes;
import javax.sql.DataSource;

/**
 *
 * @author Anlod
 */
public interface ClientesDao {
    
    Clientes findClientesByTel(String tel,DataSource nameDataSource);
}
