/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombia.cali.colombiancaliycali.dao;

import com.colombia.cali.colombiancaliycali.dao.generic.CaliycaliDao;
import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombia.cali.colombiancaliycali.util.Formatos;
import com.colombian.cali.colombiancaliycali.dto.DetalleCompraDTO;
import com.colombian.cali.colombiancaliycali.dto.DetalleFacturaDTO;
import com.colombian.cali.colombiancaliycali.dto.FacturaAutocompletarDto;
import com.colombian.cali.colombiancaliycali.dto.FacturaTotalReporteDto;
import com.colombian.cali.colombiancaliycali.dto.FacturaReporteSedeDto;
import com.colombian.cali.colombiancaliycali.entidades.Factura;
import com.colombian.cali.colombiancaliycali.mapper.FacturaMapper;
import java.util.Date;
import java.util.List;
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
public class FacturaDaoImpl implements FacturaDao{
    private JdbcTemplate jdbctemplate;
    @Autowired
    private ProjectsDao projectsDao;
    @Autowired
    private CaliycaliDao caliycaliDao;
    private static final String estado_default_comprobante="N";
    @Override
    public void insertarFacturaNueva(String nameDatasource, DetalleFacturaDTO detalleFacturaDTO, Long idsede, String estadoFactura) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDatasource));

        //Inserto en factura
        try {
            this.jdbctemplate.execute(caliycaliDao.insertJdbTemplate("fecha_factura,estado_factura,valor_total,codigo_proveedor,idsede", "factura", "'" + Formatos.dateTostring(new Date()) + "','" + estadoFactura + "'," + detalleFacturaDTO.getTotalFactura() + ",1," + idsede));
        } catch (DataAccessException e) {
            System.out.println("ERROR FacturaService guardarFactura: inserción factura " + e.getMessage());
        }
    }

    @Override
    public void insertarFactura(String nameDatasource, DetalleFacturaDTO detalleFacturaDTO, Long idsede, String estadoFactura) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDatasource));
        //Date fechaFactura = detalleFacturaDTO.get
        //Inserto en factura
        try {
            String sql = caliycaliDao.insertJdbTemplate("fecha_factura,estado_factura,valor_total,codigo_proveedor,idsede,numero_factura", "factura", "'" + detalleFacturaDTO.getFechaFactura() + "','" + estadoFactura + "'," + detalleFacturaDTO.getTotalFactura() + ",1," + idsede.longValue()+","+detalleFacturaDTO.getNumeroFactura());
            System.out.println("SQL insertarFactura::"+sql);
            this.jdbctemplate.execute(sql);
        } catch (DataAccessException e) {
            System.out.println("ERROR FacturaService guardarFactura: inserción factura " + e.getMessage());
        }
    }
    
    @Override
    public void insertarDetalle(String nameDatasource, DetalleFacturaDTO detalleFacturaDTO, Long idsede, String estadoFactura,Long idFactura) {
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
    public Long secuenciaDetalle(String nameDatasource) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDatasource));
        Long secuencia = 0L;
        try {
            secuencia = (Long) this.jdbctemplate.queryForLong("select max(numero_factura) from factura");
        } catch (DataAccessException e) {
            System.out.println("FacturasServiceImpl: Error obtenerSecuenciaFactura: " + e.getMessage());
        }
        
        return secuencia;
    }

    @Override
    public void insertarFacturaSede(String nameDatasource, DetalleFacturaDTO detalleFacturaDTO, String estadoFactura, Long idFactura) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDatasource));
        //Inserto en factura
        String fecha = detalleFacturaDTO.getFechaFactura();
        if(fecha==null){
            fecha = Formatos.dateTostring(new Date());
            detalleFacturaDTO.setFechaFactura(fecha);
        }
        
        try {
            this.jdbctemplate.execute(caliycaliDao.insertJdbTemplate("fecha_factura,estado_factura,valor_total,codigo_proveedor,numero_factura", "factura", "'" + detalleFacturaDTO.getFechaFactura() + "','" + estadoFactura + "'," + detalleFacturaDTO.getTotalFactura() + ",1," + idFactura));
        } catch (DataAccessException e) {
            System.out.println("ERROR FacturaService guardarFacturaSede: inserción factura " + e.getMessage());
        }
   }
    
    @Override
    public void insertarDetalleSede(String nameDatasource, DetalleCompraDTO detalleCompraDTO, String estadoFactura, Long idFactura) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDatasource));
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
    public void insertarDetalleSede(String nameDatasource, DetalleFacturaDTO detalleFacturaDTO, String estadoFactura, Long idFactura) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDatasource));
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
    public Factura findFactura(String nameDatasource,Long idfactura) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDatasource));
        StringBuilder sql = new StringBuilder("select * from factura where numero_factura = "+idfactura.intValue());
        Factura factura = null;
        try {
            factura = (Factura) this.jdbctemplate.queryForObject(sql.toString(),  new BeanPropertyRowMapper(Factura.class));
        } catch (DataAccessException e) {
            System.out.println("FACTURADAO::"+e.getMessage());
        }
        return factura;
    }

    @Override
    public List<FacturaReporteSedeDto> reporteTotalFacturaXSede(String nameDatasource, String fechaInicio, String fechaFin, Long idsede) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDatasource));
        StringBuilder sql = new StringBuilder(
        "select f.numero_factura,f.fecha_factura,f.valor_total from factura f where f.estado_factura = 'A' " +
        "and f.idsede = "+idsede+" and f.fecha_factura between '"+fechaInicio+"' and '"+fechaFin+"' order by f.fecha_factura"
        );
        
        List<FacturaReporteSedeDto> reporte = null;
        try {
            reporte = jdbctemplate.query(sql.toString(), new BeanPropertyRowMapper<FacturaReporteSedeDto>(FacturaReporteSedeDto.class));
        } catch (DataAccessException e) {
            System.out.println("reporteTotalFacturaXSede::"+e.getMessage());
        }
        return  reporte;
    }

    @Override
    public List<FacturaTotalReporteDto> reporteTotalFactura(String nameDatasource, String fechaInicio, String fechaFin) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDatasource));
        StringBuilder sql = new StringBuilder(
        "select f.numero_factura,f.fecha_factura,s.sede,f.valor_total from factura f " +
        "inner join sedes s on s.idsedes = f.idsede " +
        "where f.fecha_factura between '"+fechaInicio+"' and '"+fechaFin+"' order by f.fecha_factura desc");
        
        List<FacturaTotalReporteDto> reporte = null;
        try {
            reporte = jdbctemplate.query(sql.toString(), new BeanPropertyRowMapper<FacturaTotalReporteDto>(FacturaTotalReporteDto.class));
        } catch (Exception e) {
            System.out.println("reporteTotalFactura::"+e.getMessage());
        }
        return reporte;
    }

    @Override
    public void borrarFactura(String nameDataSource, Long numeroFactura) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
         try {
            this.jdbctemplate.execute(caliycaliDao.deleteJdbTemplate("factura", "numero_factura=" + numeroFactura));
        } catch (DataAccessException e) {
            System.out.println("Error de conexión :" + e.getMessage());
        }
    }

    @Override
    public void borrarDetalleFactura(String nameDataSource, Long numeroFactura){
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        try {
            this.jdbctemplate.execute(caliycaliDao.deleteJdbTemplate("detalle_factura", "numero_factura=" + numeroFactura));
        } catch (Exception e) {
            System.out.println("Error de conexión :" + e.getMessage());
        }
    }

    @Override
    public void actualizarSedeFactura(String nameDataSource, DetalleFacturaDTO detalleFacturaDTO, String estadoFactura) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        try {
            this.jdbctemplate.execute(caliycaliDao.updateJdbTemplate("idsede=" + detalleFacturaDTO.getSede(), "factura", "numero_factura=" + detalleFacturaDTO.getNumeroFactura() + ""));
        } catch (DataAccessException e) {
            System.out.println("Error de conexión actualizarSedeFactura :" + e.getMessage());
        }
    }

    @Override
    public void actualizarSedeDetalleFactura(String nameDataSource, DetalleFacturaDTO detalleFacturaDTO, String estadoFactura) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        try {
            this.jdbctemplate.execute(caliycaliDao.updateJdbTemplate("idsede=" + detalleFacturaDTO.getSede(), "detalle_factura", "numero_factura=" + detalleFacturaDTO.getNumeroFactura() + ""));
        } catch (DataAccessException e) {
            System.out.println("Error de conexión actualizarSedeDetalleFactura :" + e.getMessage());
        }
    }

    @Override
    public List<FacturaAutocompletarDto> buscarFacturaAutocompletar(String nameDataSource, String numeroFactura, Long idproveedor) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        List<FacturaAutocompletarDto> factura=null;
        try {
            factura = this.jdbctemplate.query(caliycaliDao.selectJdbTemplate("numero_factura as value,fecha_factura as fechaFactura,estado_factura as estadoFactura,valor_total as valorTotal, codigo_proveedor as codigoProveedor",
                    "factura", "cast(numero_factura as char) like '"+numeroFactura+"%' and codigo_proveedor = "+idproveedor+" and estado_pago_comprobante='"+estado_default_comprobante+"'"), new BeanPropertyRowMapper<FacturaAutocompletarDto>(FacturaAutocompletarDto.class));
        } catch (DataAccessException e) {
            System.out.println("Error de conexión buscarFacturaAutocompletar :" + e.getMessage());
        }
        return factura;
    }

    
    
}
