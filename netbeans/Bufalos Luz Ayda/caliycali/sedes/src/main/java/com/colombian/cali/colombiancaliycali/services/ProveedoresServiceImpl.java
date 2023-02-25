/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services;

import com.colombia.cali.colombiancaliycali.dao.ProveedoresDao;
import com.colombian.cali.colombiancaliycali.dto.ReporteComprasTotalesProvDTO;
import com.colombian.cali.colombiancaliycali.dto.ReporteComprasTotalesXProveedorDTO;
import com.colombian.cali.colombiancaliycali.entidades.Proveedor;
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

    @Override
    @Transactional(readOnly = true)
    public List<Proveedor> proveedores(String nameDatasource) {
        return proveedoresDao.proveedores(nameDatasource);
    }

    @Override
    @Transactional(readOnly = true)
    public Proveedor proveedor(String nameDatasource, Long idproveedor) {
        return proveedoresDao.getProveedor(nameDatasource, idproveedor);
    }

    @Override
    @Transactional
    public void guardarProveedor(String nameDatasource, Proveedor proveedor) {
        proveedoresDao.guardarProveedor(nameDatasource, proveedor);
    }

    @Override
    @Transactional
    public void actualizarProveedor(String nameDatasource, Proveedor proveedor) {
        proveedoresDao.actualizarProveedor(nameDatasource, proveedor);
    }

    @Override
    @Transactional
    public void eliminarProveedor(String nameDatasource, Long idproveedor) {
        proveedoresDao.eliminarProveedor(nameDatasource, idproveedor);
    }
}
