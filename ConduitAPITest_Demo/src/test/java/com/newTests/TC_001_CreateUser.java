package com.newTests;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ConduitAPI.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_001_CreateUser extends TestBase
{
	RequestSpecification httpRequest;
	Response response;
	
//	final String isbn = RestUtils.isbnNumber();
//	final String aisle = RestUtils.aisleNumber();
//	final String author = RestUtils.authorName();
//	final String isbnID = isbn.concat(aisle);

	
	@SuppressWarnings("unchecked")
	@BeforeClass
	public void createUser() throws InterruptedException
	{
		logger.info("********* Started TC_001_CreateUser **********");
		RestAssured.baseURI = "https://api.realworld.io/api";
		httpRequest = RestAssured.given();
		

		// Add a header stating the Request body is a JSON
		JSONObject requestParams = new JSONObject();
		JSONArray authArray = new JSONArray();
		JSONObject authparam = new JSONObject();
		
		authparam.put("username","tester"+RandomStringUtils.randomAlphabetic(2));
		authparam.put("email", RandomStringUtils.randomAlphabetic(2)+"@tester2334.com"); 
		authparam.put("password", "tester2334");
		authArray.add(authparam);

		
		requestParams.put("user", authparam);
		
		// Add the JSON to the body of the request
		httpRequest.header("Content-Type", "application/json");
		
		// Add the JSON to the body of the request
		httpRequest.body(requestParams.toJSONString());
		
		// Use POST Method to Add Users
		response = httpRequest.request(Method.POST, "/users");
		
	}
	
	
	@Test(priority=1)
	void checkStatusCode_createUser()
	{
		logger.info("********* Status_Code_Validation **********");
		int statusCode = response.getStatusCode(); // Getting status code
		logger.info("Status_Code"+ statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	

}
