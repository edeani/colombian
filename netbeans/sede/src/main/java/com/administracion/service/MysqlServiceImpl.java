/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.service;

import com.administracion.dao.SecuenciasMysqlDao;
import com.administracion.service.autorizacion.ConnectsAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jose Efren
 */
@Service
public class MysqlServiceImpl implements MysqlService{

    @Autowired
    private SecuenciasMysqlDao secuenciasMysqlDao;
    @Autowired
    private ConnectsAuth connectsAuth;
    
    @Override
    @Transactional(readOnly = true)
    public Long secuenciaTabla(String nameDataSource, String tabla) {
        return secuenciasMysqlDao.secuenceTable(connectsAuth.getDataSourceSede(nameDataSource), tabla);
    }
    
}
