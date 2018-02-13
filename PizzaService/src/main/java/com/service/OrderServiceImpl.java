package com.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import com.model.OrderItem;
import com.utility.FileHandlerUtil;
import com.utility.OrderMultiFieldSort;

public class OrderServiceImpl implements OrderService {

	private static final Logger logger = Logger.getLogger(OrderServiceImpl.class.getName());

	static String[] header = new String[2]; // header info
	FileHandlerUtil fileUtil = new FileHandlerUtil();

	/**
	 * Get file contents from file in sorted format
	 * 
	 * @param filePath read data from the path
	 * @return sorted contents of order
	 * @throws FileNotFoundException
	 */
	public List<OrderItem> contentReader(String filePath) throws FileNotFoundException {
		if (filePath == null || filePath.isEmpty())
			return null;
		File file = new File(filePath);

		if (!file.exists())
			throw new FileNotFoundException("File not found");

		List<OrderItem> items = fileUtil.readFileFromLocal(filePath);

		// sort order lexicographical
		Collections.sort(items, new OrderMultiFieldSort());
		return items;
	}

	/**
	 * Write content to file
	 * @param items  content to write
	 * @param fileToWrite path where data write
	 * @throws FileNotFoundException
	 */
	public void contentWriter(List<String> items, String pathToWrite) throws FileNotFoundException {

		if (pathToWrite == null || pathToWrite.isEmpty())
			return;
		File file = new File(pathToWrite);
		if (!file.exists())
			throw new FileNotFoundException("File not found");
		
		fileUtil.wirteToFile(items, pathToWrite);
	}


	public void contentWriter(String filePath) {
		// TODO Auto-generated method stub

	}


	public void contentWriter(File file) {
		throw new UnsupportedOperationException("Not implemented yet.");
	}


	public List<OrderItem> contentReader(File file) {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	
	public void saveToDB() {
		throw new UnsupportedOperationException("Not implemented yet.");

	}

	
	public void saveToDB(List<OrderItem> items) {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	
	public void fetchFromDB() {
		throw new UnsupportedOperationException("Not implemented yet.");

	}


	public void fetchFromDB(String Id) {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

}
