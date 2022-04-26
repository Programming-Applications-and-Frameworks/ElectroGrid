<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="model.Customer" %>
<%

if (request.getParameter("fullname") != null)
{
	if (request.getParameter("fullname") != null)
	 {
	 Customer itemObj = new Customer();
	 String stsMsg = itemObj.insertcustomermanagement(request.getParameter("fullname"),
	 request.getParameter("telephoneNumber"),
	 request.getParameter("address"),
	 request.getParameter("District"),
	 request.getParameter("meterBoxId"),
	 request.getParameter("readingCurrent"),
	 request.getParameter("nic")
	
			 );
	 session.setAttribute("statusMsg", stsMsg);
	 } 
}

//Delete item----------------------------------
if (request.getParameter("customerId") != null)
{
	Customer itemObj = new Customer();
String stsMsg = itemObj.deletecustomermanagement(request.getParameter("customerId"));
session.setAttribute("statusMsg", stsMsg);
} 

%>

<html>
<head>
<link rel="stylesheet" href="views/bootstrap.min.css">
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="View/bootstrap.min.css">

<title>customer Management</title>
</head>

<body>

<h1> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp;customer Management</h1>
<form method="post" action="Customer.jsp">
<div class="container">
 <div class="row">
 <div class="col">


 customer Name: <input name="fullname" type="text"  class="form"><br>
 customer Phone: <input name="" type="text"  class="form"><br>
 customer Email: <input name="customerEmail" type="text"  class="form"><br>
  customer Password: <input name="customerPassword" type="password"  class="form"><br>
 <input name="btnSubmit" type="submit" value="Save"class="btn btn-primary"><br>

 </div>
 </div>
</div>
</form>
<div class="alert-success">
 <% out.print(session.getAttribute("statusMg"));%>
</div>
<br>
<%
Customer itemObj = new Customer();
 out.print(itemObj.readItems());
%>

</body>
</html>