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
	 String stsMsg = itemObj.insertPayment(
	 request.getParameter("TransactionID"),
	 request.getParameter("Paymentmethod"),
	 request.getParameter("Amount"),
	 request.getParameter("Bill_No"),
	 request.getParameter("Bank_id") );
	 session.setAttribute("statusMsg", stsMsg);
	 } 
}

//Delete item----------------------------------
if (request.getParameter("customerId") != null)
{
	Customer itemObj = new Customer();
String stsMsg = itemObj.deletePayment(request.getParameter("customerId"));
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

<h1> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp;Payment Management</h1>
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

%>

</body>
</html>