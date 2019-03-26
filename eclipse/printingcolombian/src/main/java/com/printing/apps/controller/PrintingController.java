package com.printing.apps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.printing.apps.entity.Printing;
import com.printing.apps.services.PrintingService;

@RestController
@RequestMapping("/print")
public class PrintingController {

	@Autowired
	private PrintingService printingService;

	@RequestMapping("/pending/orders")
	@ResponseBody
	public String printingOrder() {

		printingService.printOrdersPending();

		return "ok";
	}

	@PostMapping("/pending/byorder")
	@ResponseBody
	public String printingOrder(@RequestParam Long idOrder) {

		Printing p = printingService.savePrintOrder(idOrder);
		if (p == null) {
			printingService.printOrderPendingById(idOrder);
		}

		return "ok";
	}

	@PostMapping("/save")
	@ResponseBody
	public String savePrintingOrder(@RequestParam Long idOrder) {

		printingService.savePrintOrder(idOrder);

		return "ok";
	}
}
