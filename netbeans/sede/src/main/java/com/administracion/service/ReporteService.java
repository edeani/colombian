/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dto.ReporteConsolidadoDto;
import java.util.List;

/**
 *
 * @author EderArmando
 */
public interface ReporteService {
    public List<ReporteConsolidadoDto> reporteConsolidado(Integer idSede,String fechaInicial,String fechaFinal);
}
