/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.dao;

import domicilios.entidad.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository("UsuarioDaoImpl")
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario> implements UsuarioDao{


    public UsuarioDaoImpl(Class<Usuario> entityClass) {
        super(entityClass);
    }
     
    @Override
    public List<Usuario> listUsuarios() {
        return null;
    }
   
    
}
