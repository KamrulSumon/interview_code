package com.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.model.OrderItem;
import com.utility.FileHandlerUtil;

public class OrderServiceImplTest {
	
	//@InjectMocks
	OrderService service = new OrderServiceImpl();

	
	FileHandlerUtil fileUtil = org.mockito.Mockito.mock(FileHandlerUtil.class);
	
	
	String filePath;
	List<OrderItem> items;
	List<OrderItem> sortedItems;
	List<String> contentToWrite;
	
	
	@Before
	public void setup() {
		
		filePath = "/Users/sumon/Desktop/Aquent/sample_data_ordered.txt";
		
		items = new ArrayList<>(Arrays.asList(
				new OrderItem("Meat", "1506176687"),
				new OrderItem("pizza", "1477578287"),
				new OrderItem("Bread", "1477405487")
				));
		
		sortedItems = new ArrayList<>(Arrays.asList(
				new OrderItem("Bread", "1477405487"),
				new OrderItem("Meat", "1506176687"),
				new OrderItem("pizza", "1477578287")		
				));
		
		contentToWrite = new ArrayList<>(Arrays.asList(
				"Bread		2016-10-25 10:24:47 EDT",
				"Meat		2017-09-23 10:24:47 EDT",
				"Pizza		2016-10-24 10:24:47 EDT",
				"VegVeg		2016-09-19 10:24:47 EDT",
				"bread		2016-09-23 10:24:47 EDT",
				"bread		2016-10-23 10:24:47 EDT"
				));
	}
	
	
	@Test
	public void contentReaderTest() throws FileNotFoundException {
		Mockito.when(fileUtil.readFileFromLocal(filePath)).thenReturn(items);
		List<OrderItem> orderItems = service.contentReader(filePath);
		assertEquals(sortedItems.get(0).getItem(), orderItems.get(0).getItem());
	}
	
	
	@Test(expected=FileNotFoundException.class)
	public void contentReaderWithNoFileTest() throws FileNotFoundException {
		filePath = "sample_data_ordered32.txt";
		Mockito.when(fileUtil.readFileFromLocal(filePath)).thenReturn(items);
		List<OrderItem> orderItems = service.contentReader(filePath);
	}
	
	
	@Test
	public void contentReaderNullTest() throws FileNotFoundException {
		filePath = null;
		Mockito.when(fileUtil.readFileFromLocal(filePath)).thenReturn(items);
		List<OrderItem> orderItems = service.contentReader(filePath);
		assertNull(orderItems);
	}
	
	
	
	@Test(expected=FileNotFoundException.class)
	public void contentWriterWithNoFileTest() throws FileNotFoundException {
		filePath = "sample_data_ordered32.txt";
		service.contentWriter(contentToWrite, filePath);
	}
	
	
	@Test
	public void contentWriterNullTest() throws FileNotFoundException {
		filePath = null;
		Mockito.when(fileUtil.readFileFromLocal(filePath)).thenReturn(items);
		service.contentWriter(contentToWrite, filePath);
	}
	

}
