

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import model.PaymentCard;
import model.ShoppingCart;
import model.Shopping_Assns;

/**
 * Servlet implementation class Check_out
 */
@WebServlet("/Check_out")
public class Check_out extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Check_out() {
        super();
      }
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Date now  = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("E MM/dd/yyyy hh:mm a");
    	String Page = "";
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
    	int Quantity_left, Quantity_selected;
    	long product_id = 0;
    	
    	
    	if(request.getParameter("place_order") != null){
    		String fName = request.getParameter("first_name");
    		String lName = request.getParameter("last_name");
    		String street_address = request.getParameter("street_address");
    		String city = request.getParameter("city");
    		String state = request.getParameter("state");
    		String zip_code = request.getParameter("zip_code");
    		String Exp_month = request.getParameter("exp_month");
    		String Exp_year = request.getParameter("exp_year");
    		
    		if(All_DB.select_card(fName, lName, street_address, city, state, zip_code, Exp_month, Exp_year) == null){
    			Page = "/Checkout.jsp";
    			String invalid = "Invalid card information";
    			request.setAttribute("invalid", invalid);
    			
    		}else{
    			List <PaymentCard> my_card = new ArrayList<PaymentCard>();
    			
    			
    			for(ShoppingCart my_cart:All_DB.select_shopping_cart(user_id)){
    	    		
    	    		product_id = my_cart.getProductId();
    	    		
    	    		Quantity_left = All_DB.select_single_product(product_id).getQuantity();
    	    		Quantity_selected = my_cart.getUnitQuantity();
    	    		
    	    		All_DB.update_product(product_id, Quantity_left - Quantity_selected, -1);
    	            
    	    		my_card.get(0).setBalance(my_card.get(0).getBalance() - my_cart.getUnitPrice());
    	    		
    	    		All_DB.update_cart(my_cart.getId(),sdf.format(now),1);
    	    	}
    	    	All_DB.delete_cart(user_id);
    	   
    	    	All_DB store_in_db = new All_DB();

    				List <ShoppingCart> my_cart = new ArrayList<ShoppingCart>();
    				my_cart = All_DB.select_shopping_cart(user.getId());
    				
    				session = request.getSession();
    				session.setAttribute("my_cart", my_cart);
    		
    				session.setAttribute("All_DB", store_in_db);
    			Page = "/Profile.jsp";
    		}
    	}
    	

    	getServletContext().getRequestDispatcher(Page).forward(request,response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

}
