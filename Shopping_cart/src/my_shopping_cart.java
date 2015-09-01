

import java.io.IOException;
import java.text.NumberFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.All_DB;
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
		long ID = Long.parseLong(session.getAttribute("UserID").toString());
		String  welcome = "";

		FirstName = All_DB.select_single_id(ID).getFirstname();
		LastName = All_DB.select_single_id(ID).getLastname();
		welcome += FirstName + " " + LastName + " !";
		
		 String Sign_out ="<a href=\"/Shopping_cart/SignIn.jsp\"> Sign out</a> ";
		 System.out.println("Request.getParamater Value = " + request.getParameter("ProductID"));
		 
		 NumberFormat currency = NumberFormat.getCurrencyInstance();
			String ProductName,Description;
			int Quantity;
			double Price;

			
			if(request.getParameter("ProductID") != null){
				
				long product_id = Long.parseLong(request.getParameter("ProductID"));
				ProductName = All_DB.select_single_product(product_id).getName();
				Description =  All_DB.select_single_product(product_id).getDescription();
				Quantity = All_DB.select_single_product(product_id).getQuantity();
				Price = All_DB.select_single_product(product_id).getPrice();
				
				ShoppingCart my_cart = new ShoppingCart();
				my_cart.setProductId(product_id);
				my_cart.setUserId(ID);
				my_cart.setUnitPrice(Price);
				my_cart.setUnitQuantity(Quantity);
				
				Shopping_Assns cart_class = new Shopping_Assns();
				cart_class.setCart(my_cart);
				All_DB.insert(cart_class);
			}
			
			
			double sum = 0;
			cart_products+= "<nav class=\"navbar navbar-default col-sm-10\">" ;
			if(All_DB.select_shopping_cart(ID) != null){
				ShoppingCart my_cart = new ShoppingCart();
				
				for(ShoppingCart My_cart:All_DB.select_shopping_cart(ID)){
					long product_id = My_cart.getProductId();
					
					ProductName = All_DB.select_single_product(product_id).getName();
					Quantity = All_DB.select_single_product(product_id).getQuantity();
					Price = All_DB.select_single_product(product_id).getPrice();
				    
					sum += Price * Quantity;
					

					
					cart_products+= " <p class=\"navbar-text navbar-left \" ><b>Product Name: </b>"+ ProductName + "</p>" +
							"<p class=\"navbar-text navbar-right\"><b>Price: </b>" +  currency.format(Price) +
							"<b> Qty: " + Quantity +  "</b></p>";
				
				}
				cart_products += "-------------------------------------------------------------------";
				cart_products += "<ul class=\"navbar-text nav-default \">" + "<p class=\"navbar-text navbar-default\"><b>Total: </b>" +  currency.format(sum);
				cart_products += "</p></u1></nav>";
			}else{
				cart_products = "<nav class=\"navbar navbar-default col-sm-10\"> Cart is Empty</nav>";
			}
		
		
		
		
		 request.setAttribute("welcome", welcome);
    	 request.setAttribute("sign_in_out", Sign_out );
	     request.setAttribute("account", "");
	     request.setAttribute("cart", cart_products);


	     getServletContext().getRequestDispatcher("/my_shopping_cart.jsp").forward(request,response);
      
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
