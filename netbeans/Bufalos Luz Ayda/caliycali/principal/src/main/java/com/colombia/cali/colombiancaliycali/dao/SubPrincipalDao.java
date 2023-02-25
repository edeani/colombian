/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.dao;

import com.colombian.cali.colombiancaliycali.entidades.Subprincipal;

/**
 *
 * @author EderArmando
 */
public interface SubPrincipalDao {
    
    Subprincipal findSubPrincipalByIdsede(String nameDatasource,Integer idsede);
}
