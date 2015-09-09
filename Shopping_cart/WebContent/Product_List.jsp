
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="Navbar.jsp"/>

</head>
<body>





<c:forEach var="product" items="${Products}">
<nav class="navbar navbar-default col-sm-8 col-sm-offset-2"> 
			 <p class="navbar-text navbar-left " ><b>Product Name: </b>${product.getName()}  </p>
		     <ul class="nav nav-pills nav-right col-sm-offset-8"> 
		        
                     <li role="presentation" class="active"><p class="navbar-text "><b>Price: </b>${product.getPrice()}<b> Qty: </b> ${product.getQuantity()}  </p></li>
			        <li role="presentation" class="active"><a href="Product_details?productID=${product.getId()}" > 
			        <p style="color:red;"><b>${left_qty} </b> </p>
				        <span class="glyphicon glyphicon-align-justify"></span> View</a></li>
				        
				    
			 </ul>
			 </nav>
</c:forEach>


${products}
</body>
</html>