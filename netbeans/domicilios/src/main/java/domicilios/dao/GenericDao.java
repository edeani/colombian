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
    
    public List<T> findAll();
    public List<T> query(String query,HashMap<String,Object> parametros);
}
