package cn.com.servlet.http;

import java.io.PrintWriter;

public class HttpServletResponseImpl implements HttpServletResponse {
	private PrintWriter out = null;
	private String status;
	public HttpServletResponseImpl(PrintWriter out,String status){
		this.out = out;
		this.status = status;
		sendResponse();
	}
	@Override
	public PrintWriter getWriter() {

		return out;
	}
	/*
	 * 向客户端发送response
	 * */
	public void sendResponse(){
		out.println("HTTP/1.0 "+status+" OK\r\nContent-type: text/html; charset=UTF-8\r\n\r\n");
	}

}
