/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombian.cali.colombiancaliycali.services;

import com.colombian.cali.colombiancaliycali.dto.DetallePagosProveedorDto;
import com.colombian.cali.colombiancaliycali.dto.DetallePagosTercerosDto;
import com.colombian.cali.colombiancaliycali.entidades.CajaMenor;
import com.colombian.cali.colombiancaliycali.entidades.DetalleCajaMenor;
import com.colombian.cali.colombiancaliycali.entidades.DetallePagos;
import com.colombian.cali.colombiancaliycali.entidades.Pagos;
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
