package com.ConduitAPI.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ConduitAPI.testBase.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_003_GetUser extends TestBase
{
	RequestSpecification httpRequest;
	Response getUserResponse;
	
	@BeforeClass
	void getUser() throws InterruptedException
	{
		RestAssured.baseURI = "https://api.realworld.io/api";
		httpRequest = RestAssured.given();
		getUserResponse = httpRequest.request(Method.GET, "/profiles/"+username);
	
	}
	
	@Test
	void getUser_checkStatusCode()
	{
		System.out.println("\n********* Status_Code_Validation for GetUser **********");
		int statusCode = getUserResponse.getStatusCode(); // Getting status code
		System.out.println("GetUser_Status_Code: "+ statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test(priority=2)
	void getUser_checkResponseTime()
	{
		System.out.println("\n********* Response_Time_Validation for GetUser **********");
		long responseTime = getUserResponse.getTime(); 
		System.out.println("GetUser_Response_Time: " + responseTime);	
		Assert.assertTrue(responseTime<3000);
	}
	
	@Test(priority = 3, enabled=false)
	void getUser_checkResponseUsername()
	{
		System.out.println("\n********* Response_Username_Validation for GetUser **********");
		String responseBody = getUserResponse.getBody().asPrettyString();
		JsonPath js = new JsonPath(responseBody);
		
		String respUsername = js.get("user.username");
		
		System.out.println("Response_Body: \n"+responseBody);
		Assert.assertEquals(respUsername, username);
		
	}

}
