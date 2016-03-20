/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.facturador.services;

import co.com.facturador.services.util.GenericService;
import co.facturador.daos.UsuarioJpaController;
import co.facturador.entity.Usuario;

/**
 *
 * @author EderArmando
 */
public class LoginServiceImpl extends GenericService implements LoginService{

    @Override
    public boolean loguearUsuario(String user, String password) {
           UsuarioJpaController usuarioDao = new UsuarioJpaController(getFactory());
           Usuario usuario = usuarioDao.findXuserXpassword(user, password);
           if(usuario!=null){
               return Boolean.TRUE;
           }else{
               return Boolean.FALSE;
           }
    }
    
}
