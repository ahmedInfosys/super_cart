<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Check out</title>
<jsp:include page="Navbar.jsp"/>

</head>
<body >



<c:set var="sum" value="${0}"/>   
<c:forEach var="cart_item" items="${my_cart}">

<c:set var="sum" value="${sum + cart_item.getUnitPrice()*cart_item.getUnitQuantity()}"/> 
</c:forEach>
<c:set var="sum" value="${sum}"/> 


    
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

<ul class="nav nav-pills nav-default col-sm-offset-5">
<p class="nav nav-pills nav-default  col-sm-offset-5"><b>Sub-total:   </b>  <fmt:formatNumber value='${sum}' type="currency"  /><br>
     <b>Tax:        </b>        <fmt:formatNumber value='${(0.06*sum)}' type="currency"  /><br>
    <b>Grand total: </b><fmt:formatNumber value='${sum + (0.06*sum)}' type="currency"  /><br>
    <b>Super Duper card: -</b><fmt:formatNumber value="25" type="currency"  />
    <label><input type="checkbox" name="SDC" id="SDC" checked="yes">  Include super duper discount</label>
    <br>
    

      <b>Total: </b><fmt:formatNumber value='${sum + (0.06*sum) - 25}' type="currency"  /><br>


   

   </p><br><br><br>

 
</ul>
<br><br><br><br><br>

<div class="panel panel-primary col-sm-5 col-sm-offset-2">
		<div class= "panel-heading">
                 
			Insert credit/debit cart information below
		</div>	
		<div class ="panel-body col-sm-8">
		<form action="Check_out" method="post">
		  <ul class="nav nav-pills nav-default list-inline">
		     <li role="presentation" class="active"> 
		          <input type="hidden" name="place_order" value="  "/>
				  <input class="form-control col-sm-1"  name="first_name"  id="first_name" placeholder="First Name" required/>
			</li>
		       <li role="presentation" class="active">    </li>
		     <li > 
		          <input type="hidden" name="place_order" value="  "/>
				  <input class="form-control col-sm-1"  name="last_name"  id="last_name" placeholder="Last Name" required/>
		     
		     </li>
		        <li role="presentation" class="active"> 
		          <input type="hidden" name="place_order" value="  "/>
				  <input class="form-control col-sm-1"  name="card_number"  id="card_number" placeholder="XXXX-XXXX-XXXX-XXXX" required/>
		     
		     </li>
		     
		      <li role="presentation" class="active"> 
		          <input type="hidden" name="place_order" value="  "/>
				  <input class="form-control col-sm-1"  name="street_address"  id="street_address" placeholder="Street Address" required/>
		     
		     </li>
		      <li role="presentation" class="active"> 
		          <input type="hidden" name="place_order" value="  "/>
				  <input class="form-control col-sm-1"  name="city"  id="city" placeholder="City" required/>
		     
		     </li>
		      <li role="presentation" class="active"> 
		          <input type="hidden" name="place_order" value="  "/>
				  <input class="form-control col-sm-1"  name="state"  id="state" placeholder="State" required/>
		     
		     </li>
		      <li role="presentation" class="active"> 
		          <input type="hidden" name="place_order" value="  "/>
				  <input class="form-control col-sm-1"  name="zip_code"  id="zip_code" placeholder="zipcode" required/>
		     
		     </li>
		     
		     <br><br><br><br><br><br><br><br><br><br><br><br><br><br>
		     <p style="color:red;">${invalid}</p><br><br>
		     <li role="presentation" class="active"> 
		         
 Expiration month:<select class="form-control " id="exp_month" name="exp_month"> 
		         <c:forEach begin="1" end="12" var="month"  >
		            <option >${month}</option>
		         </c:forEach>
		         </select>
		     </li>
	
			<li role="presentation" class="active"> 
		         
 Expiration year:<select class="form-control " id="exp_year" name="exp_year"> 
		         <c:forEach begin="15" end="20" var="year">
		            <option >${year}</option>
		         </c:forEach>
		         </select>
			</li>

      		

	 
	  	
	  	<br><br><br><br><br>
	  	
	  	<h4>Shipping address</h4>



		     
		      <li role="presentation" class="active"> 
		          <input type="hidden" name="place_order" value="  "/>
				  <input class="form-control col-sm-1"  name="shipping_street_address"  placeholder="Street Address" required/>
		     
		     </li>
		      <li role="presentation" class="active"> 
		          <input type="hidden" name="place_order" value="  "/>
				  <input class="form-control col-sm-1"  name="shipping_city"   placeholder="City" required/>
		     
		     </li>
		      <li role="presentation" class="active"> 
		          <input type="hidden" name="place_order" value="  "/>
				  <input class="form-control col-sm-1"  name="shipping_state"  placeholder="State" required/>
		     
		     </li>
		      <li role="presentation" class="active"> 
		          <input type="hidden" name="place_order" value="  "/>
				  <input class="form-control col-sm-1"  name="shipping_zip_code"   placeholder="zipcode" required/>
		     
		     </li><br><br><br><br><br>
		     <button class="btn col-sm-3 col-sm-offset-1" style="background-color:#3399FF">Buy</button>
</ul>
</form>
</div>

</div>



  
</body>
</html>