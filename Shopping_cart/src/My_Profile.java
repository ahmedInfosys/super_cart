

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
import model.ShoppingCart;
import model.Shopping_Assns;

@WebServlet("/My_Profile")
public class My_Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public My_Profile() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String  FirstName = null, LastName = null, Email = null, zipCode, welcome = " ", profile_image;
    			
    			
    			String  Password;
    			String  invalid = "", create_an_account = "";

    			String  Page = "";
    	        long ID;
    			
    			SimpleDateFormat  sdf = new SimpleDateFormat("E MM/dd/yyyy hh:mm a");
    			Shopping_Assns shop_classes = new Shopping_Assns();
    			
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
    			
    			if(request.getParameter("zip") != null){
    				
    				System.out.println("For Sign Up.");
    				FirstName = request.getParameter("fName");
    				LastName = request.getParameter( "lName" );
    				Email=request.getParameter("email");
    				zipCode= request.getParameter("zip");
    				profile_image = request.getParameter("img");
    				
    				if(!All_DB.ExistsorNot(Email).isEmpty()){
    					Page = "/SignUp.jsp";
    					String Already_Exists = "The user " + Email + " is already exists, please <a href=\"/Shopping_cart/SignIn.jsp\"> sign in.</a>" ;
    					request.setAttribute("alreadyin", Already_Exists);
    				}else{
    					
    					Page = "/Profile.jsp";
    					Date now = new Date();

    					OnlineCustomer user = new OnlineCustomer();
    					
    					user.setFirstname(FirstName);
    					user.setLastname(LastName);
    					user.setEmail(Email);
    					user.setZipcode(Integer.parseInt(zipCode));
    			
    					user.setJoinDate(sdf.format(now));
    					shop_classes.setCustomer(user);
    					All_DB.insert(shop_classes);
    					ID = user.getId();
    					
    					
    					session = request.getSession();
    		        	session.setAttribute("User", user);/////////////////////////change////////////////////////
    		        	All_DB store_in_db = new All_DB();
    					session.setAttribute("All_DB", store_in_db);

    				}
    			}else if (request.getParameter("password") != null){
    				
    				System.out.println("For Sign in.");
    				
    				
    				Email = request.getParameter("email");
    				Password = request.getParameter("password");
    				
    				OnlineCustomer single_user = new OnlineCustomer();
    				
    				single_user = All_DB.Isvalid(Email, Password);
    				System.out.println(single_user);
    				
    				if(single_user == null){
    					
    					System.out.println("Invalid");
    					Page = "/SignIn.jsp";
    					
    					invalid = "Invalid Email or/and password";
    					create_an_account = "<a href=\"/Shopping_cart/SignUp.jsp\">Create an account</a>";
    					request.setAttribute("invalid_user", invalid);
    					request.setAttribute("Sign_up", create_an_account);
    				}
    				else{
    				
    		        	session = request.getSession();
    		        	session.setAttribute("User", single_user);/////change
    		        	All_DB store_in_db = new All_DB();
    		        	Page = "/Profile.jsp";
    		
    						
    						List <ShoppingCart> my_cart = new ArrayList<ShoppingCart>();
    						my_cart = All_DB.select_shopping_cart(single_user.getId());
    						
    						session = request.getSession();
    						session.setAttribute("my_cart", my_cart);
    				
    						session.setAttribute("All_DB", store_in_db);


    				}
    				
    				
    			}else {
    				
    				session = request.getSession(true);
    				OnlineCustomer user = new OnlineCustomer();
    				
		        	user = (OnlineCustomer) session.getAttribute("User");/////change
		        	All_DB store_in_db = new All_DB();
		        	Page = "/Profile.jsp";
		
						
						List <ShoppingCart> my_cart = new ArrayList<ShoppingCart>();
						my_cart = All_DB.select_shopping_cart(user.getId());
						
						session = request.getSession();
						session.setAttribute("my_cart", my_cart);
				
						session.setAttribute("All_DB", store_in_db);

    			}

    	    	
    	    	 String Sign_out ="<a href=\"/Bullhorm/SignIn.jsp\"> Sign out</a> ";

    	    	 request.setAttribute("sign_in_out", Sign_out );
    	    	  getServletContext().getRequestDispatcher(Page).forward(request,response);
    	    	 
    		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doGet(request, response);
	}

}
