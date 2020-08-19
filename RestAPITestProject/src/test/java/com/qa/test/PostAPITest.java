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
import com.qa.data.UsersPOST;
import com.qa.util.TestUtil;

public class PostAPITest extends TestBase{
	
	TestBase testBase;
	TestUtil testUtil;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
	String ServiceURL;
	String ApiURL;
	String URL;
	
	
	@BeforeTest
	public void setup() {
		testBase = new TestBase();
		ServiceURL = prop.getProperty("serviceURL");
		ApiURL = prop.getProperty("apiURL");
		URL = ServiceURL + ApiURL;
		System.out.println(URL);
	}
	
	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		
		//Creating and adding header values to pass into header of post request
		HashMap<String,String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		System.out.println("Headers Passed with API URL : ");
		System.out.println("Key : Content-Type");
		System.out.println("Value : application/json");		
		System.out.println("");
		
		//Creating Data to be passed as Request JSON Payload
 		//Convert POJO to JSON -- Marshalling 
		//Use Jackson API
		ObjectMapper mapper= new ObjectMapper();
		UsersPOST userspost = new UsersPOST("morpheus","leader");
		
		//Marshalling : create a JSON file under com.qa.data and use writeValue to pass data there
		mapper.writeValue(new File("C:\\Users\\komal\\eclipse-workspace\\RestAPITesting\\RestAPITestProject\\src\\main\\java\\com\\qa\\data\\users.json"), userspost);
		
		//Convert Object to JSON JSON string
		String JSONString = mapper.writeValueAsString(userspost);
		System.out.println(JSONString);
		System.out.println("");
		
		//call post API
		closeableHttpResponse = restClient.post(URL, JSONString, headerMap);
		
		
		//Status code validation : 201 correct status code for POST request
		int statusCode =  closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201);
		System.out.println("Status code validation successful! Status code is : " + statusCode);
		System.out.println("");
		
		//Get JSON Response Payload
		String JSONResponseRaw = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject ResponseJSON = new JSONObject(JSONResponseRaw);
		System.out.println("JSON Response Payload is ---------->"+ResponseJSON);
		
		//Unmarshalling : Converting JSON to JAVA and Printing
		UsersPOST userResponseObj = mapper.readValue(JSONResponseRaw, UsersPOST.class); //actual User Objects
		System.out.println("User Response Object Value is : "+ userResponseObj);
		System.out.println("");
		
		
		//Validating that valued passed in Request API call is equal to value received in Response API
		//users and usersResponse both are storing values for name and Job. We have validate that these values are same. 
		//Once this is passed, our test case for post call is passed.
		Assert.assertTrue((userspost.getName().equals(userResponseObj.getName())));
		Assert.assertTrue((userspost.getJob().equals(userResponseObj.getJob())));
		
		System.out.println("ID : "+ userResponseObj.getId());
		System.out.println("Created At : "+ userResponseObj.getCreatedAt());
		System.out.println("Name : "+ userResponseObj.getName());
		System.out.println("Job : "+ userResponseObj.getJob());
	}

}
