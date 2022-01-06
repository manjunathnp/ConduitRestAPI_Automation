package com.ConduitAPI.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ConduitAPI.testBase.TestBase;

import io.restassured.path.json.JsonPath;

public class TC_001_CreateUser extends TestBase
{	
	@Test(priority=1)
	void createUser_checkStatusCode()
	{
		System.out.println("\n************* Created User Details *************");
		System.out.println("Username: "+username);
		System.out.println("Password: "+email);
		
		System.out.println("\n********* Status_Code_Validation for CreateUser **********");
		int statusCode = TestBase.createUseRresponse.getStatusCode(); // Getting status code
		System.out.println("CreateUser_Status_Code: "+ statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test(priority=2)
	void createUser_checkResponseTime()
	{
		System.out.println("\n********* Response_Time_Validation for CreateUser **********");
		long responseTime = createUseRresponse.getTime(); 
		System.out.println("CreateUser_Response_Time: " + responseTime);	
		Assert.assertTrue(responseTime<3000);
	}
	
	@Test(priority = 3)
	void createUser_checkResponseUsername()
	{
		System.out.println("\n********* Response_Username_Validation for CreateUser **********");
		String responseBody = createUseRresponse.getBody().asPrettyString();
		JsonPath js = new JsonPath(responseBody);
		
		String respUsername = js.get("user.username");
		
		System.out.println("Response_Body: \n"+responseBody);
		Assert.assertEquals(respUsername, username);
		
	}

	
	@Test(priority = 4)
	void createUser_checkResponseEmail()
	{
		System.out.println("\n********* Response_Email_Validation for CreateUser **********");
		String responseBody = createUseRresponse.getBody().asPrettyString();
		JsonPath js = new JsonPath(responseBody);
		
		String respEmail = js.get("user.email");

		System.out.println("Response_Body: \n"+responseBody);
		Assert.assertEquals(respEmail, email);
		
	}
}
