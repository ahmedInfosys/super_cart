package model;

	


	import java.util.ArrayList;
import java.util.List;

	import javax.persistence.EntityManager;
	import javax.persistence.EntityTransaction;
	import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

	import model.DBUtil;

	 

	public class All_DB {
/////////////////////////////////For Product table///////////////////////////////////////////
		public static List<Product> select_all_products(){
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			String qString = "select p from Product p where p.quantity > 0";
			TypedQuery <Product> List_of_table = em.createQuery(qString, Product.class);
			
			List<Product> list_of_products;
			try{
				list_of_products = List_of_table.getResultList();
				if(list_of_products == null || list_of_products.isEmpty()){
					list_of_products = null;
				}
			}finally{
				em.close();
			}
			return list_of_products;
		}
		
		public static List<Product> Search(String search){
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			String qString = "select p from Product p where lower(p.name) like :search and p.quantity > 0";

			TypedQuery <Product> List_of_table = em.createQuery(qString, Product.class);

			List_of_table.setParameter("search","%" + search + "%");
			
			List<Product> list_of_products;
			try{
				list_of_products = List_of_table.getResultList();
				if(list_of_products == null || list_of_products.isEmpty()){
					list_of_products = null;
				}
			}finally{
				em.close();
			}
			return list_of_products;
		}
		public static Product select_single_product(long id){
			EntityManager em = DBUtil.getEmFactory().createEntityManager();

			Product pro = new Product();
			try{

				pro = em.find(Product.class, id);
				
			}catch(NoResultException e){
				e.printStackTrace();
			}finally{
				em.clear();
			}
			return pro;
		}
//////////////////////////////////////////////////////////////////////////////////////////
////////////////////////for the user////////////////////////////////////////////////////
		
		public static List<OnlineCustomer> select_all_customers(){
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			String qString = "select c from  OnlineCustomer c ";
			TypedQuery <OnlineCustomer> List_of_table = em.createQuery(qString, OnlineCustomer.class);
			
			List<OnlineCustomer> list_of_customers;
			try{
				list_of_customers = List_of_table.getResultList();
				if(list_of_customers == null || list_of_customers.isEmpty()){
					list_of_customers = null;
				}
			}finally{
				em.close();
			}
			return list_of_customers;
		}
		public static OnlineCustomer select_single(String Email, String Password){
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			String qString = "select c from  OnlineCustomer c where c.email = :Email" +
					" and c.zipcode = :Password"  ;
			TypedQuery <OnlineCustomer> q = em.createQuery(qString, OnlineCustomer.class);
			
			q.setParameter("Email", Email);
			q.setParameter("Password", Password);
			
			OnlineCustomer user = null;
			try{
				user = q.getSingleResult();
				
			}catch(NoResultException e){
				System.out.println(e);
			}finally{
				em.clear();
			}
			return user;
		}
		
		public static OnlineCustomer select_single_id(long id){
			EntityManager em = DBUtil.getEmFactory().createEntityManager();

			OnlineCustomer user = new OnlineCustomer();
			try{

				user = em.find(OnlineCustomer.class, id);
				
				System.out.println(" From User DB " + user.getFirstname());
				
			}catch(NoResultException e){
				e.printStackTrace();
			}finally{
				em.clear();
			}
			return user;
		}
//		
		public static List <OnlineCustomer> ExistsorNot(String Email){
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			String qString = "select distinct c from  OnlineCustomer c where c.email = :Email";
			TypedQuery <OnlineCustomer> q = em.createQuery(qString, OnlineCustomer.class);
			
			q.setParameter("Email", Email);
			
			List <OnlineCustomer> user = new ArrayList<OnlineCustomer>();
			try{
				user = q.getResultList();
				//System.out.println(user.getEmail());
			}catch(NoResultException e){
				System.out.println(e);
			}finally{
				em.clear();
			}
			return user;
		}
		
		
		public static OnlineCustomer Isvalid(String Email, String Password){
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			int pass = Integer.parseInt(Password);
			String qString = "select c from  OnlineCustomer c where c.email = :Email" +
					" and c.zipcode = :Password"  ;
			TypedQuery <OnlineCustomer> q = em.createQuery(qString, OnlineCustomer.class);
			
			q.setParameter("Email", Email);
			q.setParameter("Password", pass);
			
			OnlineCustomer user = null;
			try{
				user = q.getSingleResult();
				
			}catch(NoResultException e){
				System.out.println(e);
			}finally{
				em.clear();
			}
			return user;
		}
		
		
		
//////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////for shopping cart/////////////////////////////////////
		public static List<ShoppingCart> select_shopping_cart(long user_id){
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			String qString = "select s from  ShoppingCart s where s.userId = :ID";
			TypedQuery <ShoppingCart> q = em.createQuery(qString, ShoppingCart.class);
			
			q.setParameter("ID", user_id);
			
			List <ShoppingCart> cart = new ArrayList<ShoppingCart>();
			try{
				cart = q.getResultList();
				
			}catch(NoResultException e){
				System.out.println(e);
			}finally{
				em.clear();
			}
			return cart;
		}
		//////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////for reviews////////////////////////////////////////////////
		
		public static List<Comment> select_all_comments(long product_id){
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			String qString = "select com from  Comment com  where com.postId = :ID";
			TypedQuery <Comment> List_of_table = em.createQuery(qString, Comment.class);
			List_of_table.setParameter("ID", product_id);
			List<Comment> list_of_comments;
			try{
				list_of_comments = List_of_table.getResultList();
				if(list_of_comments == null || list_of_comments.isEmpty()){
					list_of_comments = null;
				}
			}finally{
				em.close();
			}
			return list_of_comments;
		}
		///////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////for Payment card///////////////////////////////////////////////
		
		public static List<PaymentCard> select_card(
				String first_name,String last_name,String street_address,String city,String state,String zipcode,
				String exp_month,String exp_year
				){
			String exp_date = exp_month + "/" + exp_year;
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			String qString = "select p from PaymentCard p where p.firstName = :first_name and p.lastName = : last_name "
					+ "and p.billingStreetAddress = : street_address and p.billingCity = :city and p.billingState = :state"
					+ "and p.billingZipCode = :zipcode and p.expirationDate = :exp_date";
					
			TypedQuery <PaymentCard> List_of_table = em.createQuery(qString, PaymentCard.class);
			List_of_table.setParameter("first_name", first_name);
			List_of_table.setParameter("last_name", last_name);
			List_of_table.setParameter("street_address", street_address);
			List_of_table.setParameter("city", city);
			List_of_table.setParameter("state", state);
			List_of_table.setParameter("zipcode", zipcode);
			List_of_table.setParameter("exp_date", exp_date);
			List<PaymentCard> list_of_accounts;
			try{
				list_of_accounts = List_of_table.getResultList();
				if(list_of_accounts == null || list_of_accounts.isEmpty()){
					list_of_accounts = null;
				}
			}finally{
				em.close();
			}
			return list_of_accounts;
		}
		//////////////////////////////////////////////////////////////////////////////////////////////
		public static void insert(Shopping_Assns shopping_classes) {
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			EntityTransaction trans = em.getTransaction();
			trans.begin(); 
			try {
				if(shopping_classes.getCustomer() != null){
					System.out.println("Inserted customer");
					OnlineCustomer cust = shopping_classes.getCustomer();
				    em.persist(cust);
				}
				else if(shopping_classes.getProduct() != null ){
					Product pro = shopping_classes.getProduct();
					em.persist(pro);
				}else if(shopping_classes.getCart() != null){
					ShoppingCart cart = shopping_classes.getCart();
					em.persist(cart);
				}else if(shopping_classes.getComment() != null){
					Comment Comm = shopping_classes.getComment();
					em.persist(Comm);
				}
				trans.commit();
			} catch (Exception e) {
				System.out.println(e);	
				trans.rollback();
			} finally {
				em.close();
			}
		}
		public static void insert_customer(OnlineCustomer cust) {
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			EntityTransaction trans = em.getTransaction();
			trans.begin(); 
			try {

					em.persist(cust);
					trans.commit();
				
			} catch (Exception e) {
				System.out.println(e);	
				trans.rollback();
			} finally {
				em.close();
			}
		}
			
		public static void update_product(long id,int quantity, double price){
			EntityManager em = DBUtil.getEmFactory().createEntityManager();

			Product Pro = new Product();
			try{
				Pro = em.find(Product.class, id);
				em.getTransaction().begin();
				if(quantity != -1) Pro.setQuantity(quantity);
				else if(price != -1) Pro.setPrice(price);
		
				em.getTransaction().commit();
				
			}catch(NoResultException e){
				e.printStackTrace();
			}finally{
				em.clear();
			}
		}
		
//		
		public static void update_cart(long cart_id, String date, int past_purchase){
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			ShoppingCart my_cart = new ShoppingCart();
			
			try{
				my_cart = em.find(ShoppingCart.class, cart_id);
				em.getTransaction().begin();
                my_cart.setPastPurchase(past_purchase);
                my_cart.setPurchaseDate(date);
				em.getTransaction().commit();
				
			}catch(NoResultException e){
				e.printStackTrace();
			}finally{
				em.clear();
			}
		}
//		public static void update(Shopping_Assns shopping_classes) {
//			EntityManager em = DBUtil.getEmFactory().createEntityManager();
//			EntityTransaction trans = em.getTransaction();
//			trans.begin(); 
//			try {
//				if(shopping_classes.getCustomer() != null){
//					OnlineCustomer cust = shopping_classes.getCustomer();
//				    em.merge(cust);
//				}
//				else if(shopping_classes.getProduct() != null ){
//					Product pro = shopping_classes.getProduct();
//					em.merge(pro);
//				}
//				trans.commit();
//			} catch (Exception e) {
//				System.out.println(e);
//				trans.rollback();
//			} finally {
//				em.close();
//			}
//		}
			
		public static void delete_item (long item_id){
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			String qString = "delete from  ShoppingCart s where s.id = :ID";
			EntityTransaction trans = em.getTransaction();
			trans.begin(); 
			try{
				Query q = em.createQuery(qString,  ShoppingCart.class);
			    q.setParameter("ID", item_id).executeUpdate();
			    trans.commit();
			}catch(Exception e){
				System.out.println(e);
				trans.rollback();
			}finally{
				em.close();
			}
		}
		
		public static void delete_cart (long user_id){
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			String qString = "delete from  ShoppingCart s where s.userId = :ID";
			EntityTransaction trans = em.getTransaction();
			trans.begin(); 
			try{
				Query q = em.createQuery(qString,  ShoppingCart.class);
			    q.setParameter("ID", user_id).executeUpdate();
			    trans.commit();
			}catch(Exception e){
				System.out.println(e);
				trans.rollback();
			}finally{
				em.close();
			}
		}
		public static void delete(Shopping_Assns shopping_classes) {
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			EntityTransaction trans = em.getTransaction();
			trans.begin(); 
			try {
				if(shopping_classes.getCustomer() != null){
					OnlineCustomer cust = shopping_classes.getCustomer();
				    em.remove(cust);
				}
				else if(shopping_classes.getProduct() != null ){
					Product pro = shopping_classes.getProduct();
					em.remove(pro);
				}
				trans.commit();
			} catch (Exception e) {
				System.out.println(e);
				trans.rollback();
			} finally {
				em.close();
			} 
		}


	}
	/////////////////////////////////////////////////////////////////////////////////////////////

