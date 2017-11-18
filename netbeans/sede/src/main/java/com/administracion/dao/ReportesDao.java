/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.dto.ReporteConsolidadoDto;
import com.administracion.dto.SubSedesDto;
import java.util.List;

/**
 *
 * @author EderArmando
 */
public interface ReportesDao {
    public List<ReporteConsolidadoDto> reporteConsolidado(List<SubSedesDto> subSedes,String fechaInicial,String fechaFinal); 
}
