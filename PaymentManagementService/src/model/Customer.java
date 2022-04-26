package model;
import java.sql.*;
public class Customer {
	//A common method to connect to the DB
	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/payment", "root", "");
			System.out.print("DB connected");
			// For testing
			
			
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.print("DB not connected");
		}

		return con;
	}

	// Insert
	public String insertPayment(String TransactionID, String Paymentmethod, String Amount, String Bill_No,String Bank_id) {

		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into payment(`Payment_ID`,`TransactionID`,`Paymentmethod`,`Amount`,`Bill_No`,`Bank_id`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, TransactionID);
			preparedStmt.setString(3, Paymentmethod);
			preparedStmt.setString(4, Amount);
			preparedStmt.setString(5, Bill_No);
			preparedStmt.setString(6, Bank_id);
		

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Insertion successful";

		} catch (Exception e) {
			output = "Insertion Unsuccess";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	// Read
	public String readPayment() {

		String output = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border=\"4\"><tr><th>Transaction ID</th>" + "<th>Payment Method</th><th>Amount</th>"
					+ "<th>Bill_No</th>" + "<th>Bank_ID</th></tr>";

			String query = "select * from payment";

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {

				String Payment_ID  = Integer.toString(rs.getInt("Payment_ID"));
				String TransactionID = rs.getString("TransactionID");
				String Paymentmethod = rs.getString("Paymentmethod");
				String Amount = rs.getString("Amount");
				String Bill_No = rs.getString("Bill_No");
				String Bank_id = rs.getString("Bank_id");
			
				

				// Add into the html table
				output += "<tr><td>" + TransactionID + "</td>";
				output += "<td>" + Paymentmethod + "</td>";
				output += "<td>" + Amount + "</td>";
				output += "<td>" + Bill_No + "</td>";
				output += "<td>" + Bank_id + "</td>";

			}

			con.close();

			// Complete the html table
			output += "</table>";

		} catch (Exception e) {
			output = "Error while reading.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	// Update
	public String updatePayment(String Payment_ID ,String TransactionID, String Paymentmethod, String Amount, String Bill_No,String Bank_id) {

		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE payment SET TransactionID=?,Paymentmethod=?,Amount=?,Bill_No=?,Bank_id=? WHERE Payment_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			
			preparedStmt.setString(1, TransactionID);
			preparedStmt.setString(2, Paymentmethod);
			preparedStmt.setString(3, Amount);
			preparedStmt.setString(4, Bill_No);
			preparedStmt.setString(5, Bank_id);
			preparedStmt.setInt(6, Integer.parseInt(Payment_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Successfully Updated";

		} catch (Exception e) {
			output = "Updating unsuccesful .";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	// Delete
	public String deletePayment(String Payment_ID) {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from payment where Payment_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(Payment_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";

		} catch (Exception e) {
			output = "Error while deleting the Product Details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
		} 

