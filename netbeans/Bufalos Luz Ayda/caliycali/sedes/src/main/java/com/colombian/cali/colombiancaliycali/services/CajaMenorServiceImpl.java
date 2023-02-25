/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombian.cali.colombiancaliycali.services;

import com.colombia.cali.colombiancaliycali.dao.CajaMenorDao;
import com.colombia.cali.colombiancaliycali.dao.ComprasDao;
import com.colombian.cali.colombiancaliycali.dto.DetallePagosProveedorDto;
import com.colombian.cali.colombiancaliycali.dto.DetallePagosTercerosDto;
import com.colombian.cali.colombiancaliycali.entidades.CajaMenor;
import com.colombian.cali.colombiancaliycali.entidades.Compras;
import com.colombian.cali.colombiancaliycali.entidades.DetalleCajaMenor;
import java.util.List;
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
    private static final String estado_aprobado_comprobante = "S";
    
    @Override
    @Transactional
    public void guardarPagosTercerosCajaMenor(String nameDataSource, CajaMenor pagos, List<DetalleCajaMenor> detallePagos) {
        cajaMenorDao.guardarPagosCajaMenor(nameDataSource, pagos);
        for (DetalleCajaMenor elementoDetallePagosTerceros : detallePagos) {
            cajaMenorDao.guardarDetallePagosTercerosCajaMenor(nameDataSource, elementoDetallePagosTerceros);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetallePagosTercerosDto> buscarDetallePagosTercerosCajaMenorDtos(String nameDataSource, Long idpago) {
        return cajaMenorDao.buscarDetallePagosTercerosCajaMenorDtos(nameDataSource, idpago);
    }

    @Override
    @Transactional(readOnly = true)
    public CajaMenor buscarPagoXIdPagoCajaMenor(String nameDataSource, Long idpago) {
        return cajaMenorDao.buscarPagoXIdPagoCajaMenor(nameDataSource, idpago);
    }

    @Override
    public void guardarPagosProveedorCajaMenor(String nameDataSource, CajaMenor pagosCajaMenor, List<DetalleCajaMenor> detalleCajaMenor) {
        cajaMenorDao.guardarPagosCajaMenor(nameDataSource, pagosCajaMenor);
        for (DetalleCajaMenor elementoDetalleCajaMenor : detalleCajaMenor) {
            Compras compra = comprasDao.getCompra(elementoDetalleCajaMenor.getNumeroCompra(), nameDataSource);
            cajaMenorDao.guardarDetallePagosProveedorCajaMenor(nameDataSource, elementoDetalleCajaMenor);
            compra.setSaldo(compra.getSaldo() - elementoDetalleCajaMenor.getTotal());
            if (compra.getSaldo().intValue() == 0) {
                compra.setEstadoCompraProveedor(estado_aprobado_comprobante);
            }
            comprasDao.actualizarCompra(nameDataSource, compra);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetallePagosProveedorDto> buscarDetallePagosDtos(String nameDataSource, Long idCajaMenor) {
        return cajaMenorDao.buscarDetallePagosProveedorCajaMenorDtos(nameDataSource, idCajaMenor);
    }
    
}
