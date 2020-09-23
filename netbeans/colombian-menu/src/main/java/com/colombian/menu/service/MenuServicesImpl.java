/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.menu.service;

import com.colombian.menu.conf.GoogleApiConfigurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 10 Spring Creators
 */
@Service
public class MenuServicesImpl implements MenuService{
    
    @Autowired
    private GoogleApiConfigurations googleApiConfigurations;
    
    
}
