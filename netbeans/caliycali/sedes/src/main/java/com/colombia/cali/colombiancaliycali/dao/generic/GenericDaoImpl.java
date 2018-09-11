/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.dao.generic;
import com.colombia.cali.colombiancaliycali.util.Formatos;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Eslayfer
 */
@SuppressWarnings("unchecked")
@Repository
public class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GenericDaoImpl.class);
	/**
	 * Clase a persistir
	 */
	private Class persistentClass;
	
	private EntityManager entityManager;
	
        
	/**
	 * Constructor. Carga la clase a persistir
	 */
	public GenericDaoImpl() {

		Type tipo = getClass().getGenericSuperclass();

		if (tipo instanceof ParameterizedType) {
			this.persistentClass = (Class) ((ParameterizedType) tipo).getActualTypeArguments()[0];
		} else {
			this.persistentClass = null;
		}
	}
	
	/**
	 * @return the persistentClass
	 */
    @Override
	public Class getPersistentClass() {
		return persistentClass;
	}

	/**
	 * @param persistentClass
	 *            the persistentClass to set
	 */
    @Override
	public void setPersistentClass(Class persistentClass) {
		this.persistentClass = persistentClass;
	}

    @Override
	public Object findById(Class entityClass, Long id)
	  {
	    return getEntityManager().find(entityClass, id);
	  }
	

    @Override
	public T findById(ID id) {
		return (T) getEntityManager().find(persistentClass, id);
	}

    @Override
	public List<T> findAll() {
		return getEntityManager().createQuery("from " + persistentClass.getSimpleName()).getResultList();
	}

    @Override
	public T persist(T entity) {

		getEntityManager().persist(entity);
		return entity;
	}


    @Override
	public T update(T entity) {

		return getEntityManager().merge(entity);
	}

    @Override
	public void remove(ID id) {
		T toDel = findById(id);
		if (toDel != null) {
			getEntityManager().remove(toDel);
		}
	}

    @Override
	public void remove(T entity) {
		if (entity != null) {
			getEntityManager().remove(entity);
		}
	}

        @Override
	public T findUniqueByFields(String[] columnas, Object[] valores) {
		String queryString = createFieldFilteredHQL(columnas, valores, getPersistentClass(), null);
		
		Query query = getEntityManager().createQuery(queryString);
		if(columnas!=null && valores!=null)
			this.setNamedParameters(query, columnas, valores, null);
		List<T> result = query.getResultList();
		return !result.isEmpty() ? result.get(0) : null;
	}
	
    @Override
	public T findUniqueByFields(Class<?> clazz,String[] columnas, Object[] valores){
                /*System.out.println( "llgue al dao" +valores[0].toString());
                 System.out.println( "llgue valores" +columnas[0].toString());
                 System.out.println( "la clase es" +clazz.getSimpleName());*/
		String queryString = createFieldFilteredHQL(columnas, valores, clazz, null);
         Query query=null;
                try{
		query = getEntityManager().createQuery(queryString);
                System.out.println( "ek query es "+query.toString() );
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                 
		if(columnas!=null && valores!=null)
			this.setNamedParameters(query, columnas, valores, null);
		List<T> result = query.getResultList();
                
		return !result.isEmpty() ? result.get(0) : null;
	}
	
	/**
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
    @Override
	public List<T> findByFields(String[] columnas, Object[] valores) {
		String queryString = createFieldFilteredHQL(columnas, valores, getPersistentClass(), null);
		Query query = getEntityManager().createQuery(queryString);
		if(columnas!=null && valores!=null)
			this.setNamedParameters(query, columnas, valores,null);
		List<T> result = query.getResultList();
		return !result.isEmpty() ? result : null;
		
	}
	
    @Override
	public List<T> findByFields(String[] columnas, Object[] valores,String[] operadores) {
		String queryString = createFieldFilteredHQL(columnas, valores, getPersistentClass(), operadores);
		Query query = getEntityManager().createQuery(queryString);
		if(columnas!=null && valores!=null)
			this.setNamedParameters(query, columnas, valores,operadores);
		List<T> result = query.getResultList();
		return !result.isEmpty() ? result : null;
		
	}
	
	
	
    @Override
	public List<T> findByPaginate(int page, int end, String[] columnas, Object[] valores,String orden, String[] operadores) {		
		String queryString = createFieldFilteredHQL(columnas, valores, getPersistentClass(),operadores);
		
		if(orden!=null){
			if(!orden.equals("")){
				StringBuffer queryTmp = new StringBuffer(queryString);
				queryTmp.append(" ORDER BY "+orden);
				queryString = queryTmp.toString();
			}
		}
		
		Query query = getEntityManager().createQuery(queryString);
		if(columnas!=null && valores!=null)
			this.setNamedParameters(query, columnas, valores,operadores);
		if(end>0){
			query.setFirstResult(end * (page-1) );
	        query.setMaxResults(end).getResultList();
		}		
		List<T> result = query.getResultList();
		return result;

	}
	
	@Transactional(readOnly=true)
	public int countReg() {
		String queryString = "select count(*) from " + persistentClass.getSimpleName() + " model ";
		Query query = getEntityManager().createQuery(queryString);
		int cant=0;
		try{
			cant = Integer.parseInt(""+query.getSingleResult()); 
		}catch (Exception e) {
			cant=0;
		}
		return cant;		
	}
	
	@Transactional(readOnly=true)
	public int countReg(String[] columnas, Object[] valores, String[] operadores){
		
		String queryString = createCountFieldFilteredHQL(columnas, valores, getPersistentClass(), operadores);
		Query query = getEntityManager().createQuery(queryString);
		if(columnas!=null && valores!=null)
			this.setNamedParameters(query, columnas, valores,operadores);

		int cant=0;
		try{
			cant = Integer.parseInt(""+query.getSingleResult()); 
		}catch (Exception e) {
			cant=0;
		}
		return cant;
	}
	
	
	protected String createFieldFilteredHQL(String[] fieldNames, Object[] fieldValues, Class<?> clazz, String[] operadores)
	  {
	    StringBuffer query = new StringBuffer("SELECT e FROM ");
	    query.append(clazz.getSimpleName());
	    query.append(" AS e ");
	    if(fieldNames!=null){
	    	if(fieldNames.length>0){
	    		query.append(" WHERE ");
		    	for (int i = 0; i < fieldNames.length; i++) {
		  	      query.append(" e");
		  	      query.append(".");
		  	      query.append(fieldNames[i]);
		  	      if (fieldValues[i] == null) {
		  	        query.append("  is null");
		  	      } else if (fieldValues[i].equals("notNull")) {
			  	        query.append("  is not null");
			  	  }else {
			  		query.append(operadores!=null?operadores[i]:"=");
			  		  if(operadores!=null){
			  			  String operador = operadores[i].trim();
			  			  if(operador.equals("in")){
			  				query.append("(:" + fieldNames[i].replaceAll("\\.", "_")+")");
			  			  }else{
			  				query.append(":" + fieldNames[i].replaceAll("\\.", "_"));
			  			  }
			  		  }else{
			  			query.append(":" + fieldNames[i].replaceAll("\\.", "_"));
			  		  }
		  	      }
		  	      if (i < fieldNames.length - 1) {
		  	        query.append(" AND ");
		  	      }
		  	    }
	    	}
	    }
	    return query.toString();
	}
	
	protected String createCountFieldFilteredHQL(String[] fieldNames, Object[] fieldValues, Class<?> clazz, String[] operadores)
	  {
	    StringBuffer query = new StringBuffer("select count(*) from " + clazz.getSimpleName() + " model ");
	    if(fieldNames!=null){
	    	if(fieldNames.length>0){
	    		query.append(" WHERE ");
		    	for (int i = 0; i < fieldNames.length; i++) {
		  	      query.append(fieldNames[i]);
		  	      if (fieldValues[i] == null) {
		  	        query.append("  is null");
		  	      } else if (fieldValues[i].equals("notNull")) {
			  	        query.append("  is not null");
			  	  }else {
			  		  
		  	      query.append(operadores!=null?operadores[i]:"=");
		  	      if(operadores!=null){
		  			  String operador = operadores[i].trim();
		  			  if(operador.equals("in")){
		  				query.append("(:" + fieldNames[i].replaceAll("\\.", "_")+")");
		  			  }else{
		  				query.append(":" + fieldNames[i].replaceAll("\\.", "_"));
		  			  }
		  		  }else{
		  			query.append(":" + fieldNames[i].replaceAll("\\.", "_"));
		  		  }
		  	      }
		  	      if (i < fieldNames.length - 1) {
		  	        query.append(" AND ");
		  	      }
		  	    }
	    	}
	    }

	    return query.toString();
	}
	
	protected void setNamedParameters(Query query, String[] paramNames,
			Object[] paramValues,String[] operadores) {
		for (int i = 0; i < paramNames.length; i++) {
			if (paramValues[i] != null) {
				if(!paramValues[i].equals("notNull")){
					if(operadores!=null){
						String operador = operadores[i].trim();
			  			if(operador.equals("in")){
			  				try{
			  					String []valores = ((String) paramValues[i]).split(",");
								if(valores.length>0){
									if(Formatos.esNumero(valores[0])){
										List<Long> val = new ArrayList<Long>();
										for(int j=0;j<valores.length;j++){
											val.add(Formatos.recuperarNumeroString(valores[j]));
										}
										query.setParameter(paramNames[i].replaceAll("\\.", "_"), val);
									}else{
										List<String> val = new ArrayList<String>();
										for(int j=0;j<valores.length;j++){
											val.add(valores[j]);
										}
										query.setParameter(paramNames[i].replaceAll("\\.", "_"), val);
									}
								}
			  				}catch(Exception e){
			  					LOGGER.error("error ",e.getMessage());
			  				}							
			  			}else{
			  				query.setParameter(paramNames[i].replaceAll("\\.", "_"), paramValues[i]);
			  			}
					}else{
                                            System.out.println("la consulat es ");
						query.setParameter(paramNames[i].replaceAll("\\.", "_"), paramValues[i]);
                                                System.out.println("la consulat es "+query.toString());
					}
				}	
			}
		}
	}

    @Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
    