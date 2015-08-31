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
</head>
<body>
<nav class="navbar navbar-inverse">
    <div>
      <ul class="nav navbar-nav">
      	<li><h1><b>${welcome}</b></h1></li>
      </ul>
     
	 ${sign_in_out}
	 ${account}	
      
    </div>  
  </nav>

<div class="panel panel-primary col-sm-4 col-sm-offset-4">
		<div class= "panel-heading">
			Person Detail
		</div>	
		<div class ="panel-body">
			<p><span class="glyphicon glyphicon-user"></span><b> Name:  </b> ${sessionScope.user.first_name} ${sessionScope.user.last_name} </p>
			<p><span class="glyphicon glyphicon-calendar"> </span><b> Address: </b>  ${sessionScope.user.address.street_address}${sessionScope.user.address.city}${sessionScope.user.address.state}${sessionScope.User.address.zipcode} </p>
			<p><span class="glyphicon glyphicon-calendar"> </span><b> Age: </b>  ${sessionScope.user.age} </p>
			  
		</div>
	</div>

  
</body>
</html>