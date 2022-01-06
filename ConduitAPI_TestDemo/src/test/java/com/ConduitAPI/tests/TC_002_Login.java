package com.ConduitAPI.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ConduitAPI.testBase.TestBase;

import io.restassured.path.json.JsonPath;

public class TC_002_Login extends TestBase
{

	@Test(priority=1, enabled=true)
	void LoginUser_checkStatusCode()
	{
		System.out.println("\n********* Status_Code_Validation for Login **********");
		int statusCode = loginResponse.getStatusCode(); // Gettng status code
		System.out.println("Login_Status_Code: "+ statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	@Test(priority=2)
	void LoginUser_checkResponseTime()
	{
		System.out.println("\n********* Response_Time_Validation for LoginUser **********");
		long responseTime = loginResponse.getTime(); 
		System.out.println("Login_Response_Time: " + responseTime);	
		Assert.assertTrue(responseTime<3000);
	}
	
	@Test(priority = 3)
	void LoginUser_checkResponseUsername()
	{
		System.out.println("\n********* Response_Username_Validation for LoginUser **********");
		String responseBody = loginResponse.getBody().asPrettyString();
		JsonPath js = new JsonPath(responseBody);
		
		String respUsername = js.get("user.username");
		
		System.out.println("Response_Body: \n"+responseBody);
		Assert.assertEquals(respUsername, TC_001_CreateUser.username);
		
	}

	
	@Test(priority = 4)
	void LoginUser_checkResponseEmail()
	{
		System.out.println("\n********* Response_Email_Validation for LoginUser **********");
		String responseBody = loginResponse.getBody().asPrettyString();
		JsonPath js = new JsonPath(responseBody);
		
		String respEmail = js.get("user.email");

		System.out.println("Response_Body: \n"+responseBody);
		Assert.assertEquals(respEmail, TC_001_CreateUser.email);
		
	}
}
