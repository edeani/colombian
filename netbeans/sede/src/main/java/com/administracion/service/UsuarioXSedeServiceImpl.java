/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dao.UserXSedeDao;
import com.administracion.entidad.Userxsede;
import com.administracion.util.LeerXml;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author edeani
 */
@Service
public class UsuarioXSedeServiceImpl implements UsuarioXSedeService{

    @Autowired
    private UserXSedeDao userXSedeDao;
    @Autowired
    private LeerXml leerXml;
    
    @Transactional(readOnly = true)
    @Override
    public Userxsede findUusarioByCorreoSede(String correo, String nameSede) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("username", correo);
        parametros.put("sede", nameSede);
        return userXSedeDao.queryOpjectJpa(leerXml.getQuery("UsuarioJpa.findXcorreoSedeName"), parametros);
    }
    
}
