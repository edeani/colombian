package com.printing.apps.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.printing.apps.dto.ItemFacturaDto;

@Repository
public class OrdenesDaoImpl  implements OrdenesDao{

	@Override
	public List<ItemFacturaDto> findBill(Long idOrder) {
		// TODO Auto-generated method stub
		return null;
	}

}
