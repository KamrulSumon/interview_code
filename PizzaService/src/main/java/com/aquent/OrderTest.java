package com.aquent;

import java.io.FileNotFoundException;
import java.util.List;

import com.model.OrderItem;
import com.service.OrderServiceImpl;
import com.utility.*;

public class OrderTest {

	public static void main(String[] args) throws FileNotFoundException {
		String filePath =  "src/main/resources/sample_data_ordered.txt"   ;//"/Users/sumon/Desktop/Aquent/sample_data_ordered.txt";
		String filePath2 = "/Users/sumon/Desktop/Aquent/sample_data_ordered2.txt";
		OrderServiceImpl oSvc = new OrderServiceImpl();
		FileHandlerUtil fileUtil = new FileHandlerUtil();
		
		List<OrderItem> sortedItems = oSvc.contentReader(filePath); 
		for(OrderItem itm: sortedItems)
			System.out.println(itm.getItem() + " -- "+ itm.getUnixTimeStamp());
		List<String> readableFormat = fileUtil.formatInHumanReadable(sortedItems);
		
		
		for(String itm: readableFormat)
			System.out.println(itm);
		
		
		oSvc.contentWriter(readableFormat, filePath2);

	}

}
