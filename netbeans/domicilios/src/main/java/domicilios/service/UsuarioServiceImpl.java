/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.service;

import domicilios.dao.UsuarioDao;
import domicilios.entidad.Usuario;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Transactional(readOnly = true)
    @Override
    public Usuario findUsuarioByUserName(String username) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("username", username);
        List<Usuario> usuarios = usuarioDao.query("select u from Usuario u where u.nombreusuario=:username", parametros);
        if (usuarios != null) {
            return usuarios.get(0);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Usuario> listUsuarios() {
        return usuarioDao.findAll();
    }

}
