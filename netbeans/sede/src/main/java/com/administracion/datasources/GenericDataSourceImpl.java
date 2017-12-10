/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.datasources;

import com.administracion.dto.SubSedesDto;
import com.administracion.entidad.Sedes;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author EderArmando
 */
@Component
@Scope(value = "session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GenericDataSourceImpl implements GenericDataSource {
    
    private DriverManagerDataSource dataSourcePrincipal;
    private DriverManagerDataSource dataSource_;
    private DriverManagerDataSource dataSourceSub_;
    
    @Autowired
    public void initPrincipal(DriverManagerDataSource dataSource){
        this.dataSourcePrincipal = dataSource;
    }
    @Autowired
    public void init(DriverManagerDataSource dataSourceSede) {
        this.dataSource_ = dataSourceSede;
    }

    @Autowired
    public void initSubSede(DriverManagerDataSource dataSourceSubSede) {
        this.dataSourceSub_ = dataSourceSubSede;
    }

    @Override
    public void updateGenericDataSource(Sedes sede) {
        dataSource_.setPassword(sede.getPassword());
        dataSource_.setUrl(sede.getUrl());
        dataSource_.setUsername(sede.getUsername());
    }

    @Override
    public void updateGenericDataSource(SubSedesDto subSede) {
        dataSourceSub_.setPassword(subSede.getPassword());
        dataSourceSub_.setUrl(subSede.getUrl());
        dataSourceSub_.setUsername(subSede.getUsername());
    }

    @Override
    public DataSource getGenericDataSource() {
        return (DataSource) this.dataSource_;
    }

    @Override
    public DataSource getGenericDataSourceSubSede() {
        return (DataSource) this.dataSourceSub_;
    }

    @Override
    public DriverManagerDataSource getDataSourcePrincipal() {
        return this.dataSourcePrincipal;
    }
    
}
