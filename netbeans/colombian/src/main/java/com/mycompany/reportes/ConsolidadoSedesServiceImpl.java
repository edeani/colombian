/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reportes;

import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.entidades.Sedes;
import com.mycompany.mapper.ConsolidadoMapper;
import com.mycompany.util.Conexion;
import com.mycompany.util.Formatos;
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
        Double tu=0D;
        Double tc=0D;
        for (int i = 0; i < sedes.size(); i++) {
            //System.out.println("ciclo "+i + sedes.get(i).getSed_nombre());
            Connection connection=null;
            //Me conecto a la base de datos
            Conexion conexion = new Conexion();
           
            conexion.establecerConexion(sedes.get(i));
            connection = conexion.getConexion();
            
            if(connection != null){
            ResultSet rs = null;

            String query = "select 'Pollo',sum((ordenes + llevar +mesas)) as unidades, "+
                           "(select sum(valor_consignacion) as consignacion from consignaciones where fecha between '"+fi_+"' and '"+ff_+"') as consignacion ,"+
                           "(select case when sum(m.valor_total) is null then sub2.vl else sum(m.valor_total) + sub2.vl end as totalventas from mesa m,( "+
                           " SELECT case when sum(ll.valor_total) is null then  sub1.vo else sum(ll.valor_total) + sub1.vo end  as vl from llevar ll,( "+
                           " SELECT case when sum(o.valor_total) is null then 0 else sum(o.valor_total) end  as vo from orden o where estado_orden = 'A' and fecha_orden between   '"+fi_+"' and '"+ff_+"'"+
                           " )sub1 where estado_orden = 'A' and fecha_orden between  '"+fi_+"' and '"+ff_+"' )sub2 where estado_orden = 'A' and  fecha_orden between '"+fi_+"' and '"+ff_+"') as ventas " +
                           " from( select d.*, (case when sum(deto.numero_unidades * ppi3.porcentaje) is null then  0 else  sum(deto.numero_unidades * ppi3.porcentaje) end)   ordenes "+
                           " from( select c.*,(case when sum(dl.numero_unidades * ppi2.porcentaje_llevar) is null then 0 else sum(dl.numero_unidades * ppi2.porcentaje_llevar) end)  as llevar from ( select b.*,(case when sum(dm.numero_unidades * ppi1.porcentaje_mesas) is null then 0 else sum(dm.numero_unidades * ppi1.porcentaje_mesas) end )as mesas   from ( select a.*,ppi.codigo_producto,sum(da.numero_unidades * ppi.porcentaje) as averias,ppi.porcentaje as porcentaje, "+
	                   " ppi.porcentaje_llevar as porcentaje_llevar,ppi.porcentaje_mesas as porcentaje_mesas from ( select inv.codigo_producto_inventario as codigo_producto_inventario,inv.descripcion_producto as producto, "+
	                   " (select inv1.stock_hoy from inventario inv1 where inv1.fecha_inicial = '"+fi_+"' and inv1.codigo_producto_inventario = inv.codigo_producto_inventario) as inicial, "+
                           " (select inv1.stock_real from inventario inv1 where inv1.fecha_final = '"+ff_+"' and inv1.codigo_producto_inventario = inv.codigo_producto_inventario) as stock_real, "+
                           " sum(df.numero_unidades) as compras   from  inventario inv  left join (select * from detalle_factura df where df.fecha_factura  between '"+fi_+"' and '"+ff_+"')df on df.codigo_producto_inventario = inv.codigo_producto_inventario "+
                           " left join (select * from factura f where f.fecha_factura between '"+fi_+"' and '"+ff_+"')f on f.numero_factura = df.numero_factura and f.estado_factura = 'A' "+
                           " where inv.codigo_producto_inventario in(1,2,131,134) group by inv.codigo_producto_inventario )a " +
                           " left join producto_por_inventario ppi on ppi.codigo_producto_inventario = a.codigo_producto_inventario left join (select da.*,a.fecha_averia from averias a inner join detalle_averias da on a.numero_averia=da.numero_averia and a.estado_averia='A' "+
			   " where a.fecha_averia between '"+fi_+"' and '"+ff_+"' "+
                           ") da on da.codigo_producto = ppi.codigo_producto group by 1 )b left join producto_por_inventario ppi1 on ppi1.codigo_producto_inventario = b.codigo_producto_inventario "+
                           " left join (select dm.*,m.fecha_orden,m.valor_total from mesa m  inner join detalle_mesa dm on m.numero_orden = dm.numero_orden and m.estado_orden = 'A' "+
                           " where m.fecha_orden between '"+fi_+"' and '"+ff_+"' ) dm on dm.codigo_producto = ppi1.codigo_producto group by 1 "+
                           " )c left join producto_por_inventario ppi2 on ppi2.codigo_producto_inventario = c.codigo_producto_inventario left join (select dl.*,l.fecha_orden,l.valor_total from llevar l inner join detalle_llevar dl on dl.numero_orden = l.numero_orden and l.estado_orden = 'A' " +
	                   " where l.fecha_orden between '"+fi_+"' and '"+ff_+"' ) dl   on dl.codigo_producto = ppi2.codigo_producto group by 1 )d left join producto_por_inventario ppi3 on ppi3.codigo_producto_inventario = d.codigo_producto_inventario "+
                           " left join (select deto.*   ,o. fecha_orden,o.valor_total from orden o inner join detalle_orden deto on deto.numero_orden = o.numero_orden and o.estado_orden = 'A' "+
			   " where o.fecha_orden between '"+fi_+"' and '"+ff_+"'  ) deto    on deto.codigo_producto = ppi3.codigo_producto group by 1 )e ";

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
           
            }else {
                    ConsolidadoMapper cons = new ConsolidadoMapper();

                    cons.setSede(sedes.get(i).getSed_nombre()+" NO CONECTA A LA BASE DE DATOS");
                    cons.setUnidades("0");
                    cons.setVenta("0");

                    consolidados.add(cons);
                    
                     user.setMensaje("NO CONECTA LA BASE DE DATOS "+user.getSede().getSed_nombre());
        
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
