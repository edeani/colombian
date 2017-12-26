/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.service;


import com.administracion.dao.CuentasPucDao;
import com.administracion.dto.CuentasAutoCompletarDto;
import com.administracion.dto.ItemsDTO;
import com.administracion.entidad.CuentasPuc;
import com.administracion.service.autorizacion.ConnectsAuth;
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
    
    @Autowired
    private  ConnectsAuth connectsAuth;
    
    @Override
    @Transactional(readOnly = true)
    public CuentasPuc buscarCuenta(String idCuenta, String nameDataSource) {
        CuentasPuc cuentasPuc = cuentasPucDao.consultarCuentaPuc(idCuenta, connectsAuth.getDataSourceSubSede(nameDataSource));
        return cuentasPuc;
    }

    @Override
    @Transactional
    public void actualizarCuenta(CuentasPuc cuentasPuc, String nameDataSource) {
        cuentasPucDao.actualizarCuentaPuc(cuentasPuc,connectsAuth.getDataSourceSubSede(nameDataSource));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CuentasAutoCompletarDto> autocompletarIdCuenta(String idCuenta, String nameDataSource) {
        return cuentasPucDao.buscarCuentaLikeId(idCuenta,connectsAuth.getDataSourceSubSede(nameDataSource));
    }

    @Override
    @Transactional
    public void guardarCuenta(CuentasPuc cuentasPuc, String nameDataSource) {
        cuentasPucDao.insertarCuentaPuc(cuentasPuc,connectsAuth.getDataSourceSubSede(nameDataSource));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemsDTO> cuentasBase(String nameDataSource) {
        return cuentasPucDao.cuentasBase(connectsAuth.getDataSourceSubSede(nameDataSource));
    }
    
}
