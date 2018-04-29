/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.dao;


import com.administracion.dto.ItemsDTO;
import com.administracion.entidad.Proveedor;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Jose Efren
 */
public interface ProveedoresDao {
    public Proveedor getProveedor (DataSource nameDataSource,Long idproveedor);
    public List<Proveedor> proveedores(DataSource nameDataSource);
    public List<ItemsDTO> proveedoresItems(DataSource nameDataSource);
    public void guardarProveedor(DataSource nameDataSource,Proveedor proveedor);
    public void actualizarProveedor(DataSource nameDataSource,Proveedor proveedor);
    public void eliminarProveedor(DataSource nameDataSource,Long idproveedor);
    
}
