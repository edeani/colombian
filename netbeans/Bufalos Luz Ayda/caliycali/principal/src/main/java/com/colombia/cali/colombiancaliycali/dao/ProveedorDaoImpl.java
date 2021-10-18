/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombia.cali.colombiancaliycali.dao;

import com.colombia.cali.colombiancaliycali.dao.generic.CaliycaliDao;
import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombian.cali.colombiancaliycali.dto.ReporteComprasTotalesProvDTO;
import com.colombian.cali.colombiancaliycali.dto.ReporteComprasTotalesXProveedorDTO;
import com.colombian.cali.colombiancaliycali.entidades.Proveedor;
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
public class ProveedorDaoImpl implements ProveedoresDao{
    private JdbcTemplate jdbctemplate;
    @Autowired
    private ProjectsDao projectsDao;
    @Autowired
    private  CaliycaliDao caliycaliDao;
    @Override
    public Proveedor getProveedor(String nameDatasource, Long idproveedor) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDatasource));
        Proveedor proveedor = null;
        try {
            proveedor = (Proveedor) jdbctemplate.queryForObject(caliycaliDao.selectJdbTemplate("*", "proveedor", "idproveedor="+idproveedor.intValue()), new BeanPropertyRowMapper(Proveedor.class));
        } catch (Exception e) {
            System.out.println("ERROR::getProveedor::"+e.getMessage());
        }
        
        return proveedor;
    }
        @Override
        public List<Proveedor> proveedores(String nameDatasource){
        jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDatasource));
        List<Proveedor> proveedores= null;
        try {
            proveedores = jdbctemplate.query(caliycaliDao.selectJdbTemplate("*", "proveedor", ""), new BeanPropertyRowMapper<Proveedor>(Proveedor.class));
        } catch (DataAccessException e) {
            System.out.println("proveedores::Cero registros");
        }
        
        return  proveedores;
    }

    @Override
    public void guardarProveedor(String nameDatasource, Proveedor proveedor) {
        jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDatasource));
        String sql = caliycaliDao.insertJdbTemplate("nombre,direccion,telefono,correo,nit", "proveedor","'"+proveedor.getNombre()+"','"+
                proveedor.getDireccion()+"','"+proveedor.getTelefono()+"','"+proveedor.getCorreo()+"','"+proveedor.getNit()+"'");
        try {
            jdbctemplate.execute(sql);
        } catch (Exception e) {
            System.out.println("ERROR::guardarProveedor::"+e.getCause());
        }
    }

    @Override
    public void actualizarProveedor(String nameDatasource, Proveedor proveedor) {
        jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDatasource));
        String sql = caliycaliDao.updateJdbTemplate("nombre='"+proveedor.getNombre()+"',direccion='"+proveedor.getDireccion()+"',telefono='"+
                proveedor.getTelefono()+"',correo='"+proveedor.getCorreo()+"',nit='"+proveedor.getNit()+"'", "proveedor","idproveedor="+proveedor.getIdproveedor());
        try {
            jdbctemplate.execute(sql);
        } catch (Exception e) {
            System.out.println("ERROR::actualizarProveedor::"+e.getCause());
        }
    }

    @Override
    public void eliminarProveedor(String nameDatasource, Long idproveedor) {
        jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDatasource));
        String sql = caliycaliDao.deleteJdbTemplate("proveedor","idproveedor="+idproveedor);
        try {
            jdbctemplate.execute(sql);
        } catch (Exception e) {
            System.out.println("ERROR::eliminarProveedor::"+e.getMessage());
        }
    }
}
