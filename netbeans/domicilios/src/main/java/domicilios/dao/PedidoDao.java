/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.dao;

import domicilios.dto.PedidoViewDto;
import domicilios.entidad.Pedido;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author user
 */
public interface PedidoDao extends GenericDao<Pedido>{
    List<PedidoViewDto> findAllPageSql(Integer first, Integer cantidad, HashMap<String, Object> parametros);
}
