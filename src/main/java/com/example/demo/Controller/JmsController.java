package com.example.demo.Controller;

import com.example.demo.JMS.Message;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.jms.JMSException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.List;

@Path("/jms")
@PermitAll
public class JmsController implements ExceptionMapper {
    @EJB
    private Message message;

    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    @Context
    HttpHeaders httpHeaders;

    @POST
    @Path("/sendMessage")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @PermitAll
    public Response sendMessage(String text) throws JMSException {

        message.sendMessage(text);
        return  Response.ok()
                .entity("Message " + text + " was sent to all user")
                .build();
    }

    @GET
    @Path("/receiveAllMessage")
    @Produces("application/json")
    public Response getAllMessage() throws JMSException {
        List<String> receiveAllMessage = message.receiveAll();
        return  Response.ok()
                .entity("Message " + receiveAllMessage + " from admin")
                .build();
    }

    @Override
    public Response toResponse(Throwable throwable) {
        return Response.status(500)
                .entity("ATTENTION! ERROR HANDLER IS FOUND A NEW ERROR")
                .build();
    }
}
