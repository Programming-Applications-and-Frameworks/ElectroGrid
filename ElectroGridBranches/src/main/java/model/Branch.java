package model;

import java.sql.*;

public class Branch

{ 
	
	//A common method to connect to the DB

	private Connection connect()
 
	{
 
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
	
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	 	}
//Insert user details
	public String insertCEBdetails( String m, String a1, String a2 , String c,String z,String p)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			// create a prepared statement
			String query = " insert into CEB_info(`mapLocationCode`,`addressLine1`,`addressLine2`,`city`,`zipCode`,`phone`)"+ " values (?,?,?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			 
			 preparedStmt.setString(1, m);
			 preparedStmt.setString(2, a1);
			 preparedStmt.setString(3, a2);
			 preparedStmt.setString(4, c);
			 preparedStmt.setString(5, z);
			 preparedStmt.setString(6, p);
			 
			 
			 
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
	//Read user details
	
	public String readCEBDetails()
	{
		String output = "";
		try
		{
			Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for reading."; }
			
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Map Location Code</th><th>Address Line 1</th>" +
						 "<th>Address Line 1</th>" +"<th>City</th>" +"<th>ZIP Code</th>" +
						 "<th>Phone</th></tr>"; 
				

				String query = "select * from CEB_info";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next())
				{
					
					String m = rs.getString("mapLocationCode");
					String a1 = rs.getString("addressLine1");
					String a2 = rs.getString("addressLine2");
					String c= rs.getString("city");
					String z = rs.getString("zipCode");
					String p = rs.getString("phone");
					
					// Add into the html table
					
					output += "<tr><td>" + m + "</td>";
					output += "<td>" + a1+ "</td>";
					output += "<td>" + a2 + "</td>";
					output += "<td>" + c + "</td>";
					output += "<td>" + z + "</td>";
					output += "<td>" + p + "</td>";
					
					
					
				 }
				con.close();
				 // Complete the html table
				output += "</table>";
				 }
				 catch (Exception e)
				 {
				 output = "Error while reading the items.";
				 System.err.println(e.getMessage());
				 }
				 return output;
				 }
//Update user details
	
	public String updateCEBDetails(String mx, String a1x, String a2x , String cx, String zx, String px) 

	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			// create a prepared statement
			String query = "UPDATE CEB_info SET addressLine1=?,addressLine2=?,city=?,zipCode=?,phone=?, WHERE mapLocationCode=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			 preparedStmt.setString(1, mx);
			 preparedStmt.setString(2, a1x);
			 preparedStmt.setString(3, a2x);
			 preparedStmt.setString(4, cx);
			 preparedStmt.setString(5, zx);
			 preparedStmt.setString(6, px);
			 
			 
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteCEBLocation(String mLocat_code)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			// create a prepared statement
			String query = "delete from CEB_info where mapLocationCode=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1,mLocat_code);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}