/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.entidad.Sedes;

/**
 *
 * @author EderArmando
 */
public interface SedesService {
    public Sedes findSedeXId(Long idSede);
    public Sedes findSedeXName(String sede);
}
