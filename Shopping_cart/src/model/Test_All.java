package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class Test_All {

	@Test
	public void test() {
		
		OnlineCustomer cust = new OnlineCustomer();
		cust.setFirstname("Ahmed 5");
		cust.setLastname("Hameed 5");
		cust.setId(5);
//		//All_DB.insert_customer(cust);
//		
//		Shopping_Assns pro_class = new Shopping_Assns();
//		
//		pro_class.setCustomer(cust);
//		All_DB.insert(pro_class);
		
		for (OnlineCustomer custom:All_DB.select_all_customers()){
			System.out.println(custom.getFirstname());
		}
	}

}
