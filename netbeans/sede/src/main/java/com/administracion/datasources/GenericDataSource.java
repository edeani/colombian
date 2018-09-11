/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.datasources;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author EderArmando
 */
public interface GenericDataSource {
    public DriverManagerDataSource getDataSourcePrincipal();
}
