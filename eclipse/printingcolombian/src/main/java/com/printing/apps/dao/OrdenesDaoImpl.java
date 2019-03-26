package com.printing.apps.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.printing.apps.components.LeerXml;
import com.printing.apps.dto.DataOrden;
import com.printing.apps.dto.ItemFacturaDto;

@Repository
public class OrdenesDaoImpl implements OrdenesDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private LeerXml getQuery;

	@Override
	public List<ItemFacturaDto> findBill(Long idOrder) {
		
		MapSqlParameterSource params = new MapSqlParameterSource("idOrder", idOrder);
		
		String sql = getQuery.getQuery("OrdenesSql.bringOrderById");
		
		List<ItemFacturaDto> detalleOrden = new ArrayList<ItemFacturaDto>();
		try {
			detalleOrden = this.namedParameterJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<ItemFacturaDto>(ItemFacturaDto.class));
		} catch (Exception e) {
			Logger.getLogger(OrdenesDaoImpl.class.getName()).log(Level.SEVERE, null, e);
		}
		return detalleOrden;
	}

	@Override
	public DataOrden getDataOrder(Long idOrder) {
		MapSqlParameterSource params = new MapSqlParameterSource("idOrder", idOrder);
		
		String sql = getQuery.getQuery("OrdenesSql.getOrderInformationById");
		
		DataOrden ordenInf = null;
		try {
			ordenInf = this.namedParameterJdbcTemplate.query(
					sql, params, new BeanPropertyRowMapper<DataOrden>(DataOrden.class)).get(0);
		} catch (DataAccessException e) {
			Logger.getLogger(OrdenesDaoImpl.class.getName()).log(Level.SEVERE, null, "Data not found idOrder "+idOrder);
		}
		return ordenInf;
	}

}

