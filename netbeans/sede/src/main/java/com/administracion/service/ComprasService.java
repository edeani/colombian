/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;


import com.administracion.dto.ComprasProveedorFechaDto;
import com.administracion.dto.ComprasTotalesDTO;
import com.administracion.dto.CuentasPagarProveedoresDto;
import com.administracion.dto.DetalleCompraDTO;
import com.administracion.dto.ItemsDTO;
import com.administracion.dto.ReporteComprasTotalesProvDTO;
import com.administracion.dto.ReporteComprasTotalesXProveedorDTO;
import com.administracion.entidad.Compras;
import com.administracion.entidad.Proveedor;
import java.util.List;

/**
 *
 * @author user
 */
public interface ComprasService {

    public List<ItemsDTO> listaProveedores(String nameDataSource);

    public Proveedor getProveedor(String nameDataSource, Long idproveedor);

    public void guardarCompra(String nameDataSource, DetalleCompraDTO detalleCompraDTO);

    public List<ComprasTotalesDTO> comprasTotales(String nameDataSource, String fechaInicial, String fechaFinal, String estadoCompra);

    public List<ComprasTotalesDTO> comprasTotalesProveedor(String nameDataSource, String fechaInicial, String fechaFinal, String estadoCompra, Long codigoProveedor);

    public List<ComprasTotalesDTO> getDetalleCompraDTO(String nameDataSource, Long idcompra,Integer codigoProveedor);

    public DetalleCompraDTO getCompraDTO(String nameDataSource, Long idcompra,Integer codigoProveedor);

    public void actualizarCompra(String nameDataSource, DetalleCompraDTO detalleCompraDTO);
    
    public void actualizarCompraCabecera(String nameDataSource, Long consecutivo);

    public List<ReporteComprasTotalesXProveedorDTO> comprasTotalesXProveedor(String nameDatasource, Long idproveedor, String fechaInicio, String fechaFin);

    public List<ReporteComprasTotalesProvDTO> comprasTotalesProveedores(String nameDatasource, String fechaInicio, String fechaFin);
    
    public List<Compras> comprasAVencer(String nameDataSource,int numeroDias,Long idProveedor);
    
    public List<ComprasProveedorFechaDto> reporteComprasProveedorFechaDto(String nameDataSource, String fechInicial, String fechaFinal);
    
    public List<CuentasPagarProveedoresDto> reporteCuentasPagarProveedoresDto(String nameDataSource, String fechaInicial, String fechaFinal, Long idProveedor);
    
    public Compras getCompraXIDproveedor(String  nameDataSource,Long idCompra,Integer codigopProveedor);
}
