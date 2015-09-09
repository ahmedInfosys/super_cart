

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
import model.Comment;
import model.OnlineCustomer;
import model.Product;
import model.ShoppingCart;
import model.Shopping_Assns;

/**
 * Servlet implementation class Product_details
 */
@WebServlet("/Product_details")
public class Product_details extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Product_details() {
        super();
  
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long product_id ;
		String ProductName,Description, Picture;
		int Quantity;
		double Price;
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
		
		 String Sign_out ="<a href=\"/Shopping_cart/SignIn.jsp\"> Sign out</a> ";
		
		product_id = Long.parseLong(request.getParameter("productID").toString());
		
		System.out.println(product_id);

		ProductName = All_DB.select_single_product(product_id).getName();
		Description =  All_DB.select_single_product(product_id).getDescription();
		Quantity = All_DB.select_single_product(product_id).getQuantity();
		Price = All_DB.select_single_product(product_id).getPrice();
		Picture = All_DB.select_single_product(product_id).getPicture();
		Product pro = new Product ();
		
		pro.setId(product_id);
		pro.setName(ProductName);
		pro.setDescription(Description);
		pro.setPrice(Price);
		pro.setQuantity(Quantity);
		pro.setPicture(Picture);
		
		session = request.getSession(true);
		OnlineCustomer user = new OnlineCustomer();
		
		
    	user = (OnlineCustomer) session.getAttribute("User");

		
		Comment comment = new Comment();
		SimpleDateFormat sdf = new SimpleDateFormat("E MM/dd/yyyy hh:mm a");
		
		
		
		//If comment exists in the comment field
        if(request.getParameter("review") != null){
        	
        	
        	Date now  = new Date();
        	comment.setCommentDate(sdf.format(now));
        	comment.setContentText(request.getParameter("review"));
        	comment.setPostId(product_id);
        	comment.setRatingScale(Integer.parseInt(request.getParameter("scale")));
        	comment.setUserId(user.getId());
        	
        	
        	Shopping_Assns comm_class = new Shopping_Assns();
        	comm_class.setComment(comment);
        	
        	All_DB.insert(comm_class);
        }
        
    	All_DB store_in_db = new All_DB();
    	
    	session = request.getSession();
    	
		session.setAttribute("All_DB", store_in_db);	
		session.setAttribute("product_details", pro);
		request.setAttribute("sign_in_out", Sign_out);
		
		//List all comment of that product
         
		List <Comment> list_of_comments = new ArrayList<Comment>();
		if(All_DB.select_all_comments(product_id) != null){
			
				list_of_comments = All_DB.select_all_comments(product_id);
	        	
	       }else{
	    	   list_of_comments = null;
	       }

		session.setAttribute("Comments", list_of_comments);
		
		 getServletContext().getRequestDispatcher("/Product_details.jsp").forward(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
