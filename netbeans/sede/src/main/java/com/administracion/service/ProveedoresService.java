/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.service;


import com.administracion.entidad.Proveedor;
import java.util.List;

/**
 *
 * @author Jose Efren
 */
public interface ProveedoresService {
    public Proveedor proveedor(String nameDatasource,Long idproveedor);
    public List<Proveedor> proveedores(String nameDatasource);
    public void guardarProveedor(String nameDatasource,Proveedor proveedor);
    public void actualizarProveedor(String nameDatasource,Proveedor proveedor);
    public void eliminarProveedor(String nameDatasource,Long idproveedor);
}
