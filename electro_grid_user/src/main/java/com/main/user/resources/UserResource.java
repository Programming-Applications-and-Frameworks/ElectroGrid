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
import com.main.user.models.User;
import com.main.user.services.UserService;


@Path("/users")
public class UserResource {
	private UserService service = new UserService();
	
	@Secured
	@GET
	@Path("/get-users")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers() throws SQLException{
		List<User> users = new ArrayList<User>();
		users = service.getUsers();
		
		String json = new Gson().toJson(users);
				
		return Response.ok(json).build();
	}
	
	@Secured
	@GET
	@Path("/get-user/{user_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserById(@PathParam("user_id") Integer user_id) {
	    if(user_id == null || user_id == 0) {
	    	JsonArray jsonObj = new JsonArray();
	    	jsonObj.addJsonObject("error", "user_id cannot be blank");
	        return Response.serverError().entity(jsonObj.getJsonObject()).build();
	    }
	    
	    List<User> users = new ArrayList<User>();
	    users = service.getUserById(user_id);
	    
	    if(users == null || users.size() == 0) {
	    	JsonArray jsonObj = new JsonArray();
	    	jsonObj.addJsonObject("error", "User not found for user_id: "+user_id);
	        return Response.status(Response.Status.NOT_FOUND).entity(jsonObj.getJsonObject()).build();
	    }
	    
	    String json = new Gson().toJson(users);
	    return Response.ok(json).build();
	}
	
	
	@POST
	@Path("/add-user")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(String body) {
		// checks for a valid JSON object
		Gson gson = new Gson();
		User user = null;
		try {
			user = gson.fromJson(body, User.class) ;
			if (user == null) {
				JsonArray jsonObj = new JsonArray();
		    	jsonObj.addJsonObject("error", "Provide a valid user body.");
				return Response.serverError().entity(jsonObj.getJsonObject()).build();
			}
		} catch (Exception e) {
			JsonArray jsonObj = new JsonArray();
	    	jsonObj.addJsonObject("error", "Provide a valid user body.");
			return Response.serverError().entity(jsonObj.getJsonObject()).build();
		}
		
		// adding the user to DB
		Object o = service.insertUser(user);
		
		
		if (o == null || o instanceof String) {
			JsonArray jsonObj = new JsonArray();
	    	jsonObj.addJsonObject("error", (String)o);
			return Response.serverError().entity(jsonObj.getJsonObject()).build();
		}
		
		return Response.ok(new Gson().toJson(o)).build();
	}
	
	@Secured
	@PUT
	@Path("/update-user/{user_id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(String body, @PathParam("user_id") Integer user_id) {
		// checks whether provided id is valid or not
		if(user_id == null || user_id == 0) {
	        return Response.serverError().entity("user_id cannot be blank").build();
	    }
	    
	    List<User> users = new ArrayList<User>();
	    users = service.getUserById(user_id);
	    
	    if(users == null || users.size() == 0) {
	    	JsonArray jsonObj = new JsonArray();
	    	jsonObj.addJsonObject("error", "User not found for user_id: "+user_id);
	        return Response.status(Response.Status.NOT_FOUND).entity(jsonObj.getJsonObject()).build();
	    }
	    
		// checks for a valid JSON object
		Gson gson = new Gson();
		User user = null;
		try {
			user = gson.fromJson(body, User.class);
			if (user == null) {
				JsonArray jsonObj = new JsonArray();
		    	jsonObj.addJsonObject("error", "Provide a valid user body.");
				return Response.serverError().entity(jsonObj.getJsonObject()).build();
			}
		} catch (Exception e) {
			JsonArray jsonObj = new JsonArray();
	    	jsonObj.addJsonObject("error", "Provide a valid user body.");
			return Response.serverError().entity(jsonObj.getJsonObject()).build();
		}
		
		// update the user details
		user.setUserId(user_id);
		Object o = service.updateUser(user);
		
		
		if (o == null || o instanceof String) {
			JsonArray jsonObj = new JsonArray();
	    	jsonObj.addJsonObject("error", (String)o);
			return Response.serverError().entity(jsonObj.getJsonObject()).build();
		}
		
		return Response.ok(new Gson().toJson(o)).build();
	}
	
	@Secured
	@DELETE
	@Path("/delete-user/{user_id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteUser(@PathParam("user_id") Integer user_id) {
	    if(user_id == null || user_id == 0) {
	        return Response.serverError().entity("user_id cannot be blank").build();
	    }
	    
	    List<User> users = new ArrayList<User>();
	    users = service.getUserById(user_id);
	    
	    if(users == null || users.size() == 0) {
	    	JsonArray jsonObj = new JsonArray();
	    	jsonObj.addJsonObject("error", "User not found for user_id: "+user_id);
	        return Response.status(Response.Status.NOT_FOUND).entity(jsonObj.getJsonObject()).build();
	    }
	    
	    Object o = service.deleteUser(user_id, users.get(0));
	    
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
