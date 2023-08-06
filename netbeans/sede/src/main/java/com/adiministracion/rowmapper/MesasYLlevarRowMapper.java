/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adiministracion.rowmapper;

import com.mycompany.mapper.Mesasyllevar;
import com.mycompany.util.Constants;
import com.mycompany.util.Formatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Anlod
 */
public class MesasYLlevarRowMapper implements RowMapper<Mesasyllevar>{

    private static final Formatos formatosMesasYLlevar = new Formatos();
    
    private static final String FECHA = "fecha";
    private static final String ORDEN = "orden";
    private static final String TIPO = "tipo";
    private static final String VALOR = "valor";
    private static final String MESA = "mesa";
    private static final String COD_MESERA = "cod_mesera";
    private static final String REGISTROS = "registros";
    private static final String HORA = "hora";
    
    @Override
    public Mesasyllevar mapRow(ResultSet rs, int i) throws SQLException {
        Mesasyllevar mesasyllevar = new Mesasyllevar();
        
        mesasyllevar.setFecha(formatosMesasYLlevar.extractDateResultSet(rs, FECHA));
        mesasyllevar.setCodMesera(rs.getString(COD_MESERA));
        
        mesasyllevar.setHora(formatosMesasYLlevar.hasColumn(rs, HORA)?
                formatosMesasYLlevar.dateTostring(
                formatosMesasYLlevar.extractDateResultSet(rs, HORA)
                ,Constants.Formatos.FORMAT_HOUR):null);
        mesasyllevar.setMesa(rs.getString(MESA));
        mesasyllevar.setOrden(rs.getString(ORDEN));
        mesasyllevar.setRegistros(formatosMesasYLlevar.hasColumn(rs, REGISTROS)?
                rs.getString( REGISTROS):null);
        mesasyllevar.setTipo(rs.getString(TIPO));
        mesasyllevar.setValor(rs.getString(VALOR));
        
        return mesasyllevar;
    }
    
    
}
