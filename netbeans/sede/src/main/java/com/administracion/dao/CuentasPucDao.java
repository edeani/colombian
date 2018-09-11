/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.dao;


import com.administracion.dto.CuentasAutoCompletarDto;
import com.administracion.dto.ItemsDTO;
import com.administracion.entidad.CuentasPuc;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Jose Efren
 */
public interface CuentasPucDao {
    public void actualizarCuentaPuc(CuentasPuc cuentasPuc,DataSource nameDataSource);
    public CuentasPuc consultarCuentaPuc(String idCuenta,DataSource nameDataSource);
    public List<CuentasAutoCompletarDto> buscarCuentaLikeId(String idCuenta,DataSource nameDataSource);
    public void insertarCuentaPuc(CuentasPuc cuentasPuc,DataSource nameDataSource);
    public List<ItemsDTO> cuentasBase(DataSource nameDataSource);
}
