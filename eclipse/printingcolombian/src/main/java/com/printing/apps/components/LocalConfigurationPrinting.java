package com.printing.apps.components;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LocalConfigurationPrinting {
	
	/**
	 * Component attributes
	 */
	private String encabezado;
	private String encabezado_domicilio;
	private String representante;
	private String representante_domicilio;
	private String nit;
	private String telefono;
	private String local_printer_name;
	
	/**
	 * Name properties
	 */
	private static final String PROP_ENCABEZADO = "encabezado";
	private static final String PROP_ENCABEZADO_DOMICILIO = "encabezado_domicilio";
	private static final String PROP_REPRESENTANTE = "representante";
	private static final String PROP_REPRESENTANTE_DOMICILIO = "representante_domicilio";
	private static final String PROP_NIT = "nit";
	private static final String PROP_TELEFONO = "telefono";
	private static final String PROP_LOCAL_PRINTER_NAME = "local_printer_name";
	
	@Value("${path.configuration.printing}")
	String PATH_DATAPOINT;	
	
	@PostConstruct
	void init() {
		refreshConfiguration();
	}
	
	public void refreshConfiguration() {
		try {
			Properties propertie = new Properties();
			propertie.load(new FileInputStream(PATH_DATAPOINT));

			this.encabezado = propertie.getProperty(PROP_ENCABEZADO);
			this.encabezado_domicilio = propertie.getProperty(PROP_ENCABEZADO_DOMICILIO);
			this.representante = propertie.getProperty(PROP_REPRESENTANTE);
			this.representante_domicilio = propertie.getProperty(PROP_REPRESENTANTE_DOMICILIO);
			this.nit = propertie.getProperty(PROP_NIT);
			this.telefono = propertie.getProperty(PROP_TELEFONO);
			this.local_printer_name = propertie.getProperty(PROP_LOCAL_PRINTER_NAME);
			
		} catch (IOException e) {
			Logger.getLogger(LocalConfigurationPrinting.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	public String getEncabezado() {
		return encabezado;
	}
	public void setEncabezado(String encabezado) {
		this.encabezado = encabezado;
	}
	public String getEncabezado_domicilio() {
		return encabezado_domicilio;
	}
	public void setEncabezado_domicilio(String encabezado_domicilio) {
		this.encabezado_domicilio = encabezado_domicilio;
	}
	public String getRepresentante() {
		return representante;
	}
	public void setRepresentante(String representante) {
		this.representante = representante;
	}
	public String getRepresentante_domicilio() {
		return representante_domicilio;
	}
	public void setRepresentante_domicilio(String representante_domicilio) {
		this.representante_domicilio = representante_domicilio;
	}
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getLocal_printer_name() {
		return local_printer_name;
	}
	public void setLocal_printer_name(String local_printer_name) {
		this.local_printer_name = local_printer_name;
	}
	
	
	
	
}
