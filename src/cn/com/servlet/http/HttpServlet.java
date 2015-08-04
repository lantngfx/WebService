package cn.com.servlet.http;

import java.io.IOException;

public abstract class HttpServlet {
	//初始化
	public void init(){
		
	}
	//销毁前执行的 
	public void destory(){
		
	}
	
	public void server(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		String method = request.getMethod();
		System.out.println(method);
		if(method.equals("GET")){
			doGet(request,response);
		}else if(method.equals("post")){
			doPost(request,response);
		}else{
			
		}
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		
	}
	
}
