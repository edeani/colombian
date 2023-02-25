/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services;

import com.colombian.cali.colombiancaliycali.dto.DetalleFacturaDTO;
import com.colombian.cali.colombiancaliycali.dto.DetalleFacturaTrasladoDTO;
import com.colombian.cali.colombiancaliycali.dto.FacturaAutocompletarDto;
import com.colombian.cali.colombiancaliycali.dto.FacturaReporteSedeDto;
import com.colombian.cali.colombiancaliycali.dto.FacturaTotalReporteDto;
import com.colombian.cali.colombiancaliycali.dto.FacturaVentaDTO;
import com.colombian.cali.colombiancaliycali.dto.TrasladosDto;
import com.colombian.cali.colombiancaliycali.dto.VentasTotalesDTO;
import com.colombian.cali.colombiancaliycali.entidades.Factura;
import com.colombian.cali.colombiancaliycali.entidades.Inventario;
import com.colombian.cali.colombiancaliycali.entidades.Sedes;
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
