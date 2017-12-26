/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;


import com.administracion.dao.ComprasDao;
import com.administracion.dao.PagosDao;
import com.administracion.dto.DetallePagosProveedorDto;
import com.administracion.dto.DetallePagosTercerosDto;
import com.administracion.dto.PagosCabeceraDto;
import com.administracion.dto.ReportePagosDto;
import com.administracion.entidad.Compras;
import com.administracion.entidad.DetallePagos;
import com.administracion.entidad.Pagos;
import com.administracion.service.autorizacion.ConnectsAuth;
import java.util.List;
import javax.sql.DataSource;
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
    @Autowired
    private ConnectsAuth connectsAuth;
    private  final String estado_aprobado_comprobante = "S";

    @Override
    @Transactional(readOnly = true)
    public Long secuenciaPagos(String nameDataSource) {
        return pagosDao.secuenciaPagos(connectsAuth.getDataSourceSubSede(nameDataSource));
    }

    @Override
    @Transactional
    public void guardarPagosTerceros(String nameDataSource, Pagos pagosTerceros, List<DetallePagos> detallePagosTerceros) {
        DataSource ds = connectsAuth.getDataSourceSubSede(nameDataSource);
        pagosDao.guardarPagos(ds, pagosTerceros);
        detallePagosTerceros.forEach((elementoDetallePagosTerceros) -> {
            pagosDao.guardarDetallePagosTerceros(ds, elementoDetallePagosTerceros);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetallePagosTercerosDto> buscarDetallePagosTercerosDtos(String nameDataSource, Long idpagotercero) {
        return pagosDao.buscarDetallePagosTercerosDtos(connectsAuth.getDataSourceSubSede(nameDataSource), idpagotercero);
    }

    @Override
    @Transactional(readOnly = true)
    public Pagos buscarPagoXIdPago(String nameDataSource, Long idpagotercero) {
        return pagosDao.buscarPagoXIdPago(connectsAuth.getDataSourceSubSede(nameDataSource), idpagotercero);
    }

    @Override
    @Transactional
    public void guardarPagosProveedor(String nameDataSource, Pagos pagosProveedor, List<DetallePagos> detallePagosProveedor) {
        DataSource ds =connectsAuth.getDataSourceSubSede(nameDataSource);
        pagosDao.guardarPagos(ds, pagosProveedor);
        detallePagosProveedor.stream().map((elementoDetallePagosProveedor) -> {
            Compras compra = comprasDao.getCompra(elementoDetallePagosProveedor.getNumeroCompra(), ds);
            pagosDao.guardarDetallePagosProveedor(ds, elementoDetallePagosProveedor);
            compra.setSaldo(compra.getSaldo() - elementoDetallePagosProveedor.getTotal());
            return compra;
        }).map((compra) -> {
            if (compra.getSaldo().intValue() == 0) {
                compra.setEstadoCompraProveedor(estado_aprobado_comprobante);
            }
            return compra;
        }).forEachOrdered((compra) -> {
            comprasDao.actualizarCompra(ds, compra);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetallePagosProveedorDto> buscarDetallePagosDtos(String nameDataSource, Long idpagoproveedor) {
        return pagosDao.buscarDetallePagosDtos(connectsAuth.getDataSourceSubSede(nameDataSource), idpagoproveedor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PagosCabeceraDto> buscarPagosProveedorXFecha(String nameDataSource, String fecha) {
        return pagosDao.buscarPagosXFecha(connectsAuth.getDataSourceSubSede(nameDataSource), fecha);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReportePagosDto> reportePagos(String nameDataSource, String fechaInicial, String fechaFinal, Long idsede) {
        return pagosDao.reportePagos(connectsAuth.getDataSourceSubSede(nameDataSource), fechaInicial,fechaFinal,idsede);
    }
    
}
