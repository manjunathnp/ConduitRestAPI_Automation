package com.newTests;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ConduitAPI.base.TestBase;
import com.ConduitAPI.utils.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_004_CreatePost extends TestBase{
	RequestSpecification httpRequest;
	Response response;

	
	@SuppressWarnings("unchecked")
	@BeforeClass
	public void createPost() throws InterruptedException
	{
		logger.info("********* Started TC_004_CreatePost **********");
		RestAssured.baseURI = "https://api.realworld.io/api";
		httpRequest = RestAssured.given();
		

		// Add a header stating the Request body is a JSON
		JSONObject requestParams = new JSONObject();
		JSONArray authArray = new JSONArray();
		JSONObject authparam = new JSONObject();
		
		authparam.put("title","Testing API - "+RandomStringUtils.randomAlphabetic(2));
		authparam.put("description", "API Testing authored by "+"tester2334"); 
		authparam.put("body", "Application Programming Interface Testing");
		authArray.add(authparam);
		
		requestParams.put("article", authparam);
		
		
		// Add the JSON to the body of the request
		httpRequest.header("Content-Type", "application/json");
		httpRequest.header("Authorization", "Token "+RestUtils.testToken());
		
		//httpRequest.header("Authorization", RestUtils.token());
		//httpRequest.header("Authorization", TestBase.token);
		// Add the JSON to the body of the request
		httpRequest.body(requestParams.toJSONString());
			
		
		// Use POST Method to Add Users
		response = httpRequest.request(Method.POST, "/articles");
		System.out.println(response.body().asPrettyString());

		
	}
	
	@Test(priority=1)
	void checkStatusCode_createUser()
	{
		logger.info("********* Status_Code_Validation **********");
		int statusCode = response.getStatusCode(); // Getting status code
		logger.info("Status_Code"+ statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC_004_CreatePost **********");
	}
}
