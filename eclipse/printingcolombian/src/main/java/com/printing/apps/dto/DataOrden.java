package com.printing.apps.dto;

import java.util.Date;


public class DataOrden {
	
	private Long numero_orden;
	private String observacion;
	private String numero_telefono;
	private String descripcion_cliente;
	private String direccion_cliente;
	private Date fecha_orden;
	private String barrio;
	
	public Long getNumero_orden() {
		return numero_orden;
	}
	public void setNumero_orden(Long numero_orden) {
		this.numero_orden = numero_orden;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getNumero_telefono() {
		return numero_telefono;
	}
	public void setNumero_telefono(String numero_telefono) {
		this.numero_telefono = numero_telefono;
	}
	public String getDescripcion_cliente() {
		return descripcion_cliente;
	}
	public void setDescripcion_cliente(String descripcion_cliente) {
		this.descripcion_cliente = descripcion_cliente;
	}
	public String getDireccion_cliente() {
		return direccion_cliente;
	}
	public void setDireccion_cliente(String direccion_cliente) {
		this.direccion_cliente = direccion_cliente;
	}
	public Date getFecha_orden() {
		return fecha_orden;
	}
	public void setFecha_orden(Date fecha_orden) {
		this.fecha_orden = fecha_orden;
	}
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	
	
	
}
