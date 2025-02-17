/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administracion.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Anlod
 */
@Entity
@Table(name="control_notas_credito")
public class ControlNotasCredito implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_control_notac")
    private Long idControlNotaC;
    
    @Column(name = "idsede")
    private Integer idsede;
    
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @Column(name = "total")
    private Double total;
    
    @Column(name = "idusuario")
    private Integer idUsuario;
    
    @Size(max = 50)
    @Column(name = "usuario")
    private String usuario;
    
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    
    
    public ControlNotasCredito(){
        //Do Nothing
    }

    public ControlNotasCredito(Long idControlNotaC, Integer idsede, Date fecha, Double total, Integer idUsuario, String usuario, Date fechaCreacion) {
        this.idControlNotaC = idControlNotaC;
        this.idsede = idsede;
        this.fecha = fecha;
        this.total = total;
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getIdControlNotaC() {
        return idControlNotaC;
    }

    public void setIdControlNotaC(Long idControlNotaC) {
        this.idControlNotaC = idControlNotaC;
    }

    public Integer getIdsede() {
        return idsede;
    }

    public void setIdsede(Integer idsede) {
        this.idsede = idsede;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    
    
}