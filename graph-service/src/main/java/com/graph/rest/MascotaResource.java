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

import com.graph.controller.dao.services.MascotaServices;
import com.graph.rest.response.ResponseFactory;


@Path("/mascota")
public class MascotaResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    public Response getAll() {
        return ResponseFactory.buildResponse(new MascotaServices(),"getMascotas");
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/save")
    public Response save(String personaJson) {
        return ResponseFactory.buildResponse(new MascotaServices(),"saveMascota", personaJson);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{id}")
    public Response get(@PathParam("id") Integer id) {
        return ResponseFactory.buildResponse(new MascotaServices(),"getMascotaById",id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update")
    public Response update(String personaJson) {
       return ResponseFactory.buildResponse(new MascotaServices(),"updateMascota", personaJson);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Integer id) {
        return ResponseFactory.buildResponse(new MascotaServices(),"deleteMascota",id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/enumerations")
    public Response enumerations() {
        return ResponseFactory.buildResponse(new MascotaServices(),"tiposMascota");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/sort/{attribute}/{orden}/{method}")
    public Response sort(@PathParam("attribute") String attribute,
                        @PathParam("orden") Integer orden,
                        @PathParam("method") Integer method) 
    {
        return ResponseFactory.buildResponse(new MascotaServices(),"sort",attribute,orden,method);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search/{attribute}/{x}")
    public Response enumerations(@PathParam("attribute") String attribute, @PathParam("x") String x) {
        return ResponseFactory.buildResponse(new MascotaServices(),"search", attribute, x);
    }
}
