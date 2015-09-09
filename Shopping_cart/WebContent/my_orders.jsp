<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="Navbar.jsp"/>

</head>
<body >

<c:forEach var="order" items="${my_orders}">
<nav class="navbar col-sm-10"> 
		 <ul class="nav nav-pills nav-left "> 
	      <li role="presentation" class="active"><p><b>Order number: </b> ${order.getId()}  </p> 
	      <p><b>Order date: </b>${order.getOrderDate()}  </p> 
	       <p><b>Order Amount: </b><fmt:formatNumber value="${order.getOrderAmount()} " type="currency" /> </p>
	       <p><b>Order Shipping address: </b> ${order.getShippingAddress()} </p> 
	       <p><b>Order Details: </b>
	       <c:forTokens items="${order.getOrderDetails()}" var="detail" delims=",">
	          <p> ${detail}</p>

	       </c:forTokens>
	        </li>
				    
		  </ul>
</nav>

</c:forEach>





  
</body>
</html>