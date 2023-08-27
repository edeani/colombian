/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.adiministracion.rowmapper.InventarioDTORowMapper;
import com.adiministracion.rowmapper.InventarioRowMapper;
import com.administracion.dto.FacturaVentaDTO;
import com.administracion.dto.InventarioClienteDto;
import com.administracion.dto.InventarioDTO;
import com.administracion.dto.InventarioFinalDTO;
import com.administracion.dto.ItemsDTO;
import com.administracion.entidad.Inventario;
import com.administracion.util.LeerXml;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public class InventarioDaoImpl extends GenericDaoImpl<Inventario> implements InventarioDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventarioDao.class);
    private JdbcTemplate jdbctemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private LeerXml leerXml;

    @Override
    public void actualizarPromedio(DataSource nameDataSource, Long idproducto, Double promedio) {

        this.jdbctemplate = new JdbcTemplate(nameDataSource);

        try {
            this.jdbctemplate.execute(updateJdbTemplate("promedio=" + promedio,
                     "inventario",
                     "codigo_producto_inventario = " + idproducto));
        } catch (DataAccessException e) {
            LOGGER.error("Error actualizarPromedio:: No se actualizó el producto");
        }

    }

    @Override
    public List<InventarioDTO> listInventarioDto(DataSource nameDataSource) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        return this.jdbctemplate.query(leerXml.getQuery("InventarioSql.list"), new InventarioDTORowMapper());
    }

    @Override
    public void eliminarProducto(DataSource nameDataSource, Long idProducto) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        this.jdbctemplate.execute(deleteJdbTemplate("inventario", "codigo_producto_inventario=" + idProducto));
    }

    @Override
    public void insertarProducto(DataSource nameDataSource, InventarioDTO inventarioDTO) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        try {
            this.jdbctemplate.execute(insertJdbTemplate("codigo_producto_inventario,descripcion_producto,fecha_inicial,stock_minimo,stock_hoy,fecha_final,stock_real,promedio", "inventario",
                     inventarioDTO.getCodigoProductoInventario() + ",'" + inventarioDTO.getDescripcionProducto() + "','"
                    + inventarioDTO.getFechaInicial() + "'," + inventarioDTO.getStockMinimo() + "," + inventarioDTO.getStockHoy() + ",'" + inventarioDTO.getFechaFinal() + "'," + inventarioDTO.getStockReal()
                    + "," + inventarioDTO.getPromedio()));
        } catch (DataAccessException e) {
            LOGGER.error("insertarProducto:: No se insertó el producto");
        }
    }
    
    @Override
    public void insertarProductoSubsede(DataSource nameDataSource, InventarioDTO inventarioDTO) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        try {
            this.jdbctemplate.execute(insertJdbTemplate("codigo_producto_inventario,descripcion_producto,fecha_inicial,stock_minimo,stock_hoy,fecha_final,stock_real", "inventario",
                     inventarioDTO.getCodigoProductoInventario() + ",'" + inventarioDTO.getDescripcionProducto() + "','"
                    + inventarioDTO.getFechaInicial() + "'," + inventarioDTO.getStockMinimo() + "," + inventarioDTO.getStockHoy() + ",'" + inventarioDTO.getFechaFinal() + "'," + inventarioDTO.getStockReal()
                    ));
        } catch (DataAccessException e) {
            LOGGER.error("insertarProducto:: No se insertó el producto");
        }
    }

    @Override
    public void actualizarProducto(DataSource nameDataSource, InventarioDTO inventarioDTO) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);

        try {
            this.jdbctemplate.execute(updateJdbTemplate("descripcion_producto='" + inventarioDTO.getDescripcionProducto() + "',fecha_inicial='" + inventarioDTO.getFechaInicial()
                    + "',stock_minimo=" + inventarioDTO.getStockMinimo() + ",stock_hoy=" + inventarioDTO.getStockHoy()
                    + ",fecha_final='" + inventarioDTO.getFechaFinal() + "',stock_real=" + inventarioDTO.getStockReal() + ",promedio=" + inventarioDTO.getPromedio(),
                     "inventario",
                     "codigo_producto_inventario = " + inventarioDTO.getCodigoProductoInventario()));
        } catch (DataAccessException e) {
            LOGGER.error("actualizarProducto:: No se actualizó el producto");
        }
    }
    
    @Override
    public void actualizarProductoSubsede(DataSource nameDataSource, InventarioDTO inventarioDTO) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);

        try {
            this.jdbctemplate.execute(updateJdbTemplate("descripcion_producto='" + inventarioDTO.getDescripcionProducto() + "',fecha_inicial='" + inventarioDTO.getFechaInicial()
                    + "',stock_minimo=" + inventarioDTO.getStockMinimo() + ",stock_hoy=" + inventarioDTO.getStockHoy()
                    + ",fecha_final='" + inventarioDTO.getFechaFinal() + "',stock_real=" + inventarioDTO.getStockReal(),
                     "inventario",
                     "codigo_producto_inventario = " + inventarioDTO.getCodigoProductoInventario()));
        } catch (DataAccessException e) {
            LOGGER.error("actualizarProducto:: No se actualizó el producto");
        }
    }

    @Override
    public InventarioDTO traerProductoDto(DataSource nameDataSource, Long idProducto) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        InventarioDTO inventarioDTO = null;
        try {
            inventarioDTO = (InventarioDTO) this.jdbctemplate.queryForObject(selectJdbTemplate("*",
                    "inventario", "codigo_producto_inventario = ? "), new Object[]{idProducto}, new InventarioDTORowMapper());

        } catch (DataAccessException e) {
            LOGGER.error("traerProductoDto::Inventario: Se encontraron 0 registros");
        }
        return inventarioDTO;
    }

    @Override
    public Double promedioInventario(DataSource nameDataSource, Long idProducto, String fechaini, String fechafin) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        Double promedio = 0D;
        try {
            promedio = this.jdbctemplate.queryForObject(leerXml.getQuery("InventarioSql.promedio"), new Object[]{fechaini, fechafin, idProducto}, Double.class);
        } catch (DataAccessException e) {
            LOGGER.error("promedioInventario:: No se calculó el promedio " + e.getMessage());
        }
        return promedio;
    }

    @Override
    public List<InventarioFinalDTO> inventarioFinal(DataSource nameDatasource, String fechaInicial, String fechaFinal) {
        MapSqlParameterSource params = new MapSqlParameterSource("fechaInicial", fechaInicial);
        params.addValue("fechaFinal", fechaFinal);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(nameDatasource);
        return namedParameterJdbcTemplate.query(leerXml.getQuery("InventarioSql.invenarioFinal"), params, new BeanPropertyRowMapper(InventarioFinalDTO.class));
    }

    @Override
    public List<ItemsDTO> listaProductoOptions(DataSource nameDatasource) {
        List<ItemsDTO> productos = null;
        this.jdbctemplate = new JdbcTemplate(nameDatasource);

        try {
            productos = this.jdbctemplate.query(leerXml.getQuery("InventarioSql.productoItemDto"), new BeanPropertyRowMapper(ItemsDTO.class));
        } catch (DataAccessException e) {
            System.out.println("Se encontraron 0 registros: " + e.getMessage());
        }

        return productos;
    }

    @Override
    public List<ItemsDTO> listaProductosLabel(DataSource nameDataSource) {
        List<ItemsDTO> productos = null;
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        try {
            productos = this.jdbctemplate.query(leerXml.getQuery("InventarioSql.productoItemDtoOrderId"), new BeanPropertyRowMapper(ItemsDTO.class));
        } catch (DataAccessException e) {
            LOGGER.error("listaProductosLabel::Se encontraron 0 registros: " + e.getMessage());
        }
        return productos;
    }

    @Override
    public Inventario traerProducto(DataSource nameDatasource, Long idProducto) {
        this.jdbctemplate = new JdbcTemplate(nameDatasource);
        Inventario inventario = null;
        try {
            inventario = (Inventario) this.jdbctemplate.queryForObject(selectJdbTemplate("*", "inventario", "codigo_producto_inventario= ?"), new Object[]{idProducto}, new InventarioRowMapper());
        } catch (DataAccessException e) {
            LOGGER.error("traerProducto::La consulta retornó 0 elementos");
        }

        return inventario;
    }

    @Override
    public List<FacturaVentaDTO> traerProductosFactura(DataSource datasource, Long idFactura) {
        this.jdbctemplate = new JdbcTemplate(datasource);
        List<FacturaVentaDTO> detalleFactura = null;

        try {
            detalleFactura = this.jdbctemplate.query("SELECT df.codigo_producto_inventario as codigo, "
                    + " inv.descripcion_producto as producto, "
                    + " df.numero_unidades as unidades, "
                    + " inv.promedio as valorUnitario, "
                    + " df.valor_producto as totalProducto,f.idsede as idsede,f.estado_factura as estado FROM detalle_factura df "
                    + " inner join inventario inv on inv.codigo_producto_inventario = df.codigo_producto_inventario "
                    + " inner join factura f on f.numero_factura = df.numero_factura "
                    + " where df.numero_factura = ? order by secuencial_factura", new Object[]{idFactura}, new BeanPropertyRowMapper(FacturaVentaDTO.class));
        } catch (DataAccessException e) {
           LOGGER.error("ERROR traerProductosFactura: La consulta retornó 0 elementos " + e.getMessage());
        }

        return detalleFactura;
    }

    @Override
    public List<InventarioClienteDto> traerProductoClienteInventario(DataSource dataSource, String sedePrincipal, String tel, String fechaInicial, String fechaFinal) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        List<InventarioClienteDto> productosInventarioCliente = new ArrayList<>();
        try {
            String sqlProdInv = leerXml.getQuery("InventarioSql.clientes");
            
            MapSqlParameterSource paramsProdInv = new MapSqlParameterSource("tel", tel);
            paramsProdInv.addValue("fechaFinal", fechaFinal);
            paramsProdInv.addValue("fechaInicial", fechaInicial);
            
            productosInventarioCliente =  namedParameterJdbcTemplate.query(String.format(sqlProdInv, sedePrincipal), paramsProdInv
                    ,new BeanPropertyRowMapper<>(InventarioClienteDto.class));
        } catch (DataAccessException e) {
            LOGGER.error("ERROR traerProductoClienteInventario: La consulta retornó 0 elementos " + e.getMessage());
        }
        return productosInventarioCliente;
    }

}
