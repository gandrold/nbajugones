/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nbajugones.datagetter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.apache.http.impl.client.HttpClientBuilder;
import org.w3c.dom.Entity;

/**
 *
 * @author iblanco
 */
public abstract class BasicGetter<T> {

	private static final String GZIP = "gzip";

	private static final String TOKEN = "cb401e31-27eb-4211-a28b-b0e05c6316a7";

	public abstract Class<T> getGetterClass();

	public T getResult(String urlString, String token) throws IOException {
		URL url = new URL(urlString);
		HttpURLConnection uc = (HttpURLConnection) url.openConnection();
		uc.setRequestProperty("User-agent", "fantasystats/0.0.1 iblancogarcia@gmail.com");
		uc.setRequestProperty("Accept-encoding", GZIP);
		if (token != null) {
			String basicAuth = "Bearer " + token;
			uc.setRequestProperty("Authorization", basicAuth);
		}
		int statusCode = uc.getResponseCode();
		String encoding = uc.getContentEncoding();
		InputStream in = null;
		String response = "";
		if (statusCode == HttpURLConnection.HTTP_OK) {
			in = uc.getInputStream();
			if (in != null) {
				response = readHttpResponse(in, encoding);
			}
		} else {
			// handle HTTP error
			System.err.println("Server returned HTTP status: " + statusCode + " " + uc.getResponseMessage());
			in = uc.getErrorStream();
			if (in != null) {
				response = readHttpResponse(in, encoding);
				System.err.println(response);
				throw new IOException(response);
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(response, getGetterClass());
	}

	public T getResultsWithApache(String url, String token) throws IOException {
		int timeout = 15;
		RequestConfig config = RequestConfig.custom()
				.setSocketTimeout(timeout * 1000)
				.setConnectTimeout(timeout * 1000)
				.build();
		//Create the request
		UnsafeSSLHelper unsafeSSLHelper = new UnsafeSSLHelper();
		HttpClient client = HttpClientBuilder.create().setSslcontext(unsafeSSLHelper.createUnsecureSSLContext()).
				setHostnameVerifier(unsafeSSLHelper.getPassiveX509HostnameVerifier()).build();
		HttpGet request = new HttpGet(url);
		request.setHeader("Authorization", "Bearer " + token);
		request.setHeader("User-agent", "fantasystats/0.0.1 iblancogarcia@gmail.com");
		request.setHeader(HttpHeaders.ACCEPT_ENCODING, GZIP);
		HttpResponse response = client.execute(request);
		if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(StringUtils.stripAccents(EntityUtils.toString(entity, Charset.forName("UTF-8").name())), getGetterClass());
		}
		return null;
	}

	public List<T> getResults(String url, String token) throws IOException {
		int timeout = 15;
		RequestConfig config = RequestConfig.custom()
				.setSocketTimeout(timeout * 1000)
				.setConnectTimeout(timeout * 1000)
				.build();
		//Create the request
		UnsafeSSLHelper unsafeSSLHelper = new UnsafeSSLHelper();
		HttpClient client = HttpClientBuilder.create().setSslcontext(unsafeSSLHelper.createUnsecureSSLContext()).
				setHostnameVerifier(unsafeSSLHelper.getPassiveX509HostnameVerifier()).build();
		HttpGet request = new HttpGet(url);
		request.setHeader("Authorization", "Bearer " + token);
		request.setHeader("User-agent", "fantasystats/0.0.1 iblancogarcia@gmail.com");
		request.setHeader("Accept-encoding", GZIP);
		HttpResponse response = client.execute(request);
		if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();
			try (InputStream is = entity.getContent()) {
				ObjectMapper mapper = new ObjectMapper();
				return  mapper.readValue(is, mapper.getTypeFactory().constructCollectionType(List.class, getGetterClass()));
			}
		}
		return null;
	}

	private String readHttpResponse(InputStream in, String encoding) {
		StringBuilder sb = new StringBuilder();
		// Verify the response is compressed, before attempting to decompress it
		try {
			if (GZIP.equals(encoding)) {
				in = new GZIPInputStream(in);
			}
		} catch (IOException ex) {
			System.err.println("Error trying to read gzip data.");
			ex.printStackTrace();
		}

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException ex) {
			System.err.println("Error reading response.");
			ex.printStackTrace();
			System.exit(1);
		}
		return sb.toString();
	}

}
