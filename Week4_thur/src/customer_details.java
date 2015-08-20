// Import required java libraries
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

// Extend HttpServlet class

  
@WebServlet("/customer_details")
public class customer_details extends HttpServlet {
	  
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			// int customer_id  = Integer.parseInt(request.getParameter("customer_id"));
			 String url = "jdbc:oracle:thin:testuser/password@localhost"; 
		      //properties for creating connection to Oracle database
		      Properties props = new Properties();
		      
		      props.setProperty("user", "testdb");
		      props.setProperty("password", "password");
		    
		      //creating connection to Oracle database using JDBC
		      Connection conn;
			
			  PreparedStatement preStatement;
			  String  custDetails = "";
			  String sql_command2 = "select cust_first_name, cust_last_name, cust_street_address1, cust_city,cust_state,cust_postal_code,phone_number1 from demo_customers where customer_id = " + request.getParameter("customer_id");
			  try{
					conn = DriverManager.getConnection(url,props);
					preStatement = conn.prepareStatement(sql_command2);
					ResultSet result = preStatement.executeQuery();
				
				    while(result.next()){
				    	custDetails += "<h3> Name:" + result.getString("cust_first_name") + " " + result.getString("cust_last_name")+ "</h3>" ;
				    	//custDetails += "<h3> Last Name: " +  + "</h3>" ;
				    	custDetails += "<h3> Address: "   + result.getString("cust_street_address1") + "</h3>";
				    	custDetails += "<h3>"   +result.getString("cust_city") +", " + result.getString("cust_state") + "   " + result.getInt( "cust_postal_code") + "</h3>";
				    	custDetails += "<h3> Phone Number: "   + result.getString("phone_number1") + "</h3>";
				    	
				    }
			  }catch (SQLException e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  request.setAttribute("customer_details",custDetails);
			  getServletContext().getRequestDispatcher("/customer_details.jsp").forward(request, response);
		  }

		   public void destroy() 
		   { 
		     // do nothing. 
		   } 
}