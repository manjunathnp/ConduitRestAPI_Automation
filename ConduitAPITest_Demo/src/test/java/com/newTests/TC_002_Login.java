package com.newTests;

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
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_002_Login extends TestBase{
	RequestSpecification httpRequest;
	Response response;
	
	final String username = RestUtils.username();
	final String  email = RestUtils.email();
	final String password = RestUtils.password();
	
	@SuppressWarnings("unchecked")
	@BeforeClass
	public void userLogin() throws InterruptedException
	{
		logger.info("********* Started TC_002_Login **********");
		RestAssured.baseURI = "https://api.realworld.io/api";
		httpRequest = RestAssured.given();
		

		// Add a header stating the Request body is a JSON
		JSONObject requestParams = new JSONObject();
		
		// Create an object of JSONArray
		JSONArray authArray = new JSONArray();
		
		JSONObject authparam = new JSONObject();
		
		authparam.put("username","tester2334");
		authparam.put("email", "tester2334@tester2334.com"); 
		authparam.put("password", "tester2334");
		
	
		authArray.add(authparam);
		
		requestParams.put("user", authparam);
		
		// Add the JSON to the body of the request
		httpRequest.header("Content-Type", "application/json");
		
		// Add the JSON to the body of the request
		httpRequest.body(requestParams.toJSONString());
		
		// Use POST Method to Login
		response = httpRequest.request(Method.POST, "/users/login");
		
		String responseBody = response.getBody().asPrettyString();
		JsonPath js = new JsonPath(responseBody);
		
		String token = js.get("user.token");
		System.out.println(token);
		

	}
	
	@Test(priority=1, enabled=true)
	void checkStatusCode_LoginUser()
	{
		logger.info("********* Status_Code_Validation **********");
		int statusCode = response.getStatusCode(); // Gettng status code
		logger.info("Status_Code: "+ statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC_002_LoginUser **********");
	}
}
