package com.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class FileHandlerUtilTest {

	String item = null;
	FileHandlerUtil fileUtil;
	
	@Before
	public void setup() {
		item = "Bread		1477405487";
		fileUtil = new FileHandlerUtil();
	
	}
	
	
	@Test
	public void splitStringTest() {
		String[] parts = fileUtil.splitString(item);
		String expected = "Bread";
		assertEquals(expected, parts[0]);
	}
	
	@Test
	public void splitStringNotMatchTest() {
		item = "Pizza		1477405487";
		String[] parts = fileUtil.splitString(item);
		String expected = "Bread";
		assertNotEquals(expected, parts[0]);
	}
	
	@Test
	public void splitStringNullTest() {
		item = null;
		String[] parts = fileUtil.splitString(item);
		assertNull(parts);
	}
	
	@Test
	public void splitStringEmptyTest() {
		item = "";
		String[] parts = fileUtil.splitString(item);
		assertNull(parts);
	}
}
