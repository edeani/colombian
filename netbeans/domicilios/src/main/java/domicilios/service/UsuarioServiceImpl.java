/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.service;

import domicilios.dao.RolDao;
import domicilios.dao.UsuarioDao;
import domicilios.entidad.Rol;
import domicilios.entidad.Usuario;
import domicilios.util.LeerXml;
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
    
    @Autowired
    private RolDao rolDao;

    @Transactional(readOnly = true)
    @Override
    public Usuario findUsuarioByUserName(String username) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("username", username);
        List<Usuario> usuarios = usuarioDao.queryJpa(LeerXml.getQuery("UsuarioJpa.findXnombreusuario"), parametros);
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

    @Transactional
    @Override
    public void crearUsuario(Usuario usuario) {
        usuarioDao.save(usuario);
    }

    @Transactional
    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuarioDao.Update(usuario);
    }

    @Transactional
    @Override
    public void borrarUsuario(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    @Transactional(readOnly = true)
    @Override
    public Rol roles(Integer idrol) {
        return rolDao.findById(idrol);
    }

}
