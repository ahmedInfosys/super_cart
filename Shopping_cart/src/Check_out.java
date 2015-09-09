

import java.io.IOException;
import java.text.NumberFormat;
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
import model.Order;
import model.PaymentCard;
import model.ShoppingCart;
import model.Shopping_Assns;
import model.Transaction;

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
    	NumberFormat currency = NumberFormat.getCurrencyInstance();
    	String Page = "", order_details = "";
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
    	double amount_sum = 0;
    	
    	if(request.getParameter("place_order") != null){
    		String fName = request.getParameter("first_name").toLowerCase();
    		String lName = request.getParameter("last_name").toLowerCase();
    		String street_address = request.getParameter("street_address").toLowerCase();
    		String city = request.getParameter("city").toLowerCase();
    		String state = request.getParameter("state").toLowerCase();
    		String zip_code = request.getParameter("zip_code").toLowerCase();
    		String Exp_month = request.getParameter("exp_month").toLowerCase();
    		String Exp_year = request.getParameter("exp_year").toLowerCase();
    		long card_number = Long.parseLong(request.getParameter("card_number"));
    		if(All_DB.select_card(fName, lName, street_address, city, state, zip_code, Exp_month, Exp_year, card_number) == null){
    			Page = "/Checkout.jsp";
    			String invalid = "Invalid card information";
    			request.setAttribute("invalid", invalid);
    			
    			
    		}else{
    			long card_id = All_DB.select_card(fName, lName, street_address, city, state, zip_code, Exp_month, Exp_year, card_number).getId();
    			PaymentCard my_card = new PaymentCard();
    			Order order = new Order();
    			my_card =  All_DB.select_card(fName, lName, street_address, city, state, zip_code, Exp_month, Exp_year, card_number);

    			for(ShoppingCart my_cart:All_DB.select_shopping_cart(user_id)){
    	    		
    				amount_sum += my_cart.getUnitPrice()*my_cart.getUnitQuantity();
    	    		product_id = my_cart.getProductId();
    	    		
    	    		All_DB.update_card(my_card.getId(),my_card.getBalance() -  my_cart.getUnitPrice());
    	    	    
    	    		order_details += "Product Name: " + All_DB.select_single_product(product_id).getName() +
    	    				"|            Quantity :" + my_cart.getUnitQuantity() + "|            Price: " + currency.format(my_cart.getUnitPrice()) + ",";
    	    		
    	    		
    	    		Quantity_left = All_DB.select_single_product(product_id).getQuantity();
    	    		Quantity_selected = my_cart.getUnitQuantity();
    	    		
    	    		All_DB.update_product(product_id, Quantity_left - Quantity_selected, -1);
    	            
    	    		my_card.setBalance(my_card.getBalance() - my_cart.getUnitPrice());
    	    		
    	    		All_DB.update_cart(my_cart.getId(),sdf.format(now),1);
    	    	}
    			
    			
    			String shipping_address = request.getParameter("shipping_street_address") + " " +
    			request.getParameter("shipping_city") + ", " + request.getParameter("shipping_state") + 
    			" " + request.getParameter("shipping_zip_code");
    			 order.setOrderAmount(amount_sum);
    			 order.setOrderDate(sdf.format(now));
    			 order.setShippingAddress(shipping_address);
    			 order.setOrderDetails(order_details);
    			 order.setUserId(user_id);
    			Shopping_Assns order_class = new Shopping_Assns();
    			order_class.setOrder(order);
    			
    			
    			All_DB.insert(order_class);
    			
    	    	All_DB.delete_cart(user_id);
    	   
    	    	All_DB store_in_db = new All_DB();

    				List <ShoppingCart> my_cart = new ArrayList<ShoppingCart>();
    				my_cart = All_DB.select_shopping_cart(user.getId());
    				
    				session = request.getSession();
    				session.setAttribute("my_cart", my_cart);
    		
    				session.setAttribute("All_DB", store_in_db);
    			Page = "/Thanks.jsp";
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
