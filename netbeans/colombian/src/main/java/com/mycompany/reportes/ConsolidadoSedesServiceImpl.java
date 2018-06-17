/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reportes;

import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.entidades.Sedes;
import com.mycompany.mapper.ConsolidadoMapper;
import com.mycompany.mapper.Inventario;
import com.mycompany.util.Conexion;
import com.mycompany.util.Formatos;
import com.mycompany.util.LectorPropiedades;
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
public class ConsolidadoSedesServiceImpl implements ConsolidadoSedesService {

    private UserSessionBean user = UserSessionBean.getInstance();
    private String password = user.getSede().getPassword();
    private String totalVentas;
    private String totalUnidades;
    private String totalConsignacion;

    @Override
    public List<ConsolidadoMapper> consolidadoSede(Date fi, Date ff) {

        List<Sedes> sedes = user.getSedes();
        List<ConsolidadoMapper> consolidados = new ArrayList<ConsolidadoMapper>();
        DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        Formatos formato = new Formatos();
        String fi_ = formato.dateTostring(dfDefault.format(fi));
        String ff_ = formato.dateTostring(dfDefault.format(ff));
        Double tv = 0D;
        Double tu = 0D;
        Double tc = 0D;
        for (int i = 0; i < sedes.size(); i++) {
            //System.out.println("ciclo "+i + sedes.get(i).getSed_nombre());
            Connection connection = null;
            //Me conecto a la base de datos
            Conexion conexion = new Conexion();
            conexion.setUser(user.getSede().getUsuario());
            conexion.setPassword(sedes.get(i).getPassword());

            conexion.setServer(sedes.get(i).getIdentificador() + "/" + sedes.get(i).getBd());

            conexion.establecerConexion();
            connection = conexion.getConexion();

            if (connection != null) {
                ResultSet rs = null;

                String query = "select nivel2.*,(select sum(valor_consignacion) as consignacion  "
                        + "from consignaciones  "
                        + "where fecha between '"+fi_+"' and '"+ff_+"') as consignacion , "
                        + "(select sub2a.totalmesas + sub2a.vl as totalventas from( "
                        + "select case when sum(m.valor_total) is null then 0 else sum(m.valor_total) end  as totalmesas, sub2.vl  "
                        + "    from mesa m, "
                        + "    ( "
                        + "    select sub1a.vl+sub1a.vo as vl from( "
                        + "    SELECT case when sum(ll.valor_total) is null then 0 else sum(ll.valor_total) end as vl, sub1.vo   "
                        + "    from llevar ll, "
                        + "    (   "
                        + "		SELECT case when sum(o.valor_total) is null then 0 else sum(o.valor_total) end  as vo  "
                        + "        from orden o  "
                        + "        where estado_orden = 'A' and fecha_orden between   '"+fi_+"' and '"+ff_+"'  "
                        + "	)sub1  "
                        + "		where estado_orden = 'A' and fecha_orden between  '"+fi_+"' and '"+ff_+"'  "
                        + "	group by 2  "
                        + "    )sub1a "
                        + "	)sub2  "
                        + "where estado_orden = 'A' and  fecha_orden between '"+fi_+"' and '"+ff_+"' "
                        + "group by 2 "
                        + ")sub2a "
                        + ") as ventas from( "
                        + "				select 'Pollo' ,sum(nivel1.mesas+nivel1.ordenes+nivel1.llevar) as unidades "
                        + "                from( "
                        + "                SELECT  "
                        + "                             inventario.codigo_producto_inventario,     "
                        + "                             inventario.descripcion_producto as producto,  "
                        + "                             sum(detalle_mesa.numero_unidades * producto_por_inventario.porcentaje_mesas) as mesas,0 as ordenes,0 as llevar  "
                        + "                        FROM producto_por_inventario,     "
                        + "                             detalle_mesa,     "
                        + "                             mesa,  "
                        + "                             inventario    "
                        + "                       WHERE ( detalle_mesa.codigo_producto = producto_por_inventario.codigo_producto ) and    "
                        + "                             ( inventario.codigo_producto_inventario = producto_por_inventario.codigo_producto_inventario ) and  "
                        + "                             ( detalle_mesa.numero_orden = mesa.numero_orden ) and  "
                        + "                             ( mesa.fecha_orden BETWEEN '"+fi_+"'  and '"+ff_+"')  and  "
                        + "                             ( mesa.estado_orden = 'A') "
                        + "                             and inventario.codigo_producto_inventario in(1,2,131,134)  "
                        + "                    GROUP BY inventario.codigo_producto_inventario,     "
                        + "                             inventario.descripcion_producto  "
                        + "                    union all  "
                        + "                    SELECT   "
                        + "                             inventario.codigo_producto_inventario,     "
                        + "                             inventario.descripcion_producto,0,  "
                        + "                             sum(detalle_orden.numero_unidades * producto_por_inventario.porcentaje) ordenes,0  "
                        + "                        FROM producto_por_inventario,     "
                        + "                             detalle_orden,  "
                        + "                             orden,  "
                        + "                             inventario    "
                        + "                       WHERE ( detalle_orden.codigo_producto = producto_por_inventario.codigo_producto ) and    "
                        + "                             ( inventario.codigo_producto_inventario = producto_por_inventario.codigo_producto_inventario ) and  "
                        + "                             ( detalle_orden.numero_orden = orden.numero_orden ) and  "
                        + "                             ( orden.fecha_orden BETWEEN '"+fi_+"'  and '"+ff_+"')   and  "
                        + "                             ( orden.estado_orden = 'A') "
                        + "                             and inventario.codigo_producto_inventario in(1,2,131,134)  "
                        + "                    GROUP BY inventario.codigo_producto_inventario,     "
                        + "                             inventario.descripcion_producto    "
                        + "                    union all  "
                        + "                    SELECT  "
                        + "                             inventario.codigo_producto_inventario,     "
                        + "                             inventario.descripcion_producto,0,0,  "
                        + "                             sum(detalle_llevar.numero_unidades * producto_por_inventario.porcentaje_llevar) unidades "
                        + "                        FROM producto_por_inventario,     "
                        + "                             detalle_llevar,     "
                        + "                             llevar,  "
                        + "                             inventario    "
                        + "                       WHERE ( detalle_llevar.codigo_producto = producto_por_inventario.codigo_producto ) and    "
                        + "                             ( inventario.codigo_producto_inventario = producto_por_inventario.codigo_producto_inventario ) and  "
                        + "                             ( detalle_llevar.numero_orden = llevar.numero_orden ) and  "
                        + "                             ( llevar.fecha_orden BETWEEN '"+fi_+"'  and '"+ff_+"')  and  "
                        + "                             ( llevar.estado_orden = 'A')  "
                        + "                             and inventario.codigo_producto_inventario in(1,2,131,134)  "
                        + "                    GROUP BY inventario.codigo_producto_inventario,     "
                        + "                             inventario.descripcion_producto  "
                        + "             )nivel1 group by 1 "
                        + "             )nivel2;";

                Statement st = null;

                try {
                    //System.out.println("0 Estado"+conexion.estado);
                    st = connection.createStatement();
                    PreparedStatement ps = connection.prepareStatement(query);
                    // ps.setDate(1, d);
                    rs = ps.executeQuery();

                    if (rs.next()) {
                        ConsolidadoMapper cons = new ConsolidadoMapper();
                        cons.setSede(sedes.get(i).getSed_nombre());
                        cons.setUnidades(formato.numeroToStringFormato(rs.getLong("unidades")));
                        cons.setVenta(formato.numeroToStringFormato(rs.getDouble("ventas")));
                        cons.setConsignacion(formato.numeroToStringFormato(rs.getDouble("consignacion")));
                        tv += rs.getDouble("ventas");
                        tu += rs.getDouble("unidades");
                        tc += rs.getDouble("consignacion");
                        consolidados.add(cons);

                    }

                    conexion.cerrar(rs);
                    conexion.cerrar(st);
                    conexion.destruir();
                } catch (Exception e) {

                    System.out.println(e.getMessage());
                    //JOptionPane.showConfirmDialog(null, e.getMessage());
                }

            } else {
                ConsolidadoMapper cons = new ConsolidadoMapper();

                cons.setSede(sedes.get(i).getSed_nombre() + " NO CONECTA A LA BASE DE DATOS");
                cons.setUnidades("0");
                cons.setVenta("0");

                consolidados.add(cons);

                user.setMensaje("NO CONECTA LA BASE DE DATOS " + user.getSede().getSed_nombre());

            }

        }

        totalVentas = formato.numeroToStringFormato(tv);
        totalUnidades = formato.numeroToStringFormato(tu);
        totalConsignacion = formato.numeroToStringFormato(tc);
        return consolidados;
    }

    /**
     * @return the totalVentas
     */
    public String getTotalVentas() {
        return totalVentas;
    }

    /**
     * @return the totalUnidades
     */
    public String getTotalUnidades() {
        return totalUnidades;
    }

    /**
     * @return the totalConsignacion
     */
    public String getTotalConsignacion() {
        return totalConsignacion;
    }

    /**
     * @param totalConsignacion the totalConsignacion to set
     */
    public void setTotalConsignacion(String totalConsignacion) {
        this.totalConsignacion = totalConsignacion;
    }
}
