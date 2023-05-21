/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;


import com.administracion.dto.InventarioDTO;
import com.administracion.dto.InventarioFinalDTO;
import com.administracion.dto.ItemsDTO;
import com.administracion.dto.ReporteInventarioDTO;
import java.util.List;

/**
 *
 * @author edeani
 */
public interface InventarioService {
    
    public List<ReporteInventarioDTO> reporteInventario(String nameDataSource,String fecha);
    public List<InventarioDTO> reporteInventario(String nameDataSource);
    public void eliminarProducto(String nameDataSource,Long idProducto);
    public void insertarProducto(String nameDataSource,InventarioDTO inventarioDTO);
    public void actualizarProducto(String nameDataSource,InventarioDTO inventarioDTO);
    public InventarioDTO traerProducto(String nameDatasource,Long idProducto);
    public boolean actualizarPromedioInventario(String nameDatasource,Long idProducto,Double promedio);
    public Double calcularPromedioInventario(String nameDatasource, Long idProducto,String fechaini,String fechafin);
    public List<InventarioFinalDTO> reporteInventarioFinal(String nameDatasource,String fechaInicial,String fechaFinal);
    public List<ItemsDTO> listaProductoOptions(String nameDatasource);
    public List<ItemsDTO> listaProductosLabel(String nameDatasource);
    
    public InventarioDTO traerProductoSubSede(String nameDatasource, Long idProducto);
    public List<InventarioDTO> reporteInventarioSubSede(String nameDataSource);
    public void eliminarProductoSubSede(String nameDataSource,Long idProducto);
    public void insertarProductoSubSede(String nameDataSource,InventarioDTO inventarioDTO);
    public void actualizarProductoSubSede(String nameDataSource,InventarioDTO inventarioDTO);
}