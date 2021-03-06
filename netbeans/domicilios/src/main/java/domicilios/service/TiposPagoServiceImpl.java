/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.service;

import domicilios.dao.TipoPagoDao;
import domicilios.entidad.Tipopago;
import domicilios.util.LeerXml;
import static java.awt.PageAttributes.MediaType.A;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Service
public class TiposPagoServiceImpl implements TipoPagoService {

    @Autowired
    private TipoPagoDao tipoPagoDao;

    @Autowired
    private LeerXml leerXml;

    @Transactional(readOnly = true)
    @Override
    public List<Tipopago> tiposDePagoActivos() {

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("estado", "A");

        final String TIPOSPAGO_ACTIVOS= "TiposPagoJpa.tipoPagoActivos";
        
        
        return tipoPagoDao.queryJpa(leerXml.getQuery(TIPOSPAGO_ACTIVOS), parametros);
    }

}
