<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="Navbar.jsp"/>

</head>
<body background="http://www.freewallpaperfullhd.com/wp-content/uploads/2015/02/Nature-Wallpaper-HD-1920x1080.jpg">


 

<div class="panel panel-primary col-sm-5 col-sm-offset-4">
		<div class= "panel-heading">
                 
			Person Detail
		</div>	
		<div class ="panel-body">
	  		<p><span class="glyphicon glyphicon-user"></span><b> Name:  </b> ${sessionScope.User.getFirstname()} ${sessionScope.User.getLastname()}</p> 
    		<p><span class="glyphicon glyphicon-envelope"> </span><b> Email Address: </b>${sessionScope.User.getEmail()}  </p>
    		<p><span class="glyphicon glyphicon-calendar"> </span><b> Join Date: </b> ${sessionScope.User.getJoinDate()}   </p>
	</div>
</div>

  
</body>
</html>