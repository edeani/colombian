/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.dao;


import com.administracion.dto.ComprasTotalesDTO;
import com.administracion.dto.DetalleCompraDTO;
import com.administracion.dto.ReporteComprasTotalesProvDTO;
import com.administracion.dto.ReporteComprasTotalesXProveedorDTO;
import com.administracion.entidad.Compras;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Jose Efren
 */
public interface ComprasDao {
    
    public Compras getCompra(Long idcompra,DataSource nameDataSource);
    public List<ComprasTotalesDTO> getDetalleCompraDTO(Long idcompra,DataSource nameDataSource);    
    public void borrarDetalleCompra(Long idcompra,DataSource nameDataSource);
    public void borrarCompra(Long idcompra,DataSource nameDataSource);
    public void insertarCompra(DataSource nameDataSource, DetalleCompraDTO detalleCompraDTO);
    public void insertarCompraSede(DataSource nameDataSource, DetalleCompraDTO detalleCompraDTO);
    public void insertarDetalleCompra(DataSource nameDataSource,int i, String numeroFactura, String[] datosFila, String codigoProveedor,Date fechacompra);
    public List<ReporteComprasTotalesXProveedorDTO> comprasTotalesXProveedor(DataSource nameDataSource, Long idproveedor, String fechaInicio,String fechaFin);
    public List<ReporteComprasTotalesProvDTO> comprasTotalesProveedores(DataSource nameDataSource, String fechaInicio,String fechaFin);
    public Long totalComprasPollo(DataSource nameDataSource,String fechaInicio,String fechaFin);
    public void actualizarCompra(DataSource nameDataSource,Compras compras);
    public List<Compras> comprasAVencer(DataSource nameDataSource,int numeroDias,Long idProveedor);
}