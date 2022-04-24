package com.example.demo.Controller;

import com.example.demo.Database.HibernateSessionFactoryUtil;
import com.example.demo.Entity.Shop;
import com.example.demo.Services.ShopService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/flight")
public class ShopController {

    @EJB
    ShopService shopService;

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
        List<Shop> result = shopService.getAll();
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
                    .entity((shopService.getFlightById(id)))
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
            @FormParam("name") String name) {
        try {
            Shop flight = new Shop(null, name, null, null);
            shopService.createNewFlight(flight);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/updateFlight/{id}")
    public Response updateClient(@FormParam("name") String name, @PathParam("id") Long id) {
        try {
            shopService.updateFlightByName(id, name);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        try {
            shopService.deleteAirplaneById(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
