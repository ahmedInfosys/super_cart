

import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.All_DB;
import model.OnlineCustomer;
import model.Order;
import model.Shopping_Assns;

/**
 * Servlet implementation class MyOrders
 */
@WebServlet("/MyOrders")
public class MyOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MyOrders() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(true);
		if(session.getAttribute("User") == null){
			OnlineCustomer user = new OnlineCustomer();
			user.setFirstname("Customer");
			user.setLastname(",Please sign in to complete shopping");
			Shopping_Assns user_class = new Shopping_Assns();
			user_class.setCustomer(user);
			
			All_DB.insert(user_class);
			session.setAttribute("User", user);
		}
    	System.out.println(session.getAttribute("User").toString());
    	OnlineCustomer user = new OnlineCustomer();
    	user = (OnlineCustomer) session.getAttribute("User");
    	
    	long user_id = user.getId();
        List<Order> my_order = All_DB.select_order(user_id);
        session = request.getSession();
        session.setAttribute("my_orders", my_order);

    	getServletContext().getRequestDispatcher("/my_orders.jsp").forward(request,response);

    	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
