package com.utility;

import java.util.Comparator;

import com.model.OrderItem;;

public class OrderMultiFieldSort implements Comparator<OrderItem>{

	@Override
	public int compare(OrderItem item1, OrderItem item2) {
		int result = item1.getItem().compareTo(item2.getItem());
		//if both item same, then sort by timestamp
		if(result == 0) 
			result = item1.getUnixTimeStamp().compareTo(item2.getUnixTimeStamp());
		return result;
	}

}
