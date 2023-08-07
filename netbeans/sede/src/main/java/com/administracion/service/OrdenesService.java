/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.administracion.service;

import com.administracion.dto.OrdenesClienteProdDto;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Anlod
 */
public interface OrdenesService {
    
    List<OrdenesClienteProdDto> ordenesReporteClientesSubSede(String nameDataSource, String fechaInicial, String fechaFinal);
}
