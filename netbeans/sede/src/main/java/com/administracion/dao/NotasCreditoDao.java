/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.administracion.dao;

import com.administracion.dto.NotasDetalleDto;
import com.administracion.dto.NotasDto;
import com.administracion.entidad.NotasCredito;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Anlod
 */
public interface NotasCreditoDao extends GenericDao<NotasCredito>{
    public void guardarNotaCreditoDetalle(DataSource dataSource, NotasDto notasCredito);
    public List<NotasDetalleDto> consultarDetalleNotaCredito(DataSource dataSource, int idComprobante);
}
