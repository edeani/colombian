/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dto.SubSedesDto;

/**
 *
 * @author EderArmando
 */
public interface UserSubSedeService {
    public SubSedesDto findSedeDtoXUser(Long cedula);
}
