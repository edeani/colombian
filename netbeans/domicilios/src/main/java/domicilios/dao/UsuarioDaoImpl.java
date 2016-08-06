/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.dao;

import domicilios.entidad.Usuario;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario> implements UsuarioDao{
     
    @Override
    public List<Usuario> listUsuarios() {
       return null;
    }
   
    
}
