/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adiministracion.rowmapper;

import com.mycompany.mapper.VentasMapper;
import com.mycompany.util.Constants;
import com.mycompany.util.Formatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Anlod
 */
public class VentasMapperRowMapper implements RowMapper<VentasMapper> {

    @Override
    public VentasMapper mapRow(ResultSet rs, int i) throws SQLException {
        VentasMapper ventasMapperMapped = new VentasMapper();

        Formatos formatosVentasMapper = new Formatos();
        final String tipoColumn = "tipo";
        final String empty = "";
        if (formatosVentasMapper.hasColumn(rs, tipoColumn)) {
            ventasMapperMapped.setTipo(rs.getString(tipoColumn));
        } else {
            ventasMapperMapped.setTipo(empty);
        }

        ventasMapperMapped.setCodigo_producto(rs.getLong("codigo_producto"));
        ventasMapperMapped.setDescripcion_producto(rs.getString("descripcion_producto"));
        ventasMapperMapped.setNumero_unidades(String.valueOf(rs.getInt("numero_unidades")));
        ventasMapperMapped.setValor_producto(String.format(Constants.Formatos.FORMAT_FLOAT_NODECIMALS,
                rs.getFloat("valor_producto")));

        final String totalProdCol = "total_producto";
        if (formatosVentasMapper.hasColumn(rs, totalProdCol)) {
            final Float valueTotalProd = rs.getFloat(totalProdCol);
            ventasMapperMapped.setTotal_producto(String.format(Constants.Formatos.FORMAT_FLOAT_NODECIMALS,
                    Objects.isNull(valueTotalProd) ? 0f : valueTotalProd));
        } else {
            ventasMapperMapped.setTotal_producto("0");
        }

        return ventasMapperMapped;
    }

}
