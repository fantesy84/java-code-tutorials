/**
 * 项目名: school-common
 * 包名:  com.shangde.school.util
 * 文件名: HttpClientUtils.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年11月5日
 */
package net.fantesy84.common.util.httpclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import net.fantesy84.common.util.json.jackson.JsonUtils;

/**
 * @author Andronicus
 * @since 2015年11月5日
 */
public class HttpClientUtils {
	private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HttpClientUtils.class.getName());
	private final int DEFAULT_TIMEOUT = 10000;

	private static HttpClientUtils ins;

	private HttpClient client;
	private int timeout = DEFAULT_TIMEOUT;

	private HttpClientUtils() {
		if (client == null) {
			client = HttpClients.createDefault();
		}
	}

	public static HttpClientUtils getInstance() {
		if (ins == null) {
			synchronized (HttpClientUtils.class) {
				if (ins == null) {
					ins = new HttpClientUtils();
				}
			}
		}
		return ins;
	}

	public String doGetWithJsonResult(String uri) {
		String json = null;
		log.debug("========= Call [{}] Start ==========", uri);
		try {
			HttpGet request = new HttpGet(uri);
			RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(timeout)
					.setConnectTimeout(timeout).setSocketTimeout(timeout).build();
			request.setConfig(config);
			HttpResponse response = client.execute(request);
			log.debug("Response status code: {}", response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				json = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
				log.debug("Payload : {}", json);
			}
		} catch (Exception e) {
			log.error("HttpClient has exception! message: {}", e.getMessage(), e);
			return null;
		}
		log.debug("========= Call [{}] End ==========", uri);
		return json;
	}
	
	public <T> T doGetWithJsonResult(String uri, Class<T> javaType) {
		T result = null;
		log.debug("========= Call [{}] Start ==========", uri);
		try {
			HttpGet request = new HttpGet(uri);
			RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(timeout)
					.setConnectTimeout(timeout).setSocketTimeout(timeout).build();
			request.setConfig(config);
			HttpResponse response = client.execute(request);
			log.debug("Response status code: {}", response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String json = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
				log.debug("Payload : {}", json);
				result = JsonUtils.getInstance().toBean(json, javaType);
			}
		} catch (Exception e) {
			log.error("HttpClient has exception! message: {}", e.getMessage(), e);
			return null;
		}
		log.debug("========= Call [{}] End ==========", uri);
		return result;
	}

	public String doPostWithJsonResult(String uri, Map<String, String> paramMap) {
		String json = null;
		log.debug("========= Call [{}] Start ==========", uri);
		try {
			HttpPost request = new HttpPost(uri);
			RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(timeout)
					.setConnectTimeout(timeout).setSocketTimeout(timeout).build();
			request.setConfig(config);
			List<NameValuePair> params = new ArrayList<NameValuePair>(0);
			if (paramMap != null && !paramMap.isEmpty()) {
				for (String key : paramMap.keySet()) {
					params.add(new BasicNameValuePair(key, paramMap.get(key)));
				}
				request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			}
			HttpResponse response = client.execute(request);
			log.debug("Response status code: {}", response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				json = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
				log.debug("Payload : {}", json);
			}
		} catch (Exception e) {
			log.error("HttpClient has exception! message: {}", e.getMessage(), e);
			return null;
		}
		log.debug("========= Call [{}] End ==========", uri);
		return json;
	}
	
	public <T> T doPostWithJsonResult(String uri, Map<String, String> paramMap, Class<T> javaType) {
		T result = null;
		log.debug("========= Call [{}] Start ==========", uri);
		try {
			HttpPost request = new HttpPost(uri);
			RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(timeout)
					.setConnectTimeout(timeout).setSocketTimeout(timeout).build();
			request.setConfig(config);
			List<NameValuePair> params = new ArrayList<NameValuePair>(0);
			if (paramMap != null && !paramMap.isEmpty()) {
				for (String key : paramMap.keySet()) {
					params.add(new BasicNameValuePair(key, paramMap.get(key)));
				}
				request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			}
			HttpResponse response = client.execute(request);
			log.debug("Response status code: {}", response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String json = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
				log.debug("Payload : {}", json);
				result = JsonUtils.getInstance().toBean(json, javaType);
			}
		} catch (Exception e) {
			log.error("HttpClient has exception! message: {}", e.getMessage(), e);
			return null;
		}
		log.debug("========= Call [{}] End ==========", uri);
		return result;
	}

	public String doPostWithJsonResult(String uri, String jsonParameters) {
		log.debug("========= Call [{}] Start ==========", uri);
		HttpPost request = new HttpPost(uri);
		RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(timeout)
				.setConnectTimeout(timeout).setSocketTimeout(timeout).build();
		request.setConfig(config);
		request.setEntity(new StringEntity(jsonParameters, ContentType.APPLICATION_JSON));
		HttpResponse response = null;
		String responseStr = null;
		try {
			response = client.execute(request);
			log.debug("Response status code: {}", response.getStatusLine().getStatusCode());
			responseStr = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
			log.debug("Payload : {}", responseStr);
		} catch (Exception e) {
			log.error(e.getMessage(), new IllegalStateException(e));
		}
		log.debug("========= Call [{}] End ==========", uri);
		return responseStr;
		
	}
	
	public String doPost(String url, String jsonStr) {
		log.debug("========= Call [{}] Start ==========", url);
		URL u = null;
		HttpURLConnection con = null;
		// 尝试发送请求
		// String sendData = JSONObject.fromObject(paramsMap).toString();
		try {
			u = new URL(url);
			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			OutputStreamWriter osw = new OutputStreamWriter(
					con.getOutputStream(), "UTF-8");
			log.debug("即将发送参数:{}", jsonStr);
			osw.write(jsonStr);
			osw.flush();
			osw.close();
		} catch (Exception e) {
			log.error(e.getMessage(), new IllegalStateException(e));
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		// 读取返回内容
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream(), "UTF-8"));
			String temp = "";
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), new IllegalStateException(e));
		}
		String result = buffer.toString();
		log.debug("Payload: {}", result);
		log.debug("========= Call [{}] End ==========", url);
		return result;
	}
	/**
	 * @return the timeout
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
}
