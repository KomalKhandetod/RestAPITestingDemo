package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class DeleteAPITest extends TestBase {
	TestBase testBase;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;

	String ServiceURL;
	String ApiURL;
	String URL;

	@BeforeMethod
	public void setup() {
		testBase = new TestBase();
		ServiceURL = prop.getProperty("serviceURL");
		ApiURL = prop.getProperty("apiURLDelete");
		URL = ServiceURL + ApiURL;
		System.out.println(URL);
	}

	@Test
	public void verifyDeleteAPITest() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		
		System.out.println("");
		
		//Passing Header
		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");
		System.out.println("Headers Passed with API URL : ");
		System.out.println("Key : Content-Type");
		System.out.println("Value : application/json");		
		System.out.println("");
		
		closeableHttpResponse = restClient.delete(URL, headerMap);

		System.out.println("Delete method getting called correctly");
		System.out.println("");

		// Validate Status Code
		int StatusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(StatusCode, RESPONSE_STATUS_CODE_204);
		System.out.println("Status Code is : " + StatusCode);
		System.out.println("");
		

		// Getting all Headers
		System.out.println("<----------------Printing All Headers--------------->");
		Header[] headerArray = closeableHttpResponse.getAllHeaders();

		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}

		System.out.println("Headers Array ------->" + allHeaders);
		System.out.println("================================================================");
		System.out.println("");

	}

}
