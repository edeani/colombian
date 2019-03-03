package com.printig.apps.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/print")
public class PrintingController {

	
	@RequestMapping("/order")
	@ResponseBody
	public String printingOrder() {
		return "ok";
	}
}
