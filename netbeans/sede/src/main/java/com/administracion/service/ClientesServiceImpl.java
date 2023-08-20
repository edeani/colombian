/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administracion.service;

import com.administracion.dao.ClientesDao;
import com.administracion.entidad.Clientes;
import com.administracion.service.autorizacion.ConnectsAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anlod
 */
@Service
public class ClientesServiceImpl implements ClientesService{
    
    @Autowired
    private ClientesDao clientesDao;
    @Autowired
    private ConnectsAuth connectsAuth;
    
    @Override
    public Clientes findClientesByTel(String tel,String nameDataSource) {
        return this.clientesDao.findClientesByTel(tel,connectsAuth.getDataSourceSubSede(nameDataSource));
    }
    
}
