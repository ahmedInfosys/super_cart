

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
 * Servlet implementation class my_shopping_cart
 */
@WebServlet("/my_shopping_cart")
public class my_shopping_cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public my_shopping_cart() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String  FirstName, LastName, cart_products = "";
		

		HttpSession session = request.getSession(true);
		OnlineCustomer user = new OnlineCustomer();
		user = (OnlineCustomer) session.getAttribute("User");
		
		 String Sign_out ="<a href=\"/Shopping_cart/SignIn.jsp\"> Sign out</a> ";
		 System.out.println("Request.getParamater Value = " + request.getParameter("ProductID"));
		 
		 NumberFormat currency = NumberFormat.getCurrencyInstance();
			String ProductName,Description;
			int Quantity;
			double Price;

			
			if(request.getParameter("productID") != null){
				
				long product_id = Long.parseLong(request.getParameter("productID"));
				Product pro = new Product();
				pro.setName(All_DB.select_single_product(product_id).getName());
				pro.setQuantity(All_DB.select_single_product(product_id).getQuantity());
				pro.setPrice(All_DB.select_single_product(product_id).getPrice());
				
				ShoppingCart my_cart = new ShoppingCart();
				my_cart.setProductId(product_id);
				my_cart.setUserId(user.getId());
				my_cart.setUnitPrice(All_DB.select_single_product(product_id).getPrice());
				my_cart.setUnitQuantity(All_DB.select_single_product(product_id).getQuantity());
				
				Shopping_Assns cart_class = new Shopping_Assns();
				cart_class.setCart(my_cart);
				All_DB.insert(cart_class);
			}else if(request.getParameter("remove_product") != null){
				long product_id = Long.parseLong(request.getParameter("remove_product"));
				All_DB.delete_item(product_id);
			}else if(request.getParameter("remove_all") != null){
				long user_id = Long.parseLong(request.getParameter("remove_all"));
				All_DB.delete_cart(user_id);
			}
			
			
			
			double Total = 0;
			//cart_products+= "<nav class=\"navbar navbar-default col-sm-10\">" ;
			if(All_DB.select_shopping_cart(user.getId()) != null){
				
				
				List <ShoppingCart> my_cart = new ArrayList<ShoppingCart>();
				my_cart = All_DB.select_shopping_cart(user.getId());
				
				session = request.getSession();
				session.setAttribute("my_cart", my_cart);
				All_DB store_in_db = new All_DB();
				session.setAttribute("All_DB", store_in_db);
				
//				for(ShoppingCart My_cart:All_DB.select_shopping_cart(ID)){
//					long product_id = My_cart.getProductId();
//					
//					ProductName = All_DB.select_single_product(product_id).getName();
//					Quantity = All_DB.select_single_product(product_id).getQuantity();
//					Price = All_DB.select_single_product(product_id).getPrice();
//				    
//					Total += Price * Quantity;
//					
//	
//					
//
//				 
//				}
				
			}
			
		
		
		
		
		 //request.setAttribute("welcome", welcome);
    	 request.setAttribute("sign_in_out", Sign_out );
	     //request.setAttribute("account", "");
	     //request.setAttribute("cart", cart_products);


	     getServletContext().getRequestDispatcher("/my_shopping_cart.jsp").forward(request,response);
      
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
