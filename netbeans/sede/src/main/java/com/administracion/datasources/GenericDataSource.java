/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.datasources;

import com.administracion.dto.SubSedesDto;
import com.administracion.entidad.Sedes;
import javax.sql.DataSource;

/**
 *
 * @author EderArmando
 */
public interface GenericDataSource {
    public void updateGenericDataSource(Sedes sede);
    public void updateGenericDataSource(SubSedesDto sede);
    public DataSource getGenericDataSource();
    public DataSource getGenericDataSourceSubSede();
}
