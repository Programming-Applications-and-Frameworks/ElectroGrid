package model;
import java.sql.*;
public class Customer {
	//A common method to connect to the DB
		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");

		
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/customermanagement", "root", "");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
		 }
		public String insertcustomermanagement(String Name, String telephoneNumber, String address, String District, String Email, String readingCurrent, String nic )
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for inserting."; 
		 }
		 
		 // create a prepared statement
		 String query = " insert into customermanagement (`customerId`,`Name`,`telephoneNumber`,`address`,`District`, `Email`, `readingCurrent`, `nic`)" + " values (?, ?, ?, ?, ?,?,?,?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, Name);
		 preparedStmt.setString(3, telephoneNumber);
		 preparedStmt.setString(4, address);
		 preparedStmt.setString(5, District);
		 preparedStmt.setString(6, Email);
		 preparedStmt.setString(7, readingCurrent);
		 preparedStmt.setString(8, nic);
		// execute the statement
		
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while inserting the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String readItems()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
			 
		 {return "Error while connecting to the database for reading."; }
		 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Name</th>" +
		 "<th>Telephone Number</th>" +
		 "<th>Address</th>" +
		 "<th>District</th>"+
		 "<th>Email</th>"+
		 "<th>Reading Current</th>"+
		 "<th>Nic</th>"+
		 "<th>Update</th><th>Remove</th></tr>";

		 String query = "select * from customermanagement";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String customerId = Integer.toString(rs.getInt("customerId"));
		 String Name = rs.getString("Name");
		 String telephoneNumber = rs.getString("telephoneNumber");
		 String address =rs.getString("address");
		 String District = rs.getString("District");
		 String Email = rs.getString("Email");
		 String readingCurrent = rs.getString("readingCurrent");
		 String nic = rs.getString("nic");
		 
		 
		 output += "<tr><td>" + Name + "</td>";
		 output += "<td>" + telephoneNumber + "</td>";
		 output += "<td>" + address + "</td>";
		 output += "<td>" + District + "</td>";
		 output += "<td>" + Email + "</td>";
		 output += "<td>" + readingCurrent + "</td>";
		 output += "<td>" + nic + "</td>";
		
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
				 + "<td><form method='post' action='customer.jsp'>"
				 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
				 + "<input name='customerId' type='hidden' value='" + customerId
				 + "'>" + "</form></td></tr>";
				 }
				 con.close();
				
				 output += "</table>";
				 } 
		 catch (Exception e)
		 {
		 output = "Error while reading the Datas.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String updatecustomermanagement(String customerId, String Name, String telephoneNumber, String address, String District, String Email, String readingCurrent, String nic )
		
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE customermanagement SET Name=?,telephoneNumber=?, address=?, District=?, Email=?, readingCurrent=?, nic=? WHERE customerId=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 
		 preparedStmt.setString(1, Name);
		 preparedStmt.setString(2, telephoneNumber);
		 preparedStmt.setString(3, address);
		 preparedStmt.setString(4, District);
		 preparedStmt.setString(5, Email);
		 preparedStmt.setString(6, readingCurrent);
		 preparedStmt.setString(7, nic);
		 preparedStmt.setInt(8, Integer.parseInt(customerId));
		 
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the item.";
		 System.out.println(e.getMessage());
		 }
		 return output;
		 }
		public String deletecustomermanagement(String customerId)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from customermanagement where customerId=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(customerId));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the item.";
		 System.out.println(e.getMessage());
		 }
		 return output;
		 }
		} 

