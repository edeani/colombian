/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;


import com.administracion.dao.ControlNotasCreditoDao;
import com.administracion.dao.ControlNotasDebitoDao;
import com.administracion.dao.NotasCreditoDao;
import com.administracion.dao.NotasDebitoDao;
import com.administracion.dto.NotasDetalleDto;
import com.administracion.dto.NotasDto;
import com.administracion.entidad.ControlNotasCredito;
import com.administracion.entidad.ControlNotasDebito;
import com.administracion.entidad.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.administracion.service.autorizacion.ConnectsAuth;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author EderArmando
 */
@Service
public class NotasServiceImpl implements NotasService{

    @Autowired
    private NotasDebitoDao notasDebitoDao;
    @Autowired
    private NotasCreditoDao notasCreditoDao;
    @Autowired
    private ControlNotasCreditoDao controlNotasCreditoDao;
    @Autowired
    private ControlNotasDebitoDao controlNotasDebitoDao;
    @Autowired
    private ConnectsAuth connectsAuth;
    
    @Transactional
    @Override
    public int guardarNotaDebito(String dataSource, NotasDto notasDebito,Users userND) {
        DataSource dataSourceND = connectsAuth.getDataSourceSede(dataSource);

        
        /**
         * Guardar cabecera de la nota debito
         */
        Integer lastInsertedIdND = controlNotasDebitoDao.guardarNotaDebitoControl(dataSourceND, notasDebito, userND);
        
        /**
         * Guardar detalle de la nota debito
         */
        notasDebito.setIdControlNotaDebito(lastInsertedIdND);
        notasDebitoDao.guardarNotaDebitoDetalle(dataSourceND, notasDebito);
        
        return lastInsertedIdND;
    }

    @Transactional
    @Override
    public int guardarNotaCredito(String dataSource, NotasDto notasCredito, Users userNC) {
        
        DataSource dataSourceNC = connectsAuth.getDataSourceSede(dataSource);
        /**
         * Guardar cabecera de la nota crédito
         */
        Integer lastInsertedIdNC = controlNotasCreditoDao.guardarNotaCreditoControl(dataSourceNC, notasCredito, userNC);
        /**
         * Guardar detalle de la nota crédito
         */
        notasCredito.setIdControlNotaCredito(lastInsertedIdNC);
        notasCreditoDao.guardarNotaCreditoDetalle(dataSourceNC, notasCredito);
        
        return lastInsertedIdNC;
    }

    @Override
    public List<NotasDetalleDto> consultarNotaCredito(String dataSource, int idComprobante) {
        return notasCreditoDao.consultarDetalleNotaCredito(connectsAuth.getDataSourceSede(dataSource), idComprobante);
    }

    @Override
    public ControlNotasCredito consultarControlNotaCredito(String dataSource, int idComprobante) {
        return controlNotasCreditoDao.getNotaCreditoControl(connectsAuth.getDataSourceSede(dataSource), idComprobante);
    }

    @Override
    public List<NotasDetalleDto> consultarNotaDebito(String dataSource, int idComprobante) {
        return notasDebitoDao.consultarDetalleNotaDebito(connectsAuth.getDataSourceSede(dataSource), idComprobante);
    }

    @Override
    public ControlNotasDebito consultarControlNotaDebito(String dataSource, int idComprobante) {
        return controlNotasDebitoDao.getNotaDebitoControl(connectsAuth.getDataSourceSede(dataSource), idComprobante);
    }

    
    
}
