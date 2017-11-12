/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dao.UsuarioDao;
import com.administracion.entidad.Users;
import com.administracion.util.LeerXml;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private LeerXml leerXml;


    @Transactional(readOnly = true)
    @Override
    public Users findUsuarioByCorreo(String correo) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("username", correo);
        return usuarioDao.queryOpjectJpa(leerXml.getQuery("UsuarioJpa.findXcorreo"), parametros);
    }

}
