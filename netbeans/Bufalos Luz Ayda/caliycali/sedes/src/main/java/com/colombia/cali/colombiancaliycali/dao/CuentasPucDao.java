/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombia.cali.colombiancaliycali.dao;

import com.colombian.cali.colombiancaliycali.dto.CuentasAutoCompletarDto;
import com.colombian.cali.colombiancaliycali.dto.ItemsDTO;
import com.colombian.cali.colombiancaliycali.entidades.CuentasPuc;
import java.util.List;

/**
 *
 * @author Jose Efren
 */
public interface CuentasPucDao {
    public void actualizarCuentaPuc(CuentasPuc cuentasPuc,String nameDataSource);
    public CuentasPuc consultarCuentaPuc(String idCuenta,String nameDataSource);
    public List<CuentasAutoCompletarDto> buscarCuentaLikeId(String idCuenta,String nameDataSource);
    public void insertarCuentaPuc(CuentasPuc cuentasPuc,String nameDataSource);
    public List<ItemsDTO> cuentasBase(String nameDataSource);
}
