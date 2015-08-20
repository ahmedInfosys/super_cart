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

  
  @WebServlet("/customer")
  public class customer_table extends HttpServlet {
	  
	  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Set response content type
		response.setContentType("text/html");
		
		String url = "jdbc:oracle:thin:testuser/password@localhost"; 
		//properties for creating connection to Oracle database
		Properties props = new Properties();
		
		props.setProperty("user", "testdb");
		props.setProperty("password", "password");
		
		//creating connection to Oracle database using JDBC
		Connection conn;
		
		PreparedStatement preStatement;
		String sql_command = "select customer_id, cust_first_name, cust_last_name from demo_customers ";
		
		
		String custTable = "";
		
		custTable += "<div class=\"container-fluid\"> <table class=\"table table-striped table-bordered\">";
		custTable += "<thread>";
		custTable += "<tr>";
		custTable += "<th> ID </th>";   
		custTable += "<th> First Name </th>"; 
		custTable += "<th> Last Name </th>"; 
		custTable += "</tr>";
		custTable += "</thread>";
		custTable +=  "<tbody>" ;
		
		
		try{
				conn = DriverManager.getConnection(url,props);
				preStatement = conn.prepareStatement(sql_command);
				ResultSet result = preStatement.executeQuery();
			    
			    while(result.next()){
					custTable +=  "<tr>";
					custTable +=  "<td>" + "<a href=\"customer_details?customer_id=" + result.getInt("customer_id") + " \"> "+ result.getInt("customer_id") + "</a>" + "</td>";
					custTable +=  "<td>" + result.getString("cust_first_name") + "</td>";
					custTable +=  "<td>" + result.getString("cust_last_name") + "</td>";
					custTable +=  "</tr> <br/>";
			    }
			    
			    custTable += "</tbody> </table> </div>";
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		request.setAttribute("customer_table",custTable);
		getServletContext().getRequestDispatcher("/customer.jsp").forward(request, response);
		
		//customer_details details = new  customer_details ();


			


	  } 
	  
	  
	 
}