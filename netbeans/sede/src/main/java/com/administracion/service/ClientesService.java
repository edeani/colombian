/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.administracion.service;

import com.administracion.entidad.Clientes;

/**
 *
 * @author Anlod
 */
public interface ClientesService {
    
    Clientes findClientesByTel(String tel,String nameDataSource);
}
