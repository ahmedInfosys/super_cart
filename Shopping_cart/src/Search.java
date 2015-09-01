

import java.io.IOException;
import java.text.NumberFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.All_DB;
import model.Product;



/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String FirstName, LastName, picture;
		
		HttpSession session = request.getSession(true);
		long ID = Long.parseLong(session.getAttribute("UserID").toString());
		String list_of_products = "", welcome = "";
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		FirstName = All_DB.select_single_id(ID).getFirstname();
		LastName = All_DB.select_single_id(ID).getLastname();
		welcome += FirstName + " " + LastName + " !";
		
		 String Sign_out ="<a href=\"/Bullhorm/SignIn.jsp\"> Sign out</a> ";
		
		 if(request.getParameter("search") != null){
			 String input = request.getParameter("search").toLowerCase();
			 
			 if(All_DB.Search(input) != null){
				 for(Product pro:All_DB.Search(input)){
					 list_of_products+= "<nav class=\"navbar navbar-default col-sm-10\">" +
							 " <p class=\"navbar-text navbar-default col-sm-offset-10\" ><b>Product Name: </b>"+ pro.getName() + "</p>" +
							"<ul class=\"nav nav-pills nav-right col-sm-offset-9\">" + "<p class=\"navbar-text navbar-default\"><b>Price: </b>" +  currency.format(pro.getPrice()) +
							"</a> <b> Qty: " + pro.getQuantity() +  "</b></p>";
							
					list_of_products+= "<li role=\"presentation\" class=\"active\"><a href=\"Product_details?productID=" + pro.getId() + "\"><span class=\"glyphicon glyphicon-plus\">View</a></li>" + "<li role=\"presentation\" class=\"active\"><a href=\"my_shopping_cart?ProductID=" + pro.getId() + "\"><span class=\"glyphicon glyphicon-plus\">Add</a></li>" +
							"</ul> </nav>" ;
		    	 }
			 }
			 
		 }
		 
		 
		 
   	 
   	 	request.setAttribute("welcome",welcome);
   	    request.setAttribute("sign_in_out", Sign_out);
   	 	request.setAttribute("filtered_products", list_of_products);
		getServletContext().getRequestDispatcher("/Search.jsp").forward(request,response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
