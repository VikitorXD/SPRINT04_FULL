package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.models.User;
import org.example.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

@Path("usuario")
public class UserResource {

    private final UserRepository repository = new UserRepository();

    @GET
    @Path("todos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAll() throws SQLException {
        return repository.findUser();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserBynome(@PathParam("id") int id) throws SQLException {
        return repository.findby(id).orElse(null);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(User usuario) throws SQLException {
        repository.add(usuario);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateuser(@PathParam("id") int id, User user) throws SQLException {
        if (repository.findby(id).isPresent()) {
            user.setId(id);
            repository.update(user);
            return Response.status(Response.Status.CREATED).entity(user).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(user).build();
    }

    @DELETE
    @Path("{placa}")
    public void delete(@PathParam("placa") String placa) throws SQLException {
        repository.delete(placa);
    }

}
