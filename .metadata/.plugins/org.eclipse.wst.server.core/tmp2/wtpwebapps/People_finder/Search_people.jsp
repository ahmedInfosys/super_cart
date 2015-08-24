<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Student fields</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<form action="search" method="get">
<br><br><br><br><br><br>
    <div class=" input-group col-xs-6">
      <input type="text" class="form-control" id="name" name="name" placeholder="person name" required>
      <span class="input-group-btn">
        <button class="btn btn-default" type="submit" >Search</button>
      </span>
  </div>
</form>
<form action="see_grades" method="post">
	${people}
</form>
</body>