/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombia.cali.colombiancaliycali.dao;

import com.colombian.cali.colombiancaliycali.dto.ReporteComprasTotalesProvDTO;
import com.colombian.cali.colombiancaliycali.dto.ReporteComprasTotalesXProveedorDTO;
import com.colombian.cali.colombiancaliycali.entidades.Proveedor;
import java.util.List;

/**
 *
 * @author Jose Efren
 */
public interface ProveedoresDao {
    public Proveedor getProveedor (String nameDatasource,Long idproveedor);
    public List<Proveedor> proveedores(String nameDatasource);
    public void guardarProveedor(String nameDatasource,Proveedor proveedor);
    public void actualizarProveedor(String nameDatasource,Proveedor proveedor);
    public void eliminarProveedor(String nameDatasource,Long idproveedor);
    
}
