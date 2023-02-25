/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.dao.generic;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;
import javax.persistence.EntityManager;
/**
 *
 * @author Eslayfer
 */
public interface GenericDao<T, ID extends Serializable>{
	
	/**
	 * Devuelve el Manager
	 * @return
	 */
	public EntityManager getEntityManager();
	
	/**
	 * Busca el entity por el id de una clase definida
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public Object findById(Class entityClass, Long id);
	
	/**
	 * Busca el entity por el id
	 * 
	 * @param id Llave primaria del objeto a buscar
	 * @return EL objeto encontrado
	 */
	public T findById(ID id);
	
	/**
	 * Crea el objeto
	 * 
	 * @param entity Objeto a crear
	 * @return El objeto creado
	 */
	public T persist(T entity);

	/**
	 * Crea o actualiza el objeto
	 * 
	 * @param entity Objecto a guardar
	 * @return El objecto guardado
	 */
	public T update(T entity);

	/**
	 * Elimina el objeto dado su id
	 * 
	 * @param entity Objeto a eliminar
	 */
	public void remove(ID id);
	
	/**
	 * Elimina el objeto
	 * @param entity
	 */
	public void remove(T entity);
	
	/**
	 * Busca todos los objetos
	 * 
	 * @return Lista con todos los objetos
	 */
	public List<T> findAll();
	
	/**
	 * Busca un objeto (el primero) segï¿½n la propiedad
	 * 
	 * @param property Nombre de la propiedad
	 * @param value Valor de la propiedad
	 * @return Primer Objeto encontrado que cumple con la propiedad
	 */
	public T findUniqueByFields(String[] columnas, Object[] valores);
	
	/**
	 * Busca un objeto de una clase
	 * 
	 * @param property Nombre de la propiedad
	 * @param value Valor de la propiedad
	 * @return Primer Objeto encontrado que cumple con la propiedad
	 */
	public T findUniqueByFields(Class<?> clazz,String[] columnas, Object[] valores);
	
	/**
	 * Busca la lista de objetos  por filtros
	 * @param property
	 * @param value
	 * @return
	 */
	public List<T> findByFields(String[] columnas, Object[] valores);
	
	/**
	 * Busca la lista de objetos  por filtros
	 * @param property
	 * @param value
	 * @return
	 */
	public List<T> findByFields(String[] columnas, Object[] valores,String[] operadores);
	
	/**
	 * Busca la lista de objetos  por filtros paginados
	 * @param property
	 * @param start
	 * @param end
	 * @return
	 */
	public List<T> findByPaginate(int page, int end, String[] columnas, Object[] valores, String orden,String[] operadores);
	
	/**
	 * Cuenta los registros de la entidad
	 * @return
	 */
	public int countReg();
	
	/**
	 * Cuenta los registros de la entidad por filtros
	 * @return
	 */
	public int countReg(String[] columnas, Object[] valores, String[] operadores);

	
	/**
	 * recupera el persistentClass
	 * @return the persistentClass
	 */
	public Class getPersistentClass();

	/**
	 * asigna el persistentClass
	 * @param persistentClass the persistentClass to set
	 */
	public void setPersistentClass(Class persistentClass);

}