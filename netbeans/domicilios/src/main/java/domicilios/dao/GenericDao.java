/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.dao;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author edeani
 */
public interface GenericDao <T>{
    
    List<T> findAll();
    void save(T objeto);
    void Update(T objeto);
    void delete(T objeto);
    T findById(Object id);
    T queryOpjectJpa(String queryString,HashMap<String,Object> parametros);
    List<T> queryJpa(String query,HashMap<String,Object> parametros);
    List<T> querySql(String query,HashMap<String,Object> parametros);
}
