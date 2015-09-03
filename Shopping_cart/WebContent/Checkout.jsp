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
<title>Comments area</title>
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
<body >

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
   </p><br><br><br>
    <c:if test="${param.buy_all==1}">
        <p> Thank you for buying with us, you order will be shipped soon </p>
        <c:redirect url="/Profile.jsp"></c:redirect>
    </c:if>
   
        <li role="presentation" class="active col-sm-3">
				     <a href="/Shopping_cart/Check_out?buy_all=1">  Buy </a>
		</li>	
</ul>


  
</body>
</html>