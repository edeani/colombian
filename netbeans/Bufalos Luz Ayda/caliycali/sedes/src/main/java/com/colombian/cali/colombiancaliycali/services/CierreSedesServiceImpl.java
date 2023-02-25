/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services;

import com.colombia.cali.colombiancaliycali.dao.CierreSedesDao;
import com.colombia.cali.colombiancaliycali.dao.SecuenciasMysqlDao;
import com.colombia.cali.colombiancaliycali.util.Formatos;
import com.colombian.cali.colombiancaliycali.dto.CierreSedesDto;
import com.colombian.cali.colombiancaliycali.dto.ComprobanteCierreSedesDto;
import com.colombian.cali.colombiancaliycali.dto.ComprobanteConsolidadoSedeDto;
import com.colombian.cali.colombiancaliycali.dto.ReporteComprobanteCierreDto;
import com.colombian.cali.colombiancaliycali.entidades.CierreSedes;
import com.colombian.cali.colombiancaliycali.mapper.CierreSedesMapper;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Service
public class CierreSedesServiceImpl implements CierreSedesService {

    @Autowired
    private CierreSedesDao cierreSedesDao;
    @Autowired
    private SecuenciasMysqlDao secuenciasMyqlDao;

    @Override
    @Transactional
    public void guardarComprobanteCierreService(String nameDatasource, ComprobanteCierreSedesDto comprobanteCierreSedesDto) {
        Long secuencia = 0L;

        CierreSedes cierreSedesFind = cierreSedesDao.buscarCabeceraComprobanteCierreXFechaXSede(nameDatasource, comprobanteCierreSedesDto.getFecha()
                , comprobanteCierreSedesDto.getFecha(),comprobanteCierreSedesDto.getIdSede());
        //Si no existe queda con el autoincremental
        if (cierreSedesFind == null) {
            secuencia = secuenciasMyqlDao.secuenceTable(nameDatasource, "cierre_sedes");
            comprobanteCierreSedesDto.setConsecutivo(secuencia);
        }else{//Si existe se le dejala misma secuencia
            secuencia = cierreSedesFind.getConsecutivo();
            comprobanteCierreSedesDto.setConsecutivo(secuencia);
            cierreSedesDao.borrarDetalleComprobanteCierre(nameDatasource, secuencia);
            cierreSedesDao.borrarComprobanteCierre(nameDatasource, secuencia);
        }
        

        CierreSedesMapper cierreSedesMapper = new CierreSedesMapper();
        CierreSedes cierreSedes = cierreSedesMapper.comprobanteConsolidadoSedeDtoTOCierreSede(comprobanteCierreSedesDto);
        Date fechaActual = new Date();
        if (cierreSedes.getFechaComprobante() == null) {
            cierreSedes.setFechaComprobante(fechaActual);
        }

        cierreSedesDao.guardarComprobanteCierre(nameDatasource, cierreSedes);

        List<ComprobanteConsolidadoSedeDto> detalleComprobanteCierreSedes = comprobanteCierreSedesDto.getComprobanteConsolidadoSedeDto();
        for (ComprobanteConsolidadoSedeDto detalleComprobanteCierreSede : detalleComprobanteCierreSedes) {
            detalleComprobanteCierreSede.setIdComprobante(secuencia);
            if (detalleComprobanteCierreSede.getFechaComprobante() == null) {
                detalleComprobanteCierreSede.setFechaComprobante(Formatos.dateTostring(fechaActual));
            }
            cierreSedesDao.guardarDetalleComprobanteCierre(nameDatasource, detalleComprobanteCierreSede);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReporteComprobanteCierreDto> buscarDetalleComprobanteCierreSedesView(String nameDataSource, Long idComprobanteCierre) {
        return cierreSedesDao.buscarDetalleComprobanteCierreSedesView(nameDataSource, idComprobanteCierre);
    }

    @Override
    @Transactional(readOnly = true)
    public CierreSedesDto buscarComprobanteCierreDto(String nameDataSource, Long idComprobanteCierre) {
        return cierreSedesDao.buscarComprobanteCierreDto(nameDataSource, idComprobanteCierre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CierreSedesDto> buscarComprobanteCierreDtoXFecha(String nameDataSource, String fechaInicial, String fechaFinal) {
        return cierreSedesDao.buscarComprobanteCierreDtoXFecha(nameDataSource, fechaInicial, fechaFinal);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CierreSedesDto> reporteComprobanteCierreSedesView(String nameDataSource, String fechaInicial, String fechaFinal, Long idsede) {
        return cierreSedesDao.reporteComprobanteCierreSedesView(nameDataSource, fechaInicial, fechaFinal, idsede);
    }

}
