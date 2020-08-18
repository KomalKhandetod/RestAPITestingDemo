package com.qa.client;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {
	
	//1. Create get method
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();  //creates a default http client
		HttpGet httpGet = new HttpGet(url); //creates get connection with this url
		CloseableHttpResponse closeableHttpRespnse = httpClient.execute(httpGet);//Hitting the URL and returns a closable response object
		return closeableHttpRespnse;
	}
	
}
