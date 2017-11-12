/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.entidad.Detallepedido;

/**
 *
 * @author user
 */
public interface DetallePedidoDao extends GenericDao<Detallepedido>{
    void deleteDetallePedidoSql(Long idpedido);
}
