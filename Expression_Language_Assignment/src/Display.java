

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Display
 */
@WebServlet("/Display")
public class Display extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Display() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long ID = 1;
		String first_name = "Ahmed", last_name = " Hameed";
		String street_address = "676 S Oakland st, ";
		String city = "Aurora, ";
		String state = "CO ";
		String zipcode = "80012";
		int age = 28;
		
		User user = new User();
		
		user.setAge(age);
		user.setFirst_name(first_name);
		user.setLast_name(last_name);
		user.setId(ID);
       
		address addr = new address();
	
		addr.setStreet_address(street_address);
		addr.setCity(city);
		addr.setState(state);
		addr.setZipcode(zipcode);
		 user.setAddress(addr);
		
		HttpSession session = request.getSession();
    	session.setAttribute("user", user);
    	getServletContext().getRequestDispatcher("/user_details.jsp").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
