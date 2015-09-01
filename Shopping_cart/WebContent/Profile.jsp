<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<body background="http://www.freewallpaperfullhd.com/wp-content/uploads/2015/02/Nature-Wallpaper-HD-1920x1080.jpg">

<nav class="navbar navbar-default" >
	<ul class="nav navbar-nav navbar-left">  
	   <li role="presentation" >Welcome <b>${sessionScope.User.getFirstname()} ${sessionScope.User.getLastname()}</b></li>
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

<div class="panel panel-primary col-sm-5 col-sm-offset-4">
		<div class= "panel-heading">
                 
			Person Detail
		</div>	
		<div class ="panel-body">
	  		<p><span class="glyphicon glyphicon-user"></span><b> Name:  </b> ${sessionScope.User.getFirstname()} ${sessionScope.User.getLastname()}</p> 
    		<p><span class="glyphicon glyphicon-envelope"> </span><b> Email Address: </b>${sessionScope.User.getEmail()}  </p>
    		<p><span class="glyphicon glyphicon-calendar"> </span><b> Join Date: </b> ${sessionScope.User.getJoinDate()}   </p>
		      ${Details}
	</div>
	</div>

  
</body>
</html>