package com.graph.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.graph.controller.dao.services.VeterinariaServices;
import com.graph.rest.response.ResponseFactory;


@Path("/veterinaria")
public class VeterinariaResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getAll() {
        return ResponseFactory.buildResponse(new VeterinariaServices(),"getVeterinarias");
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/save")
    public Response save(String personaJson) {
        return ResponseFactory.buildResponse(new VeterinariaServices(),"saveVeterinaria", personaJson);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{id}")
    public Response get(@PathParam("id") Integer id) {
        return ResponseFactory.buildResponse(new VeterinariaServices(),"getVeterinariaById",id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update")
    public Response update(String personaJson) {
       return ResponseFactory.buildResponse(new VeterinariaServices(),"updateVeterinaria", personaJson);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Integer id) {
        return ResponseFactory.buildResponse(new VeterinariaServices(),"deleteVeterinaria",id);
    }
}
