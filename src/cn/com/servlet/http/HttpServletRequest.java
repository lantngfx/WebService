package cn.com.servlet.http;

public interface HttpServletRequest {
	String getHead();
	String getMethod();
	String getRequestURL();
	String getProtocol();
}
