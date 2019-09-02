package com.taotao.httpclient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientTest {

	/**
	 * 测试Get请求方法
	 */
	@Test
	public void doGet() throws ClientProtocolException, IOException {
		// 创建httpClient对象【通过HttpClients工具类，创建默认的可关闭的HttpClient对象】
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建一个Get对象【类似浏览器地址输入的网址】
		HttpGet get = new HttpGet("http://www.baidu.com");
		// 执行请求【模拟浏览器给这个地址发送请求，得到响应结果】
		CloseableHttpResponse response = httpClient.execute(get);
		// 取响应的结果
		int status = response.getStatusLine().getStatusCode(); // 取状态码
		System.out.println("响应状态码是：" + status);
		HttpEntity entity = response.getEntity(); // 取响应内容
		// 取响应内容，HttpClient提供了一个工具类。
		String str = EntityUtils.toString(entity, "utf-8"); // 如果乱码，设置字符集
		System.out.println("响应内容为：" + str);
		// 关闭连接
		response.close();
		httpClient.close();
	}

	/**
	 * 测试Get请求带参数
	 */
	@Test
	public void doGetWithParam() throws URISyntaxException, ParseException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建URIBuilder
		URIBuilder uriBuilder = new URIBuilder("http://www.baidu.com/s");
		// 添加参数
		uriBuilder.addParameter("wd", "花千骨");
		HttpGet get = new HttpGet(uriBuilder.build());
		// 执行请求【模拟浏览器给这个地址发送请求，得到响应结果】
		CloseableHttpResponse response = httpClient.execute(get);
		// 取响应的结果
		int status = response.getStatusLine().getStatusCode(); // 取状态码
		System.out.println("响应状态码是：" + status);
		HttpEntity entity = response.getEntity(); // 取响应内容
		// 取响应内容，HttpClient提供了一个工具类。
		String str = EntityUtils.toString(entity, "utf-8"); // 如果乱码，设置字符集
		System.out.println("响应内容为：" + str);
		// 关闭连接
		response.close();
		httpClient.close();
	}
	
	/**
	 * 测试Post请求
	 */
	@Test
	public void doPost() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建Post对象
		HttpPost post = new HttpPost("http://localhost:8081/rest/httpclient/post");
		// 执行请求【模拟浏览器给这个地址发送请求，得到响应结果】
		CloseableHttpResponse response = httpClient.execute(post);
		// 取响应的结果
		int status = response.getStatusLine().getStatusCode(); // 取状态码
		System.out.println("响应状态码是：" + status);
		HttpEntity entity = response.getEntity(); // 取响应内容
		// 取响应内容，HttpClient提供了一个工具类。
		String str = EntityUtils.toString(entity, "utf-8"); // 如果乱码，设置字符集
		System.out.println("响应内容为：" + str);
		// 关闭连接
		response.close();
		httpClient.close();
	}
	
	/**
	 * 测试Post请求带参数
	 */
	@Test
	public void doPostWithParam() throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建Post对象
		HttpPost post = new HttpPost("http://localhost:8081/rest/httpclient/postwithparam");
		// 创建一个集合，来模拟表单
		List<NameValuePair> kvList = new ArrayList<>();
		kvList.add(new BasicNameValuePair("username", "zhangsan"));
		kvList.add(new BasicNameValuePair("password", "12345678"));
		// 包装成一个Entity对象
		StringEntity entity = new UrlEncodedFormEntity(kvList, "utf-8");
		// 设置请求内容
		post.setEntity(entity);
		// 取响应的结果
		CloseableHttpResponse response = httpClient.execute(post);
		String string = EntityUtils.toString(response.getEntity(), "utf-8");
		System.out.println("响应内容为：" + string);
		// 关闭连接
		response.close();
		httpClient.close();
	}

}
