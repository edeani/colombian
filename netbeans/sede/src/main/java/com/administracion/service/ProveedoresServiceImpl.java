/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dao.ProveedoresDao;
import com.administracion.entidad.Proveedor;
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
public class ProveedoresServiceImpl implements ProveedoresService {

    @Autowired
    private ProveedoresDao proveedoresDao;
    @Autowired
    private ConnectsAuth connectsAuth;

    @Override
    @Transactional(readOnly = true)
    public List<Proveedor> proveedores(String nameDatasource) {
        return proveedoresDao.proveedores(connectsAuth.getDataSourceSubSede(nameDatasource));
    }

    @Override
    @Transactional(readOnly = true)
    public Proveedor proveedor(String nameDatasource, Long idproveedor) {
        return proveedoresDao.getProveedor(connectsAuth.getDataSourceSubSede(nameDatasource), idproveedor);
    }

    @Override
    @Transactional
    public void guardarProveedor(String nameDatasource, Proveedor proveedor) {
        proveedoresDao.guardarProveedor(connectsAuth.getDataSourceSubSede(nameDatasource), proveedor);
    }

    @Override
    @Transactional
    public void actualizarProveedor(String nameDatasource, Proveedor proveedor) {
        proveedoresDao.actualizarProveedor(connectsAuth.getDataSourceSubSede(nameDatasource), proveedor);
    }

    @Override
    @Transactional
    public void eliminarProveedor(String nameDatasource, Long idproveedor) {
        proveedoresDao.eliminarProveedor(connectsAuth.getDataSourceSubSede(nameDatasource), idproveedor);
    }
}
