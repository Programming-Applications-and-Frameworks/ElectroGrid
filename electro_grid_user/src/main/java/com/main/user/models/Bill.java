package com.main.user.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Bill {
	private int userId;
	private String BillNumber;
	private String Prev_Reading;
	private String Current_Reading;
	private String Bill_Amount;
	private String Due_Amount;
	private String Billing_Date;
	private int bill_id;
	
	public Bill() {
	}
	
	public Bill(int userId, String BillNumber, String Prev_Reading, String Current_Reading, String Bill_Amount, String Due_Amount,
			String Billing_Date, int bill_id) {
		super();
		this.userId = userId;
		this.BillNumber = BillNumber;
		this.Prev_Reading = Prev_Reading;
		this.Current_Reading = Current_Reading;
		this.Bill_Amount = Bill_Amount;
		this.Due_Amount = Due_Amount;
		this.Billing_Date = Billing_Date;
		this.bill_id = bill_id;
	}


	public Bill(String BillNumber, String Prev_Reading, String Current_Reading, String Bill_Amount, String Due_Amount,
			String Billing_Date, int bill_id) {
		super();
		this.BillNumber = BillNumber;
		this.Prev_Reading = Prev_Reading;
		this.Current_Reading = Current_Reading;
		this.Bill_Amount = Bill_Amount;
		this.Due_Amount = Due_Amount;
		this.Billing_Date = Billing_Date;
		this.bill_id = bill_id;
	}

	public Bill(String BillNumber) {
		this.BillNumber = BillNumber;
	}
	

	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getBillNumber() {
		return BillNumber;
	}


	public void setBillNumber(String BillNumber) {
		this.BillNumber = BillNumber;
	}


	public String getPREV_READING() {
		return Prev_Reading;
	}


	public void setPREV_READING(String Prev_Reading) {
		this.Prev_Reading = Prev_Reading;
	}


	public String getCURRENT_READING() {
		return Current_Reading;
	}


	public void setCURRENT_READING(String Current_Reading) {
		this.Current_Reading = Current_Reading;
	}


	public int getBILL_ID() {
		return bill_id;
	}


	public void setBILL_ID(int bill_id) {
		this.bill_id = bill_id;
	}


	public String getDUE_AMOUNT() {
		return Due_Amount;
	}


	public void setDUE_AMOUNT(String Due_Amount) {
		this.Due_Amount = Due_Amount;
	}


	public String getBILLING_DATE() {
		return Billing_Date;
	}


	public void setBILLING_DATE(String Billing_Date) {
		this.Billing_Date = Billing_Date;
	}


	public String getBILL_AMOUNT() {
		return Bill_Amount;
	}


	public void setBILL_AMOUNT(String Bill_Amount) {
		this.Bill_Amount = Bill_Amount;
	}


	@Override
	public String toString() {
		return "User{" +
			"userId=" + userId +
			", BillNumber='" + BillNumber + '\'' +
			", Prev_Reading='" + Prev_Reading + '\'' +
			", Current_Reading='" + Current_Reading + '\'' +
			", Bill_Amount='" + Bill_Amount + '\'' +
			", Due_Amount='" + Due_Amount + '\'' +
			'}';
	}
	
	
}

