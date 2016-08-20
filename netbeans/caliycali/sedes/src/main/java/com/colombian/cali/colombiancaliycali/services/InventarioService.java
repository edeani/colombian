/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services;

import com.colombian.cali.colombiancaliycali.dto.InventarioDTO;
import com.colombian.cali.colombiancaliycali.dto.InventarioFinalDTO;
import com.colombian.cali.colombiancaliycali.dto.ItemsDTO;
import com.colombian.cali.colombiancaliycali.dto.ReporteInventarioDTO;
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
}