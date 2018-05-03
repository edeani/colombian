/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.autorizacion;

import com.administracion.dto.SedesDto;
import com.administracion.dto.SubSedesDto;
import com.administracion.dto.UserItemDto;
import com.administracion.service.SedesService;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

/**
 *
 * @author EderArmando
 */
@Component
public class ConnectsAuth {

    @Autowired
    private SedesService sedesService;
    @Autowired
    private AccesosSubsedes accesosSubsedes;
    /**
     * Tiene todas las conexiones existentes
     */
    private List<SedesDto> sedesConnect;

    @PostConstruct
    private void loadConnectsAut() {
        sedesConnect = sedesService.traerSedesDtos();
    }

    public DataSource getDataSourceSedeConnect(String nameDataSource) {
        SedesDto puntoSedeConn = findSedeXName(nameDataSource);
        DriverManagerDataSource dataSourceConn_ = new DriverManagerDataSource();
        dataSourceConn_.setPassword(puntoSedeConn.getPassword());
        dataSourceConn_.setUrl(puntoSedeConn.getUrl());
        dataSourceConn_.setUsername(puntoSedeConn.getUsername());
        return dataSourceConn_;
    }

    /**
     * Devuelve el datasource de la sede
     *
     * @param nameDataSource
     * @return
     */
    public DataSource getDataSourceSede(String nameDataSource) {
        SedesDto puntoSede = findSedeXName(nameDataSource);
        DriverManagerDataSource dataSource_ = new DriverManagerDataSource();
        dataSource_.setPassword(puntoSede.getPassword());
        dataSource_.setUrl(puntoSede.getUrl());
        dataSource_.setUsername(puntoSede.getUsername());
        return dataSource_;
    }

    /**
     * Devuelve el datasource de la subsede
     *
     * @param nameDataSource
     * @return
     */
    public DataSource getDataSourceSubSede(String nameDataSource) {
        SubSedesDto subSedesDto = findSubsedeXName(nameDataSource);
        DriverManagerDataSource dataSourceSub_ = new DriverManagerDataSource();
        dataSourceSub_.setPassword(subSedesDto.getPassword());
        dataSourceSub_.setUrl(subSedesDto.getUrl());
        dataSourceSub_.setUsername(subSedesDto.getUsername());
        return dataSourceSub_;
    }

    /**
     * Busca una conexión de las sedes a las que se puede conectar
     *
     * @param sede
     * @return
     */
    public SedesDto findSedeXNameConn(String sede) {
        for (SedesDto sede1 : sedesConnect) {
            if (sede1.getSede().equals(sede)) {
                return sede1;
            }
        }
        return null;
    }

    public SedesDto findSedeXName(String sede) {
        for (SedesDto sede1 : accesosSubsedes.getSedes()) {
            if (sede1.getSede().equals(sede)) {
                return sede1;
            }
        }
        return null;
    }

    public SedesDto findSedeXId(Integer sede) {
        for (SedesDto sede1 : accesosSubsedes.getSedes()) {
            if (Objects.equals(sede, sede1.getIdsedes())) {
                return sede1;
            }
        }
        return null;
    }

    /**
     * Devuelve los datos de conexión de las subsedes
     *
     * @param subsede
     * @return
     */
    public SubSedesDto findSubsedeXName(String subsede) {
        for (SubSedesDto subsede1 : accesosSubsedes.getSubsedes()) {
            if (subsede1.getSede().equals(subsede)) {
                return subsede1;
            }
        }
        return null;
    }
    
    public SubSedesDto findSubsedePrincipalXIdSede(Integer idSede) {
        for (SubSedesDto subsede1 : accesosSubsedes.getSubsedes()) {
            if (Objects.equals(subsede1.getIdsede(), idSede)) {
                if (subsede1.getSede().contains("Principal")) {
                    return subsede1;
                }
            }
        }
        return null;
    }
    
    /**
     * Encuuentra una subsede por el idpadre de credencials
     * @param idSede
     * @return 
     */
    public SubSedesDto findSubSedeXIdSede(Integer idSede) {
        for (SubSedesDto subsede1 : accesosSubsedes.getSubsedes()) {
            if (subsede1.getIdsede().equals(idSede)) {
                return subsede1;
            }
        }
        return null;
    }

    /**
     * Busca una subsede por el id
     *
     * @param idsubsede
     * @return
     */
    public SubSedesDto findSubsedeXId(Integer idsubsede) {
        for (SubSedesDto subsede1 : accesosSubsedes.getSubsedes()) {
            if (Objects.equals(subsede1.getId(), idsubsede)) {
                return subsede1;
            }
        }
        return null;
    }

    /**
     * Busca una subsede por el id de la sede
     *
     * @param idSede
     * @return
     */
    public SubSedesDto finSubsedeXIdCredencials(Integer idSede, Integer idSubSedePoint) {
        for (SubSedesDto subsede1 : accesosSubsedes.getSubsedes()) {
            if (Objects.equals(subsede1.getIdsede(), idSede) && Objects.equals(idSubSedePoint, subsede1.getIdsedepoint())) {
                return subsede1;
            }
        }
        return null;
    }

    /**
     * Devuelve el id de la subsede en la bd credentials
     *
     * @param nameSedePrincipal
     * @param idSubSedePoint
     * @return
     */
    public Integer getIdSubSedePrincpipal(String nameSedePrincipal, Integer idSubSedePoint) {
        SedesDto sedePrincipalDto = findSedeXName(nameSedePrincipal);
        SubSedesDto subSedePrincipalDto = finSubsedeXIdCredencials(sedePrincipalDto.getIdsedes(),
                idSubSedePoint);
        return subSedePrincipalDto.getId();
    }

    /**
     * Trae el usuario logueado en una sede
     *
     * @param sede
     * @return
     */
    public String findUserNameXSede(String sede) {
        for (SedesDto sede_ : accesosSubsedes.getSedes()) {
            if (sede_.getSede().equals(sede)) {
                return sede_.getUsersLogin();
            }
        }
        return "";
    }

    /**
     * Encuentra una sede por su nombre en base de datos
     *
     * @param sede
     * @return
     */
    public String findSedeStringXName(String sede) {
        for (SedesDto sede_ : accesosSubsedes.getSedes()) {
            if (sede_.getSede().equals(sede)) {
                return sede_.getSede();
            }
        }
        return null;
    }

    public UserItemDto findUserItemXIdSede(Integer idSede) {

        for (UserItemDto user_ : accesosSubsedes.getUserLog()) {
            if (Objects.equals(idSede, user_.getIdSedeUser())) {
                return user_;
            }
        }

        return null;
    }

    public List<SedesDto> getSedesConnect() {
        return sedesConnect;
    }

    public void setSedesConnect(List<SedesDto> sedesConnect) {
        this.sedesConnect = sedesConnect;
    }

}
