package com.printing.apps.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.printing.apps.entity.Printing;;

public interface PrintingDao {
	 
	 void updatePrintingState(String stateOld, String stateNew, Long idOrder);
	 
	 void updatePrinting(Printing printing);
	 
	 List<Printing> findPrintingState(String state);

     Printing findPrintingByIdState(Long idOrder,String state);
     
     void savePrinting(Printing printing);
}
