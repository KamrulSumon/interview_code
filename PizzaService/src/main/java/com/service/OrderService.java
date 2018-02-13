package com.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import com.model.OrderItem;

public interface OrderService {
	public List<OrderItem> contentReader(String filePath) throws FileNotFoundException;
	public List<OrderItem> contentReader(File file) throws FileNotFoundException;
	
	public void contentWriter(String pathToWrite) throws FileNotFoundException;
	public void contentWriter(List<String> items, String pathToWrite) throws FileNotFoundException;
	public void contentWriter(File file) throws FileNotFoundException;

	
	
	public void saveToDB();
	public void saveToDB(List<OrderItem> items);
	public void fetchFromDB();
	public void fetchFromDB(String Id);
}
