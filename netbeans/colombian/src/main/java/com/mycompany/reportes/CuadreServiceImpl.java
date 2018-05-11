/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reportes;

import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.util.Conexion;
import com.mycompany.util.Formatos;
import com.mycompany.mapper.Cuadre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author joseefren
 */
public class CuadreServiceImpl implements CuadreService {

    private final UserSessionBean user = UserSessionBean.getInstance();
    private final String password = user.getSede().getPassword();
    private Double valorVentas;
    private Double valorGastos;
    private Double valorConsignaciones;
    private Double valorDescuentos;
    private Double valorPagosTarjeta;

    @Override
    public List<Cuadre> cuadreDia(Date fi, Date ff) {
        Connection connection;
        //Me conecto a la base de datos
        Conexion conexion = new Conexion();
        conexion.setUser(user.getSede().getUsuario());
        if (password == null) {
            conexion.setPassword("");
        }
        conexion.setServer(user.getSede().getIdentificador() + "/" + user.getSede().getBd());
        conexion.establecerConexion();
        connection = conexion.getConexion();
        setValorVentas((Double) 0D);
        setValorGastos((Double) 0D);
        setValorConsignaciones((Double) 0D);
        setValorDescuentos(0D);
        setValorPagosTarjeta(0D);
        List<Cuadre> cuadre = new ArrayList<Cuadre>();
        if (connection != null) {
            ResultSet rs = null;

            Formatos formato = new Formatos();
            DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
            String query = "SELECT cierre_diario.fecha AS FECHA, "
                    + "cierre_diario.valor_ventas AS VENTAS, "
                    + "cierre_diario.valor_gastos AS GASTOS, "
                    + "cierre_diario.consignaciones AS CONSIGNACIONES, "
                    + "cierre_diario.caja_real AS CAJA_REAL, "
                    + "cierre_diario.descuento_ventas AS DESCUENTOS, "
                    + "cierre_diario.pago_tarjetas AS PAGO_TARJETAS "
                    + "FROM cierre_diario "
                    + "WHERE cierre_diario.fecha between '" + formato.dateTostring(dfDefault.format(fi)) + "'  and '" + formato.dateTostring(dfDefault.format(ff)) + "' " 
                    + "ORDER BY FECHA";

            Statement st = null;

            try {
                st = connection.createStatement();
                PreparedStatement ps = connection.prepareStatement(query);

                // ps.setDate(1, d);

                rs = ps.executeQuery();

                while (rs.next()) {
                    Cuadre c = new Cuadre();

                    c.setFecha(rs.getDate("FECHA"));
                    c.setValorVentas(formato.numeroToStringFormato(rs.getDouble("VENTAS")));
                    c.setValorGastos(formato.numeroToStringFormato(rs.getDouble("GASTOS")));
                    c.setValorConsignaciones(formato.numeroToStringFormato(rs.getDouble("CONSIGNACIONES")));
                    c.setValorDescuentos(formato.numeroToStringFormato(rs.getDouble("DESCUENTOS")));
                    c.setValorPagosTarjeta(formato.numeroToStringFormato(rs.getDouble("PAGO_TARJETAS")));
                    c.setValorCajaReal(formato.numeroToStringFormato(rs.getDouble("CAJA_REAL")));
                    valorVentas += rs.getDouble("VENTAS");
                    valorGastos += rs.getDouble("GASTOS");
                    valorConsignaciones += rs.getDouble("CONSIGNACIONES");
                    valorDescuentos+=rs.getDouble("DESCUENTOS");
                    valorPagosTarjeta+=rs.getDouble("PAGO_TARJETAS");

                    cuadre.add(c);

                }

            } catch (Exception e) {

                System.out.println(e.getMessage());

            }


            conexion.cerrar(rs);
            conexion.cerrar(st);
            conexion.destruir();
        } else {
            user.setMensaje("NO CONECTA LA BASE DE DATOS " + user.getSede().getSed_nombre());
        }
        return cuadre;
    }

    /**
     * @return the valorVentas
     */
    public Double getValorVentas() {
        return valorVentas;
    }

    /**
     * @param valorVentas the valorVentas to set
     */
    public void setValorVentas(Double valorVentas) {
        this.valorVentas = valorVentas;
    }

    /**
     * @return the valorGastos
     */
    public Double getValorGastos() {
        return valorGastos;
    }

    /**
     * @param valorGastos the valorGastos to set
     */
    public void setValorGastos(Double valorGastos) {
        this.valorGastos = valorGastos;
    }

    /**
     * @return the valorConsignaciones
     */
    @Override
    public Double getValorConsignaciones() {
        return valorConsignaciones;
    }

    /**
     * @param valorConsignaciones the valorConsignaciones to set
     */
    public void setValorConsignaciones(Double valorConsignaciones) {
        this.valorConsignaciones = valorConsignaciones;
    }

    @Override
    public Double getValorDescuentos() {
        return valorDescuentos;
    }

    public void setValorDescuentos(Double valorDescuentos) {
        this.valorDescuentos = valorDescuentos;
    }
    
    @Override
    public Double getValorPagosTarjeta() {
        return valorPagosTarjeta;
    }
 
    public void setValorPagosTarjeta(Double valorPagosTarjeta) {
        this.valorPagosTarjeta = valorPagosTarjeta;
    }
    
    
}
