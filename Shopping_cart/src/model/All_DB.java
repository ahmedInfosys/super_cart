package model;

	


	import java.util.List;

	import javax.persistence.EntityManager;
	import javax.persistence.EntityTransaction;
	import javax.persistence.NoResultException;
	import javax.persistence.TypedQuery;

	import model.DBUtil;

	 

	public class All_DB {
/////////////////////////////////For Product table///////////////////////////////////////////
		public static List<Product> select_all_products(){
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			String qString = "select p from Product p ";
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
//////////////////////////////////////////////////////////////////////////////////////////
		
		
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
//		public static User select_single(String Email, String Password){
//			EntityManager em = DBUtil.getEmFactory().createEntityManager();
//			String qString = "select u from User u where u.email = :Email" +
//					" and u.zipcode = :Password"  ;
//			TypedQuery <User> q = em.createQuery(qString, User.class);
//			
//			q.setParameter("Email", Email);
//			q.setParameter("Password", Password);
//			
//			User user = null;
//			try{
//				user = q.getSingleResult();
//				
//			}catch(NoResultException e){
//				System.out.println(e);
//			}finally{
//				em.clear();
//			}
//			return user;
//		}
//		
//		public static User select_single_id(long id){
//			EntityManager em = DBUtil.getEmFactory().createEntityManager();
//
//			User user = new User();
//			try{
////				user = q.getSingleResult();
//				System.out.println(id + " from user db");
//				user = em.find(User.class, id);
//				
//				System.out.println(" From User DB " + user.getFirstname());
//				
//			}catch(NoResultException e){
//				e.printStackTrace();
//			}finally{
//				em.clear();
//			}
//			return user;
//		}
//		
//		public static User select_filter(long id){
//			EntityManager em = DBUtil.getEmFactory().createEntityManager(); 
//			User user = new User();
//			try {    
//					
//					model.User cust = em.find(model.User.class, id);   
//					
//					user.setId(cust.getId());
//					user.setFirstname(cust.getFirstname());
//					user.setLastname(cust.getLastname());
//					user.setEmail(cust.getEmail());
//					user.setZipcode(cust.getZipcode());
	//  
//				} catch (Exception e){    
//					System.out.println(e);   
//				} finally {   
//					em.close(); 	
//				} 
//			return user;
//		}
		

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
			
		public static void update(Shopping_Assns shopping_classes) {
			EntityManager em = DBUtil.getEmFactory().createEntityManager();
			EntityTransaction trans = em.getTransaction();
			trans.begin(); 
			try {
				if(shopping_classes.getCustomer() != null){
					OnlineCustomer cust = shopping_classes.getCustomer();
				    em.merge(cust);
				}
				else if(shopping_classes.getProduct() != null ){
					Product pro = shopping_classes.getProduct();
					em.merge(pro);
				}
				trans.commit();
			} catch (Exception e) {
				System.out.println(e);
				trans.rollback();
			} finally {
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

