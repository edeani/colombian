package com.printing.apps.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="printing")
public class Printing {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cons")
	private Integer cons;
	
	@Column(name="orden")
	private Long orden;
	
	@Column(name="fechaImpresion",nullable=true)
	private Date fechaImpresion;
	
	@Column(name="fechaCreacion")
	private Date fechaCreacion;
	
	@Column(name="estado")
	private String estado;

	public Integer getCons() {
		return cons;
	}

	public void setCons(Integer cons) {
		this.cons = cons;
	}

	public Long getOrden() {
		return orden;
	}

	public void setOrden(Long orden) {
		this.orden = orden;
	}

	public Date getFechaImpresion() {
		return fechaImpresion;
	}

	public void setFechaImpresion(Date fechaImpresion) {
		this.fechaImpresion = fechaImpresion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	
}
