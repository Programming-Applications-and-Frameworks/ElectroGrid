package com;
import model.Customer;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Customer")

public class CustomerService {
	Customer Obj = new Customer();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
	 return Obj.readPayment();
	 }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("TransactionID") String TransactionID,
	 @FormParam("Paymentmethod") String Paymentmethod,
	 @FormParam("Amount") String Amount,
	 @FormParam("Bill_No") String Bill_No,
	@FormParam("Bank_id") String Bank_id)
	{
	 String output = Obj.insertPayment(TransactionID, Paymentmethod, Amount, Bill_No, Bank_id);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String paymentData)
	{
	//Convert the input string to a JSON object
	 JsonObject Object = new JsonParser().parse(paymentData).getAsJsonObject();
	//Read the values from the JSON object
	 String Payment_ID = Object.get("Payment_ID").getAsString();
	 String TransactionID = Object.get("TransactionID").getAsString();
	 String Paymentmethod = Object.get("Paymentmethod").getAsString();
	 String Amount = Object.get("Amount").getAsString();
	 String Bill_No = Object.get("Bill_No").getAsString();
	 String Bank_id = Object.get("Bank_id").getAsString();
	 String output = Obj.updatePayment(Payment_ID,TransactionID,Paymentmethod,Amount,Bill_No,Bank_id);
	return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String Payment_ID = doc.select("Payment_ID").text();
	 String output = Obj.deletePayment(Payment_ID);
	return output;
	}

	
}
