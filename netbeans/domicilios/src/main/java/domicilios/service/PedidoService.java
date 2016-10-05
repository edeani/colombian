/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.service;

import domicilios.dto.PedidoClienteDto;
import domicilios.entidad.Usuario;

/**
 *
 * @author user
 */
public interface PedidoService {
    void guardarPedido(PedidoClienteDto pedidoClienteDto,Usuario usuario);
}
