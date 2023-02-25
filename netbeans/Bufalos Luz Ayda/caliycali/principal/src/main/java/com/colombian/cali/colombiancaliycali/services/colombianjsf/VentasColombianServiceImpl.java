/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services.colombianjsf;


import com.mycompany.mapper.VentasMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 *
 * @author joseefren
 */
public class VentasColombianServiceImpl implements VentasColombianService {

    

    private List<VentasMapper> ventasMesa = new ArrayList<VentasMapper>();
    private List<VentasMapper> ventasDomicilio = new ArrayList<VentasMapper>();
    private List<VentasMapper> ventasMostrador = new ArrayList<VentasMapper>();
    private List<VentasMapper> ventas = new ArrayList<VentasMapper>();

    private Double totalVenta;
    private Double totalDomicilios;
    private Double totalMesa;
    private Double totalMostrador;

    @Override
    public void ventas(Date fi, Date ff) {

        /*
        conexion.establecerConexion(user.getSede());
        connection = conexion.getConexion();
        totalDomicilios = 0D;
        totalMesa = 0D;
        totalMostrador = 0D;
        totalVenta = 0D;
        if (connection != null) {
            ResultSet rs = null;
            ResultSet rs2 = null;

            Formatos formato = new Formatos();

            DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);

            String query = "SELECT 'DOMICILIOS' tipo,productos.codigo_producto,   productos.descripcion_producto,   detalle_orden.valor_producto, 	SUM(detalle_orden.numero_unidades) numero_unidades "
                    + " FROM detalle_orden,   orden,   productos   WHERE ( orden.numero_orden = detalle_orden.numero_orden ) and  ( orden.numero_telefono = detalle_orden.numero_telefono ) and   "
                    + "( productos.codigo_producto = detalle_orden.codigo_producto ) and  ( ( orden.fecha_orden BETWEEN '" + formato.dateTostring(dfDefault.format(fi)) + "' and '" + formato.dateTostring(dfDefault.format(ff)) + "') AND      ( orden.estado_orden = 'A' ) )    "
                    + " GROUP BY productos.codigo_producto, "
                    + " productos.descripcion_producto, "
                    + " detalle_orden.valor_producto "
                    + " UNION SELECT  'MESAS', productos.codigo_producto,   productos.descripcion_producto,   detalle_mesa.valor_producto, SUM(detalle_mesa.numero_unidades) "
                    + " FROM detalle_mesa,    mesa,    productos  "
                    + " WHERE ( mesa.numero_orden = detalle_mesa.numero_orden ) and  ( productos.codigo_producto = detalle_mesa.codigo_producto ) and    ( ( mesa.fecha_orden between '" + formato.dateTostring(dfDefault.format(fi)) + "' and '" + formato.dateTostring(dfDefault.format(ff)) + "') AND  "
                    + " ( mesa.estado_orden = 'A' ) )    GROUP BY productos.codigo_producto,productos.descripcion_producto,   detalle_mesa.valor_producto UNION "
                    + " SELECT  'MOSTRADOR',productos.codigo_producto,   productos.descripcion_producto,   detalle_llevar.valor_producto, SUM(detalle_llevar.numero_unidades) "
                    + " FROM detalle_llevar,   llevar,   productos   WHERE ( llevar.numero_orden = detalle_llevar.numero_orden ) and  "
                    + " ( productos.codigo_producto = detalle_llevar.codigo_producto ) and  "
                    + " ( ( llevar.fecha_orden between '" + formato.dateTostring(dfDefault.format(fi)) + "' and '" + formato.dateTostring(dfDefault.format(ff)) + "') AND  ( llevar.estado_orden = 'A' ) )    "
                    + " GROUP BY productos.codigo_producto, productos.descripcion_producto, "
                    + " detalle_llevar.valor_producto  ORDER BY 1, 2, 3";

            String query2 = "select tipo,codigo_producto,descripcion_producto, valor_producto,sum(numero_unidades) numero_unidades from(SELECT 'DOMICILIOS' tipo,productos.codigo_producto,   productos.descripcion_producto,   detalle_orden.valor_producto, 	SUM(detalle_orden.numero_unidades) numero_unidades "
                    + " FROM detalle_orden,   orden,   productos   WHERE ( orden.numero_orden = detalle_orden.numero_orden ) and  ( orden.numero_telefono = detalle_orden.numero_telefono ) and   "
                    + "( productos.codigo_producto = detalle_orden.codigo_producto ) and  ( ( orden.fecha_orden BETWEEN '" + formato.dateTostring(dfDefault.format(fi)) + "' and '" + formato.dateTostring(dfDefault.format(ff)) + "') AND      ( orden.estado_orden = 'A' ) )    "
                    + " GROUP BY productos.codigo_producto, "
                    + " productos.descripcion_producto, "
                    + " detalle_orden.valor_producto "
                    + " UNION SELECT  'MESAS', productos.codigo_producto,   productos.descripcion_producto,   detalle_mesa.valor_producto, SUM(detalle_mesa.numero_unidades) "
                    + " FROM detalle_mesa,    mesa,    productos  "
                    + " WHERE ( mesa.numero_orden = detalle_mesa.numero_orden ) and  ( productos.codigo_producto = detalle_mesa.codigo_producto ) and    ( ( mesa.fecha_orden between '" + formato.dateTostring(dfDefault.format(fi)) + "' and '" + formato.dateTostring(dfDefault.format(ff)) + "') AND  "
                    + " ( mesa.estado_orden = 'A' ) )    GROUP BY productos.codigo_producto,productos.descripcion_producto,   detalle_mesa.valor_producto UNION "
                    + " SELECT  'MOSTRADOR',productos.codigo_producto,   productos.descripcion_producto,   detalle_llevar.valor_producto, SUM(detalle_llevar.numero_unidades) "
                    + " FROM detalle_llevar,   llevar,   productos   WHERE ( llevar.numero_orden = detalle_llevar.numero_orden ) and  "
                    + " ( productos.codigo_producto = detalle_llevar.codigo_producto ) and  "
                    + " ( ( llevar.fecha_orden between '" + formato.dateTostring(dfDefault.format(fi)) + "' and '" + formato.dateTostring(dfDefault.format(ff)) + "') AND  ( llevar.estado_orden = 'A' ) )    "
                    + " GROUP BY productos.codigo_producto, productos.descripcion_producto, "
                    + " detalle_llevar.valor_producto  ORDER BY 1, 2, 3)a GROUP BY codigo_producto,descripcion_producto";
            Statement st = null;
            Statement st2 = null;
            try {
                st = connection.createStatement();
                st2 = connection.createStatement();
                PreparedStatement ps = connection.prepareStatement(query);
                PreparedStatement ps2 = connection.prepareStatement(query2);

           // ps.setDate(1, d);
                rs = ps.executeQuery();
                rs2 = ps2.executeQuery();
                totalDomicilios = 0D;
                totalMesa = 0D;
                totalMostrador = 0D;
                totalVenta = 0D;
                while (rs.next()) {
                    VentasMapper v = new VentasMapper();

                    v.setCodigo_proucto(rs.getLong("codigo_producto"));
                    v.setDescripcion_producto(rs.getString("descripcion_producto"));
                    v.setNumero_unidades(formato.numeroToStringFormato(rs.getLong("numero_unidades")));
                    v.setTipo(rs.getString("tipo"));
                    v.setValor_producto(formato.numeroToStringFormato(rs.getDouble("valor_producto")));
                    v.setTotal_producto(formato.numeroToStringFormato(rs.getDouble("valor_producto") * rs.getLong("numero_unidades")));

                    if (rs.getString("tipo").equals("DOMICILIOS")) {
                        totalDomicilios += rs.getDouble("valor_producto") * rs.getLong("numero_unidades");
                        getVentasDomicilio().add(v);
                    } else if (rs.getString("tipo").equals("MESAS")) {
                        totalMesa += rs.getDouble("valor_producto") * rs.getLong("numero_unidades");
                        getVentasMesa().add(v);
                    } else if (rs.getString("tipo").equals("MOSTRADOR")) {
                        totalMostrador += rs.getDouble("valor_producto") * rs.getLong("numero_unidades");
                        getVentasMostrador().add(v);
                    }

                    // totalVenta+=rs.getDouble("valor_producto") * rs.getLong("numero_unidades");
             
                    // ventas.add(v);
                }

                while (rs2.next()) {
                    VentasMapper v = new VentasMapper();

                    v.setCodigo_proucto(rs2.getLong("codigo_producto"));
                    v.setDescripcion_producto(rs2.getString("descripcion_producto"));
                    v.setNumero_unidades(formato.numeroToStringFormato(rs2.getLong("numero_unidades")));
                    v.setTipo(rs2.getString("tipo"));
                    v.setValor_producto(formato.numeroToStringFormato(rs2.getDouble("valor_producto")));
                    v.setTotal_producto(formato.numeroToStringFormato(rs2.getDouble("valor_producto") * rs2.getLong("numero_unidades")));

                    totalVenta += rs2.getDouble("valor_producto") * rs2.getLong("numero_unidades");

                    ventas.add(v);

                }
            } catch (Exception e) {

                System.out.println(e.getMessage());
          //JOptionPane.showConfirmDialog(null, e.getMessage());

            }

            conexion.cerrar(rs);
            conexion.cerrar(st);
            conexion.destruir();
        } else {
            user.setMensaje("NO CONECTA LA BASE DE DATOS " + user.getSede().getSed_nombre());
        }*/

    }

   


    /**
     * @return the totalVenta
     */
    @Override
    public Double getTotalVenta() {
        return totalVenta;
    }

    /**
     * @return the totalDomicilios
     */
    @Override
    public Double getTotalDomicilios() {
        return totalDomicilios;
    }

    /**
     * @return the totalMesa
     */
    @Override
    public Double getTotalMesa() {
        return totalMesa;
    }

    /**
     * @return the totalMostrador
     */
    @Override
    public Double getTotalMostrador() {
        return totalMostrador;
    }

    /**
     * @return the ventasMesa
     */
    public List<VentasMapper> getVentasMesa() {
        return ventasMesa;
    }

    /**
     * @return the ventasDomicilio
     */
    @Override
    public List<VentasMapper> getVentasDomicilio() {
        return ventasDomicilio;
    }

    /**
     * @return the ventasMostrador
     */
    @Override
    public List<VentasMapper> getVentasMostrador() {
        return ventasMostrador;
    }

    /**
     * @return the ventas
     */
    @Override
    public List<VentasMapper> getVentas() {
        return ventas;
    }

}
