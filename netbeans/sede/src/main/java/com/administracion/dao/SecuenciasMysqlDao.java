/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import javax.sql.DataSource;

/**
 *
 * @author user
 */
public interface SecuenciasMysqlDao {
    public Long secuenceTable(DataSource nameDataSource,String table);
    void updateSecuencialTabla(DataSource nameDataSource, String tabla, Long secuencia);
}
