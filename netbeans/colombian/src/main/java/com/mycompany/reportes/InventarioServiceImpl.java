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
            String query = "select h.*,  h.fisico - h.final as diferencia from( "
                    + " select g.codigo_producto_inventario,g.producto,case when g.inicial is null then 0 else g.inicial end as inicial,g.compras,g.ventas,g.averias,g.traslados "
                    + " ,g.inicial + g.compras - g.ventas - g.averias - g.traslados as final,g.fisico from( "
                    + " select f.codigo_producto_inventario,f.producto,case when f.inicial is null then 0 else f.inicial end as inicial,case when f.compras is null then 0 else f.compras end as compras, "
                    + " case when f.ventas is null then 0 else f.ventas end as ventas, case when f.averias is null then 0 else f.averias end as averias, "
                    + " case when f.traslados is null then 0 else f.traslados end as traslados,case when f.stock_real is null then 0 else f.stock_real end as fisico "
                    + " from (select e.*,(o+l+m) as ventas from ( select e.* , case  when ordenes is null then 0 else ordenes end as o,case when llevar is null then 0 else llevar end as l,case when mesas is null then 0 else mesas end as m,sum(dt.numero_unidades) as traslados "
                    + " from( select d.*,sum(deto.numero_unidades * ppi3.porcentaje) as ordenes "
                    + " from( select c.*,sum(dl.numero_unidades * ppi2.porcentaje_llevar)  as llevar  "
                    + " from ( select b.*,sum(dm.numero_unidades * ppi1.porcentaje_mesas) as mesas from "
                    + " ( select a.*,ppi.codigo_producto,sum(da.numero_unidades * ppi.porcentaje) as averias,ppi.porcentaje as porcentaje, "
                    + " ppi.porcentaje_llevar as porcentaje_llevar,ppi.porcentaje_mesas as porcentaje_mesas "
                    + " from ( select inv.codigo_producto_inventario as codigo_producto_inventario,inv.descripcion_producto as producto, (select inv1.stock_hoy from inventario inv1 where inv1.fecha_inicial = '" + formato.dateTostring(dfDefault.format(Finicial)) + "' and inv1.codigo_producto_inventario = inv.codigo_producto_inventario) as inicial, "
                    + " (select inv1.stock_real from inventario inv1 where inv1.fecha_final = '" + formato.dateTostring(dfDefault.format(Ffinal)) + "' and inv1.codigo_producto_inventario = inv.codigo_producto_inventario) as stock_real, "
                    + " (select sum(df.numero_unidades) as compras from factura f "
                    + " inner join detalle_factura df on f.numero_factura = df.numero_factura and f.estado_factura = 'A' "
                    + " where f.fecha_factura between '"+formato.dateTostring(dfDefault.format(Finicial))+"'  and '"+formato.dateTostring(dfDefault.format(Ffinal))+"' AND f.estado_factura= 'A' and  df.codigo_producto_inventario = inv.codigo_producto_inventario) as compras "
                    + " from  inventario inv "
                    + ")a "
                    + " left join producto_por_inventario ppi on ppi.codigo_producto_inventario = a.codigo_producto_inventario "
                    + " left join (select da.*,a.fecha_averia from averias a inner join detalle_averias da on a.numero_averia=da.numero_averia and a.estado_averia='A' "
                    + " where a.fecha_averia between '" + formato.dateTostring(dfDefault.format(Finicial)) + "'  and '" + formato.dateTostring(dfDefault.format(Ffinal)) + "' "
                    + " ) da on da.codigo_producto = ppi.codigo_producto "
                    + " group by 1 "
                    + " )b "
                    + " left join producto_por_inventario ppi1 on ppi1.codigo_producto_inventario = b.codigo_producto_inventario "
                    + " left join (select dm.*,m.fecha_orden from mesa m  inner join detalle_mesa dm on m.numero_orden = dm.numero_orden and m.estado_orden = 'A' "
                    + " where m.fecha_orden between '" + formato.dateTostring(dfDefault.format(Finicial)) + "'  and '" + formato.dateTostring(dfDefault.format(Ffinal)) + "' "
                    + " ) dm "
                    + " on dm.codigo_producto = ppi1.codigo_producto "
                    + " group by 1 )c "
                    + " left join producto_por_inventario ppi2 on ppi2.codigo_producto_inventario = c.codigo_producto_inventario "
                    + " left join (select dl.*,l.fecha_orden from llevar l inner join detalle_llevar dl on dl.numero_orden = l.numero_orden and l.estado_orden = 'A' "
                    + " where l.fecha_orden between '" + formato.dateTostring(dfDefault.format(Finicial)) + "'  and '" + formato.dateTostring(dfDefault.format(Ffinal)) + "' "
                    + " ) dl   on dl.codigo_producto = ppi2.codigo_producto "
                    + " group by 1 )d "
                    + " left join producto_por_inventario ppi3 on ppi3.codigo_producto_inventario = d.codigo_producto_inventario "
                    + " left join (select deto.*,o.fecha_orden from orden o inner join detalle_orden deto on deto.numero_orden = o.numero_orden and o.estado_orden = 'A' "
                    + " where o.fecha_orden between '" + formato.dateTostring(dfDefault.format(Finicial)) + "'  and '" + formato.dateTostring(dfDefault.format(Ffinal)) + "' "
                    + " ) deto on deto.codigo_producto = ppi3.codigo_producto group by 1 )e  "
                    + " left join (select dt.* from traslado t inner join detalle_traslado dt on dt.numero_traslado = t.numero_traslado and t.estado_traslado = 'A' "
                    + " where t.fecha_traslado between '" + formato.dateTostring(dfDefault.format(Finicial)) + "'  and '" + formato.dateTostring(dfDefault.format(Ffinal)) + "' "
                    + " ) dt     on dt.codigo_producto = e.codigo_producto_inventario "
                    + " group by 1 )e)f )g )h order by 1";

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
                    i.setVentas(rs.getFloat(5));
                    i.setConsumo(rs.getFloat(6));
                    i.setTraslados(rs.getFloat(7));
                    i.setInventarioFinal(rs.getFloat(8));
                    i.setReal(rs.getFloat(9));
                    i.setDiferencia(rs.getFloat(10));

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
