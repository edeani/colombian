/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombia.cali.colombiancaliycali.dao;

import com.colombian.cali.colombiancaliycali.dto.DetalleCompraDTO;
import com.colombian.cali.colombiancaliycali.dto.DetalleFacturaDTO;
import com.colombian.cali.colombiancaliycali.dto.FacturaAutocompletarDto;
import com.colombian.cali.colombiancaliycali.dto.FacturaTotalReporteDto;
import com.colombian.cali.colombiancaliycali.dto.FacturaReporteSedeDto;
import com.colombian.cali.colombiancaliycali.entidades.Factura;
import java.util.List;

/**
 *
 * @author Jose Efren
 */
public interface FacturaDao {
    
    public void insertarFacturaNueva(String nameDatasource, DetalleFacturaDTO detalleFacturaDTO, Long idsede, String estadoFactura);
    public void insertarDetalle(String nameDatasource, DetalleFacturaDTO detalleFacturaDTO, Long idsede, String estadoFactura,Long idFactura);
    public void insertarFactura(String nameDatasource, DetalleFacturaDTO detalleFacturaDTO, Long idsede, String estadoFactura);
    public void insertarFacturaSede(String nameDatasource, DetalleFacturaDTO detalleFacturaDTO, String estadoFactura,Long idFactura);
    public void insertarDetalleSede(String nameDatasource, DetalleFacturaDTO detalleFacturaDTO, String estadoFactura,Long idFactura);
    public void insertarDetalleSede(String nameDatasource, DetalleCompraDTO detalleCompraDTO, String estadoFactura, Long idFactura);
    public Long secuenciaDetalle(String nameDatasource);
    public Factura findFactura(String nameDatasource,Long idfactura);
    public List<FacturaReporteSedeDto> reporteTotalFacturaXSede(String nameDatasource,String fechaInicio,String fechaFin,Long idsede);
    public List<FacturaTotalReporteDto> reporteTotalFactura(String nameDatasource,String fechaInicio,String fechaFin);
    public void borrarFactura(String nameDataSource, Long numeroFactura);
    public void borrarDetalleFactura(String nameDataSource, Long numeroFactura);
    public void actualizarSedeFactura(String nameDataSource, DetalleFacturaDTO detalleFacturaDTO, String estadoFactura);
    public void actualizarSedeDetalleFactura(String nameDataSource, DetalleFacturaDTO detalleFacturaDTO, String estadoFactura);
    public List<FacturaAutocompletarDto> buscarFacturaAutocompletar(String nameDataSource,String numeroFactura,Long idproveedor);
}
