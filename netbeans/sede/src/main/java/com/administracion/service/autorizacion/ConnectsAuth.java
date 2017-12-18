/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.autorizacion;

import com.administracion.dto.SedesDto;
import com.administracion.service.SedesService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

/**
 *
 * @author EderArmando
 */
@Component
public class ConnectsAuth{
    
    @Autowired
    private SedesService sedesService;
    
    private List<SedesDto> sedesConnect;
    
    private DriverManagerDataSource dataSource_;
    private DriverManagerDataSource dataSourceSub_;
    
    
    @PostConstruct
    private void loadConnectsAut(){
        sedesConnect = sedesService.traerSedesDtos();
    }

    public List<SedesDto> getSedesConnect() {
        return sedesConnect;
    }

    public void setSedesConnect(List<SedesDto> sedesConnect) {
        this.sedesConnect = sedesConnect;
    }
    
    
}
