package cn.com.servlet.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class WebService {
	private ServerSocket ss = null;
	private Socket s = null;
	//
	private static ConfigBean cb;
	static{
		cb = LoadConfig.getConfig();
	}
	//

	public WebService() {
		try {
			ss = new ServerSocket(9001);
			System.out.println("服务器已经启动，等待连接.....");
			while (true) {
				s = ss.accept();
				new SessionThread(s,cb).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class SessionThread extends Thread {
	private Socket s = null;
	private BufferedReader br = null;
	private PrintWriter out = null;
	private BufferedReader brRead = null;
	//
	private ConfigBean cb;
	private List<String> welcome;
	private Map<String,String> config;
	//

	public SessionThread(Socket s,ConfigBean cb) {
		this.s = s;
		this.cb  = cb;
		//
		welcome = cb.getWelcome();
		config = cb.getServletInfo();
		//
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(s.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			HttpServletRequest request = new HttpServletResquestImpl(br);
			HttpServletResponse response = null;
			if (request.getHead().split(" ").length != 3) {
				return;
			}
			if (!(request.getMethod().equalsIgnoreCase("GET") || request
					.getMethod().equalsIgnoreCase("post"))) {
				return;
			}
			// .....
//			String url = "/index.html";          
			//1
			String url = request.getRequestURL();
			File f = null;
			if(url.equals("/")){
				for(String w:welcome){
					f = new File(url.substring(1));
					if(f.exists()){
						url = w;
						break;
					}
				}
				if(url.equals("/")){
				  response=new HttpServletResponseImpl(out, "404");
					return ;
				}
			}
			//
			/*if (!(request.getRequestURL().equals("/"))) {
				url = request.getRequestURL();
			}*/
			if (url.toLowerCase().endsWith(".html")
					|| url.toLowerCase().endsWith(".htm")) {
				String filePath = url.substring(1);
				f = new File(filePath);
				if (f.exists()) {
					new HttpServletResponseImpl(out, "200");

					brRead = new BufferedReader(new InputStreamReader(
							new FileInputStream(f)));
					String data = null;
					while ((data = brRead.readLine()) != null) {
						out.println(data);
					}
				} else {
					 response=new HttpServletResponseImpl(out, "404");
				}
			} else if (url.toLowerCase().endsWith(".jsp")) {
				// ....
			} else {
				String sc = config.get(url);
				if(sc==null){
					new HttpServletResponseImpl(out, "404");
					return ;
				}
				try {
					Class c = Class.forName(sc);
					HttpServlet servlet = (HttpServlet)c.newInstance();
					response=new HttpServletResponseImpl(out, "200");
					servlet.init();
					servlet.server(request, response);
					servlet.destory();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response=new HttpServletResponseImpl(out, "500");
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response=new HttpServletResponseImpl(out, "500");
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response=new HttpServletResponseImpl(out, "500");
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response=new HttpServletResponseImpl(out, "500");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			new HttpServletResponseImpl(out, "500");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (s != null) {
				try {
					s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (brRead != null) {
				try {
					brRead.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			out.close();
		}

	}
}
