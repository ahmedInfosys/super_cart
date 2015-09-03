<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>Search products</title>
<style>h1{background: black; text:white}</style>
<style>
img{
    position: relative;
    width: 100px;
    height: 100px;
    float: right;
    border: 3px solid #8AC007;

}

p.navbar-text{
position: relative;
float: right;
right: 0px;
}

</style>

</head>
<body background="http://www.freewallpaperfullhd.com/wp-content/uploads/2015/02/Nature-Wallpaper-HD-1920x1080.jpg">

<nav class="navbar navbar-default" >
	<ul class="nav navbar-nav navbar-left">
	   <li role="presentation" >Welcome <b>${sessionScope.User.getFirstname()} ${sessionScope.User.getLastname()} </b></li>
	   <c:choose>
	   <c:when test="${sessionScope.User.getFirstname() != ('Customer')}">   
	      <li role="presentation"><a href="/Shopping_cart/My_Profile"><span class="glyphicon glyphicon-user"></span>Profile</a></li>
	   </c:when>
	   </c:choose>
	   <li role="presentation"><a href="/Shopping_cart/List_products"><span class="glyphicon glyphicon-book"></span>Products</a></li>
	   <li role="presentation"><a href="/Shopping_cart/my_shopping_cart"><span class="glyphicon glyphicon-shopping-cart"></span>Shopping cart <span class="badge">  ${my_cart.size()}</span></a></li>
	   <li role="presentation"><a href="/Shopping_cart/Checkout.jsp">Check out</a></li>
	   <li role="presentation"><a href="/Shopping_cart/Search"><span class="glyphicon glyphicon-search"></span>Search products</a></li>
	</ul>
	<ul class="nav navbar-nav navbar-right">
	<c:choose>
	<c:when test="${sessionScope.User.getFirstname() == ('Customer')}">
	    <li role="presentation"><a  href="/Shopping_cart/SignIn.jsp">Sign in</a></li>
	    <li role="presentation"><a  href="/Shopping_cart/SignUp.jsp">Create account</a></li>
	 </c:when>
	 <c:otherwise>
	   <li role="presentation"><a  href="/Shopping_cart/Logout?click=1">Sign out</a></li>
	 </c:otherwise>
	 </c:choose>
	 
    </ul>
</nav>


<div class="row">
  <div class="col-sm-6 col-sm-offset-3">
<form class= "text-center" action="Search" method="get">
    <div class="input-group">
      
      <input type="text" class="form-control" placeholder="Search for post" name="search" id="search">
      <span class="input-group-btn" >
        <button class="btn btn-default btn-primary" "type="submit" style="background-color:#1E90FF" value="submit" >Go!</button>
      </span>
    
    </div><!-- /input-group -->
</form>
  </div><!-- /.col-lg-6 -->
</div><!-- /.row -->
<br><br>
<c:forEach var="found_product" items="${found_products}">
<nav class="navbar navbar-default col-sm-10"> 
			 <p class="navbar-text navbar-default col-sm-offset-10" ><b>Product Name: </b>${found_product.getName()}  </p>
		     <ul class="nav nav-pills nav-right col-sm-offset-9"> 
                    <p class="navbar-text navbar-default"><b>Price: </b>${found_product.getPrice()}<b> Qty: </b> ${found_product.getQuantity()}  </p>
			        <li role="presentation" class="active"><a href="Product_details?productID=${found_product.getId()}" > 
				        <span class="glyphicon glyphicon-align-justify"></span> View</a></li>
			        <li role="presentation" class="active"><a href="my_shopping_cart?productID=${found_product.getId()}" ><span class="glyphicon glyphicon-plus"></span> Add</a></li>
			 </ul>
			 </nav>
</c:forEach>


</body>
</html>