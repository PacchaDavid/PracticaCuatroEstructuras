package com.graph.rest.response;

import java.lang.reflect.Method;
import java.util.HashMap;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseFactory {
    public static Response buildResponse(Object data) {
        HashMap<String, Object> res = new HashMap<>();
        res.put("Status", "Succes");
        res.put("data",data);
        try {
            return Response.ok(new ObjectMapper().writeValueAsString(res)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.ok(res).build();
        }
    }  

    public static Response buildResponseWithException(Exception e) {
        HashMap<String, Object> res = new HashMap<>();
        res.put("Status", "Error");
        res.put("data",e.getMessage());
        try {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ObjectMapper().writeValueAsString(res)).build();
        } catch (Exception exc) {
            exc.printStackTrace();
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    public static Response buildResponse(Object service, String methodName) {
        try {
            Method method = service.getClass().getMethod(methodName);
            return buildResponse(method.invoke(service));
        } catch (Exception e) {
            e.printStackTrace();
            return buildResponseWithException((Exception)e.getCause());
        }
    }

    public static Response buildResponse(Object service, String methodName, Object... args) {
        try {

            Class<?>[] argClass = new Class[args.length];
            for(int i = 0; i < args.length; i++) {
                argClass[i] = args[i].getClass();
            }

            Method method = service.getClass().getMethod(methodName, argClass);

            return buildResponse(method.invoke(service, args));
        } catch (Exception e) {
            e.printStackTrace();
            return buildResponseWithException((Exception)e.getCause());
        }
    }

}


