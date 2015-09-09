<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="Navbar.jsp"/>
</head>
<body>



<c:set var="sum" value="${0}"/>   
<c:forEach var="cart_item" items="${my_cart}">

<c:set var="sum" value="${sum + sessionScope.All_DB.select_single_product(cart_item.getProductId()).getPrice()}"/> 
<nav class="navbar navbar-default col-sm-8 col-sm-offset-2">
	  <p class="navbar-text navbar-left "><b>Product Name: </b> ${sessionScope.All_DB.select_single_product(cart_item.getProductId()).getName()} </p>
				<ul class="nav nav-pills nav-right col-sm-offset-8"> 
			
				     <li role="presentation" class="active">
				     <p ><b>Price: </b><fmt:formatNumber value='${cart_item.getUnitPrice()}' type="currency" groupingUsed='true' />  <b> Qty: </b> ${cart_item.getUnitQuantity()}</p></li>
					<li role="presentation" class="active">
					        <a href="my_shopping_cart?remove_item=${cart_item.getId()}">Remove Item</a>
					</li>
				</ul> 
</nav>
</c:forEach>

<br><br><br><br>
<c:choose>
    <c:when test="${my_cart.isEmpty()}">
        <p class="navbar-text navbar-left">Your shopping cart is empty</p>
        <br />
     </c:when>
  	 <c:when test="${my_cart.size() == 1}">
			   <p class="navbar-text  navbar-left">You Have 1 item in your shopping cart</p>
			   <ul class="nav nav-pills nav-left col-sm-offset-9">
		       <li role="presentation" class="active"><a href="my_shopping_cart?remove_all=${sessionScope.User.getId()}">  Remove all  </a></li>  
		       <li role="presentation" class="active"><a href="/Shopping_cart/Checkout.jsp">  Check out  </a></li>  
		       </ul>
	</c:when>
     <c:when test="${my_cart.size() > 1}">
		   
		       <p class="navbar-text  navbar-left">You Have ${my_cart.size()} item in your shopping cart</p>   
		       <ul class="nav nav-pills nav-left col-sm-offset-9">
		       <li role="presentation" class="active"><a href="my_shopping_cart?remove_all=${sessionScope.User.getId()}">  Remove all  </a></li>  
		       <li role="presentation" class="active"><a href="/Shopping_cart/Checkout.jsp">  Check out  </a></li>  
		       </ul>
     </c:when>
    
</c:choose>



			
			

</body>
</html>