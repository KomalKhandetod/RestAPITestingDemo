package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {

	// 1. Create get method (Returns without headers)
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault(); // creates a default http client
		HttpGet httpGet = new HttpGet(url); // creates get connection with this url

		CloseableHttpResponse closeableHttpRespnse = httpClient.execute(httpGet);// Hitting the URL and returns a
																					// closable response object
		return closeableHttpRespnse;
	}

	// 2. Create get method by overloading it (Returns with headers)
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault(); // creates a default http client
		HttpGet httpGet = new HttpGet(url); // creates get connection with this url

		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse closeableHttpRespnse = httpClient.execute(httpGet);// Hitting the URL and returns a
																					// closable response object
		return closeableHttpRespnse;
	}

	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault(); // Creates a default http Client
		HttpPost httpPost = new HttpPost(url); // Create a connection with URL
		httpPost.setEntity(new StringEntity(entityString)); // Send the data to be posted via the request

		// To pass headers with the API request
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
		return closeableHttpResponse;
	}

	public CloseableHttpResponse put(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();// Creates default http Client
		HttpPut httpPut = new HttpPut(url);
		httpPut.setEntity(new StringEntity(entityString));

		// To pass headers with API request
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpPut.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPut);
		return closeableHttpResponse;
	}
	
	
}
