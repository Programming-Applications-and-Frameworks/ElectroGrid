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
	 return Obj.readItems();
	 }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertcustomermanagement(@FormParam("Name") String Name,
	 @FormParam("telephoneNumber") String telephoneNumber,
	 @FormParam("address") String address,
	 @FormParam("District") String District,
	@FormParam("Email") String Email,
    @FormParam("readingCurrent") String readingCurrent,
     @FormParam("nic") String nic)
	{
	 String output = Obj.insertcustomermanagement(Name, telephoneNumber, address, District, Email, readingCurrent, nic);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatecustomermanagement(String customerData)
	{
	//Convert the input string to a JSON object
	 JsonObject Object = new JsonParser().parse(customerData).getAsJsonObject();
	//Read the values from the JSON object
	 String customerId = Object.get("customerId").getAsString();
	 String Name = Object.get("Name").getAsString();
	 String telephoneNumber = Object.get("telephoneNumber").getAsString();
	 String address = Object.get("address").getAsString();
	 String District = Object.get("District").getAsString();
	 String Email = Object.get("Email").getAsString();
	 String readingCurrent = Object.get("readingCurrent").getAsString();
	 String nic = Object.get("nic").getAsString();
	 String output = Obj.updatecustomermanagement(customerId, Name, telephoneNumber, address, District, Email, readingCurrent, nic);
	return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletecustomermanagement(String customerData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(customerData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String customerId = doc.select("customerId").text();
	 String output = Obj.deletecustomermanagement(customerId);
	return output;
	}

	
}
