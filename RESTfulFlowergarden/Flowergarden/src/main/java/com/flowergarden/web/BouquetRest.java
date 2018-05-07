package com.flowergarden.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowergarden.dao.FlowerDAO;
import com.flowergarden.dao.MarriedBouquetDAO;
import com.flowergarden.model.Bouquet;
import com.flowergarden.model.Flower;
import com.flowergarden.model.GeneralFlower;
import com.flowergarden.model.MarriedBouquet;
import org.springframework.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Collection;

@Path("/bouquet")
public class BouquetRest {


    private MarriedBouquetDAO marriedBouquetDAO;
    private FlowerDAO flowerDAO;
    private ObjectMapper mapper;

    public BouquetRest(MarriedBouquetDAO marriedBouquetDAO, FlowerDAO flowerDAO, ObjectMapper mapper) {
        this.marriedBouquetDAO = marriedBouquetDAO;
        this.flowerDAO = flowerDAO;
        this.mapper = mapper;
    }

    @GET
    @Path("/{id}/price")
    public Response getBouquetPrice(@PathParam("id") int id) throws SQLException {
        try {
            Bouquet<GeneralFlower> bouquet = marriedBouquetDAO.getBouquetById(id);
            return response(String.valueOf(bouquet.getPrice()));
        } catch (NotFoundException e) {
            return notFound();
        }
    }

    @GET
    @Path("/{id}/freshness-reduce")
    public Response reduceFreshness(@PathParam("id") int id) throws SQLException {
        try {
            MarriedBouquet bouquet = marriedBouquetDAO.getBouquetById(id);
            for (GeneralFlower flower : bouquet.getFlowers()) {
                flower.getFreshness().reduce();
            }
            marriedBouquetDAO.updateBouquet(id, bouquet);
            return response("OK");
        } catch (NotFoundException e) {
            return notFound();
        } catch (UnsupportedOperationException e) {
            return badRequest(e.getMessage());
        }
    }

    @GET
    @Path("/flower/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFlower(@PathParam("id") int id) throws SQLException, JsonProcessingException {
        try {
            Flower flower = flowerDAO.getFlowerById(id);
            String json = mapper.writeValueAsString(flower);
            return response(json);
        } catch (NotFoundException e) {
            return notFound();
        }
    }

    @GET
    @Path("/{id}/flowers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFlowers(@PathParam("id") int id) throws SQLException, JsonProcessingException {
        try {
            Bouquet<GeneralFlower> bouquet = marriedBouquetDAO.getBouquetById(id);
            String json = mapper.writeValueAsString(bouquet.getFlowers());
            return response(json);
        } catch (NotFoundException e) {
            return notFound();
        }

    }

    private Response response(String json) {
        return Response.status(HttpStatus.OK.value()).entity(json).build();
    }

    private Response notFound() {
        return Response.status(HttpStatus.NOT_FOUND.value()).entity("ENTITY NOT FOUND").build();
    }

    private Response badRequest(String message) {
        return Response.status(551).entity(message).build();
    }
}
