package com.printing.apps;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.printing.apps.components.LocalConfigurationConnection;

@SpringBootApplication
public class PrintingcolombianApplication {
	
	@Autowired
	private LocalConfigurationConnection confConnection;
	
	public static void main(String[] args) {
		SpringApplication.run(PrintingcolombianApplication.class, args);
	}
	
	@Bean
	public DataSource dataSource() {
	    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
	        dataSourceBuilder.url(confConnection.getUrl());
	        dataSourceBuilder.username(confConnection.getUser());
	        dataSourceBuilder.password(confConnection.getPassword());
	        dataSourceBuilder.driverClassName(confConnection.getDriverClassName());
	        return dataSourceBuilder.build();   
	}
}
