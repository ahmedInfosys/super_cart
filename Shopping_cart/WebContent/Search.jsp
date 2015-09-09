<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="Navbar.jsp"/>

</head>
<body>



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
<nav class="navbar navbar-default col-sm-8 col-sm-offset-2"> 
              
			 <p class="navbar-text navbar-left " ><b>Product Name: </b>${found_product.getName()}  </p>
		     <ul class="nav nav-pills nav-right col-sm-offset-9"> 
		            <li role="presentation" class="active">
                    <p cstyle="color:red;"><b>Price: </b>${found_product.getPrice()}<b> Qty: </b> ${found_product.getQuantity()}   </p> </li>
			        <li role="presentation" class="active"><a href="Product_details?productID=${found_product.getId()}" > 
				        <span class="glyphicon glyphicon-align-justify"></span> View</a></li>
			        
			 </ul>
			 </nav>
</c:forEach>


</body>
</html>