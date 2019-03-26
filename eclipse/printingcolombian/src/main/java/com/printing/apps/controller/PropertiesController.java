package com.printing.apps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.printing.apps.components.LocalConfigurationPrinting;

@RestController
@RequestMapping("/properties")
public class PropertiesController {
	
	@Autowired
	private LocalConfigurationPrinting localConfigurationPrinting;
	
	@RequestMapping("/print")
	@ResponseBody
	public String refreshProperties() {
		String status = "OK";
		
		localConfigurationPrinting.refreshConfiguration();
		
		return status;
	}
}
