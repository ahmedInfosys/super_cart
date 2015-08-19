
//Import required java libraries
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

//Extend HttpServlet class
@WebServlet("/Hello")
public class demo_customer extends HttpServlet {

private String fName,lName,email;

public void init() throws ServletException
{
   // Do required initialization
  fName = "Ahmed";
  lName = "Hameed";
  email = "ahha5402@colorado.edu";
}

public void doGet(HttpServletRequest request,
                 HttpServletResponse response)
         throws ServletException, IOException{
	  try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
	} catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
   // Set response content type
   response.setContentType("text/html");

   // Actual logic goes here.
   //PrintWriter out = response.getWriter();
   
   String url = "jdbc:oracle:thin:testuser/password@localhost"; 
   //properties for creating connection to Oracle database
   Properties props = new Properties();
   
   props.setProperty("user", "testdb");
   props.setProperty("password", "password");
 
   //creating connection to Oracle database using JDBC
   Connection conn;
	
	  PreparedStatement preStatement;
	  String sql_command = "select cust_first_name, cust_last_name from demo_customers where customer_id = 3";
	 try{
			conn = DriverManager.getConnection(url,props);
			preStatement = conn.prepareStatement(sql_command);
			ResultSet result = preStatement.executeQuery();
		
		    while(result.next()){
				request.setAttribute("SMS", result.getString("cust_first_name") + "  " + result.getString("cust_last_name"));
		    }
		    
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/OUTPUT.jsp").forward(request, response);
   
} 

public void destroy() 
{ 
  // do nothing. 
} 
}



//// Import required java libraries
//import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
//
//// Extend HttpServlet class
//public class demo_customer extends HttpServlet {
// 
//  private String message;
//
//  public void init() throws ServletException
//  {
//      // Do required initialization
//      message = "Hello World";
//  }
//
//  public void doGet(HttpServletRequest request,
//                    HttpServletResponse response)
//            throws ServletException, IOException
//  {
//      // Set response content type
//      response.setContentType("text/html");
//
//      // Actual logic goes here.
//      PrintWriter out = response.getWriter();
//      out.println("&lt;h1&gt;" + message + "&lt;/h1&gt;"); 
//   } 
//
//   public void destroy() 
//   { 
//     // do nothing. 
//   } 
//}