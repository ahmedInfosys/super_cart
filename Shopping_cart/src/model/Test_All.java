package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class Test_All {

	@Test
	public void test() {
		
//		OnlineCustomer cust = new OnlineCustomer();
//		Product pro = new Product();
//		pro.setName("Drill 3");
//		pro.setPicture("http://ace.imageg.net/graphics/product_images/pACE3-6387638_alternate1_enh-z8.jpg");
//		pro.setDescription("Dewalt Drill 3");
//		pro.setPrice(40);
//		pro.setQuantity(1);
//		pro.setUserid(1);
		
//		All_DB.delete_item(3);
//		for(Comment comm:All_DB.select_all_comments(4) ){
//			System.out.println(comm.getContentText());
//		}
		
//		cust.setFirstname("Ahmed 2");
//		cust.setLastname("Hameed 2");
		//All_DB.insert_customer(cust);
		All_DB.update_product(2, 2, -1);
//		Shopping_Assns pro_class = new Shopping_Assns();
//		
//		pro_class.setProduct(pro);
//        All_DB.insert(pro_class);
//		
//		for (OnlineCustomer custom:All_DB.select_all_customers()){
//			System.out.println(custom.getFirstname());
//		}
	}

}
