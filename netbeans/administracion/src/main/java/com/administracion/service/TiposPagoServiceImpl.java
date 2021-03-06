/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dao.TipoPagoDao;
import com.administracion.entidad.Tipopago;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Service
public class TiposPagoServiceImpl implements TipoPagoService{

    @Autowired
    private TipoPagoDao tipoPagoDao;
    
    @Transactional(readOnly = true)
    @Override
    public List<Tipopago> tiposDePago() {
        return tipoPagoDao.findAll();
    }

    @Override
    @Transactional
    public void actualizarTiposPago(Tipopago tipopago) {
        Tipopago tp = tipoPagoDao.findById(tipopago.getIdtipo());
        tp.setEstado(tipopago.getEstado());
        tp.setNombre(tipopago.getNombre());
        
        tipoPagoDao.Update(tp);
    }

    @Override
    @Transactional
    public void crearTipoPago(String estado, String nombre) {
        Tipopago tipopago = new Tipopago();
        tipopago.setEstado(estado);
        tipopago.setNombre(nombre);
        
        tipoPagoDao.save(tipopago);
    }
    
    
}
