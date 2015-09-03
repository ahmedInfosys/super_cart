

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

@WebServlet("/my_shopping_cart")
public class my_shopping_cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public my_shopping_cart() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
      doProcess(request,response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		user = (OnlineCustomer) session.getAttribute("User");
		
		 String Sign_out ="<a href=\"/Shopping_cart/SignIn.jsp\"> Sign out</a> ";
		 System.out.println("Request.getParamater Value = " + request.getParameter("productID"));
		 String page = "/my_shopping_cart.jsp";
			if(request.getParameter("productID") != null){

				long product_id = Long.parseLong(request.getParameter("productID"));
				int selected_quantity_from_details = 0 ;
				int left_quantity = All_DB.select_single_product(product_id).getQuantity();

				
				ShoppingCart my_cart = new ShoppingCart();
				my_cart.setProductId(product_id);
				my_cart.setUserId(user.getId());
				my_cart.setUnitPrice(All_DB.select_single_product(product_id).getPrice());
				System.out.println("request.getParameter() issssss" + request.getParameter("Qty_from_details"));

				if(request.getParameter("Qty_from_details") != null){
					System.out.println("Qty Not Null");
					selected_quantity_from_details = Integer.parseInt(request.getParameter("Qty_from_details"));
						if(selected_quantity_from_details > left_quantity){
							page = "/Product_details.jsp";
							request.setAttribute("left_qty", "You have selected more than " + left_quantity + " items.");
						}
				}
				my_cart.setUnitQuantity(selected_quantity_from_details);
	
				Shopping_Assns cart_class = new Shopping_Assns();
				cart_class.setCart(my_cart);
				All_DB.insert(cart_class);
			}else if(request.getParameter("remove_item") != null){
				long item_id = Long.parseLong(request.getParameter("remove_item"));
				
				All_DB.delete_item(item_id);
			}else if(request.getParameter("remove_all") != null){
				long user_id = Long.parseLong(request.getParameter("remove_all"));
				All_DB.delete_cart(user_id);
			}
	
			if(All_DB.select_shopping_cart(user.getId()) != null){
				
				
				List <ShoppingCart> my_cart = new ArrayList<ShoppingCart>();
				my_cart = All_DB.select_shopping_cart(user.getId());
				
				session = request.getSession();
				session.setAttribute("my_cart", my_cart);
				All_DB store_in_db = new All_DB();
				session.setAttribute("All_DB", store_in_db);
		
			}
    	 request.setAttribute("sign_in_out", Sign_out );
    	 getServletContext().getRequestDispatcher(page).forward(request,response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
		
	}
	
}
