/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services;

import com.colombia.cali.colombiancaliycali.dao.ComprasDao;
import com.colombia.cali.colombiancaliycali.dao.PagosDao;
import com.colombian.cali.colombiancaliycali.dto.DetallePagosProveedorDto;
import com.colombian.cali.colombiancaliycali.dto.DetallePagosTercerosDto;
import com.colombian.cali.colombiancaliycali.dto.PagosCabeceraDto;
import com.colombian.cali.colombiancaliycali.dto.ReportePagosDto;
import com.colombian.cali.colombiancaliycali.entidades.Compras;
import com.colombian.cali.colombiancaliycali.entidades.DetallePagos;
import com.colombian.cali.colombiancaliycali.entidades.Pagos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jose Efren
 */
@Service
public class PagosServiceImpl implements PagosService {

    @Autowired
    private PagosDao pagosDao;
    @Autowired
    private ComprasDao comprasDao;
    private static final String estado_aprobado_comprobante = "S";

    @Override
    @Transactional(readOnly = true)
    public Long secuenciaPagos(String nameDataSource) {
        return pagosDao.secuenciaPagos(nameDataSource);
    }

    @Override
    @Transactional
    public void guardarPagosTerceros(String nameDataSource, Pagos pagosTerceros, List<DetallePagos> detallePagosTerceros) {
        pagosDao.guardarPagos(nameDataSource, pagosTerceros);
        for (DetallePagos elementoDetallePagosTerceros : detallePagosTerceros) {
            pagosDao.guardarDetallePagosTerceros(nameDataSource, elementoDetallePagosTerceros);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetallePagosTercerosDto> buscarDetallePagosTercerosDtos(String nameDataSource, Long idpagotercero) {
        return pagosDao.buscarDetallePagosTercerosDtos(nameDataSource, idpagotercero);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagos buscarPagoXIdPago(String nameDataSource, Long idpagotercero) {
        return pagosDao.buscarPagoXIdPago(nameDataSource, idpagotercero);
    }

    @Override
    @Transactional
    public void guardarPagosProveedor(String nameDataSource, Pagos pagosProveedor, List<DetallePagos> detallePagosProveedor) {

        pagosDao.guardarPagos(nameDataSource, pagosProveedor);
        for (DetallePagos elementoDetallePagosProveedor : detallePagosProveedor) {
            Compras compra = comprasDao.getCompra(elementoDetallePagosProveedor.getNumeroCompra(), nameDataSource);
            pagosDao.guardarDetallePagosProveedor(nameDataSource, elementoDetallePagosProveedor);
            compra.setSaldo(compra.getSaldo() - elementoDetallePagosProveedor.getTotal());
            if (compra.getSaldo().intValue() == 0) {
                compra.setEstadoCompraProveedor(estado_aprobado_comprobante);
            }
            comprasDao.actualizarCompra(nameDataSource, compra);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetallePagosProveedorDto> buscarDetallePagosDtos(String nameDataSource, Long idpagoproveedor) {
        return pagosDao.buscarDetallePagosDtos(nameDataSource, idpagoproveedor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PagosCabeceraDto> buscarPagosProveedorXFecha(String nameDataSource, String fecha) {
        return pagosDao.buscarPagosXFecha(nameDataSource, fecha);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReportePagosDto> reportePagos(String nameDataSource, String fechaInicial, String fechaFinal, Long idsede) {
        return pagosDao.reportePagos(nameDataSource, fechaInicial,fechaFinal,idsede);
    }
    
}
