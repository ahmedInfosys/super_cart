

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.All_DB;
import model.OnlineCustomer;
import model.Product;
import model.ShoppingCart;
import model.Shopping_Assns;



/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Sign_out ="<a href=\"/Shopping_cart/SignIn.jsp\"> Sign out</a> ";
		 List <Product> found_products = new ArrayList<Product>();
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
		 OnlineCustomer user = new OnlineCustomer();
			
     	 user = (OnlineCustomer) session.getAttribute("User");/////change
     	 All_DB store_in_db = new All_DB();

		List <ShoppingCart> my_cart = new ArrayList<ShoppingCart>();
		my_cart = All_DB.select_shopping_cart(user.getId());
				
		session = request.getSession();
		session.setAttribute("my_cart", my_cart);
		
		session.setAttribute("All_DB", store_in_db);

		 if(request.getParameter("search") != null){
			 String input = request.getParameter("search").toLowerCase();
			 
			 if(All_DB.Search(input) != null){
					session = request.getSession();
				    found_products = All_DB.Search(input);
					session.setAttribute("found_products", found_products);
					request.setAttribute("sign_in_out", Sign_out);
			 }
			 
		 }

  	    request.setAttribute("sign_in_out", Sign_out);
  	  getServletContext().getRequestDispatcher("/Search.jsp").forward(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
