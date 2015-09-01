

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

		
		 String Sign_out ="<a href=\"/Bullhorm/SignIn.jsp\"> Sign out</a> ";
		
		ProductName = All_DB.select_single_product(product_id).getName();
		Description =  All_DB.select_single_product(product_id).getDescription();
		Quantity = All_DB.select_single_product(product_id).getQuantity();
		Price = All_DB.select_single_product(product_id).getPrice();
		
		Product pro = new Product ();
		
		pro.setId(product_id);
		pro.setName(ProductName);
		pro.setDescription(Description);
		pro.setPrice(Price);
		pro.setQuantity(Quantity);
		
		
		HttpSession session = request.getSession();
		session = request.getSession();
		session.setAttribute("product_details", pro);
         

		
		
		
		//request.setAttribute("welcome", welcome);
		request.setAttribute("sign_in_out", Sign_out);
		//request.setAttribute("Details", product_details);
		 getServletContext().getRequestDispatcher("/Product_details.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
