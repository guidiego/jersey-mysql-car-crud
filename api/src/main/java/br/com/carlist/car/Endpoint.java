package br.com.carlist.car;

import javax.ws.rs.GET;
// import javax.ws.rs.POST;
// import javax.ws.rs.PUT;

// import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
// import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
// import javax.ws.rs.core.Response.Status;


// class Person {
//     public int id;
//     public String name;

//     public Person() {

//     }

//     public Person(int i, String n) {
//         this.name = n;
//         this.id = i;
//     }
// }

@Path("/car")
public class Endpoint {
    Service service = new Service();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find() {
        List<Car> cars = new ArrayList<Car>();

        try {
            cars = this.service.select();
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }

        return Response.ok(cars).build();
    }

    // @POST
    // @Path("/")
    // @Produces(MediaType.APPLICATION_JSON)
    // public Car create(Car c) throws SQLException {
    //     this.service.save(c);
    //     return c;
    // }

    // @PUT
    // @Path("/")
    // @Produces(MediaType.APPLICATION_JSON)
    // public Car update(Car c) throws SQLException {
    //     this.service.save(c);
    //     return c;
    // }

    // @GET
    // @Path("/{id}")
    // @Produces(MediaType.APPLICATION_JSON)
    // public Car findOne(@PathParam("id") Integer id) throws SQLException {
    //     return this.service.findOne(id);
    // }

    // @DELETE
    // @Path("/{id}")
    // @Produces(MediaType.TEXT_PLAIN)
    // public String delete(@PathParam("id") Integer id) throws SQLException {
    //     this.service.remove(id);
    //     return "ok";
    // }
}