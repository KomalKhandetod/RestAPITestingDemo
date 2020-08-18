package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {
	
	//1. Create get method (Returns without headers)
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();  //creates a default http client
		HttpGet httpGet = new HttpGet(url); //creates get connection with this url
		
		CloseableHttpResponse closeableHttpRespnse = httpClient.execute(httpGet);//Hitting the URL and returns a closable response object
		return closeableHttpRespnse;
	}
	
	//2. Create get method by overloading it (Returns with headers)
	public CloseableHttpResponse get(String url, HashMap<String,String> headerMap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();  //creates a default http client
		HttpGet httpGet = new HttpGet(url); //creates get connection with this url
		
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse closeableHttpRespnse = httpClient.execute(httpGet);//Hitting the URL and returns a closable response object
		return closeableHttpRespnse;
	}
	
}
