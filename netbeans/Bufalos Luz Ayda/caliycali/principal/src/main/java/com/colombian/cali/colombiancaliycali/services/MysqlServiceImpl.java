/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombian.cali.colombiancaliycali.services;

import com.colombia.cali.colombiancaliycali.dao.SecuenciasMysqlDao;
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
    
    @Override
    @Transactional(readOnly = true)
    public Long secuenciaTabla(String nameDataSource, String tabla) {
        return secuenciasMysqlDao.secuenceTable(nameDataSource, tabla);
    }
    
}
