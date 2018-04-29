/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.jsf;


import com.administracion.entidad.Users;
import com.administracion.service.autorizacion.ConnectsAuth;
import com.administracion.service.autorizacion.SecurityService;
import com.mycompany.dto.GastosColombianDto;
import com.mycompany.dto.GastosReporteTotalColombialDto;
import com.mycompany.mapper.GastosMapper;
import com.mycompany.util.Formatos;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author joseefren
 */
@Service
public class GastosColombianServiceImpl implements GastosColombianService {

    @Autowired
    private SecurityService securityService;
    @Autowired
    private ConnectsAuth connectsAuth;

    private JdbcTemplate jdbctemplate;

    private List<GastosMapper> gastos1;
    private List<GastosMapper> gastos2;
    private List<GastosMapper> gastos3;
    private Double total;

    @Override
    public void gastos(Date fi, Date ff,String subsede) {
        Users user = securityService.getCurrentUser();
        this.jdbctemplate = new JdbcTemplate(connectsAuth.getDataSourceSubSede(subsede));
        List<GastosReporteTotalColombialDto> gastosTotales = new ArrayList<>();
        try {
            Formatos formato = new Formatos();
            DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);

            String f1 = formato.dateTostring(dfDefault.format(fi));
            String f2 = formato.dateTostring(dfDefault.format(ff));

            String query = "select c1.*,c2.*,c3.*,sum(g.gas_valor) as valor_gastos,sum(g.gas_cantidad) as cantidad from concepto c1 "
                    + " inner join concepto c2 on c2.con_cod <> c1.con_cod and c2.con_padre = c1.con_cod "
                    + " inner join concepto c3 on c3.con_cod <> c2.con_cod and c3.con_padre = c2.con_cod "
                    + " inner join gastos g on g.gas_concepto = c3.con_cod "
                    + " where (g.gas_fecha between '" + f1 + "'  and '" + f2 + "') "
                    + " group by c1.con_nivel, g.gas_concepto "
                    + " order by c1.con_cod,c2.con_cod";
            gastosTotales = this.jdbctemplate.query(query, new BeanPropertyRowMapper<>(GastosReporteTotalColombialDto.class));
            discriminarGastos(gastosTotales);
        } catch (DataAccessException e) {
            System.out.println("Error cuadreDia::" + e.getMessage());
        }
       /*

        Connection connection;
        //Me conecto a la base de datos
        Conexion conexion = new Conexion();
        if (password == null) {
            conexion.setPassword("");
        } else {
            conexion.setPassword(password);
        }
        conexion.establecerConexion(user.getSede());
        connection = conexion.getConexion();
        if (connection != null) {

            GastosMapper gasto = new GastosMapper();
            ResultSet rs = null;
            Statement st = null;

            Formatos formato = new Formatos();
            DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);

            String f1 = formato.dateTostring(dfDefault.format(fi));
            String f2 = formato.dateTostring(dfDefault.format(ff));

            String query = "select c1.*,c2.*,c3.*,sum(g.gas_valor) as valor_gastos,sum(g.gas_cantidad) as cantidad from concepto c1 "
                    + " inner join concepto c2 on c2.con_cod <> c1.con_cod and c2.con_padre = c1.con_cod "
                    + " inner join concepto c3 on c3.con_cod <> c2.con_cod and c3.con_padre = c2.con_cod "
                    + " inner join gastos g on g.gas_concepto = c3.con_cod "
                    + " where (g.gas_fecha between '" + f1 + "'  and '" + f2 + "') "
                    + " group by c1.con_nivel, g.gas_concepto "
                    + " order by c1.con_cod,c2.con_cod";
            total = 0D;
            try {
                st = connection.createStatement();
                PreparedStatement ps = connection.prepareStatement(query);

                // ps.setDate(1, d);
                rs = ps.executeQuery();
                Long codigoAnt1 = 0L;
                Long codigoAnt2 = 0L;
                int i1 = -1;
                int i2 = -1;
                List<GastosDto> gastosDto1 = new ArrayList<GastosDto>();
                List<GastosDto> gastosDto2 = new ArrayList<GastosDto>();
                List<GastosDto> gastosDto3 = new ArrayList<GastosDto>();

                gastos1 = new ArrayList<GastosMapper>();
                gastos2 = new ArrayList<GastosMapper>();
                gastos3 = new ArrayList<GastosMapper>();

                while (rs.next()) {
                    GastosDto g = new GastosDto();
                    GastosMapper gm = new GastosMapper();
                    if (rs.getLong(1) != codigoAnt1) {
                        i1++;
                        g.setCodigo(rs.getLong(1));
                        g.setNombre(rs.getString(2));
                        gm.setCodigo("" + g.getCodigo());
                        gm.setNombre(g.getNombre());
                        gm.setPadre("");
                        gastosDto1.add(g);
                        gastos1.add(gm);
                        codigoAnt1 = rs.getLong(1);

                        gastosDto1.get(i1).setValor(gastosDto1.get(i1).getValor() + rs.getDouble(13));
                        gastos1.get(i1).setValor(formato.numeroToStringFormato(gastosDto1.get(i1).getValor()));

                    } else {
                        //Acumular en el nivel 1

                        gastosDto1.get(i1).setValor(gastosDto1.get(i1).getValor() + rs.getDouble(13));
                        gastos1.get(i1).setValor(formato.numeroToStringFormato(gastosDto1.get(i1).getValor()));
                    }
                    g = null;
                    gm = null;

                    g = new GastosDto();
                    gm = new GastosMapper();

                    if (rs.getLong(5) != codigoAnt2) {
                        i2++;

                        g.setCodigo(rs.getLong(5));
                        g.setNombre(rs.getString(6));
                        gm.setCodigo("" + g.getCodigo());
                        gm.setNombre(g.getNombre());
                        gm.setPadre("" + rs.getLong(8));

                        gastosDto2.add(g);
                        gastos2.add(gm);
                        codigoAnt2 = rs.getLong(5);
                        gastosDto2.get(i2).setValor(gastosDto2.get(i2).getValor() + rs.getDouble(13));
                        gastos2.get(i2).setValor(formato.numeroToStringFormato(gastosDto2.get(i2).getValor()));

                    } else {

                        gastosDto2.get(i2).setValor(gastosDto2.get(i2).getValor() + rs.getDouble(13));
                        gastos2.get(i2).setValor(formato.numeroToStringFormato(gastosDto2.get(i2).getValor()));

                    }

                    g = null;
                    gm = null;
                    g = new GastosDto();
                    gm = new GastosMapper();
                    g.setCodigo(rs.getLong(9));
                    g.setNombre(rs.getString(10));
                    g.setValor(rs.getDouble(13));

                    //Avumulo total
                    total += g.getValor();

                    gm.setCodigo("" + g.getCodigo());
                    gm.setNombre(g.getNombre());
                    gm.setValor(formato.numeroToStringFormato(g.getValor()));
                    gm.setPadre("" + rs.getLong(12));
                    gastos3.add(gm);
                    gastosDto3.add(g);
                    g = null;
                    gm = null;
                }
            } catch (Exception e) {
            }

        } else {
            user.setMensaje("NO CONECTA LA BASE DE DATOS " + user.getSede().getSed_nombre());
        }
*/
    }

    public void discriminarGastos(List<GastosReporteTotalColombialDto> gastosTotales) {
        /**
         * TODO: Revisar como mostrar los datos en Spring
         */
        if (gastosTotales != null) {
            Long codigoAnt1 = 0L;
            Long codigoAnt2 = 0L;
            int i1 = -1;
            int i2 = -1;
            GastosColombianDto g = new GastosColombianDto();
            GastosMapper gm = new GastosMapper();
            for (GastosReporteTotalColombialDto gastoTotal : gastosTotales) {
                if (gastoTotal.getCon_cod1() != codigoAnt1.longValue()) {
                    i1++;
                    g.setCodigo(gastoTotal.getCon_cod1());
                    g.setNombre(gastoTotal.getCon_nombre1());
                    
                }
            }

            /*if (rs.getLong(1) != codigoAnt1) {
                i1++;
                g.setCodigo(rs.getLong(1));
                g.setNombre(rs.getString(2));
                gm.setCodigo("" + g.getCodigo());
                gm.setNombre(g.getNombre());
                gm.setPadre("");
                gastosDto1.add(g);
                gastos1.add(gm);
                codigoAnt1 = rs.getLong(1);

                gastosDto1.get(i1).setValor(gastosDto1.get(i1).getValor() + rs.getDouble(13));
                gastos1.get(i1).setValor(formato.numeroToStringFormato(gastosDto1.get(i1).getValor()));

            } else {
                //Acumular en el nivel 1

                gastosDto1.get(i1).setValor(gastosDto1.get(i1).getValor() + rs.getDouble(13));
                gastos1.get(i1).setValor(formato.numeroToStringFormato(gastosDto1.get(i1).getValor()));
            }
            g = null;
            gm = null;

            g = new GastosDto();
            gm = new GastosMapper();

            if (rs.getLong(5) != codigoAnt2) {
                i2++;

                g.setCodigo(rs.getLong(5));
                g.setNombre(rs.getString(6));
                gm.setCodigo("" + g.getCodigo());
                gm.setNombre(g.getNombre());
                gm.setPadre("" + rs.getLong(8));

                gastosDto2.add(g);
                gastos2.add(gm);
                codigoAnt2 = rs.getLong(5);
                gastosDto2.get(i2).setValor(gastosDto2.get(i2).getValor() + rs.getDouble(13));
                gastos2.get(i2).setValor(formato.numeroToStringFormato(gastosDto2.get(i2).getValor()));

            } else {

                gastosDto2.get(i2).setValor(gastosDto2.get(i2).getValor() + rs.getDouble(13));
                gastos2.get(i2).setValor(formato.numeroToStringFormato(gastosDto2.get(i2).getValor()));

            }

            g = null;
            gm = null;
            g = new GastosDto();
            gm = new GastosMapper();
            g.setCodigo(rs.getLong(9));
            g.setNombre(rs.getString(10));
            g.setValor(rs.getDouble(13));

            //Avumulo total
            total += g.getValor();

            gm.setCodigo("" + g.getCodigo());
            gm.setNombre(g.getNombre());
            gm.setValor(formato.numeroToStringFormato(g.getValor()));
            gm.setPadre("" + rs.getLong(12));
            gastos3.add(gm);
            gastosDto3.add(g);
            g = null;
            gm = null;*/
        }
    }

    /**
     * @return the gastosNivel1
     */
    @Override
    public List<GastosMapper> getGastosNivel1() {
        return gastos1;
    }

    /**
     * @return the gastosNivel2
     */
    @Override
    public List<GastosMapper> getGastosNivel2() {
        return gastos2;
    }

    /**
     * @return the gastosNivel3
     */
    @Override
    public List<GastosMapper> getGastosNivel3() {
        return gastos3;
    }

    /**
     * @param gastosNivel3 the gastosNivel3 to set
     */
    public void setGastosNivel3(List<GastosMapper> gastosNivel3) {
        this.gastos3 = gastosNivel3;
    }

    /**
     * @return the total
     */
    @Override
    public Double getTotal() {
        return total;
    }
}
