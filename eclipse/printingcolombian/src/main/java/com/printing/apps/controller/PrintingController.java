package com.printing.apps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.printing.apps.services.PrintingService;

@RestController
@RequestMapping("/print")
public class PrintingController {

	@Autowired
	private PrintingService printingService;
	
	@RequestMapping("/order")
	@ResponseBody
	public String printingOrder() {
		
		printingService.printOrder();
		
		return "ok";
	}
}
