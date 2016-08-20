/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services;

import com.colombian.cali.colombiancaliycali.dto.ComprasProveedorFechaDto;
import com.colombian.cali.colombiancaliycali.dto.ComprasTotalesDTO;
import com.colombian.cali.colombiancaliycali.dto.CuentasPagarProveedoresDto;
import com.colombian.cali.colombiancaliycali.dto.DetalleCompraDTO;
import com.colombian.cali.colombiancaliycali.dto.ItemsDTO;
import com.colombian.cali.colombiancaliycali.dto.ReporteComprasTotalesProvDTO;
import com.colombian.cali.colombiancaliycali.dto.ReporteComprasTotalesXProveedorDTO;
import com.colombian.cali.colombiancaliycali.entidades.Compras;
import com.colombian.cali.colombiancaliycali.entidades.Proveedor;
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

    public List<ComprasTotalesDTO> getDetalleCompraDTO(String nameDataSource, Long idcompra);

    public DetalleCompraDTO getCompraDTO(String nameDataSource, Long idcompra);

    public void actualizarCompra(String nameDataSource, DetalleCompraDTO detalleCompraDTO);

    public List<ReporteComprasTotalesXProveedorDTO> comprasTotalesXProveedor(String nameDatasource, Long idproveedor, String fechaInicio, String fechaFin);

    public List<ReporteComprasTotalesProvDTO> comprasTotalesProveedores(String nameDatasource, String fechaInicio, String fechaFin);
    
    public void actualizarFactura(String nameDataSource,Compras compras);
    
    public List<Compras> comprasAVencer(String nameDataSource,int numeroDias,Long idProveedor);
    
    public List<ComprasProveedorFechaDto> reporteComprasProveedorFechaDto(String nameDataSource, String fechInicial, String fechaFinal);
    
    public List<CuentasPagarProveedoresDto> reporteCuentasPagarProveedoresDto(String nameDataSource, String fechaInicial, String fechaFinal, Long idProveedor);
}
