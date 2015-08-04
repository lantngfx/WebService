package cn.com.servlet.http;  

 import java.io.IOException;  
import java.io.PrintWriter;  

public class ServletDem extends HttpServlet {  

 private static final long serialVersionUID = 382354197754278695L;  
   
     public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {  
        PrintWriter out = response.getWriter();  
        out.println("<html><head>");  
       out.println("<title>First Servlet Hello</title>");  
       out.println("</head><body>");  
         out.println("Hello!Servlet!");  
        out.println("</body></html>");  
         out.close();  
  
   }  
} 