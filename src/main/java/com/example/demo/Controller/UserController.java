package com.example.demo.Controller;

import com.example.demo.Database.HibernateSessionFactoryUtil;
import com.example.demo.Entity.User;
import com.example.demo.Services.UserService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/airplane")
public class UserController {

    @EJB
    UserService userService;

    @GET
    @Path("/")
    public String hello(){
        HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return "Hello";
    }
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        List<User> result = userService.getAll();
        System.out.println(result);
        return Response.ok().entity(result).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClient(@PathParam("id") Long id) {
        try {
            return  Response
                    .status(Response.Status.OK)
                    .entity((userService.getUserById(id)))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            String message = "User not found";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(message)
                    .build();
        }
    }

    @POST
    @Path("/create")
    public Response saveClient(
            @FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName) {
        try {
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            userService.createNewUser(user);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

//    @PUT
//    @Path("/updateAirplane/{id}")
//    public Response updateClient(@FormParam("name") String name, @PathParam("id") Long id) {
//        try {
//            userService.(id, name);
//            return Response.ok().build();
//        } catch (Exception e) {
//            return Response.serverError().build();
//        }
//    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        try {
            userService.deleteUserById(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

//    @PUT
//    @Path("/addAirplaneToFlight/{airplaneId}/{flightId}")
//    public Response addAirplaneToFlight(@PathParam("airplaneId") Long id,
//                                        @PathParam("flightId") Long id2) {
//        try{
//            userService.addAirplaneToFlight(id, id2);
//            return Response.ok().build();
//        } catch (Exception e){
//            return Response.serverError().build();
//        }
//    }
}
