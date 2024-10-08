/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombian.cali.colombiancaliycali.services;

import com.colombian.cali.colombiancaliycali.dto.CuentasAutoCompletarDto;
import com.colombian.cali.colombiancaliycali.dto.ItemsDTO;
import com.colombian.cali.colombiancaliycali.entidades.CuentasPuc;
import java.util.List;

/**
 *
 * @author Jose Efren
 */
public interface CuentasService {
    public CuentasPuc buscarCuenta(String idCuenta,String nameDataSource);
    public void actualizarCuenta(CuentasPuc cuentasPuc, String nameDataSource);
    public void guardarCuenta(CuentasPuc cuentasPuc, String nameDataSource);
    public List<CuentasAutoCompletarDto> autocompletarIdCuenta(String idCuenta,String nameDataSource);
    public List<ItemsDTO> cuentasBase(String nameDataSource);
}
