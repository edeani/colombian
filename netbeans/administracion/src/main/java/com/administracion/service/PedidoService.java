/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dto.PedidoClienteDto;
import com.administracion.entidad.Usuario;

/**
 *
 * @author user
 */
public interface PedidoService {
    void guardarPedido(PedidoClienteDto pedidoClienteDto,Usuario usuario);
}
