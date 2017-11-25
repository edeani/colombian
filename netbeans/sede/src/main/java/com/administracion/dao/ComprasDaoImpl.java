/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;


import com.administracion.dto.ComprasTotalesDTO;
import com.administracion.dto.DetalleCompraDTO;
import com.administracion.dto.ReporteComprasTotalesProvDTO;
import com.administracion.dto.ReporteComprasTotalesXProveedorDTO;
import com.administracion.entidad.Compras;
import com.administracion.util.Formatos;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jose Efren
 */
@Repository
public class ComprasDaoImpl extends GenericDaoImpl<Compras> implements ComprasDao {

    private JdbcTemplate jdbctemplate;

    @Autowired
    private FacturasComprasDao facturasComprasDao;
    private static final String estado_default_comprobante = "N";
    private static final String estado_default_compra = "A";

    private static final String SQL_DETALLECOMPRA_DTO = "SELECT i.codigo_producto_inventario as codigo, "
            + "i.descripcion_producto as producto, "
            + "dc.numero_unidades as unidades, "
            + "i.promedio, "
            + "dc.valor_producto as valor "
            + "FROM detalle_compra dc "
            + "inner join inventario i on i.codigo_producto_inventario = dc.codigo_producto_inventario "
            + "where dc.numero_compra = ?";

    @Override
    public Compras getCompra(Long idcompra, DataSource nameDataSource) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);

        Compras compras = null;

        try {
            compras = (Compras) jdbctemplate.queryForObject(selectJdbTemplate("*", "compras", "id_compra=" + idcompra.intValue()), new BeanPropertyRowMapper(Compras.class));
        } catch (DataAccessException e) {
            System.out.println("COMPRAS::No se encontraron recursos");
        }

        return compras;
    }

    @Override
    @SuppressWarnings({"BroadCatchBlock", "TooBroadCatch"})
    public List<ComprasTotalesDTO> getDetalleCompraDTO(Long idcompra, DataSource nameDataSource) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        List<ComprasTotalesDTO> detalleCompra = null;
        try {
            detalleCompra = jdbctemplate.query(SQL_DETALLECOMPRA_DTO, new Object[]{idcompra}, new BeanPropertyRowMapper(ComprasTotalesDTO.class));
        } catch (DataAccessException e) {
            System.out.println("Error getDetalleCompraDTO::No se encontraron recursos");
        }

        return detalleCompra;
    }

    @Override
    public void borrarDetalleCompra(Long idcompra, DataSource nameDataSource) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        this.jdbctemplate.execute(deleteJdbTemplate("detalle_compra", "numero_compra=" + idcompra));
    }
    
    @Override
    public void insertarCompra(DataSource nameDataSource, DetalleCompraDTO detalleCompraDTO) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        System.out.println("SQL::" + insertJdbTemplate("id_compra,fecha_compra,estado_compra,valor_total,codigo_proveedor,estado_compra_proveedor,fecha_vencimiento,saldo,idfacturacompra,idsede", "compras", detalleCompraDTO.getNumeroFactura() + ",'" + detalleCompraDTO.getFecha() + "','" + estado_default_compra + "'," + detalleCompraDTO.getTotalFactura()
                + "," + detalleCompraDTO.getCodigoProveedor() + ",'" + estado_default_comprobante + "','" + detalleCompraDTO.getFechaVencimiento() + "'," + detalleCompraDTO.getTotalFactura()+","+detalleCompraDTO.getIdsede()));
        this.jdbctemplate.execute(insertJdbTemplate("id_compra,fecha_compra,estado_compra,valor_total,codigo_proveedor,estado_compra_proveedor,fecha_vencimiento,saldo,idfacturacompra,idsede", "compras", detalleCompraDTO.getNumeroFactura() + ",'" + detalleCompraDTO.getFecha() + "','" + estado_default_compra + "'," + detalleCompraDTO.getTotalFactura()
                + "," + detalleCompraDTO.getCodigoProveedor() + ",'" + estado_default_comprobante + "','" + detalleCompraDTO.getFechaVencimiento() + "'," + detalleCompraDTO.getTotalFactura()+","+detalleCompraDTO.getIdFacturaCompra()+","+detalleCompraDTO.getIdsede()));
    }
    @Override
    public void insertarCompraSede(DataSource nameDataSource, DetalleCompraDTO detalleCompraDTO) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        System.out.println("SQL::" + insertJdbTemplate("id_compra,fecha_compra,estado_compra,valor_total,codigo_proveedor,estado_compra_proveedor,fecha_vencimiento,saldo,idfacturacompra", "compras", detalleCompraDTO.getNumeroFactura() + ",'" + detalleCompraDTO.getFecha() + "','" + estado_default_compra + "'," + detalleCompraDTO.getTotalFactura()
                + "," + detalleCompraDTO.getCodigoProveedor() + ",'" + estado_default_comprobante + "','" + detalleCompraDTO.getFechaVencimiento() + "'," + detalleCompraDTO.getTotalFactura()));
        this.jdbctemplate.execute(insertJdbTemplate("id_compra,fecha_compra,estado_compra,valor_total,codigo_proveedor,estado_compra_proveedor,fecha_vencimiento,saldo,idfacturacompra", "compras", detalleCompraDTO.getNumeroFactura() + ",'" + detalleCompraDTO.getFecha() + "','" + estado_default_compra + "'," + detalleCompraDTO.getTotalFactura()
                + "," + detalleCompraDTO.getCodigoProveedor() + ",'" + estado_default_comprobante + "','" + detalleCompraDTO.getFechaVencimiento() + "'," + detalleCompraDTO.getTotalFactura()+","+detalleCompraDTO.getIdFacturaCompra()));
    }
    @Override
    public void borrarCompra(Long idcompra, DataSource nameDataSource) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        this.jdbctemplate.execute(deleteJdbTemplate("compras", "id_compra=" + idcompra));
    }

    @Override
    public void insertarDetalleCompra(DataSource nameDataSource, int i, String numeroFactura, String[] datosFila, String codigoProveedor, Date fechacompra) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        this.jdbctemplate.execute(insertJdbTemplate("secuencial_compra,numero_compra,fecha_compra,codigo_producto_inventario,valor_producto,numero_unidades,codigo_proveedor", "detalle_compra",
                (i + 1) + "," + numeroFactura + "," + "'" + Formatos.dateTostring(fechacompra) + "'," + datosFila[0] + "," + datosFila[4] + "," + datosFila[2] + "," + codigoProveedor));
    }
    
    @Override
    public List<ReporteComprasTotalesXProveedorDTO> comprasTotalesXProveedor(DataSource nameDataSource, Long idproveedor, String fechaInicio, String fechaFin) {
        jdbctemplate = new JdbcTemplate(nameDataSource);
        String sql = "select c.id_compra as numero_compra,c.fecha_compra,c.valor_total from compras c "
                + " inner join proveedor p on p.idproveedor = c.codigo_proveedor "
                + " where c.codigo_proveedor= " + idproveedor + " and c.fecha_compra between '" + fechaInicio + "' and '" + fechaFin + "' order by c.fecha_compra";
        List<ReporteComprasTotalesXProveedorDTO> reporte = null;
        try {
            reporte = jdbctemplate.query(sql, new BeanPropertyRowMapper<>(ReporteComprasTotalesXProveedorDTO.class));
        } catch (DataAccessException e) {
            System.out.println("ERROR::comprasTotalesXProveedor::" + e.getMessage());
        }

        return reporte;
    }

    @Override
    public List<ReporteComprasTotalesProvDTO> comprasTotalesProveedores(DataSource nameDataSource, String fechaInicio, String fechaFin) {
        jdbctemplate = new JdbcTemplate(nameDataSource);
        String sql = "select p.nombre,c.id_compra as numero_compra,c.fecha_compra,c.valor_total from compras c "
                + " inner join proveedor p on p.idproveedor = c.codigo_proveedor "
                + " where  c.fecha_compra between '" + fechaInicio + "' and '" + fechaFin + "' order by p.nombre,c.fecha_compra";
        List<ReporteComprasTotalesProvDTO> reporte = null;
        try {
            reporte = jdbctemplate.query(sql, new BeanPropertyRowMapper<>(ReporteComprasTotalesProvDTO.class));
        } catch (DataAccessException e) {
            System.out.println("ERROR::comprasTotalesProveedores::" + e.getMessage());
        }

        return reporte;
    }

    @Override
    public Long totalComprasPollo(DataSource nameDataSource, String fechaInicio, String fechaFin) {
        jdbctemplate = new JdbcTemplate(nameDataSource);
        String sql = "select sum(c.valor_total) total from detalle_compra dc "
                + " where dc.fecha_compra between '" + fechaInicio + "' and '" + fechaFin + "' "
                + " where dc.fecha_compra between '" + fechaInicio + "' and '" + fechaFin + "' and  dc.codigo_producto_inventario = 1";
        Long total = 0L;
        try {
            total = jdbctemplate.queryForObject(sql,Long.class);
        } catch (DataAccessException e) {
            System.out.println("ERROR::totalComprasPollo::" + e.getMessage());
        }
        return total;
    }

    @Override
    public void actualizarCompra(DataSource nameDataSource, Compras compras) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        try {
            String sql = updateJdbTemplate("fecha_compra='" + Formatos.dateTostring(compras.getFechaCompra())
                    + "',estado_compra='" + compras.getEstadoCompra() + "',valor_total=" + compras.getValorTotal()
                    + ",codigo_proveedor=" + compras.getCodigoProveedor() + ",estado_compra_proveedor='" + compras.getEstadoCompraProveedor()
                    + "',saldo=" + compras.getSaldo() + ",fecha_vencimiento='" + Formatos.dateTostring(compras.getFechaVencimiento()) + "'"
                    + ",idsede="+compras.getIdsede(), "compras", "id_compra=" + compras.getIdCompra());
            this.jdbctemplate.execute(sql);
        } catch (DataAccessException e) {
            System.out.println("Error actualizarCompra::" + e.getMessage());
        }
    }

    @Override
    public List<Compras> comprasAVencer(DataSource nameDataSource, int numeroDias, Long idProveedor) {
        String condicionDias = "";
        if (numeroDias != 0) {
            condicionDias = "and fecha_vencimiento between date_format(date_add(now(),interval -" + numeroDias + " day),'%Y-%m-%d') and date_format(now(),'%Y-%m-%d')";
        }
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        List<Compras> compras = null;
        try {
            String sql = selectJdbTemplate("*", "compras", "fecha_vencimiento is  not null and estado_compra_proveedor ='N' and codigo_proveedor=" + idProveedor + " " + condicionDias);
            compras = this.jdbctemplate.query(sql, new BeanPropertyRowMapper<>(Compras.class));
        } catch (DataAccessException e) {
            System.out.println("Error comprasAVencer::" + e.getMessage());
        }

        return compras;
    }

}
