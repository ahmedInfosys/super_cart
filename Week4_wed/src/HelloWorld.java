// Import required java libraries
import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// Extend HttpServlet class
@WebServlet("/Hello")
public class HelloWorld extends HttpServlet {
 
  private String message;

  public void init() throws ServletException
  {
      // Do required initialization
      message = "Hello World";
  }

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException{

      // Set response content type
      response.setContentType("text/html");

      // Actual logic goes here.
      //PrintWriter out = response.getWriter();

      
      //out.println("<h3>" + message + "<h3>"); 
      request.setAttribute("SMS", message);
      getServletContext().getRequestDispatcher("/OUTPUT.jsp").forward(request, response);
   } 

   public void destroy() 
   { 
     // do nothing. 
   } 
}