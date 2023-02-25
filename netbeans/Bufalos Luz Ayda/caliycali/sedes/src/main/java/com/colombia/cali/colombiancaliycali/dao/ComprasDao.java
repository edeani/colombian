/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombia.cali.colombiancaliycali.dao;

import com.colombian.cali.colombiancaliycali.dto.ComprasTotalesDTO;
import com.colombian.cali.colombiancaliycali.dto.DetalleCompraDTO;
import com.colombian.cali.colombiancaliycali.dto.ReporteComprasTotalesProvDTO;
import com.colombian.cali.colombiancaliycali.dto.ReporteComprasTotalesXProveedorDTO;
import com.colombian.cali.colombiancaliycali.entidades.Compras;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jose Efren
 */
public interface ComprasDao {
    
    public Compras getCompra(Long idcompra,String nameDatasource);
    public List<ComprasTotalesDTO> getDetalleCompraDTO(Long idcompra,String nameDatasource);    
    public void borrarDetalleCompra(Long idcompra,String nameDatasource);
    public void borrarCompra(Long idcompra,String nameDatasource);
    public void insertarCompra(String nameDataSource, DetalleCompraDTO detalleCompraDTO);
    public void insertarCompraSede(String nameDataSource, DetalleCompraDTO detalleCompraDTO);
    public void insertarDetalleCompra(String nameDataSource,int i, String numeroFactura, String[] datosFila, String codigoProveedor,Date fechacompra);
    public List<ReporteComprasTotalesXProveedorDTO> comprasTotalesXProveedor(String nameDatasource, Long idproveedor, String fechaInicio,String fechaFin);
    public List<ReporteComprasTotalesProvDTO> comprasTotalesProveedores(String nameDatasource, String fechaInicio,String fechaFin);
    public Long totalComprasPollo(String nameDatasource,String fechaInicio,String fechaFin);
    public void actualizarCompra(String nameDataSource,Compras compras);
    public List<Compras> comprasAVencer(String nameDataSource,int numeroDias,Long idProveedor);
}
