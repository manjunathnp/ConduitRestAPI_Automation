package com.ConduitAPI.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ConduitAPI.testBase.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_005_GetPost extends TestBase{
	
	RequestSpecification httpRequest;
	Response getPostResponse;

	@BeforeClass
	void getUser() throws InterruptedException
	{
		RestAssured.baseURI = "https://api.realworld.io/api";
		httpRequest = RestAssured.given();
		getPostResponse = httpRequest.request(Method.GET, "articles/"+TC_004_CreatePost.respSlug);
	
	}
	
	@Test
	void getPost_checkStatusCode()
	{
		System.out.println("\n********* Status_Code_Validation for GetUser **********");
		int statusCode = getPostResponse.getStatusCode(); // Getting status code
		System.out.println("GetUser_Status_Code: "+ statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test(priority=2)
	void getPost_checkResponseTime()
	{
		System.out.println("\n********* Response_Time_Validation for GetPost **********");
		long responseTime = getPostResponse.getTime(); 
		System.out.println("CreatePost_Response_Time: " + responseTime);	
		Assert.assertTrue(responseTime<3000);
	}

}
