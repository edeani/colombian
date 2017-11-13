/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.entidad.Sedes;
import com.administracion.util.LeerXml;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author EderArmando
 */
@Repository
public class SedesDaoImpl extends GenericDaoImpl<Sedes> implements SedesDao{

    @Autowired
    private LeerXml leerXml;

    
    @Override
    public Sedes findXName(String sede) {
        HashMap<String,Object> parametros = new HashMap<>();
        parametros.put("sede", sede);
        return queryOpjectJpa(leerXml.getQuery("SedesJpa.findXname"), parametros);
    }
    
}
