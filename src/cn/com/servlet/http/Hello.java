package cn.com.servlet.http;

import java.io.IOException;
import java.io.PrintWriter;

public class Hello extends HttpServlet {
	// ��ʼ��
	public void init() {
		System.out.println("inti");
	}

	// ����ǰִ�е�
	public void destory() {
		System.out.println("destory");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		System.out.println("doget");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>hello</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Hello World!<h1>");
		out.println("</body>");
		out.println("</html>");
	}
}
