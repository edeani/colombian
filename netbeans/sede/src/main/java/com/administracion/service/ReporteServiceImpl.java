/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dao.ReportesDao;
import com.administracion.dao.SedesDao;
import com.administracion.dao.SubSedesDao;
import com.administracion.dto.ReporteConsolidadoDto;
import com.administracion.dto.SubSedesDto;
import com.administracion.entidad.Sedes;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author EderArmando
 */
@Service
public class ReporteServiceImpl implements ReporteService{

    @Autowired
    private SubSedesDao subSedesDao;
    
    @Autowired
    private ReportesDao reportesDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<ReporteConsolidadoDto> reporteConsolidado(Integer idSede,String fechaInicial, String fechaFinal) {
        List<SubSedesDto> subSedes = subSedesDao.subsedesXIdSede(idSede);
        return reportesDao.reporteConsolidado(subSedes, fechaInicial, fechaFinal);
    }
    
}
