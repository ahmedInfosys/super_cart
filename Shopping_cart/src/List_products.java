

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
 * Servlet implementation class List_products
 */
@WebServlet("/List_products")
public class List_products extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public List_products() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		String list_of_products = "", welcome = " ";
		 String Sign_out ="<a href=\"/Shoping_cart/SignIn.jsp\"> Sign out</a> ";
		 
		 
		if(All_DB.select_all_products() != null){
		HttpSession session = request.getSession();
        
		List<Product> products = new ArrayList<Product>();
		products = All_DB.select_all_products();
		session.setAttribute("Products", products);

			request.setAttribute("sign_in_out", Sign_out);

			 getServletContext().getRequestDispatcher("/Product_List.jsp").forward(request,response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
