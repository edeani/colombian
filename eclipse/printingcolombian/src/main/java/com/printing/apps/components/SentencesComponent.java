package com.printing.apps.components;

import org.springframework.stereotype.Component;

@Component
public class SentencesComponent implements Sentences {
	
	private static final String coma = ",";

	@Override
	public String selectJdbTemplate(String parametros, String tabla, String condiciones) {
		StringBuilder sql = new StringBuilder("");
		sql.append("SELECT ").append(parametros).append(" FROM ").append(tabla);
		if (condiciones != null && !"".equals(condiciones)) {
			sql.append(" WHERE ").append(condiciones);
		}
		System.out.println(sql.toString());
		return sql.toString();
	}

	@Override
	public String updateJdbTemplate(String parametros, String tabla, String condiciones) {
		StringBuilder sql = new StringBuilder("");
		sql.append("UPDATE ").append(tabla).append(" SET ").append(parametros);
		if (condiciones != null && !"".equals(condiciones)) {
			sql.append(" WHERE ").append(condiciones);
		}
		return sql.toString();
	}

	@Override
	public String insertJdbTemplate(String parametros, String tabla, String condiciones) {
		StringBuilder sql = new StringBuilder("");
		sql.append("INSERT INTO ").append(tabla).append(" (").append(parametros).append(") VALUES (")
				.append(condiciones).append(")");
		return sql.toString();
	}

	@Override
	public String deleteJdbTemplate(String tabla, String condiciones) {
		StringBuilder sql = new StringBuilder("");
		sql.append("DELETE FROM ").append(tabla);
		if (condiciones != null && !"".equals(condiciones)) {
			sql.append(" WHERE ").append(condiciones);
		}
		return sql.toString();
	}

	@Override
	public String addInsertJdtbTemplate(String values1, String values2, int iteracion) {
		if (iteracion == 0) {
			return values2;
		}
		return values1 + ",(" + values2 + ")";
	}
	
	@Override
	public String getComa() {
		return this.coma;
	}
}
