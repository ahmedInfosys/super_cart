

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
		 String Sign_out ="<a href=\"/Shopping_cart/SignIn.jsp\"> Sign out</a> ";
		 List <Product> found_products = new ArrayList<Product>();
		 if(request.getParameter("search") != null){
			 String input = request.getParameter("search").toLowerCase();
			 
			 if(All_DB.Search(input) != null){
					HttpSession session = request.getSession();
				    found_products = All_DB.Search(input);
					session.setAttribute("found_products", found_products);
					//request.setAttribute(arg0, arg1);
					request.setAttribute("sign_in_out", Sign_out);
			 }
			 
		 }

   	    request.setAttribute("sign_in_out", Sign_out);
		getServletContext().getRequestDispatcher("/Search.jsp").forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
