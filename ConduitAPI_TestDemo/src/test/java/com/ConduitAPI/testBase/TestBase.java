package com.ConduitAPI.testBase;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.ConduitAPI.utils.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase 
{	
	// Declare RequestSpecification & Response Parameters 
	public static RequestSpecification httpRequest;
	public static Response createUseRresponse, loginResponse;
	
	public final static String username = RestUtils.testUsername();
	public final static String email = username+RestUtils.testEmail();
	protected static String token;

	@SuppressWarnings("unchecked")
	@BeforeSuite
	public void createUser() throws InterruptedException
	{
		System.out.println("\n********* User Creation Started **********");
		RestAssured.baseURI = "https://api.realworld.io/api";
		httpRequest = RestAssured.given();
		

		// Add a header stating the Request body is a JSON
		JSONObject requestParams = new JSONObject();
		JSONArray authArray = new JSONArray();
		JSONObject authparam = new JSONObject();
		
		authparam.put("username",username);
		authparam.put("email", email); 
		authparam.put("password", RestUtils.testPassword());
		authArray.add(authparam);
		
		requestParams.put("user", authparam);
		
		// Add the JSON to the body of the request
		httpRequest.header("Content-Type", "application/json");
		
		// Add the JSON to the body of the request
		httpRequest.body(requestParams.toJSONString());
		
		// Use POST Method to Add Users
		createUseRresponse = httpRequest.request(Method.POST, "/users");
		
		System.out.println("********* User Creation Completed **********");
	}
	
	@SuppressWarnings("unchecked")
	@BeforeClass
	public void userLogin() throws InterruptedException
	{
		RestAssured.baseURI = "https://api.realworld.io/api";
		httpRequest = RestAssured.given();
		

		// Add a header stating the Request body is a JSON
		JSONObject requestParams = new JSONObject();
		
		// Create an object of JSONArray
		JSONArray authArray = new JSONArray();
		
		JSONObject authparam = new JSONObject();
		
		authparam.put("username",username);
		authparam.put("email", email); 
		authparam.put("password", RestUtils.testPassword());
	
		authArray.add(authparam);
		
		requestParams.put("user", authparam);
		
		// Add the JSON to the body of the request
		httpRequest.header("Content-Type", "application/json");
		
		// Add the JSON to the body of the request
		httpRequest.body(requestParams.toJSONString());
		
		// Use POST Method to Login
		loginResponse = httpRequest.request(Method.POST, "/users/login");
		
		String responseBody = loginResponse.getBody().asPrettyString();
		JsonPath js = new JsonPath(responseBody);
		
		token = js.get("user.token");

	}
	
//	@BeforeClass
//	public void setUp()
//	{
//		// Define Logger Parameters
//		logger = Logger.getLogger("ConduitAPITesting");
//		PropertyConfigurator.configure("log4j.properties");
//		logger.setLevel(Level.DEBUG);
//		
//		
//	}


}

