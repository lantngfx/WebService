/*package cn.com.servlet.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class WebService {
	private ServerSocket ss = null;
	private Socket s = null;

	public WebService() {
		try {
			ss = new ServerSocket(9001);
			System.out.println("服务器已经启动，等待连接.....");
			while (true) {
				s = ss.accept();
				new SessionThread(s).start();
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

	public SessionThread(Socket s) {
		this.s = s;
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
			if (request.getHead().split(" ").length != 3) {
				return;
			}
			if (!(request.getMethod().equalsIgnoreCase("GET") || request
					.getMethod().equalsIgnoreCase("post"))) {
				return;
			}
			// .....
			String url = "/index.html";
			if (!(request.getRequestURL().equals("/"))) {
				url = request.getRequestURL();
			}
			if (url.toLowerCase().endsWith(".html")
					|| url.toLowerCase().endsWith(".htm")) {
				String filePath = url.substring(1);
				File f = new File(filePath);
				if (f.exists()) {
					new HttpServletResponseImpl(out, "200");

					brRead = new BufferedReader(new InputStreamReader(
							new FileInputStream(f)));
					String data = null;
					while ((data = brRead.readLine()) != null) {
						out.println(data);
					}
				} else {
					new HttpServletResponseImpl(out, "404");
				}
			} else if (url.toLowerCase().endsWith(".jsp")) {
				// ....
			} else {
				// ......
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
*/