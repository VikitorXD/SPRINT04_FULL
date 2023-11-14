package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.models.Veiculo;
import org.example.repository.VeiculoRepository;

import java.sql.SQLException;
import java.util.List;

@Path("veiculo")
public class VeiculoResource {

    private final VeiculoRepository repository = new VeiculoRepository();

    @GET
    @Path("todos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Veiculo> getAll() throws SQLException {
        return repository.findVeiculo();
    }

    @GET
    @Path("{placa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Veiculo getVeiculo(@PathParam("placa") String placa) throws SQLException {
        return repository.findby(placa).orElse(null);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addVeiculo(Veiculo veiculo) throws SQLException {
        repository.addveiculo(veiculo);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateveiculo(@PathParam("id") int id, Veiculo veiculo) throws SQLException {
        if (repository.findbyid(id).isPresent()) {
            veiculo.setID(id);
            repository.update(veiculo);
            return Response.status(Response.Status.CREATED).entity(veiculo).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(veiculo).build();
    }

    @DELETE
    @Path("{cpf}")
    public void deleteCard(@PathParam("cpf") String cpf) throws SQLException {
        repository.delete(cpf);
    }
}
