/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adiministracion.rowmapper;

import com.mycompany.mapper.Mesasyllevar;
import com.mycompany.util.ConstantsColombianJsf;
import com.mycompany.util.Formatos;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    /**
     * Verificación de las columnas
     */
    private Boolean colRegistros = Boolean.TRUE;
    private Boolean colHora = Boolean.TRUE;
    private Boolean colsVerified = Boolean.FALSE;
    @Override
    public Mesasyllevar mapRow(ResultSet rs, int i) throws SQLException {
        
        /**
         * Verificar las columnas
         */
        if(Boolean.FALSE.equals(colsVerified)){
            colHora = formatosMesasYLlevar.hasColumn(rs, HORA);
            colRegistros = formatosMesasYLlevar.hasColumn(rs, REGISTROS);
            colsVerified = Boolean.TRUE;
        }
        
        Mesasyllevar mesasyllevar = new Mesasyllevar();
        
        mesasyllevar.setFecha(formatosMesasYLlevar.extractDateTimeResultSet(rs, FECHA));
        mesasyllevar.setCodMesera(rs.getString(COD_MESERA));
        
        mesasyllevar.setHora(Boolean.TRUE.equals(colHora)?
                formatosMesasYLlevar.dateTostring(formatosMesasYLlevar.extractDateTimeResultSet(rs, HORA)
                ,ConstantsColombianJsf.Formatos.FORMAT_HOUR):null);
        mesasyllevar.setMesa(rs.getString(MESA));
        mesasyllevar.setOrden(rs.getString(ORDEN));
        mesasyllevar.setRegistros(colRegistros?
                rs.getString( REGISTROS):null);
        mesasyllevar.setTipo(rs.getString(TIPO));
        mesasyllevar.setValor(rs.getString(VALOR));
        
        return mesasyllevar;
    }
    
    
}
