package com.model;

import java.util.Comparator;

/**
 * Data model for order item
 */
public class OrderItem implements Comparator<OrderItem>{

    private String item;
    private String unixTimeStamp;

	public OrderItem(String item, String timeStamp) {
		this.item = item;
		this.unixTimeStamp = timeStamp;
	}

	public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getUnixTimeStamp() {
        return unixTimeStamp;
    }

    public void setUnixTimeStamp(String unixTimeStamp) {
        this.unixTimeStamp = unixTimeStamp;
    }
    
    /**
     * Sort object lexicographically
     */
	public int compare(OrderItem item1, OrderItem item2) {
		int result = item1.getItem().compareTo(item2.getItem());
		//if both item same, then sort by timestamp
		if(result == 0) 
			result = item1.getUnixTimeStamp().compareTo(item2.getUnixTimeStamp());
		return result;
	}
}
