/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.administracion.dao;

import com.administracion.dto.OrdenesClienteProdDto;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Anlod
 */
public interface OrdenesDao {
    
    List<OrdenesClienteProdDto> ordenesReporteClientesSubSede(DataSource nameDataSource, String fechaInicial, String fechaFinal, String nameDataPrincipal);
}
