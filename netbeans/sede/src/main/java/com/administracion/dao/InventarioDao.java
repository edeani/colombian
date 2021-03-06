/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.dto.FacturaVentaDTO;
import com.administracion.dto.InventarioDTO;
import com.administracion.dto.InventarioFinalDTO;
import com.administracion.dto.ItemsDTO;
import com.administracion.entidad.Inventario;
import java.util.List;
import javax.sql.DataSource;



/**
 *
 * @author user
 */
public interface InventarioDao extends GenericDao<Inventario>{
    
    public void actualizarPromedio(DataSource nameDataSource,Long idproducto,Double promedio);
    List<InventarioDTO> listInventarioDto(DataSource nameDataSource);
    void eliminarProducto(DataSource nameDataSource,Long idProducto);
    void insertarProducto(DataSource nameDataSource,InventarioDTO inventarioDTO);
    void actualizarProducto(DataSource nameDataSource,InventarioDTO inventarioDTO);
    InventarioDTO  traerProductoDto(DataSource nameDataSource,Long idProducto);
    Double promedioInventario(DataSource nameDataSource,Long idProducto,String fechaini,String fechafin);
    List<InventarioFinalDTO> inventarioFinal(DataSource nameDatasource, String fechaInicial, String fechaFinal);
    List<ItemsDTO> listaProductoOptions(DataSource nameDatasource);
    List<ItemsDTO> listaProductosLabel(DataSource nameDataSource);
    Inventario traerProducto(DataSource nameDatasource, Long idProducto);
    List<FacturaVentaDTO> traerProductosFactura(DataSource datasource, Long idFactura);
}
