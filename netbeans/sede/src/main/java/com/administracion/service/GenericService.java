/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.service.autorizacion.ConnectsAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author EderArmando
 */
@Service
public abstract class GenericService {
    @Autowired
    ConnectsAuth connectsAuth;
    
}
