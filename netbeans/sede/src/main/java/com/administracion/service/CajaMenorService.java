/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.service;

import com.administracion.dto.DetallePagosProveedorDto;
import com.administracion.dto.DetallePagosTercerosDto;
import com.administracion.entidad.CajaMenor;
import com.administracion.entidad.DetalleCajaMenor;
import java.util.List;

/**
 *
 * @author Jose Efren
 */
public interface CajaMenorService {
    public void guardarPagosTercerosCajaMenor(String nameDataSource, CajaMenor pagosTerceros, List<DetalleCajaMenor> detallePagos);
    public List<DetallePagosTercerosDto> buscarDetallePagosTercerosCajaMenorDtos(String nameDataSource, Long idpago);
    public CajaMenor buscarPagoXIdPagoCajaMenor(String nameDataSource, Long idpago);
    public void guardarPagosProveedorCajaMenor(String nameDataSource, CajaMenor pagosCajaMenor, List<DetalleCajaMenor> detalleCajaMenor);
    public List<DetallePagosProveedorDto> buscarDetallePagosDtos(String nameDataSource, Long idCajaMenor);
}
