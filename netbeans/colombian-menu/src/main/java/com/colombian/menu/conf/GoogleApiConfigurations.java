/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.menu.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author 10 Spring Creators
 */
@Component
@ConfigurationProperties(prefix = "sheet")
public class GoogleApiConfigurations {
    
    private String idColombianmenu;

    public String getIdColombianmenu() {
        return idColombianmenu;
    }

    public void setIdColombianmenu(String idColombianmenu) {
        this.idColombianmenu = idColombianmenu;
    }
    
    
}
