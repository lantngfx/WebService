package cn.com.servlet.http;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpServletResquestImpl implements HttpServletRequest {
	private String head;
	private BufferedReader br = null;
	
	public HttpServletResquestImpl(BufferedReader br){
		this.br = br;
		try {
			head = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 返回客户端发送的request的第一行
	 * */
	@Override
	public String getHead() {

		return head;
	}
	/*
	 * 返回客户端的请求方式“GET”，“POST”
	 * */
	@Override
	public String getMethod() {
		
		return head.split(" ")[0];
	}
	/*
	 * 返回客户端的请求的资源的路径
	 * */
	@Override
	public String getRequestURL() {

		return head.split(" ")[1];
	}
	/*
	 * 返回客户端的请求所用的协议版本，HTTP/1.0 或HTTP/1.1
	 * */
	@Override
	public String getProtocol() {

		return head.split(" ")[2];
	}

}
