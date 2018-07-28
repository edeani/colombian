/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reportes;

import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.util.Conexion;
import com.mycompany.util.Formatos;
import com.mycompany.mapper.Inventario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author joseefren
 */
public class InventarioServiceImpl implements InventarioService {

    private UserSessionBean user = UserSessionBean.getInstance();
    private String password = user.getSede().getPassword();

    @Override
    public List<Inventario> traerInventario(Date Ffinal, Date Finicial) {

        Connection connection;
        //Me conecto a la base de datos
        Conexion conexion = new Conexion();
        conexion.setUser(user.getSede().getUsuario());
        if (password == null) {
            conexion.setPassword("");
        } else {
            conexion.setPassword(password);
        }
        conexion.setServer(user.getSede().getIdentificador() + "/" + user.getSede().getBd());
        conexion.establecerConexion();
 
        connection = conexion.getConexion();
        List<Inventario> inventario = new ArrayList<Inventario>();
        if (connection != null) {
            Double caja_inicial = 0D;
            ResultSet rs = null;

            Formatos formato = new Formatos();
            DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
            //java.sql.Date  d = formato.fechaMenos(dfDefault.format(fechaCierre), 1);
            String query = "select nivelc.codigo_producto_inventario,nivelc.producto,nivelc.inicial,nivelc.compras, "
                    + "nivelc.stock_real as fisico,nivelc.mesas+nivelc.ordenes+nivelc.llevar as ventas, "
                    + "nivelc.averias,nivelc.traslados from ( "
                    + "select nivelb.codigo_producto_inventario,nivelb.producto,sum(nivelb.inicial) as inicial, "
                    + "sum(nivelb.stock_real) as stock_real,sum(nivelb.compras) as compras,sum(nivelb.mesas) as mesas "
                    + ",sum(nivelb.ordenes) as ordenes,sum(nivelb.llevar) as llevar,sum(nivelb.averias) as averias, "
                    + "sum(nivelb.traslados) as traslados "
                    + "from( "
                    + "select inv.codigo_producto_inventario,inv.descripcion_producto as producto, "
                    + "sum(inv.stock_hoy) as inicial,0 as stock_real,0 as compras,0 as mesas, 0 as ordenes,0 as llevar,0 as averias "
                    + ",0 as traslados "
                    + "from inventario inv "
                    + "where inv.fecha_inicial between '"+formato.dateTostring(dfDefault.format(Finicial))+"' and '"+formato.dateTostring(dfDefault.format(Ffinal))+"' "
                    + "group by 1,2 "
                    + "union all  "
                    + " "
                    + "select inv.codigo_producto_inventario,inv.descripcion_producto as producto,0, "
                    + "sum(inv.stock_real) as sock_real, 0 as compras, 0, 0 ,0 ,0,0 "
                    + "from inventario inv "
                    + "where inv.fecha_final between '"+formato.dateTostring(dfDefault.format(Finicial))+"' and '"+formato.dateTostring(dfDefault.format(Ffinal))+"' "
                    + "group by 1,2 "
                    + "union all "
                    + "select a0.codigo_producto_inventario,a0.producto,0 as inicial,0 as stock_real, "
                    + "sum(a0.numero_unidades) as compra,0,0,0,0,0 "
                    + "from ( "
                    + "select inv1.codigo_producto_inventario,inv1.descripcion_producto as producto, "
                    + "case when nivela.numero_unidades is null then 0 else nivela.numero_unidades end as numero_unidades "
                    + "from inventario as inv1  "
                    + "left join "
                    + "( "
                    + "select inv.codigo_producto_inventario,inv.descripcion_producto as producto,df.numero_unidades from inventario inv "
                    + "inner join detalle_factura df on df.codigo_producto_inventario = inv.codigo_producto_inventario "
                    + "inner join factura f on f.numero_factura = df.numero_factura and f.fecha_factura between '"+formato.dateTostring(dfDefault.format(Finicial))+"' and '"+formato.dateTostring(dfDefault.format(Ffinal))+"' AND f.estado_factura= 'A' "
                    + ")nivela on nivela.codigo_producto_inventario = inv1.codigo_producto_inventario "
                    + ")a0 "
                    + "group by 1,2 "
                    + "union all "
                    + "SELECT "
                    + "         inventario.codigo_producto_inventario,    "
                    + "         inventario.descripcion_producto as producto,0,0,0, "
                    + "         sum(detalle_mesa.numero_unidades * producto_por_inventario.porcentaje_mesas) as mesas,0,0,0,0 "
                    + "    FROM producto_por_inventario,    "
                    + "         detalle_mesa,    "
                    + "         mesa, "
                    + "         inventario   "
                    + "   WHERE ( detalle_mesa.codigo_producto = producto_por_inventario.codigo_producto ) and   "
                    + "         ( inventario.codigo_producto_inventario = producto_por_inventario.codigo_producto_inventario ) and "
                    + "         ( detalle_mesa.numero_orden = mesa.numero_orden ) and "
                    + "         ( mesa.fecha_orden BETWEEN '"+formato.dateTostring(dfDefault.format(Finicial))+"'  and '"+formato.dateTostring(dfDefault.format(Ffinal))+"')  and "
                    + "         ( mesa.estado_orden = 'A')  "
                    + "GROUP BY inventario.codigo_producto_inventario,    "
                    + "         inventario.descripcion_producto "
                    + "union all "
                    + "SELECT  "
                    + "         inventario.codigo_producto_inventario,    "
                    + "         inventario.descripcion_producto,0,0,0,0, "
                    + "         sum(detalle_orden.numero_unidades * producto_por_inventario.porcentaje) ordenes,0,0,0 "
                    + "    FROM producto_por_inventario,    "
                    + "         detalle_orden, "
                    + "         orden, "
                    + "         inventario   "
                    + "   WHERE ( detalle_orden.codigo_producto = producto_por_inventario.codigo_producto ) and   "
                    + "         ( inventario.codigo_producto_inventario = producto_por_inventario.codigo_producto_inventario ) and "
                    + "         ( detalle_orden.numero_orden = orden.numero_orden ) and "
                    + "         ( orden.fecha_orden BETWEEN '"+formato.dateTostring(dfDefault.format(Finicial))+"'  and '"+formato.dateTostring(dfDefault.format(Ffinal))+"')   and "
                    + "         ( orden.estado_orden = 'A') "
                    + "GROUP BY inventario.codigo_producto_inventario,    "
                    + "         inventario.descripcion_producto   "
                    + "union all "
                    + "SELECT "
                    + "         inventario.codigo_producto_inventario,    "
                    + "         inventario.descripcion_producto,0,0,0,0,0, "
                    + "         sum(detalle_llevar.numero_unidades * producto_por_inventario.porcentaje_llevar) unidades  ,0,0 "
                    + "    FROM producto_por_inventario,    "
                    + "         detalle_llevar,    "
                    + "         llevar, "
                    + "         inventario   "
                    + "   WHERE ( detalle_llevar.codigo_producto = producto_por_inventario.codigo_producto ) and   "
                    + "         ( inventario.codigo_producto_inventario = producto_por_inventario.codigo_producto_inventario ) and "
                    + "         ( detalle_llevar.numero_orden = llevar.numero_orden ) and "
                    + "         ( llevar.fecha_orden BETWEEN '"+formato.dateTostring(dfDefault.format(Finicial))+"'  and '"+formato.dateTostring(dfDefault.format(Ffinal))+"')  and "
                    + "         ( llevar.estado_orden = 'A') "
                    + "GROUP BY inventario.codigo_producto_inventario,    "
                    + "         inventario.descripcion_producto "
                    + "union all "
                    + "SELECT   "
                    + "         inventario.codigo_producto_inventario,    "
                    + "         inventario.descripcion_producto,0,0,0,0,0,0,    "
                    + "         sum(detalle_averias.numero_unidades * producto_por_inventario.porcentaje) as averias,0 "
                    + "    FROM producto_por_inventario,    "
                    + "         detalle_averias,    "
                    + "         averias, "
                    + "         inventario   "
                    + "   WHERE ( detalle_averias.codigo_producto = producto_por_inventario.codigo_producto ) and   "
                    + "         ( inventario.codigo_producto_inventario = producto_por_inventario.codigo_producto_inventario ) and "
                    + "         ( detalle_averias.numero_averia = averias.numero_averia ) and "
                    + "         ( averias.fecha_averia BETWEEN '"+formato.dateTostring(dfDefault.format(Finicial))+"'  and '"+formato.dateTostring(dfDefault.format(Ffinal))+"')  and "
                    + "         ( averias.estado_averia = 'A') "
                    + "GROUP BY inventario.codigo_producto_inventario,    "
                    + "         inventario.descripcion_producto "
                    + "union all "
                    + "SELECT "
                    + "         inventario.codigo_producto_inventario,    "
                    + "         inventario.descripcion_producto,0,0,0,0,0,0,0, "
                    + "         sum(detalle_traslado.numero_unidades) unidades   "
                    + "    FROM producto_por_inventario,    "
                    + "         detalle_traslado,    "
                    + "         traslado, "
                    + "         inventario   "
                    + "   WHERE ( detalle_traslado.codigo_producto = producto_por_inventario.codigo_producto ) and   "
                    + "         ( inventario.codigo_producto_inventario = producto_por_inventario.codigo_producto_inventario ) and "
                    + "         ( detalle_traslado.numero_traslado = traslado.numero_traslado ) and "
                    + "         ( traslado.fecha_traslado BETWEEN '"+formato.dateTostring(dfDefault.format(Finicial))+"'  and '"+formato.dateTostring(dfDefault.format(Ffinal))+"')  and "
                    + "         ( traslado.estado_traslado = 'A') "
                    + "GROUP BY inventario.codigo_producto_inventario,    "
                    + "         inventario.descripcion_producto "
                    + ")nivelb "
                    + "group by 1,2 "
                    + ")nivelc";

            //JOptionPane.showConfirmDialog(null, fechaCierre + "\n" + query +"\n"+d.toString());
            //Ejecutar la consulta
            //+formato.dateTostring(dfDefault.format(Finicial))+"'  and '"+formato.dateTostring(dfDefault.format(Ffinal))+"')  and ( factura.estado_factura = 'A') and (detalle_factura.numero_factura=factura.numero_factura)"+


            Statement st = null;
            try {
                st = connection.createStatement();
                PreparedStatement ps = connection.prepareStatement(query);

                // ps.setDate(1, d);

                rs = ps.executeQuery();

                while (rs.next()) {
                    Inventario i = new Inventario();

                    i.setCodigo(rs.getInt(1));
                    i.setNombreProducto(rs.getString(2));
                    i.setInicial(rs.getFloat(3));
                    i.setCompras(rs.getFloat(4));
                    i.setVentas(rs.getFloat(6));
                    i.setConsumo(rs.getFloat(7));
                    i.setTraslados(rs.getFloat(8));
                    i.setInventarioFinal(rs.getFloat(3)+rs.getFloat(4)-rs.getFloat(6)-rs.getFloat(8)-rs.getFloat(7));
                    i.setReal(rs.getFloat(5));
                    i.setDiferencia(i.getReal()-i.getInventarioFinal());

                    inventario.add(i);

                }

                conexion.cerrar(rs);
                conexion.cerrar(st);
                conexion.destruir();

            } catch (Exception e) {

                System.out.println(e.getMessage());
                //JOptionPane.showConfirmDialog(null, e.getMessage());

            }
        }else{
            user.setMensaje("NO CONECTA LA BASE DE DATOS "+user.getSede().getSed_nombre());
        }


            return inventario;

        }

    }
