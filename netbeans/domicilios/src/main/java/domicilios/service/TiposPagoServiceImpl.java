/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.service;

import domicilios.dao.TipoPagoDao;
import domicilios.entidad.Tipopago;
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
    
}
