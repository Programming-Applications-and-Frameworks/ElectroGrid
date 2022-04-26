package com.main.user.resources;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.main.user.auth.Secured;
import com.main.user.extras.JsonArray;
import com.main.user.models.Bill;
import com.main.user.services.BillService;


@Path("/bills")
public class BillResource {
	private BillService service = new BillService();
	
	
	@GET
	@Path("/get-bills")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getbills() throws SQLException{
		List<Bill> bills = new ArrayList<Bill>();
		bills = service.getBills();
		
		String json = new Gson().toJson(bills);
				
		return Response.ok(json).build();
	}
	
	
	@GET
	@Path("/get-bill/{bill_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBillById(@PathParam("bill_id") Integer bill_id) {
	    if(bill_id == null || bill_id == 0) {
	    	JsonArray jsonObj = new JsonArray();
	    	jsonObj.addJsonObject("error", "bill_id cannot be blank");
	        return Response.serverError().entity(jsonObj.getJsonObject()).build();
	    }
	    
	    List<Bill> bills = new ArrayList<Bill>();
	    bills = service. getBillById(bill_id);
	    
	    if(bills == null || bills.size() == 0) {
	    	JsonArray jsonObj = new JsonArray();
	    	jsonObj.addJsonObject("error", "Bill not found for bill_id: "+bill_id);
	        return Response.status(Response.Status.NOT_FOUND).entity(jsonObj.getJsonObject()).build();
	    }
	    
	    String json = new Gson().toJson(bills);
	    return Response.ok(json).build();
	}
	
	
	@POST
	@Path("/add-bill")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addBill(String body) {
		// checks for a valid JSON object
		Gson gson = new Gson();
		Bill bill = null;
		try {
			bill = gson.fromJson(body, Bill.class) ;
			if (bill == null) {
				JsonArray jsonObj = new JsonArray();
		    	jsonObj.addJsonObject("error", "Provide a valid bill body.");
				return Response.serverError().entity(jsonObj.getJsonObject()).build();
			}
		} catch (Exception e) {
			JsonArray jsonObj = new JsonArray();
	    	jsonObj.addJsonObject("error", "Provide a valid bill body.");
			return Response.serverError().entity(jsonObj.getJsonObject()).build();
		}
		
		// adding the bill to DB
		Object o = service.insertBill(bill);
		
		
		if (o == null || o instanceof String) {
			JsonArray jsonObj = new JsonArray();
	    	jsonObj.addJsonObject("error", (String)o);
			return Response.serverError().entity(jsonObj.getJsonObject()).build();
		}
		
		return Response.ok(new Gson().toJson(o)).build();
	}
	
	
	@PUT
	@Path("/update-bill/{bill_id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateBill(String body, @PathParam("bill_id") Integer bill_id) {
		// checks whether provided id is valid or not
		if(bill_id == null || bill_id == 0) {
	        return Response.serverError().entity("bill_id cannot be blank").build();
	    }
	    
	    List<Bill> bills = new ArrayList<Bill>();
	    bills = service.getBillById(bill_id);
	    
	    if(bills == null || bills.size() == 0) {
	    	JsonArray jsonObj = new JsonArray();
	    	jsonObj.addJsonObject("error", "Bill not found for bill_id: "+bill_id);
	        return Response.status(Response.Status.NOT_FOUND).entity(jsonObj.getJsonObject()).build();
	    }
	    
		// checks for a valid JSON object
		Gson gson = new Gson();
		Bill bill = null;
		try {
			bill = gson.fromJson(body, Bill.class);
			if (bill == null) {
				JsonArray jsonObj = new JsonArray();
		    	jsonObj.addJsonObject("error", "Provide a valid bill body.");
				return Response.serverError().entity(jsonObj.getJsonObject()).build();
			}
		} catch (Exception e) {
			JsonArray jsonObj = new JsonArray();
	    	jsonObj.addJsonObject("error", "Provide a valid bill body.");
			return Response.serverError().entity(jsonObj.getJsonObject()).build();
		}
		
		// update the bill details
		bill.setBILL_ID(bill_id);
		Object o = service.updateBill(bill);
		
		
		if (o == null || o instanceof String) {
			JsonArray jsonObj = new JsonArray();
	    	jsonObj.addJsonObject("error", (String)o);
			return Response.serverError().entity(jsonObj.getJsonObject()).build();
		}
		
		return Response.ok(new Gson().toJson(o)).build();
	}
	
	
	@DELETE
	@Path("/delete-bill/{bill_id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteBill(@PathParam("bill_id") Integer bill_id) {
	    if(bill_id == null || bill_id == 0) {
	        return Response.serverError().entity("bill_id cannot be blank").build();
	    }
	    
	    List<Bill> bills = new ArrayList<Bill>();
	    bills = service.getBillById(bill_id);
	    
	    if(bills == null || bills.size() == 0) {
	    	JsonArray jsonObj = new JsonArray();
	    	jsonObj.addJsonObject("error", "Bill not found for bill_id: "+bill_id);
	        return Response.status(Response.Status.NOT_FOUND).entity(jsonObj.getJsonObject()).build();
	    }
	    
	    Object o = service.deleteBill(bill_id, bills.get(0));
	    
	    if (o == null || o instanceof String) {
	    	System.out.println(o.toString());
	    	System.out.println((String)o);
	    	JsonArray jsonObj = new JsonArray();
	    	jsonObj.addJsonObject("error", (String)o);
			return Response.serverError().entity(jsonObj.getJsonObject()).build();
		}
	    
	    return Response.ok(o).build();
	}
}
