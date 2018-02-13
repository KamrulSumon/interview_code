package com.aquent;

import java.io.FileNotFoundException;
import java.util.List;

import com.model.OrderItem;
import com.service.OrderServiceImpl;
import com.utility.*;

/**
 * Test Pizza order system
 * @author Kamrul Hasan
 *
 */
public class OrderTest {

	public static void main(String[] args) throws Exception {
		OrderServiceImpl oSvc = new OrderServiceImpl();
		FileHandlerUtil fileUtil = new FileHandlerUtil();
		
		if(args.length < 2) throw new Exception("Please enter number of arguments correctly,(toread and towrite in file)");
		
		String pathToRead = args[0];
		String pathToWrite = args[1];

		if(pathToRead == null || pathToRead.isEmpty()) throw new Exception("File can't be empty or null for reading");
		if(pathToWrite == null || pathToWrite.isEmpty()) throw new Exception("File can't be empty or null for writing");
		
		List<OrderItem> sortedItems = oSvc.contentReader(pathToRead); 
		List<String> readableFormat = fileUtil.formatInHumanReadable(sortedItems);
		
		oSvc.contentWriter(readableFormat, pathToWrite);

	}

}
