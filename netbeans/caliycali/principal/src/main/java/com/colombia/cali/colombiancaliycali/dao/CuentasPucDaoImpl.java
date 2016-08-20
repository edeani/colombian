/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.dao;

import com.colombia.cali.colombiancaliycali.dao.generic.CaliycaliDao;
import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombian.cali.colombiancaliycali.dto.CuentasAutoCompletarDto;
import com.colombian.cali.colombiancaliycali.dto.ItemsDTO;
import com.colombian.cali.colombiancaliycali.entidades.CuentasPuc;
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
public class CuentasPucDaoImpl implements CuentasPucDao {

    private JdbcTemplate jdbctemplate;
    @Autowired
    private ProjectsDao projectsDao;
    @Autowired
    private CaliycaliDao caliycaliDao;

    @Override
    public void actualizarCuentaPuc(CuentasPuc cuentasPuc, String nameDataSource) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));

        try {
            this.jdbctemplate.execute(caliycaliDao.updateJdbTemplate("tipo='" + cuentasPuc.getTipo() + "',nombre_cta='" + cuentasPuc.getNombreCta() + "'", "cuentas_puc", "cod_cta=" + cuentasPuc.getCodCta()));
        } catch (DataAccessException e) {
            System.out.println("actualizarCuentaPuc " + e.getMessage());
        }
    }

    @Override
    public CuentasPuc consultarCuentaPuc(String idCuenta, String nameDataSource) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        CuentasPuc cuentasPuc = null;
        try {
            cuentasPuc = this.jdbctemplate.queryForObject(caliycaliDao.selectJdbTemplate("cod_cta,trim(nombre_cta) as nombre_cta,tipo,nivel,con_padre", "cuentas_puc", "cod_cta='" + idCuenta + "'"), new BeanPropertyRowMapper<CuentasPuc>(CuentasPuc.class));
        } catch (DataAccessException e) {
            System.out.println("consultarCuentaPuc " + e.getMessage());
        }

        return cuentasPuc;
    }

    @Override
    public List<CuentasAutoCompletarDto> buscarCuentaLikeId(String idCuenta, String nameDataSource) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        List<CuentasAutoCompletarDto> cuentasAutoCompletarDtos = null;
        try {
            cuentasAutoCompletarDtos = this.jdbctemplate.query(caliycaliDao.selectJdbTemplate("cod_cta as idCuenta,trim(nombre_cta) as nombreCuenta,tipo,nivel,con_padre, concat(trim(cod_cta),' ',trim(nombre_cta)) as value", "cuentas_puc", "cod_cta like '" + idCuenta + "%'"), new BeanPropertyRowMapper<CuentasAutoCompletarDto>(CuentasAutoCompletarDto.class));
        } catch (DataAccessException e) {
            System.out.println("buscarCuentaLikeId " + e.getMessage());
        }

        return cuentasAutoCompletarDtos;
    }

    @Override
    public void insertarCuentaPuc(CuentasPuc cuentasPuc, String nameDataSource) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));

        try {
            this.jdbctemplate.execute(caliycaliDao.insertJdbTemplate("cod_cta,tipo,nivel,clase,nombre_cta,con_padre", "cuentas_puc",
                    "'" + cuentasPuc.getCodCta() + "','" + cuentasPuc.getTipo() + "'," + cuentasPuc.getNivel() + "," + cuentasPuc.getClase()
                    + ",'" + cuentasPuc.getNombreCta() + "','" + cuentasPuc.getConPadre() + "'"
            ));
        } catch (DataAccessException e) {
            System.out.println("insertarCuentaPuc " + e.getMessage());
        }
    }

    @Override
    public List<ItemsDTO> cuentasBase(String nameDataSource) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        List<ItemsDTO> cuentas = null;
        try {
            cuentas = this.jdbctemplate.query(caliycaliDao.selectJdbTemplate("cod_cta as id,nombre_cta as label", "cuentas_puc", "cod_cta in('4','5','6')"), new BeanPropertyRowMapper<ItemsDTO>(ItemsDTO.class));
        } catch (DataAccessException e) {
            System.out.println("Error cuentasBase:: " + e.getMessage());
        }

        return cuentas;
    }

}
