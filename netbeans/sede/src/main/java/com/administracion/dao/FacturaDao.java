/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.dao;


import com.administracion.dto.DetalleCompraDTO;
import com.administracion.dto.DetalleFacturaDTO;
import com.administracion.dto.FacturaAutocompletarDto;
import com.administracion.dto.FacturaReporteSedeDto;
import com.administracion.dto.FacturaTotalReporteDto;
import com.administracion.dto.FacturaVentaDTO;
import com.administracion.dto.VentasTotalesDTO;
import com.administracion.entidad.Factura;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Jose Efren
 */
public interface FacturaDao {
    
    public void insertarFacturaNueva(DataSource nameDataSource, DetalleFacturaDTO detalleFacturaDTO, Long idsede, String estadoFactura);
    public void insertarDetalleSede(DataSource nameDataSource, DetalleFacturaDTO detalleFacturaDTO, Long idsede, String estadoFactura,Long idFactura);
    public void insertarFacturaSede(DataSource nameDataSource, DetalleFacturaDTO detalleFacturaDTO, Long idsede, String estadoFactura);
    public void insertarFacturaSubSede(DataSource nameDataSource, DetalleFacturaDTO detalleFacturaDTO, String estadoFactura,Long idFactura);
    public void insertarDetalleSubSede(DataSource nameDataSource, DetalleFacturaDTO detalleFacturaDTO, String estadoFactura,Long idFactura);
    public void insertarDetalleSede(DataSource nameDataSource, DetalleCompraDTO detalleCompraDTO, String estadoFactura, Long idFactura);
    public Long secuenciaDetalle(DataSource nameDataSource);
    public Factura findFactura(DataSource nameDataSource,Long idfactura);
    public List<FacturaReporteSedeDto> reporteTotalFacturaXSede(DataSource nameDataSource,String fechaInicio,String fechaFin,Long idsede);
    public List<FacturaTotalReporteDto> reporteTotalFactura(DataSource nameDataSource,String fechaInicio,String fechaFin);
    public void borrarFactura(DataSource nameDataSource, Long numeroFactura);
    public void borrarDetalleFactura(DataSource nameDataSource, Long numeroFactura);
    public void actualizarSedeFactura(DataSource nameDataSource, DetalleFacturaDTO detalleFacturaDTO, String estadoFactura);
    public void actualizarSedeDetalleFactura(DataSource nameDataSource, DetalleFacturaDTO detalleFacturaDTO, String estadoFactura);
    public List<FacturaAutocompletarDto> buscarFacturaAutocompletar(DataSource nameDataSource,String numeroFactura,Long idproveedor);
    List<VentasTotalesDTO> ventasTotales(DataSource nameDataSource, String fechaInicial, String fechaFinal, String estadoCompra);
    List<VentasTotalesDTO> ventasTotalesSede(DataSource nameDataSource, String fechaInicial, String fechaFinal, String estadoCompra, Long idSede);
    List<FacturaVentaDTO> detalleFacturaVenta(DataSource nameDataSource, Long numeroFactura);
    Factura traaerFactura(DataSource nameDataSource, Long numeroFactura);
}
