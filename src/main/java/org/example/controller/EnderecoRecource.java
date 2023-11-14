package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.models.Endereco;
import org.example.repository.EnderecoRepository;

import java.sql.SQLException;
import java.util.List;

@Path("endereco")
public class EnderecoRecource {

    private final EnderecoRepository repository = new EnderecoRepository();

    @GET
    @Path("todos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Endereco> getAll() throws SQLException {
        return repository.findEndereco();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Endereco getID(@PathParam("id") int id) throws SQLException {
        return repository.findby(id).orElse(null);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEndereco(Endereco endereco) throws SQLException {
        repository.add(endereco);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("{id}")
    public void deleteCard(@PathParam("id") int id) throws SQLException {
        repository.delete(id);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEndereco(@PathParam("id") int id, Endereco endereco) throws SQLException {
        if (repository.findby(id).isPresent()) {
            endereco.setIdendereco(id);
            repository.update(endereco);
            return Response.status(Response.Status.CREATED).entity(endereco).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(endereco).build();
    }

}
