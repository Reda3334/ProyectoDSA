package edu.upc.dsa.services;

import edu.upc.dsa.Manager;
import edu.upc.dsa.dao.DAO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/question")
public class QuestionResource {

    private final Manager manager = DAO.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response receiveQuestion(String message) {

        manager.printMessageFromAndroid(message);

        return Response.ok().build();
    }
}