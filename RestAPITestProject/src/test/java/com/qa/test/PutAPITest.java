package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.UsersPUT;

public class PutAPITest extends TestBase{
	
	TestBase testBase;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
	String ServiceURL;
	String ApiURL;
	String URL;
	
	
	@BeforeTest
	public void setup() {
		testBase = new TestBase();
		ServiceURL = prop.getProperty("serviceURL");
		ApiURL = prop.getProperty("apiURLPut");
		URL = ServiceURL + ApiURL;
		System.out.println(URL);
	}
	
	@Test
	public void putAPTTest() throws JsonGenerationException, JsonMappingException, IOException {
		
		restClient = new RestClient();
		
		//Creating and Passing header values
		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");
		System.out.println("Headers Passed with API URL : ");
		System.out.println("Key : Content-Type");
		System.out.println("Value : application/json");		
		System.out.println("");
		
		ObjectMapper mapper = new ObjectMapper();
		UsersPUT usersPut = new UsersPUT("morpheus","zion resident");
		
		mapper.writeValue(new File("C:\\Users\\komal\\eclipse-workspace\\RestAPITesting\\RestAPITestProject\\src\\main\\java\\com\\qa\\data\\users.json"), usersPut);
		
		String JSONString = mapper.writeValueAsString(usersPut);
		System.out.println(JSONString);
		System.out.println("");
		
		//calling put API
		closeableHttpResponse = restClient.put(URL, JSONString, headerMap);
		
		//Validate the response received as JSON Response Payload
		//1. Status code should be 200
		int StatusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(StatusCode, RESPONSE_STATUS_CODE_200);
		System.out.println("Status code validation successful! Status code is : " + StatusCode);
		System.out.println("");
		
		//Getting Response Payload value and storing it to string
		String JSONResponseRaw = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject JSONResponse = new JSONObject(JSONResponseRaw); //COnverts into readable JSON Format
		System.out.println("JSON Response Payload is ---------->"+JSONResponse);
		
		//Converting Response JSON Obj into Readable Java Object
		UsersPUT userResponsePut = mapper.readValue(JSONResponseRaw, UsersPUT.class);
		System.out.println("User Response Object Value is : "+ userResponsePut);
		System.out.println("");
		
		//validating both values
		Assert.assertTrue(usersPut.getName().equals(userResponsePut.getName()));
		Assert.assertTrue(usersPut.getJob().equals(userResponsePut.getJob()));
		
		System.out.println("Name : "+ userResponsePut.getName());
		System.out.println("Job : "+ userResponsePut.getJob());
		System.out.println("Created At : "+ userResponsePut.getUpdatedAt());
	}

}
