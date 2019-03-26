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
public class LocalConfigurationConnection {

	@Value("${driver.classname.connection}")
	String driverClassName;

	@Value("${path.configuration.connection}")
	String pathConnection;

	@Value("${datasource.url}")
	String DATASOURCE_URL;

	@Value("${datasource.username}")
	String DATASOURCE_USERNAME;

	@Value("${datasource.password}")
	String DATASOURCE_PASSWORD;

	private String url;

	private String user;

	private String password;

	@PostConstruct
	private void initConf() {
		try {
			Properties propertie = new Properties();
			propertie.load(new FileInputStream(pathConnection));

			this.url = propertie.getProperty(DATASOURCE_URL);
			this.user = propertie.getProperty(DATASOURCE_USERNAME);
			this.password = propertie.getProperty(DATASOURCE_PASSWORD);
		} catch (IOException e) {
			Logger.getLogger(LocalConfigurationConnection.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public LocalConfigurationConnection() {

	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
