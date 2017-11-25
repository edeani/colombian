/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.dao;


import com.adiministracion.mapper.FacturaMapper;
import com.administracion.dto.DetalleCompraDTO;
import com.administracion.dto.DetalleFacturaDTO;
import com.administracion.dto.FacturaAutocompletarDto;
import com.administracion.dto.FacturaReporteSedeDto;
import com.administracion.dto.FacturaTotalReporteDto;
import com.administracion.entidad.Factura;
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
public class FacturaDaoImpl extends GenericDaoImpl<Factura>implements FacturaDao{
    private JdbcTemplate jdbctemplate;

    private static final String estado_default_comprobante="N";
    @Override
    public void insertarFacturaNueva(DataSource nameDataSource, DetalleFacturaDTO detalleFacturaDTO, Long idsede, String estadoFactura) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);

        //Inserto en factura
        try {
            this.jdbctemplate.execute(insertJdbTemplate("fecha_factura,estado_factura,valor_total,codigo_proveedor,idsede", "factura", "'" + Formatos.dateTostring(new Date()) + "','" + estadoFactura + "'," + detalleFacturaDTO.getTotalFactura() + ",1," + idsede));
        } catch (DataAccessException e) {
            System.out.println("ERROR FacturaService guardarFactura: inserción factura " + e.getMessage());
        }
    }

    @Override
    public void insertarFactura(DataSource nameDataSource, DetalleFacturaDTO detalleFacturaDTO, Long idsede, String estadoFactura) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        //Date fechaFactura = detalleFacturaDTO.get
        //Inserto en factura
        try {
            String sql = insertJdbTemplate("fecha_factura,estado_factura,valor_total,codigo_proveedor,idsede,numero_factura", "factura", "'" + detalleFacturaDTO.getFechaFactura() + "','" + estadoFactura + "'," + detalleFacturaDTO.getTotalFactura() + ",1," + idsede+","+detalleFacturaDTO.getNumeroFactura());
            System.out.println("SQL insertarFactura::"+sql);
            this.jdbctemplate.execute(sql);
        } catch (DataAccessException e) {
            System.out.println("ERROR FacturaService guardarFactura: inserción factura " + e.getMessage());
        }
    }
    
    @Override
    public void insertarDetalle(DataSource nameDataSource, DetalleFacturaDTO detalleFacturaDTO, Long idsede, String estadoFactura,Long idFactura) {
        FacturaMapper facturaMapper = new FacturaMapper();
        System.out.println("FECHA5::"+detalleFacturaDTO.getFechaFactura());
        String valores = facturaMapper.tramaToDetalleFactura(detalleFacturaDTO, idFactura, idsede);
        //Inserto el detalle_factura
        try {
            System.out.println("VALORES DETALLE INSERCION::"+valores);
            this.jdbctemplate.execute(valores);
        } catch (DataAccessException e) {
            System.out.println("ERROR FacturaService guardarFactura: inserción detalle_factura " + e.getMessage());
        }
    }

    @Override
    public Long secuenciaDetalle(DataSource nameDataSource) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        Long secuencia = 0L;
        try {
            secuencia = this.jdbctemplate.queryForObject("select max(numero_factura) from factura",Long.class);
        } catch (DataAccessException e) {
            System.out.println("FacturasServiceImpl: Error obtenerSecuenciaFactura: " + e.getMessage());
        }
        
        return secuencia;
    }

    @Override
    public void insertarFacturaSede(DataSource nameDataSource, DetalleFacturaDTO detalleFacturaDTO, String estadoFactura, Long idFactura) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        //Inserto en factura
        String fecha = detalleFacturaDTO.getFechaFactura();
        if(fecha==null){
            fecha = Formatos.dateTostring(new Date());
            detalleFacturaDTO.setFechaFactura(fecha);
        }
        
        try {
            this.jdbctemplate.execute(insertJdbTemplate("fecha_factura,estado_factura,valor_total,codigo_proveedor,numero_factura", "factura", "'" + detalleFacturaDTO.getFechaFactura() + "','" + estadoFactura + "'," + detalleFacturaDTO.getTotalFactura() + ",1," + idFactura));
        } catch (DataAccessException e) {
            System.out.println("ERROR FacturaService guardarFacturaSede: inserción factura " + e.getMessage());
        }
   }
    
    @Override
    public void insertarDetalleSede(DataSource nameDataSource, DetalleCompraDTO detalleCompraDTO, String estadoFactura, Long idFactura) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        FacturaMapper facturaMapper= new FacturaMapper();
        
        String valores = facturaMapper.tramaCompraToDetalleFacturaSede(detalleCompraDTO, idFactura, detalleCompraDTO.getIdsede());
        System.out.println("VALORES::"+valores);
        try {
            this.jdbctemplate.execute(valores);
        } catch (DataAccessException e) {
            System.out.println("ERROR FacturaService guardarFacturaSede: inserción detalle_factura " + e.getMessage());
        }
    }

    @Override
    public void insertarDetalleSede(DataSource nameDataSource, DetalleFacturaDTO detalleFacturaDTO, String estadoFactura, Long idFactura) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        FacturaMapper facturaMapper= new FacturaMapper();
        
        String valores = facturaMapper.tramaToDetalleFacturaSede(detalleFacturaDTO, idFactura, detalleFacturaDTO.getSede());
        System.out.println("VALORES::"+valores);
        try {
            this.jdbctemplate.execute(valores);
        } catch (DataAccessException e) {
            System.out.println("ERROR FacturaService guardarFacturaSede: inserción detalle_factura " + e.getMessage());
        }
    }

    @Override
    public Factura findFactura(DataSource nameDataSource,Long idfactura) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        String sql = "select * from factura where numero_factura = "+idfactura.intValue();
        Factura factura = null;
        try {
            factura = (Factura) this.jdbctemplate.queryForObject(sql,  new BeanPropertyRowMapper(Factura.class));
        } catch (DataAccessException e) {
            System.out.println("FACTURADAO::"+e.getMessage());
        }
        return factura;
    }

    @Override
    public List<FacturaReporteSedeDto> reporteTotalFacturaXSede(DataSource nameDataSource, String fechaInicio, String fechaFin, Long idsede) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        String sql = "select f.numero_factura,f.fecha_factura,f.valor_total from factura f where f.estado_factura = 'A' " +
                "and f.idsede = "+idsede+" and f.fecha_factura between '"+fechaInicio+"' and '"+fechaFin+"' order by f.fecha_factura";
        
        List<FacturaReporteSedeDto> reporte = null;
        try {
            reporte = jdbctemplate.query(sql, new BeanPropertyRowMapper<>(FacturaReporteSedeDto.class));
        } catch (DataAccessException e) {
            System.out.println("reporteTotalFacturaXSede::"+e.getMessage());
        }
        return  reporte;
    }

    @Override
    public List<FacturaTotalReporteDto> reporteTotalFactura(DataSource nameDataSource, String fechaInicio, String fechaFin) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        String sql = "select f.numero_factura,f.fecha_factura,s.sede,f.valor_total from factura f " +
                "inner join sedes s on s.idsedes = f.idsede " +
                "where f.fecha_factura between '"+fechaInicio+"' and '"+fechaFin+"' order by f.fecha_factura desc";
        
        List<FacturaTotalReporteDto> reporte = null;
        try {
            reporte = jdbctemplate.query(sql, new BeanPropertyRowMapper<>(FacturaTotalReporteDto.class));
        } catch (DataAccessException e) {
            System.out.println("reporteTotalFactura::"+e.getMessage());
        }
        return reporte;
    }

    @Override
    public void borrarFactura(DataSource nameDataSource, Long numeroFactura) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
         try {
            this.jdbctemplate.execute(deleteJdbTemplate("factura", "numero_factura=" + numeroFactura));
        } catch (DataAccessException e) {
            System.out.println("Error de conexión :" + e.getMessage());
        }
    }

    @Override
    public void borrarDetalleFactura(DataSource nameDataSource, Long numeroFactura){
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        try {
            this.jdbctemplate.execute(deleteJdbTemplate("detalle_factura", "numero_factura=" + numeroFactura));
        } catch (DataAccessException e) {
            System.out.println("Error de conexión :" + e.getMessage());
        }
    }

    @Override
    public void actualizarSedeFactura(DataSource nameDataSource, DetalleFacturaDTO detalleFacturaDTO, String estadoFactura) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        try {
            this.jdbctemplate.execute(updateJdbTemplate("idsede=" + detalleFacturaDTO.getSede(), "factura", "numero_factura=" + detalleFacturaDTO.getNumeroFactura() + ""));
        } catch (DataAccessException e) {
            System.out.println("Error de conexión actualizarSedeFactura :" + e.getMessage());
        }
    }

    @Override
    public void actualizarSedeDetalleFactura(DataSource nameDataSource, DetalleFacturaDTO detalleFacturaDTO, String estadoFactura) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        try {
            this.jdbctemplate.execute(updateJdbTemplate("idsede=" + detalleFacturaDTO.getSede(), "detalle_factura", "numero_factura=" + detalleFacturaDTO.getNumeroFactura() + ""));
        } catch (DataAccessException e) {
            System.out.println("Error de conexión actualizarSedeDetalleFactura :" + e.getMessage());
        }
    }

    @Override
    public List<FacturaAutocompletarDto> buscarFacturaAutocompletar(DataSource nameDataSource, String numeroFactura, Long idproveedor) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        List<FacturaAutocompletarDto> factura=null;
        try {
            factura = this.jdbctemplate.query(selectJdbTemplate("numero_factura as value,fecha_factura as fechaFactura,estado_factura as estadoFactura,valor_total as valorTotal, codigo_proveedor as codigoProveedor",
                    "factura", "cast(numero_factura as char) like '"+numeroFactura+"%' and codigo_proveedor = "+idproveedor+" and estado_pago_comprobante='"+estado_default_comprobante+"'"), new BeanPropertyRowMapper<>(FacturaAutocompletarDto.class));
        } catch (DataAccessException e) {
            System.out.println("Error de conexión buscarFacturaAutocompletar :" + e.getMessage());
        }
        return factura;
    }

    
    
}
