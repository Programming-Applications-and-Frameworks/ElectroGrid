package com.main.user.services;

import java.sql.*;
import java.util.ArrayList;

import com.main.user.models.Bill;


public class BillService {
	
	private Connection connection;
	
	
	public BillService() {
		String url = "jdbc:mysql://127.0.0.1:3306/electro_grid";
		String username = "root";
		String password = "";
		
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connection = DriverManager.getConnection(url, username, password);
			
			if(connection != null){
                System.out.println("Successfully connected to database.");
            }
			   
        } catch (SQLException e) {
			System.out.println(e + ". Failed to conncet to the DB.");
		}

	}
	
	public ArrayList<Bill> getBills() {
		String cmd = "select * from bill"; 
		
		ArrayList<Bill> bills = new ArrayList<>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(cmd);
			ResultSet result = ps.executeQuery();
			
			if (result == null) {
				return new ArrayList<>();
			}
			
			while(result.next()) {

				bills.add(new Bill(result.getInt("userId"), result.getString("BillNumber"), result.getString("Prev_Reading"),
						result.getString("Current_Reading"),result.getString("Bill_Amount"), result.getString("Due_Amount"), result.getString("Billing_Date"),
						result.getInt("bill_id")));
			}
	
		}catch (Exception e) {
			System.out.println(e + ". Could'nt able to get data.");
		}
		
		return bills;
	}
	
	public ArrayList<Bill> getBillById(int bill_id) {
		String cmd = "select * from bill where bill_id = ?"; 
		
		ArrayList<Bill> Bills = new ArrayList<>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(cmd);
			ps.setInt(1, bill_id);
			
			ResultSet result = ps.executeQuery();
			
			if (result == null) {
				return new ArrayList<Bill>();
			}
			
			while(result.next()) {

				Bills.add(new Bill(result.getInt("UserId"), result.getString("BillNumber"), result.getString("Prev_Reading"),
						result.getString("Current_Reading"),result.getString("Bill_Amount"), result.getString("Due_Amount"), result.getString("Billing_Date"),
						result.getInt("bill_id")));
			}
	
		}catch (Exception e) {
			System.out.println(e + ". Could'nt able to get data.");
		}
		
		return Bills;
	}
	
	public ArrayList<Bill> getbillByCURRENT_READING(String Current_Reading) {
		String cmd = "select * from bill where Current_Reading = ?"; 
		
		ArrayList<Bill> Bills = new ArrayList<>();
		
		try {
			PreparedStatement ps = connection.prepareStatement(cmd);
			ps.setString(1, Current_Reading);
			
			ResultSet result = ps.executeQuery();
			
			if (result == null) {
				return new ArrayList<Bill>();
			}
			
			while(result.next()) {

				Bills.add(new Bill(result.getInt("UserId"), result.getString("BillNumber"), result.getString("Prev_Reading"),
						result.getString("Current_Reading"),result.getString("Bill_Amount"), result.getString("Due_Amount"), result.getString("Billing_Date"),
						result.getInt("bill_id")));
			}
	
		}catch (Exception e) {
			System.out.println(e + ". Could'nt able to get data.");
		}
		
		return Bills;
	}
	
	
	
	public Object insertBill(Bill bill) {
		String cmd = "insert into bill(BillNumber,Prev_Reading,Billing_Date,Due_Amount,Current_Reading,Bill_Amount) values (?,?,?,?,?,?)"; 
		
		Object o = null;
		
		try {
			PreparedStatement ps = connection.prepareStatement(cmd, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, bill.getBillNumber());
			ps.setString(2, bill.getPREV_READING());
			ps.setString(3, bill.getBILLING_DATE());
			ps.setString(4, bill.getDUE_AMOUNT());
			ps.setInt(5, bill.getBILL_ID());
			ps.setString(6, bill.getCURRENT_READING());
			ps.setString(7, bill.getBILL_AMOUNT());
			ps.execute() ;
			
			ResultSet resultForId = ps.getGeneratedKeys();
			if(resultForId.next()) {
				Bill u = bill;
				u.setBILL_ID(resultForId.getInt(1));
				o = u;
			} else {
				o = "Failed to create data.";
			}
			
		}catch (Exception e) {
			System.out.println(e + " Failed to create data.");
			o = e + ". Failed to create data.";
		}
		
		return o;
	}
	
	public Object updateBill(Bill bill) {
		String cmd = "update bill set BillNumber= ?,Prev_Reading= ?,Billing_Date= ?,Current_Reading= ?,Bill_Amount= Bill_Amount(?) where UserId= ?"; 
		
		Object o = null;
		
		try {
			PreparedStatement ps = connection.prepareStatement(cmd);
			ps.setString(1, bill.getBillNumber());
			ps.setString(2, bill.getPREV_READING());
			ps.setString(3, bill.getBILLING_DATE());
			ps.setString(4, bill.getDUE_AMOUNT());
			ps.setInt(5, bill.getBILL_ID());
			ps.setString(6, bill.getCURRENT_READING());
			ps.setString(7, bill.getBILL_AMOUNT());
			ps.setInt(8, bill.getUserId());
			ps.executeUpdate();
			
			o = bill;
			
		}catch (Exception e) {
			System.out.println(e + " Failed to create data.");
			o = e + ". Failed to create data.";
		}
		
		return o;
	}
	
	public Object deleteBill(int bill_id, Bill bill) {
		String cmd = "delete from bill where bill_id = ?"; 
		
		Object o = null;
		
		try {
			PreparedStatement ps = connection.prepareStatement(cmd);
			ps.setInt(1, bill_id);
			ps.executeUpdate();
			
			o = bill.getUserId();
			
	
		}catch (Exception e) {
			System.out.println(e + ". Could'nt able to delete record.");
			o = e + ". Could'nt able to delete record.";
		}
		
		return o;
	}

}
