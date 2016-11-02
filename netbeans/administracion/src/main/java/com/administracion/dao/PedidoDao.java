/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.dto.PedidoDto;
import com.administracion.entidad.Pedido;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author user
 */
public interface PedidoDao extends GenericDao<Pedido>{
    List<PedidoDto> findAllPageSql(Integer first, Integer cantidad, HashMap<String, Object> parametros);
    void updateEstado(Long idpedido,String estado);
}
