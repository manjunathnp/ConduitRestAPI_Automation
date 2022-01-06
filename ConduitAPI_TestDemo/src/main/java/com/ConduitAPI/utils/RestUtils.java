package com.ConduitAPI.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils 
{
	
	public final static String testUsername()
	{
		 return "tester"+RandomStringUtils.randomAlphabetic(2);
	}
	
	public final static String testEmail()
	{
		 return "@testemail.com";
	}
	
	public final static String testPassword()
	{
		return"Test@123";
		 
	}

	
}
