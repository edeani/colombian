/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.dao;


import com.administracion.dto.DetallePagosProveedorDto;
import com.administracion.dto.DetallePagosTercerosDto;
import com.administracion.entidad.CajaMenor;
import com.administracion.entidad.DetalleCajaMenor;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Jose Efren
 */
public interface CajaMenorDao extends GenericDao<CajaMenor>{
    public void guardarDetallePagosTercerosCajaMenor(DataSource nameDataSource, DetalleCajaMenor detalleCajaMenor);
    public void guardarPagosCajaMenor(DataSource nameDataSource, CajaMenor pagosTerceros); 
    public List<DetallePagosTercerosDto> buscarDetallePagosTercerosCajaMenorDtos(DataSource nameDataSource, Long idpagotercero);
    public CajaMenor buscarPagoXIdPagoCajaMenor(DataSource nameDataSource, Long idpago);
    public void guardarDetallePagosProveedorCajaMenor(DataSource nameDataSource, DetalleCajaMenor detalleCajaMenor);
    public List<DetallePagosProveedorDto> buscarDetallePagosProveedorCajaMenorDtos(DataSource nameDataSource, Long idpago);
}
