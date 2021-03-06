package com.printing.apps.dao;

import java.util.List;

import com.printing.apps.dto.DataOrden;
import com.printing.apps.dto.ItemFacturaDto;

public interface OrdenesDao {

	List<ItemFacturaDto> findBill(Long idOrder);
	
	DataOrden getDataOrder(Long idOrder);
}
