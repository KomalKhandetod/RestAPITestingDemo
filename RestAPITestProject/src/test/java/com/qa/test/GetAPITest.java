package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class GetAPITest extends TestBase {

	TestBase testBase;
	RestClient restClient;
	CloseableHttpResponse closeableHttpRespnse;

	String ServiceURL;
	String ApiURL;
	String URL;
	public int StatusCode;

	@BeforeMethod
	public void setup() {
		testBase = new TestBase();

		ServiceURL = prop.getProperty("serviceURL");
		ApiURL = prop.getProperty("apiURL");
		URL = ServiceURL + ApiURL;
		System.out.println(URL);
	}

	@Test(priority = 1)
	public void verifyGetAPITest() throws ClientProtocolException, IOException {
		System.out.println("Test Case 1 ---------->");
		restClient = new RestClient();
		closeableHttpRespnse = restClient.get(URL);
		System.out.println("Get Method is getting called correctly");

		// Getting Status Code
		StatusCode = closeableHttpRespnse.getStatusLine().getStatusCode();
		System.out.println("Status Code : " + StatusCode);
		
		Assert.assertEquals(StatusCode, testBase.RESPONSE_STATUS_CODE_200, "Status code is not 200");

		// Getting Response Payload (JSON String)
		String ResponseData = EntityUtils.toString(closeableHttpRespnse.getEntity(), "UTF-8");
		JSONObject ResponseJSON = new JSONObject(ResponseData);
		System.out.println("Response JSON from API" + ResponseJSON);

		// Getting all Headers
		Header[] headerArray = closeableHttpRespnse.getAllHeaders();

		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}

		System.out.println("Headers Array ------->" + allHeaders);
	}

}
