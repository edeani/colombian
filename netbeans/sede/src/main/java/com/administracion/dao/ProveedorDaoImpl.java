/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.dao;


import com.administracion.entidad.Proveedor;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jose Efren
 */
@Repository
public class ProveedorDaoImpl extends GenericDaoImpl<Proveedor> implements ProveedoresDao{
    private JdbcTemplate jdbctemplate;

    @Override
    public Proveedor getProveedor(DataSource nameDataSource, Long idproveedor) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        Proveedor proveedor = null;
        try {
            proveedor = (Proveedor) jdbctemplate.queryForObject(selectJdbTemplate("*", "proveedor", "idproveedor="+idproveedor.intValue()), new BeanPropertyRowMapper(Proveedor.class));
        } catch (DataAccessException e) {
            System.out.println("ERROR::getProveedor::"+e.getMessage());
        }
        
        return proveedor;
    }
        @Override
        public List<Proveedor> proveedores(DataSource nameDataSource){
        jdbctemplate = new JdbcTemplate(nameDataSource);
        List<Proveedor> proveedores= null;
        try {
            proveedores = jdbctemplate.query(selectJdbTemplate("*", "proveedor", ""), new BeanPropertyRowMapper<>(Proveedor.class));
        } catch (DataAccessException e) {
            System.out.println("proveedores::Cero registros");
        }
        
        return  proveedores;
    }

    @Override
    public void guardarProveedor(DataSource nameDataSource, Proveedor proveedor) {
        jdbctemplate = new JdbcTemplate(nameDataSource);
        String sql = insertJdbTemplate("nombre,direccion,telefono,correo,nit", "proveedor","'"+proveedor.getNombre()+"','"+
                proveedor.getDireccion()+"','"+proveedor.getTelefono()+"','"+proveedor.getCorreo()+"','"+proveedor.getNit()+"'");
        try {
            jdbctemplate.execute(sql);
        } catch (DataAccessException e) {
            System.out.println("ERROR::guardarProveedor::"+e.getMessage());
        }
    }

    @Override
    public void actualizarProveedor(DataSource nameDataSource, Proveedor proveedor) {
        jdbctemplate = new JdbcTemplate(nameDataSource);
        String sql = updateJdbTemplate("nombre='"+proveedor.getNombre()+"',direccion='"+proveedor.getDireccion()+"',telefono='"+
                proveedor.getTelefono()+"',correo='"+proveedor.getCorreo()+"',nit='"+proveedor.getNit()+"'", "proveedor","idproveedor="+proveedor.getIdproveedor());
        try {
            jdbctemplate.execute(sql);
        } catch (DataAccessException e) {
            System.out.println("ERROR::actualizarProveedor::"+e.getMessage());
        }
    }

    @Override
    public void eliminarProveedor(DataSource nameDataSource, Long idproveedor) {
        jdbctemplate = new JdbcTemplate(nameDataSource);
        String sql = deleteJdbTemplate("proveedor","idproveedor="+idproveedor);
        try {
            jdbctemplate.execute(sql);
        } catch (DataAccessException e) {
            System.out.println("ERROR::eliminarProveedor::"+e.getMessage());
        }
    }
}
