/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dto.PedidoClienteDto;
import com.administracion.dto.PedidoDto;
import com.administracion.entidad.Usuario;
import java.util.List;

/**
 *
 * @author user
 */
public interface PedidoService {
    void guardarPedido(PedidoClienteDto pedidoClienteDto,Usuario usuario);
    List<PedidoDto> findPedidosXPage(Integer page,Integer cantidad,String fechaInicial,String fechaFinal);
    void updateEstado(Long idpedido,String estado);
}
