package br.com.carlist.car;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/car")
public class Endpoint {
    Service service = new Service();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Car> find() throws SQLException {
        return this.service.find();
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Car create(Car c) throws SQLException {
        this.service.save(c);
        return c;
    }

    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Car update(Car c) throws SQLException {
        this.service.save(c);
        return c;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Car findOne(@PathParam("id") Integer id) throws SQLException {
        return this.service.findOne(id);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("id") Integer id) throws SQLException {
        this.service.remove(id);
        return "ok";
    }
}