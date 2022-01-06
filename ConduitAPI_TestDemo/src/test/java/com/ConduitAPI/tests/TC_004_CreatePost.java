package com.ConduitAPI.tests;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ConduitAPI.testBase.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_004_CreatePost extends TestBase{
	RequestSpecification httpRequest;
	Response createPostResponse;
	protected static String respSlug;
	
	@SuppressWarnings("unchecked")
	@BeforeClass
	public void createPost() throws InterruptedException
	{
		RestAssured.baseURI = "https://api.realworld.io/api";
		httpRequest = RestAssured.given();
		

		// Add a header stating the Request body is a JSON
		JSONObject requestParams = new JSONObject();
		JSONArray authArray = new JSONArray();
		JSONObject authparam = new JSONObject();
		
		authparam.put("title","Testing API by "+username);
		authparam.put("description", "API Testing authored by "+username); 
		authparam.put("body", "Application Programming Interface Testing by "+username);
		authArray.add(authparam);
		
		requestParams.put("article", authparam);
		
		
		// Add the JSON to the body of the request
		httpRequest.header("Content-Type", "application/json");
		httpRequest.header("Authorization", "Token "+TestBase.token);
		
		// Add the JSON to the body of the request
		httpRequest.body(requestParams.toJSONString());
			
		
		// Use POST Method to Add Users
		createPostResponse = httpRequest.request(Method.POST, "/articles");
		
	}
	
	@Test(priority=1)
	void createPost_checkStatusCode()
	{
		System.out.println("\n********* Status_Code_Validation for CreatePost**********");
		int statusCode = createPostResponse.getStatusCode(); // Getting status code
		System.out.println("CreatePost_Status_Code: "+ statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test(priority=2)
	void createPost_checkResponseTime()
	{
		System.out.println("\n********* Response_Time_Validation for GetUser **********");
		long responseTime = createPostResponse.getTime(); 
		System.out.println("CreatePost_Response_Time: " + responseTime);	
		Assert.assertTrue(responseTime<3000);
	}
	
	@Test(priority = 3)
	void createPost_checkResponseTitle()
	{
		System.out.println("\n********* Response_Title_Validation for GetUser **********");
		String responseBody = createPostResponse.getBody().asPrettyString();
		JsonPath js = new JsonPath(responseBody);
		
		String respTitle = js.get("article.title");
		
		System.out.println("Response_Body: \n"+responseBody);
		Assert.assertEquals(respTitle, "Testing API by "+username);
		
	}
	
	@Test(priority = 4)
	void createPost_checkResponseDescription()
	{
		System.out.println("\n********* Response_Description_Validation for GetUser **********");
		String responseBody = createPostResponse.getBody().asPrettyString();
		JsonPath js = new JsonPath(responseBody);
		
		String respDescription = js.get("article.description");
		
		System.out.println("Response_Body: \n"+responseBody);
		Assert.assertEquals(respDescription, "API Testing authored by "+username);
		
	}
	
	@Test(priority = 5)
	void createPost_checkResponseBody()
	{
		System.out.println("\n********* Response_Body_Validation for GetUser **********");
		String responseBody = createPostResponse.getBody().asPrettyString();
		JsonPath js = new JsonPath(responseBody);
		
		String respBody = js.get("article.body");
		respSlug = js.get("article.slug");
		
		System.out.println("Response_Body: \n"+responseBody);
		Assert.assertEquals(respBody, "Application Programming Interface Testing by "+username);
		
	}
}
