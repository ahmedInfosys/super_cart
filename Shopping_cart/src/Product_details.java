

import java.io.IOException;
import java.text.NumberFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.All_DB;

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
		
		long product_id = Long.parseLong(request.getParameter("productID"));
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		String ProductName,Description;
		int Quantity;
		double Price;
		
		String product_details = "", FirstName, LastName;
		

		HttpSession session = request.getSession(true);
		long ID = Long.parseLong(session.getAttribute("UserID").toString());
		String list_of_products = "", welcome = "";

		FirstName = All_DB.select_single_id(ID).getFirstname();
		LastName = All_DB.select_single_id(ID).getLastname();
		welcome += FirstName + " " + LastName + " !";
		
		 String Sign_out ="<a href=\"/Bullhorm/SignIn.jsp\"> Sign out</a> ";
		
		ProductName = All_DB.select_single_product(product_id).getName();
		Description =  All_DB.select_single_product(product_id).getDescription();
		Quantity = All_DB.select_single_product(product_id).getQuantity();
		Price = All_DB.select_single_product(product_id).getPrice();
		
		product_details += "<p><b> Name:  </b>"+ ProductName +  "</p>" ;
		product_details += "<p><b> Description: </b>"   + Description + "</p>";
		product_details += "<p><b> Quantity: </b>"   + Quantity + "</p>";
		product_details += "<p><b> Price: </b>"   + currency.format(Price) + "</p>";
		//product_details += "<p><b> Quantity: </b>"   + Quanityt + "</p>";
		
		
		product_details += "<ul class=\"nav nav-pills nav-right col-sm-offset-9\">" +
		"<li role=\"presentation\" class=\"active\"><a href=\"my_shopping_cart?ProductID=" +  product_id + "\"><span class=\"glyphicon glyphicon-plus\">Add to cart</a></li></ul>"; 
		request.setAttribute("welcome", welcome);
		request.setAttribute("sign_in_out", Sign_out);
		request.setAttribute("Details", product_details);
		 getServletContext().getRequestDispatcher("/Product_details.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
