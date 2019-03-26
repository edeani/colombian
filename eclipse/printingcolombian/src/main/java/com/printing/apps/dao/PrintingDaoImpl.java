package com.printing.apps.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.printing.apps.components.LeerXml;
import com.printing.apps.components.Sentences;
import com.printing.apps.entity.Printing;
import com.printing.apps.util.Formatos;

@Repository
public class PrintingDaoImpl implements PrintingDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private LeerXml getQuery;

	@Autowired
	private Sentences sentencesSql;

	@Override
	public Printing findPrintingByIdState(Long idOrder, String state) {
		MapSqlParameterSource params = new MapSqlParameterSource("idOrder", idOrder);
		params.addValue("state", state);

		String sql = getQuery.getQuery("PrintingSql.findByIdState");

		Printing printing = null;

		try {
			printing = namedParameterJdbcTemplate.queryForObject(sql, params, new BeanPropertyRowMapper<Printing>(Printing.class));
		} catch (DataAccessException e) {
			Logger.getLogger(PrintingDaoImpl.class.getName()).log(Level.SEVERE, "Data not found "+idOrder+" state "+state);
		}
		return printing;
	}

	@Override
	public void savePrinting(Printing printing) {

		String sql = sentencesSql.insertJdbTemplate("orden,fechaImpresion,fechaCreacion,estado", "printing",
			printing.getOrden() + sentencesSql.getComa() + 
			(printing.getFechaImpresion() == null ? "null" : "'"+Formatos.dateTostringFechacompleta(printing.getFechaImpresion())+"'") + sentencesSql.getComa()
			+ "'"+Formatos.dateTostringFechacompleta(printing.getFechaCreacion())+"'"
			+ sentencesSql.getComa() +"'"+ printing.getEstado()+"'");

		try {
			this.namedParameterJdbcTemplate.getJdbcTemplate().execute(sql);
		} catch (Exception e) {
			Logger.getLogger(PrintingDaoImpl.class.getName()).log(Level.SEVERE, null, e);
		}

	}

	@Override
	public List<Printing> findPrintingState(String state) {
		MapSqlParameterSource params = new MapSqlParameterSource("state", state);

		String sql = getQuery.getQuery("PrintingSql.findByState");

		List<Printing> printing = null;

		try {
			printing = namedParameterJdbcTemplate.query(sql, params,
					new BeanPropertyRowMapper<Printing>(Printing.class));
		} catch (Exception e) {
			Logger.getLogger(PrintingDaoImpl.class.getName()).log(Level.SEVERE,  "Data not found "+ state);
		}

		return printing;
	}

	@Override
	public void updatePrintingState(String stateOld, String stateNew, Long idOrder) {

		MapSqlParameterSource params = new MapSqlParameterSource("stateOld", stateOld);
		params.addValue("stateNew", stateNew);
		params.addValue("idOrder", idOrder);

		String sql = getQuery.getQuery("PrintingSql.updateStateById");

		try {
			namedParameterJdbcTemplate.update(sql, params);
		} catch (Exception e) {
			Logger.getLogger(PrintingDaoImpl.class.getName()).log(Level.SEVERE, null, e);
		}

	}

	@Override
	public  void updatePrinting(Printing printing) {
		String sql = sentencesSql.updateJdbTemplate(
				"orden="+printing.getOrden()+sentencesSql.getComa()+
				"fechaImpresion='"+Formatos.dateTostringFechacompleta(printing.getFechaImpresion())+"'"+sentencesSql.getComa()+
				"fechaCreacion='"+Formatos.dateTostringFechacompleta(printing.getFechaCreacion())+"'"+sentencesSql.getComa()+
				"estado='"+printing.getEstado()+"'"
			, "printing", "cons = "+printing.getCons());
		
		try {
			this.namedParameterJdbcTemplate.getJdbcTemplate().execute(sql);
		} catch (Exception e) {
			Logger.getLogger(PrintingDaoImpl.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
