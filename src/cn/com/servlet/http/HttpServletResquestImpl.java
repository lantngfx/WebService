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
	 * ���ؿͻ��˷��͵�request�ĵ�һ��
	 * */
	@Override
	public String getHead() {

		return head;
	}
	/*
	 * ���ؿͻ��˵�����ʽ��GET������POST��
	 * */
	@Override
	public String getMethod() {
		
		return head.split(" ")[0];
	}
	/*
	 * ���ؿͻ��˵��������Դ��·��
	 * */
	@Override
	public String getRequestURL() {

		return head.split(" ")[1];
	}
	/*
	 * ���ؿͻ��˵��������õ�Э��汾��HTTP/1.0 ��HTTP/1.1
	 * */
	@Override
	public String getProtocol() {

		return head.split(" ")[2];
	}

}
