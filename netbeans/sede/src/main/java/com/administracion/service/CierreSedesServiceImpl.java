/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;


import com.adiministracion.mapper.CierreSedesMapper;
import com.administracion.dao.CierreSedesDao;
import com.administracion.dao.SecuenciasMysqlDao;
import com.administracion.dto.CierreSedesDto;
import com.administracion.dto.ComprobanteCierreSedesDto;
import com.administracion.dto.ComprobanteConsolidadoSedeDto;
import com.administracion.dto.ReporteComprobanteCierreDto;
import com.administracion.entidad.CierreSedes;
import com.administracion.service.autorizacion.AccesosSubsedes;
import com.administracion.util.Formatos;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
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
    @Autowired
    private AccesosSubsedes accesosSubsedes_;

    @Override
    @Transactional
    public void guardarComprobanteCierreService(String nameDatasource, ComprobanteCierreSedesDto comprobanteCierreSedesDto) {
        Long secuencia = 0L;
        DataSource ds = accesosSubsedes_.getDataSourceSede(nameDatasource);
        CierreSedes cierreSedesFind = cierreSedesDao.buscarCabeceraComprobanteCierreXFechaXSede(ds, comprobanteCierreSedesDto.getFecha()
                , comprobanteCierreSedesDto.getFecha(),comprobanteCierreSedesDto.getIdSede());
        //Si no existe queda con el autoincremental
        if (cierreSedesFind == null) {
            secuencia = secuenciasMyqlDao.secuenceTable(ds, "cierre_sedes");
            comprobanteCierreSedesDto.setConsecutivo(secuencia);
        }else{//Si existe se le dejala misma secuencia
            secuencia = cierreSedesFind.getConsecutivo();
            comprobanteCierreSedesDto.setConsecutivo(secuencia);
            cierreSedesDao.borrarDetalleComprobanteCierre(ds, secuencia);
            cierreSedesDao.borrarComprobanteCierre(ds, secuencia);
        }
        

        CierreSedesMapper cierreSedesMapper = new CierreSedesMapper();
        CierreSedes cierreSedes = cierreSedesMapper.comprobanteConsolidadoSedeDtoTOCierreSede(comprobanteCierreSedesDto);
        Date fechaActual = new Date();
        if (cierreSedes.getFechaComprobante() == null) {
            cierreSedes.setFechaComprobante(fechaActual);
        }

        cierreSedesDao.guardarComprobanteCierre(ds, cierreSedes);

        List<ComprobanteConsolidadoSedeDto> detalleComprobanteCierreSedes = comprobanteCierreSedesDto.getComprobanteConsolidadoSedeDto();
        for (ComprobanteConsolidadoSedeDto detalleComprobanteCierreSede : detalleComprobanteCierreSedes) {
            detalleComprobanteCierreSede.setIdComprobante(secuencia);
            if (detalleComprobanteCierreSede.getFechaComprobante() == null) {
                detalleComprobanteCierreSede.setFechaComprobante(Formatos.dateTostring(fechaActual));
            }
            cierreSedesDao.guardarDetalleComprobanteCierre(ds, detalleComprobanteCierreSede);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReporteComprobanteCierreDto> buscarDetalleComprobanteCierreSedesView(String nameDataSource, Long idComprobanteCierre) {
        return cierreSedesDao.buscarDetalleComprobanteCierreSedesView(accesosSubsedes_.getDataSourceSubSede(nameDataSource), idComprobanteCierre);
    }

    @Override
    @Transactional(readOnly = true)
    public CierreSedesDto buscarComprobanteCierreDto(String nameDataSource, Long idComprobanteCierre) {
        return cierreSedesDao.buscarComprobanteCierreDto(accesosSubsedes_.getDataSourceSubSede(nameDataSource), idComprobanteCierre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CierreSedesDto> buscarComprobanteCierreDtoXFecha(String nameDataSource, String fechaInicial, String fechaFinal) {
        return cierreSedesDao.buscarComprobanteCierreDtoXFecha(accesosSubsedes_.getDataSourceSubSede(nameDataSource), fechaInicial, fechaFinal);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CierreSedesDto> reporteComprobanteCierreSedesView(String nameDataSource, String fechaInicial, String fechaFinal, Long idsede) {
        return cierreSedesDao.reporteComprobanteCierreSedesView(accesosSubsedes_.getDataSourceSubSede(nameDataSource), fechaInicial, fechaFinal, idsede);
    }

}
