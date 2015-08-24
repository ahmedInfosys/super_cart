import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Store_Assignment
 */
@WebServlet("/search")
public class find_people extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public find_people() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		response.setContentType("text/html");
		
		String search_table = "";
		String url = "jdbc:oracle:thin:testuser/password@localhost"; 
		//properties for creating connection to Oracle database
		Properties props = new Properties();
		
		props.setProperty("user", "testdb");
		props.setProperty("password", "password");
		
		//creating connection to Oracle database using JDBC
		Connection conn;
		
		PreparedStatement preStatement;
		String sql_command = "select ID, first_name,last_name from customers where last_name like" + " '" + request.getParameter("name") + "%'";

		System.out.println(sql_command);
		
		search_table += "<div class=\"container\"> <table class=\"table table-striped table-bordered\">";
		search_table +=  "<h3>"  + "Search results:" + "</h3>";
		search_table += "<thread>";
		search_table += "<tr>"; 
		search_table += "<th > First Name</th>";  
		search_table += "<th > Last Name</th>"; 
		search_table += "</tr>";
		search_table += "</thread>";
		search_table +=  "<tbody>" ;

		
		try{
				conn = DriverManager.getConnection(url,props);
				preStatement = conn.prepareStatement(sql_command);
				ResultSet result = preStatement.executeQuery();
			    
			    while(result.next()){
			    	
					search_table +=  "<tr>";
					search_table +=  "<td> <a href=\"People_details?customer_ID="+ result.getInt("ID") +" \"> "+ result.getString("First_Name") + " </a></td>";
					search_table +=  "<td >" + result.getString("Last_Name") + "</td>" ;
					search_table +=  "</tr>";
			    }
			    //average = sum/count;
			    
			    search_table += "</tbody> </table> </div>";
			    conn.close();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		
		request.setAttribute("people",search_table);

		getServletContext().getRequestDispatcher("/Search_people.jsp").forward(request, response);
	

	  } 
	  
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
