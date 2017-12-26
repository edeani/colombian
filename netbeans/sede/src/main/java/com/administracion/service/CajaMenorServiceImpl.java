/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.service;


import com.administracion.dao.CajaMenorDao;
import com.administracion.dao.ComprasDao;
import com.administracion.dto.DetallePagosProveedorDto;
import com.administracion.dto.DetallePagosTercerosDto;
import com.administracion.entidad.CajaMenor;
import com.administracion.entidad.Compras;
import com.administracion.entidad.DetalleCajaMenor;
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
public class CajaMenorServiceImpl implements CajaMenorService{

    @Autowired
    private CajaMenorDao cajaMenorDao;
    @Autowired
    private ComprasDao comprasDao;
    @Autowired
    private ConnectsAuth connectsAuth;
    
    private final String estado_aprobado_comprobante = "S";
    
    @Override
    @Transactional
    public void guardarPagosTercerosCajaMenor(String nameDataSource, CajaMenor pagos, List<DetalleCajaMenor> detallePagos) {
        DataSource ds = connectsAuth.getDataSourceSubSede(nameDataSource);
        cajaMenorDao.guardarPagosCajaMenor(ds, pagos);
        detallePagos.forEach((DetalleCajaMenor elementoDetallePagosTerceros) -> {
            cajaMenorDao.guardarDetallePagosTercerosCajaMenor(ds, elementoDetallePagosTerceros);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetallePagosTercerosDto> buscarDetallePagosTercerosCajaMenorDtos(String nameDataSource, Long idpago) {
        return cajaMenorDao.buscarDetallePagosTercerosCajaMenorDtos(connectsAuth.getDataSourceSubSede(nameDataSource), idpago);
    }

    @Override
    @Transactional(readOnly = true)
    public CajaMenor buscarPagoXIdPagoCajaMenor(String nameDataSource, Long idpago) {
        return cajaMenorDao.buscarPagoXIdPagoCajaMenor(connectsAuth.getDataSourceSubSede(nameDataSource), idpago);
    }

    @Override
    public void guardarPagosProveedorCajaMenor(String nameDataSource, CajaMenor pagosCajaMenor, List<DetalleCajaMenor> detalleCajaMenor) {
        DataSource ds = connectsAuth.getDataSourceSubSede(nameDataSource);
        cajaMenorDao.guardarPagosCajaMenor(ds, pagosCajaMenor);
        detalleCajaMenor.stream().map((elementoDetalleCajaMenor) -> {
            Compras compra = comprasDao.getCompra(elementoDetalleCajaMenor.getNumeroCompra(), ds);
            cajaMenorDao.guardarDetallePagosProveedorCajaMenor(ds, elementoDetalleCajaMenor);
            compra.setSaldo(compra.getSaldo() - elementoDetalleCajaMenor.getTotal());
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
    public List<DetallePagosProveedorDto> buscarDetallePagosDtos(String nameDataSource, Long idCajaMenor) {
        return cajaMenorDao.buscarDetallePagosProveedorCajaMenorDtos(connectsAuth.getDataSourceSubSede(nameDataSource), idCajaMenor);
    }
    
}
