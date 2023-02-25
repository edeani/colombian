/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombia.cali.colombiancaliycali.dao;

import com.colombian.cali.colombiancaliycali.dto.DetallePagosProveedorDto;
import com.colombian.cali.colombiancaliycali.dto.DetallePagosTercerosDto;
import com.colombian.cali.colombiancaliycali.entidades.CajaMenor;
import com.colombian.cali.colombiancaliycali.entidades.DetalleCajaMenor;
import java.util.List;

/**
 *
 * @author Jose Efren
 */
public interface CajaMenorDao {
    public void guardarDetallePagosTercerosCajaMenor(String nameDataSource, DetalleCajaMenor detalleCajaMenor);
    public void guardarPagosCajaMenor(String nameDataSource, CajaMenor pagosTerceros); 
    public List<DetallePagosTercerosDto> buscarDetallePagosTercerosCajaMenorDtos(String nameDataSource, Long idpagotercero);
    public CajaMenor buscarPagoXIdPagoCajaMenor(String nameDataSource, Long idpago);
    public void guardarDetallePagosProveedorCajaMenor(String nameDataSource, DetalleCajaMenor detalleCajaMenor);
    public List<DetallePagosProveedorDto> buscarDetallePagosProveedorCajaMenorDtos(String nameDataSource, Long idpago);
}
