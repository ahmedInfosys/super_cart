

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.All_DB;
import model.OnlineCustomer;
import model.Shopping_Assns;


/**
 * Servlet implementation class user_profile
 */
@WebServlet("/My_Profile")
public class My_Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public My_Profile() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String  FirstName = null, LastName = null, Email = null, zipCode, welcome = " ", profile_image;
		
		
		String  Password;
		String  invalid = "", create_an_account = "";
		
		
		String   people_details = "",Join_date = null;
		String  Page = "";
		long ID = 0;
		
		SimpleDateFormat  sdf = new SimpleDateFormat("E MM/dd/yyyy hh:mm a");
		Shopping_Assns shop_classes = new Shopping_Assns();
		
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
				
				HttpSession session = request.getSession();
	        	session.setAttribute("User", user);/////////////////////////change////////////////////////
	        	All_DB store_in_db = new All_DB();
				session.setAttribute("All_DB", store_in_db);
	        	
	        	//welcome += FirstName + " " + LastName;
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
				
	        	HttpSession session = request.getSession();
	        	session.setAttribute("User", single_user);/////change
	        	All_DB store_in_db = new All_DB();
				session.setAttribute("All_DB", store_in_db);
	        	Page = "/Profile.jsp";
//	        	session = request.getSession(true);
//				ID = Long.parseLong(session.getAttribute("UserID").toString());
//				FirstName = All_DB.select_single_id(Long.parseLong(session.getAttribute("UserID").toString())).getFirstname();
//				LastName = All_DB.select_single_id(Long.parseLong(session.getAttribute("UserID").toString())).getLastname();
//				Email =  All_DB.select_single_id(ID).getEmail();
//				Join_date =  All_DB.select_single_id(ID).getJoinDate();
//				welcome += FirstName + " " + LastName + " !";
			}
			
			
		}else if( request.getParameter("user_ID") != null){
				//ID = Long.parseLong(request.getParameter("user_ID"));
				Page = "/Profile.jsp";
				
		}else {
				System.out.println("GoTO my profile");
//				HttpSession session = request.getSession(true);
//				ID = Long.parseLong(session.getAttribute("UserID").toString());
//				FirstName = All_DB.select_single_id(Long.parseLong(session.getAttribute("UserID").toString())).getFirstname();
//				LastName = All_DB.select_single_id(Long.parseLong(session.getAttribute("UserID").toString())).getLastname();
//				Email =  All_DB.select_single_id(ID).getEmail();
//				Join_date =  All_DB.select_single_id(ID).getJoinDate();
//				welcome += FirstName + " " + LastName + " !";
			    Page = "/Profile.jsp";
		}

		

		

		
		//Display personal details
		people_details += "<p><span class=\"glyphicon glyphicon-user\"></span><b> Name:  </b>"+ FirstName + "  " + LastName + "</p>" ;
    	people_details += "<p><span class=\"glyphicon glyphicon-envelope\"> </span><b> Email Address: </b>"   + Email + "</p>";
    	people_details += "<p><span class=\"glyphicon glyphicon-calendar\"> </span><b> Join Date: </b>"   + Join_date + "</p>";
    	
    	 String Sign_out ="<a href=\"/Bullhorm/SignIn.jsp\"> Sign out</a> ";
    	 String account =  "";

    	 //request.setAttribute("welcome", welcome);
    	 request.setAttribute("sign_in_out", Sign_out );
//	     request.setAttribute("account", account);
//	     request.setAttribute("Details", people_details);


	     getServletContext().getRequestDispatcher(Page).forward(request,response);
    	
    	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doGet(request, response);
	}

}
