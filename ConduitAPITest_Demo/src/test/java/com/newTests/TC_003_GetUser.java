package com.newTests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ConduitAPI.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_003_GetUser extends TestBase
{
	RequestSpecification httpRequest;
	Response response;
	
	//final String username = "manjunath.np"; // Dont delete
	//final String username = TestBase.username;
	
	@BeforeClass
	void getUser() throws InterruptedException
	{
	
		logger.info("********* Started TC_003_GetUser **********");
		
		RestAssured.baseURI = "https://api.realworld.io/api";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "profiles/"+"tester2334");
	
	}
	
	@Test
	void checkStatusCode_getUser()
	{
		logger.info("********* Status_Code_Validation **********");
		int statusCode = response.getStatusCode(); // Getting status code
		logger.info("Status_Code"+ statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC_003_GetUser **********");
	}
}
