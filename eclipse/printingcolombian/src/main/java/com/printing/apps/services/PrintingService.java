package com.printing.apps.services;

import com.printing.apps.entity.Printing;

public interface PrintingService {
	
	void printOrdersPending();
	
	void printOrderPendingById(Long idOrder);
	
	Printing savePrintOrder(Long idOrder);
}
