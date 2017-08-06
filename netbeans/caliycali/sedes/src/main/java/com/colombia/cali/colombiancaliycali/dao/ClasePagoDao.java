/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.dao;

import com.colombian.cali.colombiancaliycali.entidades.ClasePago;

/**
 *
 * @author EderArmando
 */

public interface ClasePagoDao {
    public ClasePago findClasePagoById(Integer idClasePago,String dataSource);
}
