package com.example.demo.Controller;

import com.example.demo.Database.HibernateSessionFactoryUtil;
import com.example.demo.Entity.Product;
import com.example.demo.Services.ProductService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/product")
@PermitAll
public class ProductController {

    @EJB
    ProductService productService;

    @GET
    @Path("/")
    @PermitAll
    public String hello(){
        HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return "Hello";
    }
    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response getAll(){
        List<Product> result = productService.getAll();
        System.out.println(result);
        return Response.ok().entity(result).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response getClient(@PathParam("id") Long id) {
        try {
            return  Response
                    .status(Response.Status.OK)
                    .entity((productService.getProducttById(id)))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            String message = "Product not found";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(message)
                    .build();
        }
    }

    @POST
    @Path("/create")
    @RolesAllowed("ADMIN")
    public Response saveClient(
                               @FormParam("name") String name, @FormParam("price") int price,
                                @FormParam("amount") int amount) {
        try {
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setAmount(amount);
            productService.createNewProduct(product);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/updateProductPrice/{id}")
    public Response updateProduct(@FormParam("price") int price, @PathParam("id") Long id) {
        try {
            productService.updateProductPrice(id, price);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        try {
            productService.deleteProductById(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
