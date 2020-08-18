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
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {

	TestBase testBase;
	RestClient restClient;
	CloseableHttpResponse closeableHttpRespnse;

	String ServiceURL;
	String ApiURL;
	String URL;
	public int StatusCode;
	public String perPageValue;

	@BeforeMethod
	public void setup() {
		testBase = new TestBase();
		ServiceURL = prop.getProperty("serviceURL");
		ApiURL = prop.getProperty("apiURL");
		URL = ServiceURL + ApiURL;
		System.out.println(URL);
	}

	@Test(priority = 1)
	public void verifyGetAPIWithoutHeadersTest() throws ClientProtocolException, IOException {
		System.out.println("Test Case 1 ---------->");
		restClient = new RestClient();
		closeableHttpRespnse = restClient.get(URL);
		System.out.println("Get Method without passing headers is getting called correctly");
		System.out.println("");

		// Getting Status Code
		StatusCode = closeableHttpRespnse.getStatusLine().getStatusCode();
		System.out.println("Status Code : " + StatusCode);

		Assert.assertEquals(StatusCode, testBase.RESPONSE_STATUS_CODE_200, "Status code is not 200");
		System.out.println("");
		
		// Getting Response Payload (JSON String)
		String ResponseData = EntityUtils.toString(closeableHttpRespnse.getEntity(), "UTF-8");
		JSONObject ResponseJSON = new JSONObject(ResponseData);
		System.out.println("Response JSON from API" + ResponseJSON);
		
		System.out.println("");
		// Single Value Assertion
		System.out.println("<-----------------Single value assertions for JSON Response Payload----------------->");
		perPageValue = TestUtil.getValueByJPath(ResponseJSON, "/per_page");
		System.out.println("Value of Per Page is: " + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		System.out.println("");
		
		String totalValue = TestUtil.getValueByJPath(ResponseJSON, "/total");
		System.out.println("Value of total is: " + totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		System.out.println("");
		
		String totalPages = TestUtil.getValueByJPath(ResponseJSON, "/total_pages");
		System.out.println("Value of total pages is:" + totalPages);
		Assert.assertEquals(Integer.parseInt(totalPages), 2);
		System.out.println("");
		
		// Getting the value from JSON Array
		System.out.println("<-----------------Array value assertions for JSON Response Payload----------------->");

		String id = TestUtil.getValueByJPath(ResponseJSON, "/data[1]/id");
		String email = TestUtil.getValueByJPath(ResponseJSON, "/data[1]/email");
		String firstName = TestUtil.getValueByJPath(ResponseJSON, "/data[1]/first_name");
		String lastName = TestUtil.getValueByJPath(ResponseJSON, "/data[1]/last_name");
		String avatar = TestUtil.getValueByJPath(ResponseJSON, "/data[1]/avatar");
		System.out.println("ID: " + id);
		System.out.println("Email: " + email);
		System.out.println(firstName + " " + lastName);
		System.out.println(avatar);
		System.out.println("");
		
		// Getting all Headers
		System.out.println("<----------------Printing All Headers--------------->");
		Header[] headerArray = closeableHttpRespnse.getAllHeaders();

		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}

		System.out.println("Headers Array ------->" + allHeaders);
		System.out.println("================================================================");
		System.out.println("");
	}

	@Test(priority = 2)
	public void verifyStatusCodeTest() throws ClientProtocolException, IOException {

		System.out.println("Test Case 2 ---------->");
		restClient = new RestClient();
		closeableHttpRespnse = restClient.get(URL);

		// Getting Status Code
		StatusCode = closeableHttpRespnse.getStatusLine().getStatusCode();
		System.out.println("Status Code : " + StatusCode);

		Assert.assertEquals(StatusCode, testBase.RESPONSE_STATUS_CODE_200, "Status code is not 200");
		
		System.out.println("================================================================");
		System.out.println("");
	}

	@Test(priority = 3)
	public void verifyGetJSONResponseData() throws ClientProtocolException, IOException {
		System.out.println("Test Case 3 ---------->");
		restClient = new RestClient();
		closeableHttpRespnse = restClient.get(URL);

		// Getting Response Payload (JSON String)
		String ResponseData = EntityUtils.toString(closeableHttpRespnse.getEntity(), "UTF-8");
		JSONObject ResponseJSON = new JSONObject(ResponseData);
		System.out.println("Response JSON from API" + ResponseJSON);
		Assert.assertNotNull(ResponseJSON, "JSON Response is Empty");
		System.out.println("================================================================");
		System.out.println("");
	}

	@Test(priority = 4)
	public void verifySingleValuesJSONResponseTest() throws ClientProtocolException, IOException {
		System.out.println("Test Case 4 ---------->");
		restClient = new RestClient();
		closeableHttpRespnse = restClient.get(URL);

		// Getting Response Payload (JSON String)
		String ResponseData = EntityUtils.toString(closeableHttpRespnse.getEntity(), "UTF-8");
		JSONObject ResponseJSON = new JSONObject(ResponseData);
		System.out.println("Response JSON from API" + ResponseJSON);
		System.out.println("");

		// Single Value Assertion
		System.out.println("<-----------------Single value assertions for JSON Response Payload----------------->");
		perPageValue = TestUtil.getValueByJPath(ResponseJSON, "/per_page");
		System.out.println("Value of Per Page is: " + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);

		String totalValue = TestUtil.getValueByJPath(ResponseJSON, "/total");
		System.out.println("Value of total is: " + totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);

		String totalPages = TestUtil.getValueByJPath(ResponseJSON, "/total_pages");
		System.out.println("Value of total pages is:" + totalPages);
		Assert.assertEquals(Integer.parseInt(totalPages), 2);
		
		System.out.println("================================================================");
		System.out.println("");
	}

	@Test(priority = 5)
	public void verifyJSONArrayvalues() throws ClientProtocolException, IOException {
		System.out.println("Test Case 5 ---------->");
		restClient = new RestClient();
		closeableHttpRespnse = restClient.get(URL);

		// Getting Response Payload (JSON String)
		String ResponseData = EntityUtils.toString(closeableHttpRespnse.getEntity(), "UTF-8");
		JSONObject ResponseJSON = new JSONObject(ResponseData);
		System.out.println("Response JSON from API" + ResponseJSON);

		perPageValue = TestUtil.getValueByJPath(ResponseJSON, "/per_page");
		
		System.out.println("");

		// Getting the value from JSON Array
		System.out.println("<-----------------Array value assertions for JSON Response Payload----------------->");

		int perPage = Integer.parseInt(perPageValue);
		for (int i = 0; i < perPage; i++) {

			String id = TestUtil.getValueByJPath(ResponseJSON, "/data[" + i + "]/id");
			Assert.assertEquals(Integer.parseInt(id), i+1);
			String email = TestUtil.getValueByJPath(ResponseJSON, "/data[" + i + "]/email");
			String firstName = TestUtil.getValueByJPath(ResponseJSON, "/data[" + i + "]/first_name");
			String lastName = TestUtil.getValueByJPath(ResponseJSON, "/data[" + i + "]/last_name");
			String avatar = TestUtil.getValueByJPath(ResponseJSON, "/data[" + i + "]/avatar");
			System.out.println("ID: " + id);
			System.out.println("Email: " + email);
			System.out.println(firstName + " " + lastName);
			System.out.println(avatar);
		}
		System.out.println("================================================================");
		System.out.println("");
	}

	@Test(priority = 6)
	public void verifyGetAPITestWithHeaders() throws ClientProtocolException, IOException {
		System.out.println("Test Case 6 ---------->");
		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json"); // Mandatory Header for all JSON Files
		System.out.println("Headers Passed with API URL : ");
		System.out.println("Key : Content-Type");
		System.out.println("Value : application/json");		
		System.out.println("");

		closeableHttpRespnse = restClient.get(URL, headerMap);

		// Getting Status Code
		StatusCode = closeableHttpRespnse.getStatusLine().getStatusCode();
		System.out.println("Status Code : " + StatusCode);

		Assert.assertEquals(StatusCode, testBase.RESPONSE_STATUS_CODE_200, "Status code is not 200");
		System.out.println("");
		
		// Getting Response Payload (JSON String)
		String ResponseData = EntityUtils.toString(closeableHttpRespnse.getEntity(), "UTF-8");
		JSONObject ResponseJSON = new JSONObject(ResponseData);
		System.out.println("Response JSON from API" + ResponseJSON);
		System.out.println("");
		
		// Single Value Assertion
		System.out.println("<-----------------Single value assertions for JSON Response Payload----------------->");
		perPageValue = TestUtil.getValueByJPath(ResponseJSON, "/per_page");
		System.out.println("Value of Per Page is: " + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);

		String totalValue = TestUtil.getValueByJPath(ResponseJSON, "/total");
		System.out.println("Value of total is: " + totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);

		String totalPages = TestUtil.getValueByJPath(ResponseJSON, "/total_pages");
		System.out.println("Value of total pages is:" + totalPages);
		Assert.assertEquals(Integer.parseInt(totalPages), 2);
		System.out.println("");
		
		// Getting the value from JSON Array
		System.out.println("<-----------------Array value assertions for JSON Response Payload----------------->");

		String id = TestUtil.getValueByJPath(ResponseJSON, "/data[1]/id");
		String email = TestUtil.getValueByJPath(ResponseJSON, "/data[1]/email");
		String firstName = TestUtil.getValueByJPath(ResponseJSON, "/data[1]/first_name");
		String lastName = TestUtil.getValueByJPath(ResponseJSON, "/data[1]/last_name");
		String avatar = TestUtil.getValueByJPath(ResponseJSON, "/data[1]/avatar");
		System.out.println("ID: " + id);
		System.out.println("Email: " + email);
		System.out.println(firstName + " " + lastName);
		System.out.println(avatar);
		System.out.println("");
		
		// Getting all Headers
		Header[] headerArray = closeableHttpRespnse.getAllHeaders();

		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}

		System.out.println("Headers Array ------->" + allHeaders);
		System.out.println("================================================================");
		System.out.println("");

	}

}
