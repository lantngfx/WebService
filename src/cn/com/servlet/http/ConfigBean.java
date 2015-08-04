package cn.com.servlet.http;

import java.util.List;
import java.util.Map;

public class ConfigBean {
	private Map<String, String> servletInfo;
	private List<String> welcome;
	public Map<String, String> getServletInfo() {
		return servletInfo;
	}
	public void setServletInfo(Map<String, String> servletInfo) {
		this.servletInfo = servletInfo;
	}
	public List<String> getWelcome() {
		return welcome;
	}
	public void setWelcome(List<String> welcome) {
		this.welcome = welcome;
	}
	
	
}
