/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombian.cali.colombiancaliycali.services;

import com.colombia.cali.colombiancaliycali.dao.CuentasPucDao;
import com.colombian.cali.colombiancaliycali.dto.CuentasAutoCompletarDto;
import com.colombian.cali.colombiancaliycali.dto.ItemsDTO;
import com.colombian.cali.colombiancaliycali.entidades.CuentasPuc;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jose Efren
 */
@Service
public class CuentasServiceImpl implements CuentasService{

    @Autowired
    private CuentasPucDao cuentasPucDao;
    
    @Override
    @Transactional(readOnly = true)
    public CuentasPuc buscarCuenta(String idCuenta, String nameDataSource) {
        CuentasPuc cuentasPuc = cuentasPucDao.consultarCuentaPuc(idCuenta, nameDataSource);
        return cuentasPuc;
    }

    @Override
    @Transactional
    public void actualizarCuenta(CuentasPuc cuentasPuc, String nameDataSource) {
        cuentasPucDao.actualizarCuentaPuc(cuentasPuc, nameDataSource);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CuentasAutoCompletarDto> autocompletarIdCuenta(String idCuenta, String nameDataSource) {
        return cuentasPucDao.buscarCuentaLikeId(idCuenta, nameDataSource);
    }

    @Override
    @Transactional
    public void guardarCuenta(CuentasPuc cuentasPuc, String nameDataSource) {
        cuentasPucDao.insertarCuentaPuc(cuentasPuc, nameDataSource);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemsDTO> cuentasBase(String nameDataSource) {
        return cuentasPucDao.cuentasBase(nameDataSource);
    }
    
}
