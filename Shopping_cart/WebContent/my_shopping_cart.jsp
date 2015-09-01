<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/additional-methods.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/additional-methods.min.js"></script>
<title>Product List</title>
<style>h1{background: black; text:white}

</style>

</head>
<body>



<nav class="navbar navbar-default" >
	<ul class="nav navbar-nav navbar-left">
	   <li role="presentation" >Welcome <b>${sessionScope.User.getFirstname()} ${sessionScope.User.getLastname()} </b></li>
	    <li role="presentation"><a href="/Shopping_cart/My_Profile"><span class="glyphicon glyphicon-user"></span>Profile</a></li>
	   <li role="presentation"><a href="/Shopping_cart/List_products"><span class="glyphicon glyphicon-book"></span>Products</a></li>
	   <li role="presentation"><a href="/Shopping_cart/my_shopping_cart"><span class="glyphicon glyphicon-shopping-cart"></span>Shopping cart</a></li>
	   <li role="presentation"><a href="/Shopping_cart/ready_checkout">Check out</a></li>
	   <li role="presentation"><a href="/Shopping_cart/Search"><span class="glyphicon glyphicon-search"></span>Search products</a></li>
	</ul>
	<ul class="nav navbar-nav navbar-right">
		<li role="presentation" >${sign_in_out}</li> 
    </ul>
</nav>  

<c:set var="sum" value="${0}"/>   
<c:forEach var="cart_item" items="${my_cart}">

<c:set var="sum" value="${sum + sessionScope.All_DB.select_single_product(cart_item.getProductId()).getPrice()}"/> 
<nav class="navbar navbar-default col-sm-10">
	 <p class="navbar-text navbar-default col-sm-offset-10" ><b>Product Name: </b> ${sessionScope.All_DB.select_single_product(cart_item.getProductId()).getName()} </p>
				<ul class="nav nav-pills nav-right col-sm-offset-9"> 
				     <p class="navbar-text navbar-default"><b>Price: </b><fmt:formatNumber value='${sessionScope.All_DB.select_single_product(cart_item.getProductId()).getQuantity()}' type="currency" groupingUsed='true' />  <b> Qty: </b> ${sessionScope.All_DB.select_single_product(cart_item.getProductId()).getQuantity()}</p>
					<li role="presentation" class="active">
					        <a href="my_shopping_cart?remove_product=${cart_item.getProductId()}">Remove Item</a>
					</li>
				</ul> 
</nav>
</c:forEach>


----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
<p class="navbar-text navbar-center"><b>Total: </b><fmt:formatNumber value='${sum}' type="currency"  /></p>
<ul class="nav nav-pills nav-right col-sm-offset-9"> 
		<li role="presentation" class="active">
				     <a href="my_shopping_cart?remove_all=${sessionScope.User.getId()}">  Remove all</a>
		</li>
</u1>

			
			

</body>
</html>