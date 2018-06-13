package br.com.carlist.car;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;



@Path("/car")
public class Endpoint {
    Service service = new Service();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find() {
        System.out.println("[LOG] Endpoint.select");

        try {
            return Response.ok(
                this.service.select()
            ).build();
        } catch (Exception e) {
            System.out.println("[ERROR] Endpoint.select:" + e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Car input) {
        System.out.println("[LOG] Endpoint.update: NAME=" +
            input.name + " TYPE=" + input.type);

        try {
            return Response.ok(
                this.service.insert(input)
            ).build();
        } catch (Exception e) {
            System.out.println("[ERROR] Endpoint.create:" + e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Car input) {
        System.out.println("[LOG] Endpoint.update: ID=" + input.id);
        System.out.println("[LOG] Endpoint.update: NAME=" +
            input.name + " TYPE=" + input.type);

        try {
            return Response.ok(
                this.service.update(input)
            ).build();
        } catch (Exception e) {
            System.out.println("[ERROR] Endpoint.update:" + e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id) {
        System.out.println("[LOG] Endpoint.delete: ID=" + id);

        try {
            this.service.delete(id);
            Map<String, String> resp = new HashMap<String, String>();
            resp.put("id", id.toString());
            return Response.ok(resp).build();
        } catch (Exception e) {
            System.out.println("[ERROR] Endpoint.delete:" + e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}