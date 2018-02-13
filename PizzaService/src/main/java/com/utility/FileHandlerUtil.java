package com.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.model.OrderItem;

/**
 * Handle File read/write
 * @author Kamrul Hasan
 *
 */
public class FileHandlerUtil {
	private static final Logger logger = Logger.getLogger(FileHandlerUtil.class.getName());
	
	static String[] header = new String[2];  //header info

	/**
	 * File Reading from specified path
	 * @param filePath file path to read
	 * @return oreder items after parsing
	 */
	public List<OrderItem> readFileFromLocal(String filePath) {
		
		ArrayList<OrderItem> items = new ArrayList<OrderItem>();

		try (BufferedReader readerFromFile = new BufferedReader(new FileReader(filePath))) {
			String currentLine;
			
			currentLine = readerFromFile.readLine(); //read header
			header = splitString(currentLine);
			
			while ((currentLine = readerFromFile.readLine()) != null) {			
				if(currentLine != null && !currentLine.isEmpty()) {
				String[] parts = splitString(currentLine);
				
				if(parts[0] != null && parts[1] != null)
					items.add(new OrderItem(parts[0], parts[1]));
				}
			}

		} catch (IOException e) {
			logger.log(Level.SEVERE, "Exception happened during file read" + e.getMessage());
			e.printStackTrace();
		}
		
		return items;
	}

	
	/**
	 * parese each line of data 
	 * @param line order to parse
	 * @return get specified data
	 */
	public String[] splitString(String line) {
		
		if (line == null || line.isEmpty())
			return null;
		
		String[] parts = new String[3];
		StringBuilder sb = new StringBuilder();
		int idx = 0;
		int len = line.length();

		line = line.trim();
		
		// take first part
		while (idx < len) {
			if (line.charAt(idx) == '\t')
				break;
			sb.append(line.charAt(idx++));
		}
		parts[0] = sb.toString();
		sb = new StringBuilder();

		// take space/tab part
		while (idx < len) {
			if (line.charAt(idx) != '\t' || line.charAt(idx) == ' ')
				break;
			sb.append(line.charAt(idx++));
		}
		//parts[1] = sb.toString(); //hold space/tab inbetween
		sb = new StringBuilder();

		// take last part
		while (idx < len) {
			if (line.charAt(idx) == '\t')
				break;
			sb.append(line.charAt(idx++));
		}
		parts[1] = sb.toString();

		return parts;
	}


	/**
	 * Write data to file
	 * @param items data content to write in file
	 * @param pathToWrite 
	 */
	public void wirteToFile(List<String> items, String pathToWrite) {
		try (BufferedWriter writerToFile = new BufferedWriter(new FileWriter(pathToWrite))) {
			
			//write content to file
			for (String item : items) {
				writerToFile.write(item);
			}

		} catch (IOException e) {
			logger.log(Level.SEVERE, "Exception happened during file write" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Formatting the data in human readable format
	 * @param items data to format
	 * @return
	 */
	public  List<String> formatInHumanReadable(List<OrderItem> items){
		List<String> itemsInString = new ArrayList<String>();
		
		if(items == null || items.isEmpty()) return itemsInString;
		
		Date date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		sdf.setTimeZone(TimeZone.getDefault());
		String formattedDate;
		
		//header
		String headers = header[0] + Constant.SPACE + header[1] + Constant.END;
		itemsInString.add(headers);
		
		//write content to file
		for (OrderItem item : items) {
			date = new Date(Long.parseLong(item.getUnixTimeStamp()) * 1000L);
			formattedDate = sdf.format(date);
			String content = item.getItem() + Constant.SPACE + formattedDate + Constant.END;
			itemsInString.add(content);
		}
		
		return itemsInString;
	}

}
