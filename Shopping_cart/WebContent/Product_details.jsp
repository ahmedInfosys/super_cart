<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
</head>
<body>
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
 

<div class="panel panel-primary col-sm-8 col-sm-offset-2">
		<div class= "panel-heading">
                 
			Product details
		</div>	
		<div class ="panel-body">
		    <img src="${sessionScope.product_details.getPicture()}" alt="Pro_img" width="200" height="200" align="right">
		    <p><b> Name:        </b> ${sessionScope.product_details.getName()}</p>
		    <p><b> Description: </b> ${sessionScope.product_details.getDescription()} </p>
		    <p><b> Quantity:    </b> ${sessionScope.product_details.getQuantity()} </p>
		    <p><b> Price:       </b><fmt:formatNumber value='${sessionScope.product_details.getPrice()}' type="currency"  /> </p>
		    
		     
	            
	            <form action="my_shopping_cart" method="post">
	            <p color="red"><b>${left_qty} </b> </p>
		        <ul class="nav nav-pills nav-right col-sm-offset-4 list-inline">
		            <li role="presentation" class="active"><h4>Qty: </h4></li>
                      <li role="presentation" class="active">
			            <input type="hidden" name="productID" value="${sessionScope.product_details.getId()}"/>
				       	 <input class="form-control col-sm-2"  name="Qty_from_details"  id="Qty_from_details" required/></li>
				       	 <li role="presentation" class="active">
				       	 <button type="submit"  value = "submit" class= "button btn-primary form-control col-sm-7"><span class="glyphicon glyphicon-plus"></span>Add to cart</button>
                         </li>

				        
                </ul>
		        </form>
		 
	   </div>
</div>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<c:forEach var="comment" items="${Comments}"> 
<nav class="navbar navbar-default col-sm-8">
	<div class="navbar-text navbar-default">  
		<p ><b>Comment: </b>${comment.getContentText()}</p> 
		
	</div> 
    <div class="navbar-text navbar-right"> <p>
		<b>${All_DB.select_single_id(comment.getUserId()).getFirstname()} ${All_DB.select_single_id(comment.getUserId()).getLastname()}</b><br>
		<b>Rating scale:  </b>${comment.getRatingScale()} </p> 
		<b>${comment.getCommentDate()} </b>
	</div> 
</nav>


</c:forEach>

<c:choose>
<c:when test="${sessionScope.User.getFirstname() != ('Customer')}">
 <div class="container">
	<form role="form" method="post" action="Product_details">
			 <div class="col-sm-1 col-sm-offset-5">
			        <input type="hidden" name="productID" value="${sessionScope.product_details.getId()}"/>
					Rate:<select class="form-control " id="scale" name="scale"> 
							 <option >1</option>
							 <option >2</option>
							 <option >3</option>
							 <option >4</option>
							 <option >5</option>
		        </select>
		   </div>
	    	<div >
			    <input type="hidden" name="productID" value="${sessionScope.product_details.getId()}"/>
				<textarea maxlength="500" class="form-control" rows="4" cols="200" name="review" placeholder="Review product!" required></textarea><br>
			</div>
			<div class="form-group col-sm-4 col-sm-offset-8">
			    <button type="submit" value = "submit" class= "button btn-primary form-control "><span class="glyphicon glyphicon-comment"></span> Comment</button>
		    </div>
	</form>
</div>
</c:when>
</c:choose>
</body>
</html>