/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dto.DetalleFacturaDTO;
import com.administracion.dto.DetalleFacturaTrasladoDTO;
import com.administracion.dto.FacturaAutocompletarDto;
import com.administracion.dto.FacturaReporteSedeDto;
import com.administracion.dto.FacturaTotalReporteDto;
import com.administracion.dto.FacturaVentaDTO;
import com.administracion.dto.TrasladosDto;
import com.administracion.dto.VentasTotalesDTO;
import com.administracion.entidad.Factura;
import com.administracion.entidad.Inventario;
import com.administracion.entidad.Sedes;
import java.util.Date;
import java.util.List;

/**
 *
 * @author edeani
 */

public interface FacturasService {
    
    public Inventario traerProducto(String datasource,Long idProducto);
    public List<FacturaVentaDTO> traerProductosFactura(String datasource,Long idFactura);
    public void guardarFactura(String nameDatasource, String nombreSede,DetalleFacturaDTO detalleFacturaDTO);
    public void actualizarFactura(String nameDatasource, String nombreSede,String estadoFactura,DetalleFacturaDTO detalleFacturaDTO);
    public List<VentasTotalesDTO> ventasTotales(String nameDataSource,String fechaInicial,String fechaFinal,String estadoCompra);
    public List<VentasTotalesDTO> ventasTotalesSede(String nameDataSource,String fechaInicial,String fechaFinal,String estadoCompra,Long idSede);
    public List<FacturaVentaDTO> detalleFacturaVenta(String nameDataSource,Long numeroFactura);
    public Factura findFactura(String nameDataSource,Long numeroFactura);
    public List<DetalleFacturaDTO> trasladarFactura(String nameDataSource, DetalleFacturaTrasladoDTO detalleFacturaTrasladoDTO);
    public void cambiarSedeFactura(String nameDataSource, DetalleFacturaDTO detalleFacturaDTO,String estadoFactura,Sedes sedeOrigen,Sedes sedeDestino);
    public String borrarFactura(String nameDataSource, Long numeroFactura);
    public List<FacturaReporteSedeDto> reporteFacturaCompraProveedor(String nameDataSource,Long idsede,String fechaInicio,String fechaFin);
    public List<FacturaTotalReporteDto> reporteFacturaCompra(String nameDataSource,String fechaInicio,String fechaFin);
    public List<TrasladosDto> reporteTraslados(String nameDataSource,Date fechaInicio,Date fechaFin);
    public List<FacturaAutocompletarDto> buscarFacturaAutocompletar(String nameDataSource, String numeroFactura, Long idproveedor);
}
